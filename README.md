### Change schedule 

Unfortunately the way to change cron is update at database :( 

```sql
UPDATE QRTZ_CRON_TRIGGERS
SET CRON_EXPRESSION = '0 0/5 * 1/1 * ? *'
WHERE TRIGGER_NAME = 'updateBankStatusCacheTrigger';
```
:wip: Build endpoint to do this. 