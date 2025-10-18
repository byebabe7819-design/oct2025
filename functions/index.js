const functions = require('firebase-functions');
const express = require('express');
const bodyParser = require('body-parser');

const region = 'us-central1';
const app = express();
app.use(bodyParser.json());

// Simple health ping
app.get(['/ping','/health','/'], (req, res) => {
  return res.json({ ok: true });
});

// main assistant route (very small shim)
app.post('/assistant', async (req, res) => {
  const models = process.env.OPENAI_TRIPLE_MODELS || '';
  // minimal deterministic echo for smoke tests (no network calls here)
  const messages = req.body && req.body.messages ? req.body.messages : [];
  return res.json({ ok: true, models: models.split(','), messagesCount: messages.length });
});

exports.api = functions.region(region)
  .runWith({ memory: '512MiB', timeoutSeconds: 60, minInstances: 0 })
  .https.onRequest((req, res) => {
    // Basic CORS for smoke tests
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Access-Control-Allow-Methods', 'GET,POST,OPTIONS');
    res.set('Access-Control-Allow-Headers', 'Content-Type,Authorization');
    if (req.method === 'OPTIONS') {
      return res.status(204).send('');
    }
    // route to express app
    app(req, res);
  });
