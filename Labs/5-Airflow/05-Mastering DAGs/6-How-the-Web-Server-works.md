# How the Web Server works
- There is a parameter - worker_refresh_interval to set the number seconds to wait before refreshing a batch of workers
- Worker is the process forked by master. Worker is responsible for execution of tasks
- Review the logs and notice logs for the worker every 30 seconds:
```
docker ps
docker logs -f <container-id>
```
- Formula for # of workers: 2*cores+1
- More workers means more tasks can be executed in parallel
- Workers are refreshed due to parameter - timeout
- Notice parameter - logging_level
