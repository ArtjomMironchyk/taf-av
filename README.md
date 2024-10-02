Автоматизация тестирования веб-приложения av.by
Этот проект представляет собой фреймворк для автоматизированного тестирования веб-приложения av.by с использованием Java, Selenium . Цель проекта — обеспечить надежные, воспроизводимые и эффективные тесты для функционального и UI-тестирования сайта.

Стек технологий
Язык программирования: Java
Фреймворк для тестирования: TestNG
Инструмент для автоматизации: Selenium WebDriver
Сборка проекта: Maven
CI/CD: Jenkins 

Тестируемое приложение: av.by
Функциональные возможности
Автоматизация функционального тестирования страниц поиска автомобилей, фильтрации и авторизации.
UI-тесты для проверки отображения элементов на страницах.
Проверка фильтров по марке, модели, году выпуска, цене, состоянию и другим параметрам.
Тестирование процесса поиска и сортировки объявлений.
Интеграция с Allure для генерации подробных отчетов о результатах тестов.
Возможность запуска тестов в Jenkins или GitLab CI.
Структура проекта
src/main/java — основная логика тестов и вспомогательные классы.
src/test/java — тесты и их конфигурации.
pom.xml — файл с зависимостями Maven.
