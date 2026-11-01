// Lightweight content/syntax validation for Hutan Ceria (run with Node.js).
const fs = require('fs');
const path = require('path');
const html = fs.readFileSync('app/src/main/assets/index.html', 'utf8');
const script = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].map(m => m[1]).join('\n');
if (!script) throw new Error('Game script not found');
new Function(script);
const start = script.indexOf('const quiz=');
const end = script.indexOf('\nconst worldNames=', start);
if (start < 0 || end < 0) throw new Error('Quiz data block not found');
const quizSource = script.slice(start + 'const quiz='.length, end).replace(/;\s*$/, '');
const quiz = Function('return (' + quizSource + ')')();
const keys = ['math', 'animals', 'colors', 'life'];
for (const key of keys) {
  if (!Array.isArray(quiz[key]) || quiz[key].length !== 5) throw new Error(`${key}: expected 5 questions`);
  quiz[key].forEach((q, i) => {
    if (!Array.isArray(q) || q.length !== 5) throw new Error(`${key}[${i}]: invalid question structure`);
    if (!Array.isArray(q[2]) || q[2].length !== 3) throw new Error(`${key}[${i}]: expected 3 choices`);
    if (!q[2].includes(q[3])) throw new Error(`${key}[${i}]: correct answer missing from choices`);
    if (!q[1] || !q[4]) throw new Error(`${key}[${i}]: missing question or hint`);
  });
}
// Verify every local sprite referenced by the game exists and is a PNG.
for (const sprite of ['sprites/fifi.png', 'sprites/bubu.png', 'sprites/bonbon.png']) {
  const file = path.join('app/src/main/assets', sprite);
  if (!fs.existsSync(file)) throw new Error(`Missing character sprite: ${sprite}`);
  const signature = fs.readFileSync(file).subarray(0, 8).toString('hex');
  if (signature !== '89504e470d0a1a0a') throw new Error(`Invalid PNG asset: ${sprite}`);
}
const gradle = fs.readFileSync('app/build.gradle.kts', 'utf8');
if (!/versionCode\s*=\s*8/.test(gradle) || !/versionName\s*=\s*"1\.8"/.test(gradle)) {
  throw new Error('Android version metadata does not match v1.8');
}
console.log('OK: JavaScript syntax, 4 worlds, 20 questions, answers, 3 PNG sprites, and Android v1.8 metadata validated.');
