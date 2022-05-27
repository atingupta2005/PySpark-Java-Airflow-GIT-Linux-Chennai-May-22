# Retry and Alerting
- Refer - alert_dag.py
- ./start.sh
- Open Airflow UI
- Enable alert_dag
- Trigger dag manually
- Refresh page
- Notice how much time it took to execute - 45 sec approx
- Trigger dag again manually
- Refresh page
- Notice how much time it took to execute - 45 sec approx
- Change code - dag_timeout=timedelta(seconds=75) and save
- Trigger dag again
- Notice how much time it took to execute - 45 sec approx
- Change code - dagrun_timeout=timedelta(seconds=25) and save
- Trigger dag again
- Notice how much time it took to execute - its failed as timeout happens
- Note:
  - Even if this DAG run failed, the next DAG run will still execute
  - Also if we set one of the task with the parameter - depends_on_past, this task will still be able to run the next DAGRun as its previous instance succeeded even if the DAGRun is marked as failed
  - The parameter - dagrun_timeout is enforced if the number of active DAGRuns is equal to the number of max_active_runs_per_dag
  - If we check the value of this parameter - max_active_runs_per_dag in airflow.cfg, its set to 16. It will set to 1 as we are using sequential executer.
- In addition to timeout, we would like to do something if the DAGRun is failed or succeeded. We could add the callbacks on_failure_callback
- Refer to the functions - on_success_dag and on_failure_dag and also the callbacks in DAG object creation
- Now if DAG is success or failed, callback will be in effect accordingly
- We need to check scheduler logs
```
docker ps
docker exec -it <container-id> bash
cd
cd logs/scheduler
ls
cd latest
cat alert_dag_py.log | grep failure
cat alert_dag_py.log
```
- Open alert_dag.py and change dagrun_timeout from 25 to 75 seconds
- Now let's see how to handle if task fails. There are certain parameters to handle that
- Define the parameter in default_args
  - 'retries': 3
  - 'retry_delay': timedelta(seconds=60)
  - 'retry_exponential_backoff': False
  - 'max_retry_delay': None
- Change bash command of t1 to make it fail - "exit 1"
- Trigger the DAG from Airflow UI
- Notice that the task in the column - Recent Tasks. It has the retries done for each task. It shows 3. Finally the task will be marked as failed
- Let's add an email address to send an email if the task is not succeeded - 'emails': ['owners@test.com']
  - To really send an email, need to configure SMTP and provide real email ID
- Now run DAG again and notice email is sent or not
- In default_args, add parameters
  - on_failure_callback
  - on_success_callback
  - 'execution_timeout': timedelta(seconds=60)
- Trigger the dag and check the logs for the callback
