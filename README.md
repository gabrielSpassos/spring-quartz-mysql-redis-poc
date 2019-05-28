# Spring Quartz Mysql Redis Poc

This poc is about getting some status from a cache, and a scheduler changes this cache based at some relational database. 

Other point is that running more than one instance of the project only one instance should do the schedule job. 

## Usage

* docker-compose to start mysql and redis
* create tables (see [database.sql](https://github.com/gabrielSpassos/spring-quartz-mysql-redis-poc/blob/master/database.sql))
* run application
* go to [swagger-ui](http://localhost:8080/swagger-ui.html)

### Change schedule 

Unfortunately the way to change cron is update at database :disappointed:

```sql
UPDATE QRTZ_CRON_TRIGGERS
SET CRON_EXPRESSION = '0 0/5 * 1/1 * ? *'
WHERE TRIGGER_NAME = 'updateBankStatusCacheTrigger';
```

Created endpoint to do this. But quartz don't changes in real time :disappointed: . Only after the job is executed the new cron expression is used. 