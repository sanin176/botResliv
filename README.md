Чтобы запустить бота, необходимо:

1. Скачать и установить **MySQL**, далее подключиться в **Intellij** к данной БД.

2. Скачать программу https://ngrok.com/download и зарегистрироваться в ней(
проделать все 4 действия, что описаны там и открыть **ngrok** на порту 8080
)

3. После того как запустили программу **ngrok** нужно скопировать оттуда адрес с https,
к примеру {https://3b908c5ea8b2.ngrok.io}

4. Далее нужно в браузере прописать 
https://api.telegram.org/bot{**token** бота}/setWebhook?url={https **ngrok**}/botResliv

5. Теперь нужно открыть проект и прописать в файле **resources/application.properties** ссылку,
которая была скопирована после открытия **ngrok** (адрес с https, к примеру {https://3b908c5ea8b2.ngrok.io})
Также изменить там **логин** и **пароль** от MySQL

6. После всех действий можно заполнить базу данных с помощью программы **Postman**(https://www.postman.com),
все виды запросов описаны в **CityController**, при добавлении новой записи нужно в JSON
передавать только **{"city": "Москва", "description": "описание..."}** . Либо можно заполнить
всё в **Intellij**.

7. Переходим в чат-бот в телеграмме **@ReslivCitiesBot**