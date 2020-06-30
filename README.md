# Text processor

Projekt na przedmiot: Test automation craftsmanship.

Dostępne opcje:
- TO_LOWER_CASE,
- TO_UPPER_CASE,
- REMOVE_WHITE_SPACES,
- ROT13.

## Przykład
```
curl -H 'Content-Type: application/json' -d '{"text": "Test automation craftsmanship", "options": ["TO_UPPER_CASE", "REMOVE_WHITE_SPACES"]}' http://localhost:8080/processing

{"id":"1c0585dc-b36c-45fd-871a-b159ed65edb0"}
```
```
curl http://localhost:8080/processing/1c0585dc-b36c-45fd-871a-b159ed65edb0

{"id":"1c0585dc-b36c-45fd-871a-b159ed65edb0","options":["TO_UPPER_CASE","REMOVE_WHITE_SPACES"],"originalText":"Test automation craftsmanship","resultText":"TESTAUTOMATIONCRAFTSMANSHIP"}
```
