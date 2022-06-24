# Install Airflow using Python PIP
- Refer:
    - https://towardsdatascience.com/an-introduction-to-apache-airflow-21111bf98c1f

```
sudo apt install -y python3-venv
python3 -m ~/venv my_venv
```

```
source ~/my_venv/bin/activate
```

```
mkdir ~/airflow
cd ~/airflow
```

```
pip install "apache-airflow[celery]==2.3.2" --constraint "https://raw.githubusercontent.com/apache/airflow/constraints-2.3.2/constraints-3.7.txt"
```

```
airflow db init
```

```
airflow users create       --role Admin       --username admin       --email admin       --firstname admin       --lastname admin       --password admin
```

```
airflow users list
```

```
export AIRFLOW_HOME=/home/atingupta2005/airflow
```


```
cd ~/airflow
nohup airflow scheduler &
```

```
nohup airflow webserver &
```

```
curl localhost:8080
```
