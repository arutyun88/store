# СКЛАД

# Stack

![](https://img.shields.io/badge/Java-✓-blue.svg)
![](https://img.shields.io/badge/PostgreSQL-✓-blue.svg)

<br>

<h2>Серверная часть приложения по учёту товаров на складе.</h2>

**API** включает в себя возможность просмотра, создания, 
редактирования и удаления сущностей.

<h1>Endpoints:</h1>
<h3>/store/api/store/add</h3> 

- добавление сущности "Склад" в БД 
- Пример запроса: http://localhost:8080/store/api/store/add
- Пример тела запроса:
```json
{
    "storeName" : "Москва"
}
```
