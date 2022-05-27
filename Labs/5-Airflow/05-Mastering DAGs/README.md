# Mastering DAGs

# The most important parameters

## Downstream and upstream
![](img/downstream-upstream.png)


* start_date
  * The date from which tasks of your DAG can be scheduled and triggered.
* schedule_interval
  * The interval of time from the min(start_date) at which your DAG should be triggered.
* The DAG \[X\] starts being scheduled from the start_date and will be triggered after every schedule_interval

# What is the execution_date

![](img/9-Mastering%20DAGs1.png)

# Execution flow

![](img/9-Mastering%20DAGs2.png)

# How to define the scheduling_interval

* Either with
  * Cron expressions (ex: 0 \* \* \* \*)
  * Timedelta objects (ex: datetime .timedelta(days=l ))
* As a best practice you should use cron expressions rather than timedelta objects as specified in the documentation.

# What about end_date?

The date at which your DAG/Task should stop being scheduled

Set to None by default

Same recommendations than start_date

# Backfill and Catchup

Kick off the missing DAG runs

# DagRun

The scheduler creates a DagRunobject

Describes an instance of a given DAG in time

Contains tasks to execute

Atomic, Idempotent

![](img/9-Mastering%20DAGs3.png)

# Catchup process

Notice the gap between the execution date and start date because of Catchup - Greater than schedule_interval

![](img/9-Mastering%20DAGs4.png)

# How to turn on/ off Catchup?

* Either by
  * Setting the parameter catchup in the DAG definition to True or False
  * Changing the parameter catchup_by_default in airflow.cfg
* By default, catchup_by_default=True

# Dealing with timezones in Airflow

Make your DAGs timezone dependent

# Definition

* A timezone is a region of the globe that observes a uniform standard time
* Most of the timezones on land are offset from Coordinated Universal Time (UTC)
* Example:
  * Paris: UTC+2:OO
  * Newyork: UTC-4:OO

# Timezone in Python: Naive vs Aware

* Python datetime.datetimeobjects with the tzinfo attribute set
  * Datetime aware
* Python datetime.datetimeobjects without the tzinfo attribute set
  * Datetime naive
* Why it matters?
  * Interpretation of naive datetime objects: BAD

# Example

![](img/9-Mastering%20DAGs5.png)

# Best Practices!

* ALWAYS USE AWARE DATETIME OBJECTS
  * datetime.datetime() in python gives naive datetime objects by default
  * A datetime without a timezone is not in UTC
  * Import airflow.timezone to create your aware datetime objects
  * Or let Airflow does the conversion for you

# Timezones in Airflow

* Airflow supports timezones
* Datetime information stored in UTC
* User interface always shows in datetime in UTC
* Up to the developer to deal with the datetime
* The timezone is set in airflow.cfg to UTC by default
  * default_timezone=utc
* Airflow uses the pendulum python library to deal with time zones

# How to make your DAG timezone aware?

* Supply a timezone aware start_date using Pendulum:
  * <span style="color:#0070C0">import pendulum</span>
  * <span style="color:#0070C0">local_tz</span>  <span style="color:#0070C0">=</span>  <span style="color:#0070C0">pendulum.timezone</span>  <span style="color:#0070C0">(“Europe/Amsterdam”)</span>
  * <span style="color:#0070C0">default_args</span>  <span style="color:#0070C0">= \{ ‘</span>  <span style="color:#0070C0">start_date</span>  <span style="color:#0070C0">’: datetime(2019, 1, 1,</span>  <span style="color:#0070C0">tzinfo</span>  <span style="color:#0070C0">=</span>  <span style="color:#0070C0">local_tz</span>  <span style="color:#0070C0">), owner=’Airflow’ \}</span>
  * <span style="color:#0070C0">with DAG(‘</span>  <span style="color:#0070C0">my_dag</span>  <span style="color:#0070C0">',</span>  <span style="color:#0070C0">default_args</span>  <span style="color:#0070C0">=</span>  <span style="color:#0070C0">default_args</span>  <span style="color:#0070C0">):</span>
    * <span style="color:#0070C0">…..</span>

# Cron schedules

* Airflow assumes you will always want to run at the exact same time.
* It will ignore the DST (Daylight Saving Time).
* For example:
  * schedule_interval=0 5 \* \* \*
* will always trigger your DAG at 5 PM GMT \+ 1 every day regardless if DST is in place.

# Timedeltas

* When theschedule_intervalis set with a time delta, Airflow assumes you always will want to run with the specified interval.
* Example:
  * timedelta(hours=2)
* will always trigger your DAG 2 hours later.

# Cron vs Timedelta with Catchup

Important: Forcronexpressions, when DST happens 2 AM => 3 AM, the scheduler thinks theDAGRunof the 31 of March has been skipped and since Catchup=False, it won't get executed. The nextDAGRunwill be thelstof April at 2 AM.

![](img/9-Mastering%20DAGs6.png)

# How to make your tasks dependent

depends_on_pastandwait_for_downstream

# depends_on_past

Defined at task level

If previous task instance failed, the current task is not executed

Consequently, the current task has no status

First task instance withstart_dateallowed to run

![](img/9-Mastering%20DAGs7.png)

# wait_for_downstream

Defined at task level

An instance of task X will wait for tasks downstream of the previous instance of task X to finish successfully before it runs.

Runs concurrently

![](img/9-Mastering%20DAGs8.png)

Doesn't start because downstream tasks are not finished in DAGRUN 2

waiLfor_downstream=true

![](img/9-Mastering%20DAGs9.png)

# How to structure your DAG folder

Managing your files and DAGs

# DAG Folder

Folder where your Airflowdagsare

Defined with the parameterdags_folder

The path must be absolute

By default: $AIRFLOW_HOME/dags

Problem: too many DAGs, DAGs using many external files, …

How can we structure the DAG folder?

# First way: Zip

Create a zip file packing your DAGs and its unpacked extra files

The DAGs must be in the root of the zip file

Airflow will scan and load the zip file for DAGs

If module dependencies needed, use virtualenv and pip

![](img/9-Mastering%20DAGs10.png)

# Second way: DagBag

* ADagBagis a collection of DAGs, parsed out of a folder tree and has a high-level configuration settings.
* Make easier to run distinct environments (dev/staging/prod)
* One system can run multiple independent settings set
* Allow to add new DAG folders by creating a script in the default DAGs folder
* Warning: If a DAG is loaded with a new DagBag and is broken
  * Errors are not displayed from the UI
  * Can’t use the command "airflow list dags" (only work)
  * Still able to see the errors from the web server logs

# .airflowignore

Specifies the directories or files in the DAGs folder that Airflow should ignore

Equivalent to the .gitignorefile

Each line corresponds to a regular expression pattern

The scope of a .airflowignore is the current directory as well as its subfolders

Airflow looks for “DAG" or “airflow” in files. You can avoid wasting scans by using the .airflowignorefile

As best practice, always put a .airflowignorefile in your DAGs folder

# How to deal with failures in your DAGs

# Dag failure detections

* Detection on DAGs
  * DAG level
    * dagrun_timeout
    * sla_miss_callback
    * on_failure_callback
    * on_success_callback

# max_active_runs

Ifmax_active_runsisn’t set, Airflow usemax_active_runs_per_daginairflow.cfg

![](img/9-Mastering%20DAGs11.png)

# Dag failure detections

* Detection on DAGs
  * DAG level
    * dagrun_timeout
    * sla_miss_callback
    * on_failure_callback
    * on_success_callback

# Task failures detection

* Detection on Tasks
* Task level
  * email
  * email_on_failure
  * email_on_retry
  * retries
  * retry_delay
  * retry_exponential_backoff
  * max_retry_delay
  * execution_timeout
  * on_failure_callback
  * on_success_callback
  * on_retrv_callback

# How to test your DAGs

Unit testing

# Unit testing with Pytest

* Pytest
  * Allow for compact test suites
  * Easier to understand (minimal boilerplate)
  * Pretty and useful failure information
  * Widely used
  * Nice documentation
  * Easy to use
  * Extensible
* Check their website:[https://docs.pytest.org/en/latest](https://docs.pytest.org/en/latest)

# How to test a DAG?

* Five categories:
  * DAG Validation Tests
  * DAG/Pipeline Definition Tests
  * Unit Tests
  * Integration Tests
  * End to End Pipeline Tests

# DAG Validation Tests

* Common tests for all DAGs in Airflow
  * Check if valid
  * Check if there is no cycles
  * Check default arguments
  * High level of testing of DAGs

# DAG/ Pipeline Definition Tests

* Test if modifications are intentional
  * Check total number of tasks
  * Check the nature of tasks
  * Check the upstream and downstream dependencies of tasks

# Unit Tests

* Unit testing external functions or custom Operators
  * Check the logic

# Integration Tests

* Tests if tasks work well with each others using a subset of production data
  * Check if tasks can exchange data
  * Check the input of tasks
  * Check dependencies between multiple tasks
* Need development/test/acceptance/production environments

# End to End Pipeline Tests

* Test the data pipeline
  * Check if the output is correct
  * Check the full logic
  * Check performances
* Need development/test/acceptance/production environments

# Create different environments

* Development
  * Faked small data input
  * Verify:
    * DAG Validation Tests
    * DAG/Pipeline Test
    * Unit Tests
* Test
  * Larger amount of real data
  * Verify:
    * Integration Tests

* Acceptance
  * Copy production datasets
  * Verify:
    * End to End Pipeline Tests
* Production
  * All production data
  * Used by the end users

# Git Repository and Branches

![](img/9-Mastering%20DAGs12.png)

# Command Line Interfaces

* Some useful commands:
* airflow run
  * Run a single task instance
* airflow list_dags
  * List all the DAGs
* airflow dag_state
  * Get the status of a DAG run
* airflow task_state
  * Get the status of a Task instance
* airflow test
  * Test a task instance without checking for dependencies or recording its state in the db
