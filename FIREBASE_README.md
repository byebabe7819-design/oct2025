# Firebase secrets for CI

Add the following repository secrets (GitHub → Settings → Secrets and variables → Actions):

- `FIREBASE_SERVICE_ACCOUNT`: contents of the Firebase service account JSON
- `OPENAI_API_KEY`: your OpenAI API key
- (optional) `FIREBASE_TOKEN`: short-lived token if you use `firebase-tools` auth

Functions environment:

Create `/functions/.env` on your machine (do NOT commit) with values like:

```
OPENAI_TRIPLE_MODELS=gpt-4o-mini,gpt-4.1-mini,o3-mini
```
