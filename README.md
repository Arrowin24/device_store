# device_store
## Тестовое задание на должность Java backend разработчика  
### Описание
Необходимо было реализовать реестр техники с привязкой моделей с собственными характеристиками.
В реестр достаточно добавить по три позиции на каждый вид техники с двумя моделями для каждой.  
Сам исходный код находится в ветке *origin/realise*.
### Запуск приложения для тестирования с сервера
Я запустил приложение на виртуальном сервере, поэтому по данной ссылке ближайшую неделю этот проект будет доступен:  
http://193.168.46.68:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#  
Если по каким-то причинам он не доступен, то можно проделать шаги из инструкции.
### Инструкция по установки
1. Клонируем данный проект с github. Ссылка на скачивание https://github.com/Arrowin24/device_store
2. В IDE выбираем ветку проекта *origin/realise*.
3. Находим *properties* файл по пути **src/main/resources/application.properties** и заходим в него.
4. В первую строчку после = указываем ссылку на базу данных, в которой вы хотели бы протестировать приложение. 
Во вторую и третью строчку вставляем вставляем username и password от роли в БД.
5. Синхронизируем pom.xml чтобы подтянуть зависимости через maven.
6. Далее находим DeviceStoreApplication.java и запускаем его. Путь к файлу:  **src/main/java/ru/arrowin/test_task/DeviceStoreApplication.java**
7. В браузере переходим в swagger для тестирования приложения: http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#
8. Находим *PUT-метод* **"/start"** в блоке  **"Пополнение базы данных"**. Нажимаем *Try it out* и *execute*. Это пополнит базу данных начальным набором техники.
9. Чтобы проверить, что вся техника загрузилась в БД в этом же блоке выполним *GET-метод*  **"/start/getAll"**. В результате должен появиться список всей техники. 
10. Приложение готово к тестированию!
### Тестирование приложения
+ По условию задания по выделенным атрибутам необходимо реализовать поиск по наименованию,
вне зависимости от регистра, а также реализовать фильтрацию по виду техники, цвету, цене (от/до).  
  + Для этого находим *GET-метод* **"/api/device//findByParams"** в блоке  **"Работа со всеми видами техники"**. В нем выбираем необходимые параметры и производим фильтрацию. Также внизу данного блока можно выбрать вид сортировки.
+ Остальные фильтры сделать зависимыми от выбора вида техники и фильтровать по атрибутам моделей
  + Также в в блоке **"Работа со всеми видами техники"** есть *GET-метод* **"/api/device//findByAllParams"** в нем можно указать вид техники, если нужно и уже потом выбирать все параметры. Которые там предложены.
  + Еще внутри каждого блока конкретного вида техники есть *GET-метод* **".../getByAttributes"** можно произвести фильтрацию в базе данных по всем атрибутам, которые характерны конкретному виду техники.  
+  Реализовать сортировку реестра техники по алфавиту и по стоимости
   + В каждом методе фильтрации есть поле **Вид сортировки**, в котором можно указать вид сортировки для предоставления данных. 
+ Реализовать возможность добавлять новые позиции и модели к ним, в зависимости от выбранного вида техники.
  + В каждом блоке конкретного вида техники есть *POST-запросы* под названием **".../newDevice"** и **".../newModel"**. Первый запрос необходим, чтобы в базе данных создать новую линейку. Например в базе смартфонов создать линейку Apple. Второй запрос позволяет создавать новую модель внутри какой-то линейки. Например для линейки Apple создать iPhone 11. 
### Заключение  
В принципе на приложение ушло чуть меньше 2ух дней (около 14-16 часов работы). В нем есть некоторые моменты, которые можно доработать, однако, мне кажется, что все предъявленные в ТЗ требования выполнены. Очень хотелось бы получить обратную связь по проделанной работе.

