# Unit testing DAGs
- Refer: tst_dag.py
- Refer folder - test_dags
  - Conftest.py
    - Is a special file used by pytest in order to define the fixture functions and make them available across multiple test files
      - Fixture functions are functions which run before each test function to which it is applied
      - They are used to feed some data to the test such as database connections, URLs to test or any sort of input data
      - Instead of running the same code for every test, we can attach a fixture function to the tests and this function will run and return the data to the test before getting executed
      - We created a fixture function named dagbag which returns the DagBag object with the pytest decorator - fixture
      - By default, when a DagBag object is created, it automatically loads the DAGs from the folder dags. Means when I will use the DagBag object returned by the fixture function inside my tests, I will be able to access the Dags combined in my folder "dags"
      - Scope is set to session, meaning the function will be run once per session.
    - test_dag_validation.py
      - DAG Validation Test corresponds to the tests that will be uniformly applied to our DAGs
      - If we want to check the all DAGs contain the required alerting emails, this is where we will make that test.
      - Notice the test functions available in this file
    - Run the tests
    ```
    ./start.sh
    docker ps
    docker exec -it <container-id> bash
    cd
    cd test_dags
    ls
    pytest test_dag_validation.py -v
    ```
    - Notice the tests are skipped
    - Comment the skip decorator from the test
    - Run the test again
    ```
    pytest test_dag_validation.py -v
    ```
    - Notice the error for email id not specified in the DAG
    - Fix error by specifying email id in the DAG
    - Run the test again
    ```
    pytest test_dag_validation.py -v
    ```
    - Now error is in different DAG
    - Fix the errors and run DAG again
    ```
    pytest test_dag_validation.py -v
    ```
  - test_tst_dag_validation
    - Related to one specific DAG
  - Test
  ```
  pytest test_tst_dag_validation.py -v
  ```
