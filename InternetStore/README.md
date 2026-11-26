# Інтернет-магазин на Java

Навчальний консольний проєкт для практичної роботи №1 «Основи JAVA».

## Технології
- Java 21
- Maven
- Lombok 1.18.36

## Функціональність
- перегляд каталогу товарів;
- перегляд категорій;
- додавання товарів до кошика;
- перегляд і видалення товарів із кошика;
- оформлення замовлення;
- збереження історії замовлень у межах поточного запуску;
- пошук товарів за назвою або категорією.

## Запуск в IntelliJ IDEA
1. Відкрий папку `InternetStore` через **File → Open**.
2. Дочекайся імпорту Maven.
3. У **File → Project Structure → Project SDK** вибери JDK 21.
4. Переконайся, що Maven завантажив Lombok.
5. Запусти клас `Main`.

Якщо IDE не розпізнає згенеровані Lombok-методи, встанови/увімкни плагін Lombok у
**Settings → Plugins**, а також перевір annotation processing у
**Settings → Build, Execution, Deployment → Compiler → Annotation Processors**.

## Запуск з термінала
```bash
mvn clean compile
mvn exec:java
```

## GitHub
```bash
git init
git add .
git commit -m "Практична робота 1: інтернет-магазин на Java"
git branch -M main
git remote add origin https://github.com/USERNAME/REPOSITORY.git
git push -u origin main
```
Заміни `USERNAME/REPOSITORY` на адресу власного репозиторію.
