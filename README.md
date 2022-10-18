## Release 1.3.0
ToDoList
- User can register
- User can create, update, delete tasks
- User can view pdf
- Admin can create, update, delete tasks for other users
- Admin can delete users
- If the user has tasks for today, a reminder is sent by email 
  (you need to set the time for checking tasks for today using cron)
- Logging sending email with aop to log.txt
  after deployment and the first write, the file is located on the path "WEB_INF/classes/log.txt"

## Deployment
Deployment process as easy as possible:
Required software:
- terminal for running bash scripts
- docker
- docker-compose

to deploy application, switch to needed branch and run bash script:

$ bash start.sh ${DB_USERNAME} ${DB_PASSWORD} ${DB_ROOT_PASSWORD} ${SMTP_USERNAME} ${SMTP_PASSWORD}

or for test run (test values are using, need to smtp server and port in application.properties
. username and password in application.properties or docker-compose-test.yml)

WARNING:
    - timezone in docker container may differ from windows timezone
    this is important to start the cron service
    - email reminder tested with google
    need to get password for application in the account security settings


$ bash test.sh


Для запуска нужно:
- терминал для запуска bush скриптов
- docker
- docker-compose

Чтобы развернуть приложение нужно запустить bash скрипт с параметрами

$ bash start.sh ${DB_USERNAME} ${DB_PASSWORD} ${DB_ROOT_PASSWORD} ${SMTP_USERNAME} ${SMTP_PASSWORD}

или запустить тестовую сборку без параметров (будут использованы тестовые значения в docker-compose-test.yml,
нужно указать smtp сервер, порт в application.properties.  username, password можно указать там же или в docker-compose-test.yml)

$ bash test.sh

Предупреждение:
    - временная зона в докер контейнере может отличаться от временной зоны windows
    это важно для запуска задачи с помощью cron
    - напоминание по email тестировалось через google
    нужно получить пароль для приложения в настройках безопасности аккаунта