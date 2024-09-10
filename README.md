# Описание процедуры запуска авто-тестов.
1.	Склонировать репозиторий с Github по ссылке: https://github.com/Klimenko-E/qamid-diplom
2.	Запустить Android Studio.
3.	Запустить в Android Studio эмулятор с API29.
4.	Открыть в Android Studio проект fmh_android_15_03_24.
5.	Запустить в Android Studio на эмуляторе приложение «APP».
6.	Добавить необходимы зависимости.
7.	Запустить автотесты через терминал командой: ./gradlew connectedAndroidTest
8.	Результаты авто-теста хранятся на запущенном эмуляторе в папке: /data/data/ru.iteco.fmhandroid/files/allure-results 
