## MVP
ToDoList
- User can register
- User can create, update, delete tasks
- Admin can create, update, delete tasks for other users
- Admin can delete users
## Deployment
Deployment process as easy as possible:
Required software:
- terminal for running bash scripts
- docker
- docker-compose

to deploy application, switch to needed branch and run bash script:

$ bash start.sh ${DB_USERNAME} ${DB_PASSWORD} ${DB_ROOT_PASSWORD}

or for test run (test values are using)

$ bash test.sh


Для запуска нужно:
- терминал для запуска bush скриптов
- docker
- docker-compose

Чтобы развернуть приложение нужно запустить bash скрипт с параметрами

$ bash start.sh ${DB_USERNAME} ${DB_PASSWORD} ${DB_ROOT_PASSWORD}

или запустить тестовую сборку без параметров (будут использованы тестовые значения в docker-compose-test.yml)

$ bash test.sh
