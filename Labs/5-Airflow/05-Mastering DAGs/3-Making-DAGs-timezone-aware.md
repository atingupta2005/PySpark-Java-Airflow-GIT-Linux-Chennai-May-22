# Making DAGs timezone aware
- Manipulate dates and time zone of dags and make then timezone aware

- Refer - tz_dag.py
  - Notice state_date and schedule_interval
  - schedule_interval works based on the timezone which is specified in airflow.cfg. So it would be UTC in case it's UTC in airflow.cfg
  - Notice the print statements which print the date/time to understand when DAG tasks ran
  - Change the date/time of computer
  - Start - start.sh
  - Open Airflow UI
  - Turn on tz_dag
  - Refresh page after 1 min and notice the dates of execution
  - Check logs on terminal
  ```
  docker ps
  docker logs -f <container-id>
  ```
  - Notice the output of DAG run
    - First Line: If it's Naive (Not aware about timezone)
    - Second: If it's Timezone aware?
    - Third: Shows
      - timezone of the DAG which is UTC
      - start date
      - schedule_interval
      - last execution date: The date when data was received. Our DAG is executed using this data next at next execution date
      - next execution date in UTC:
      - next execution date in local
    - If we want to execute our next DAG run then change date to 31-03-2019 after 1 AM UTC
    - Before doing the date, let's understand about Daylight Saving time (DST)
      - This time - 31-03-2019 after 1 AM UTC corresponds to when the Daylight Saving Time happens in Europe.
        - Means that at 2 AM local time (+1 UTC) - 31-03-2019, we will still be the same day with one more hour. At 2 AM it will be 3 AM local time. The time zone will shift from UTC+1 to UTC+2
  - Now change the date/time to 31-03-2019 1:59 AM (UTC+1) - this is just before DST will happen
  - Notice the time at Airflow UI. It's 31-03-2019 00:59 AM - one hour back than the local time
  - Now wait and refresh the Airflow UI page, nothing should be executed as expected
  - Wait for 1 minute so that DST can happen. Now notice the time in Airflow UI. It's changed to 1:00 AM in Airflow UI
  - Also notice the local time is changed to 3 AM - 2 hours difference (UTC+2) instead of 1 hour difference
  - Also notice that DAG is triggered
  - Now let's understand the problem
    - Change the date to 01-04-2019 at 2 AM (UTC+1) local time. This is the time when we expect our DAG to be triggered.
    - Refresh Airflow UI page after 1 min. DAG will still not be executed
    - Why? Because our DAG is configured in UTC in tf_dag.py. Notice value schedule_interval which specified that DAG should be run every day at 1 AM in UTC. As we are now in UTC+2 due to DST, the DAG now would not be triggered at 2 AM local time but at 3 AM.
  - This can be a problem as we don't want to see our DAG being run 1 hour later just because of time zone has changed
  - If we want our DAGs to be run at 2 AM, it should still be the case even after the DST
  - How to fix?
    - You need to specify the time zone in which your DAG works
    - To do this we can use Python library - Pendulum, to get the timezone that we want
      - We specify the time zone in the start_date of the DAG - tzinfo=local_tz
      - We also change the start_date from 1 AM to 2 AM.
      - Since we are now setting our DAG in local time, we change the schedule_interval to indicate that DAG must be triggered everyday at 2 AM local time.
    - Save the file and move back to the terminal and run - stop.sh
    - Change the date back to 30-03-2019 2:15 AM
    - Start docker container - start.sh
    - Open Airflow UI and enable the dag - tz_dag
    - wait for 1 min and refresh the page
    - Notice that DAG is executed as expected
  - Some more interested parts:
    - Change the date to 31-03-2019 1:59 AM (UTC+1)
    - Refresh the page without waiting and notice that nothing happens
    - Wait for 1 min and refresh Airflow UI page. Notice that local time is changed from 2 AM to 3 AM as now DST is in place
    - Now DAG will run after local time is changed to 3 AM.
    - If the catchup parameter is set to false, the DAG would not have triggered as we are 1 hour late for the Scheduler even if it's not true - DST effect
    - Now change date again to 01-04-2019 at 2:15 AM (UTC+1) local time
    - Wait for 1 min and refresh the page and notice that DAG is run at the right time at 2 AM local time and it's not longer shifted to 3 AM as before.
    - Now look at the terminal logs
    ```
    docker ps
    docker logs -f <container-id>
    ```
    - Notice that the DAG is now configured in the local timezone which is Amsterdam in Europe. Paris also has the same time zone
    - If we have used timedelta rather than a cron expression, the DAG would have been executed at 3 AM because timedelta keeps the same schedule interval whatever the timezone and so takes DST into account
    - Stop docker container - stop.sh
    - If we want to configure DAGs in local timezone by default, we can set this in airflow.cfg
      - Open airflow.cfg and change the parameter default_timezone
      - We can change the value to set our DAGs to use local timezone
      - Remember
        - Airflow converts everything in UTC even when a naive datetime object is used
        - We should always indicate the timezone of our dates even if date is in UTC
