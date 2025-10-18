# ✅ Copilot Execution Plan — Finish Verumdone Setup

Goal: link this repo to the Firebase project **verumdone**, deploy hosting + functions, and wire CI to auto-deploy on every push to `main`.

---

## 1. Firebase Project Binding

Ensure repo root `.firebaserc` is:

```json
{
  "projects": {
    "default": "verumdone"
  }
}
```

> always lowercase: `verumdone` (must match Firebase Project ID exactly).

---

## 2. Firebase Console Account

* target project: **Verumdone**
* owned under: `liamhai7a8@gmail.com`
* all deployments must authenticate to this account.

---

## 3. Secrets

* store OpenAI key in Firebase functions:

```
firebase functions:secrets:set OPENAI_API_KEY
```

* optional override for triple-model list:

```
firebase functions:config:set openai.triple_models="gpt-4o-mini,gpt-4.1-mini,o3-mini"
```

In GitHub repo settings → Actions → Secrets, add:

* `FIREBASE_SERVICE_ACCOUNT` → JSON creds from Firebase Console service account
* `OPENAI_API_KEY` → your OpenAI API key
* (optional) `FIREBASE_TOKEN` → CLI token if you prefer tokens over service accounts

---

## 4. Deploy Targets

* **Hosting**: serve `/public` → `https://verumdone.web.app`
* **Functions**: deploy `functions/index.js` as HTTPS function `api`, mounted under `/api/**`

Unified deploy:

```
firebase deploy --only hosting,functions
```

---

## 5. GitHub Actions Workflow

Create `.github/workflows/deploy.yml` as follows (example):

```yaml
name: Firebase Deploy

on:
  push:
    branches: [ main ]

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - uses: actions/setup-node@v4
        with:
          node-version: 20

      - run: npm install -g firebase-tools

      - name: Authenticate with Firebase
        uses: google-github-actions/auth@v2
        with:
          credentials_json: ${{ secrets.FIREBASE_SERVICE_ACCOUNT }}

      - name: Deploy Hosting + Functions
        run: firebase deploy --only hosting,functions --non-interactive --force
        env:
          OPENAI_API_KEY: ${{ secrets.OPENAI_API_KEY }}
```

Notes:
- Depending on org policies you may need to restrict service account permissions (Hosting Admin + Cloud Functions Deployer).

---

## 6. Validation Checklist

* visit `https://verumdone.web.app/` → ensure `public/index.html` is served.
* GET `https://verumdone.web.app/api/assistant` → returns `{ ok:true, service:"verum-omnis", models:[...] }`.
* POST to `/api/assistant` with `{"messages":[{"role":"user","content":"HELLO"}]}` → JSON reply.

---

If you want, I can:

- Add or update `.firebaserc` and `.github/workflows/deploy.yml` in this repo (requires secrets to be configured in GitHub).
- Create a small `scripts/ci-validate.sh` that performs the validation checklist after deploy using curl and exits non-zero on failure.
