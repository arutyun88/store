# СКЛАД

# Stack
![](https://img.shields.io/badge/Java-✓-blue.svg)
![](https://img.shields.io/badge/PostgreSQL-✓-blue.svg)

<br>

<h2>Серверная часть приложения по учёту товаров на складе.</h2>

**API** включает в себя возможность просмотра, создания, 
редактирования и удаления сущностей.

<h1>Endpoints:</h1>
<h1>STORE</h1>
<h2>/store/api/store/add</h2> 

* Добавление сущности "Склад" в БД 
* Пример запроса: http://localhost:8080/store/api/store/add
* Тип запроса **PUT**
* Пример тела запроса:
```json
{
    "storeName" : "Москва"
}
```
**Варианты ответов сервера:**

* Статус ответа: 
  * 200
  * Запись добавлена. Тело ответа отсутствует.
    

* Статус ответа:
    * 405
    * Тело ответа
```json
{
  "code": 405,
  "message": "Запись уже существует"
}
```

<h2>/store/api/store/all</h2>

* Получение всех сущностей "Склад" из БД
* Пример запроса: http://localhost:8080/store/api/store/all
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
    * 200
    * Тело ответа
```json
[
  {
    "id": 122,
    "storeName": "Москва"
  },
  {
    "id": 123,
    "storeName": "Воронеж"
  }
]
```

<h2>/store/api/store/get/id/{id}</h2>

* Получение сущности "Склад" из БД **по id**
* Пример запроса: http://localhost:8080/store/api/store/get/id/122
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
    * 200
    * Тело ответа
```json
{
  "id": 122,
  "storeName": "Москва"
}
```
* Статус ответа:
    * 404
    * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

<h2>/store/api/store/get/name/{name}</h2>

* Получение сущности "Склад" из БД **по имени**
* Пример запроса: http://localhost:8080/store/api/store/get/name/Москва
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
    * 200
    * Тело ответа
```json
{
  "id": 122,
  "storeName": "Москва"
}
```
* Статус ответа:
    * 404
    * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

<h2>/store/api/store/update</h2>

* Обновление сущности "Склад" в БД, или её создание, в случае отсутствия
* Пример запроса: http://localhost:8080/store/api/store/update
* Тип запроса **POST**
* Пример тела запроса:
```json
{
  "id": 122,
  "storeName":"Новосибирск"
}
```

**Варианты ответов сервера:**

* Статус ответа:
    * 200
    * В случае, если записи не существовало ранее, она будет создана, иначе, 
      запись будет обновлена. 
    * Тело ответа отсутствует.


* Статус ответа:
    * 409
    * Склад уже зарегистрирован под другим идентификатором
    * Тело ответа
```json
{
  "code": 409,
  "message": "Дублирование записи"
}
```

<h2>/store/api/store/delete/{id}</h2>

* Удаление сущности "Склад" из БД **по id**
* Пример запроса: http://localhost:8080/store/api/store/delete/123
* Тип запроса **DELETE**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
    * 200
    * Тело ответа отсутствует.


* Статус ответа:
    * 404
    * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

<h1>PRODUCT</h1>
<h2>/store/api/product/add</h2> 

* Добавление сущности "Товар" в БД
* Пример запроса: http://localhost:8080/store/api/product/add
* Тип запроса **PUT**
* Пример тела запроса:
```json
{
  "article":"12903478",
  "productName":"Молоко",
  "lastPurchasePrice":30.45,
  "lastSalePrice":33.70
}
```
**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * В случае, если записи не существовало ранее, она будет создана, иначе,
    запись будет обновлена. 
  * Тело ответа отсутствует.


* Статус ответа:
  * 406
  * Артикул, или наименование товара, не соответствуют существующей в БД записи.
  * Тело ответа
```json
{
  "code": 406,
  "message": "Несоответствие данных"
}
```

<h2>/store/api/product/all</h2>

* Получение всех сущностей "Товар" из БД
* Пример запроса: http://localhost:8080/store/api/product/all
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Тело ответа
```json
[
  {
    "article": "12903478",
    "id": 125,
    "lastPurchasePrice": 30.45,
    "lastSalePrice": 33.7,
    "productName": "Молоко"
  },
  {
    "article": "12903479",
    "id": 126,
    "lastPurchasePrice": 10.2,
    "lastSalePrice": 11.3,
    "productName": "Хлеб"
  }
]
```

<h2>/store/api/product/get/id/{id}</h2>

* Получение сущности "Товар" из БД **по id**
* Пример запроса: http://localhost:8080/store/api/product/get/id/125
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Тело ответа
```json
{
  "article": "12903478",
  "id": 125,
  "lastPurchasePrice": 30.45,
  "lastSalePrice": 33.7,
  "productName": "Молоко"
}
```
* Статус ответа:
  * 404
  * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

<h2>/store/api/product/get/name/{productName}</h2>

* Получение сущности "Товар" из БД **по наименованию товара**
* Пример запроса: http://localhost:8080/store/api/product/get/name/Молоко
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Тело ответа
```json
{
  "article": "12903478",
  "id": 125,
  "lastPurchasePrice": 30.45,
  "lastSalePrice": 33.7,
  "productName": "Молоко"
}
```
* Статус ответа:
  * 404
  * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

<h2>/store/api/product/update</h2>

* Обновление сущности "Товар" в БД, или её создание, в случае отсутствия
* Пример запроса: http://localhost:8080/store/api/product/update
* Тип запроса **POST**
* Пример тела запроса:
```json
{
  "article": "12903478",
  "id": 125,
  "lastPurchasePrice": 31.25,
  "lastSalePrice": 34.8,
  "productName": "Молоко"
}
```

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * В случае, если записи не существовало ранее, она будет создана, иначе,
    запись изменена.
  * Тело ответа отсутствует.


* Статус ответа:
  * 405
  * Товар уже зарегистрирован под другим идентификатором
  * Тело ответа
```json
{
  "code": 405,
  "message": "Дублирование записи"
}
```

<h2>/store/api/product/delete/{id}</h2>

* Удаление сущности "Товар" из БД **по id**
* Пример запроса: http://localhost:8080/store/api/product/delete/125
* Тип запроса **DELETE**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Тело ответа отсутствует.


* Статус ответа:
  * 404
  * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

<h1>RECEIPT</h1>
<h2>/store/api/receipt/add</h2> 

* Добавление документа "Поступление" в БД
* Пример запроса: http://localhost:8080/store/api/receipt/add
* Тип запроса **PUT**
* Пример тела запроса:
```json
{
  "number":"1234567913",
  "store": {
    "id": 124
  },
  "products":[
    {
      "product":{
        "id": 126,
        "lastPurchasePrice": 4
      },
      "count": 2
    }
  ]
}
```
**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Цена последней закупки будет установлена в соответствии с указанной.
  * Тело ответа отсутствует.


* Статус ответа:
  * 404
  * Склад или Товар отсутствуют в БД
  * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

* Статус ответа:
  * 405
  * Документ уже зарегистрирована под другим идентификатором
  * Тело ответа
```json
{
  "code": 405,
  "message": "Дублирование записи"
}
```

<h2>/store/api/receipt/all</h2>

* Получение всех документов "Поступление" из БД
* Пример запроса: http://localhost:8080/store/api/receipt/all
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Тело ответа
```json
[
  {
    "id": 166,
    "number": "1234567913",
    "products": [
      {
        "count": 2,
        "id": 126,
        "name": "Хлеб",
        "price": 4.0
      }
    ],
    "store": "Москва"
  },
  {
    "id": 168,
    "number": "1234567914",
    "products": [
      {
        "count": 3,
        "id": 126,
        "name": "Хлеб",
        "price": 9.0
      }
    ],
    "store": "Москва"
  }
]
```

<h2>/store/api/receipt/get/id/{id}</h2>

* Получение документа "Поступление" из БД **по идентификатору документа**
* Пример запроса: http://localhost:8080/store/api/receipt/get/id/175
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Тело ответа
```json
{
  "id": 175,
  "number": "1234567915",
  "products": [
    {
      "count": 3,
      "id": 171,
      "name": "Молоко",
      "price": 2.0
    },
    {
      "count": 3,
      "id": 172,
      "name": "Кофе",
      "price": 5.0
    }
  ],
  "store": "Москва"
}
```
* Статус ответа:
  * 404
  * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

<h2>/store/api/receipt/get/number/{receiptNumber}</h2>

* Получение документа "Поступление" из БД **по номеру документа**
* Пример запроса: http://localhost:8080/store/api/receipt/get/number/1234567915
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Тело ответа
```json
{
  "id": 175,
  "number": "1234567915",
  "products": [
    {
      "count": 3,
      "id": 171,
      "name": "Молоко",
      "price": 2.0
    },
    {
      "count": 3,
      "id": 172,
      "name": "Кофе",
      "price": 5.0
    }
  ],
  "store": "Москва"
}
```
* Статус ответа:
  * 404
  * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

<h1>SALE</h1>
<h2>/store/api/sale/add</h2> 

* Добавление документа "Продажа" в БД
* Пример запроса: http://localhost:8080/store/api/sale/add
* Тип запроса **PUT**
* Пример тела запроса:
```json
{
  "number":"1234567915",
  "store": {
    "id": 124
  },
  "products":[
    {
      "product":{
        "id": 126,
        "lastSalePrice": 7
      },
      "count": 2
    }
  ]
}
```
**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Цена последней продажи будет установлена в соответствии с указанной.
  * Тело ответа отсутствует.


* Статус ответа:
  * 404
  * Склад или Товар отсутствуют в БД
  * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

* Статус ответа:
  * 405
  * Документ уже зарегистрирована под другим идентификатором
  * Тело ответа
```json
{
  "code": 405,
  "message": "Дублирование записи"
}
```

<h2>/store/api/sale/all</h2>

* Получение всех документов "Продажа" из БД
* Пример запроса: http://localhost:8080/store/api/sale/all
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Тело ответа
```json
[
  {
    "id": 162,
    "number": "1234567918",
    "products": [
      {
        "count": 2,
        "id": 126,
        "name": "Хлеб",
        "price": 13.0
      }
    ],
    "store": "Новосибирск"
  },
  {
    "id": 164,
    "number": "1234567919",
    "products": [
      {
        "count": 3,
        "id": 126,
        "name": "Хлеб",
        "price": 12.0
      }
    ],
    "store": "Новосибирск"
  }
]
```

<h2>/store/api/sale/get/id/{id}</h2>

* Получение документа "Продажа" из БД **по идентификатору документа**
* Пример запроса: http://localhost:8080/store/api/sale/get/id/170
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Тело ответа
```json
{
  "id": 170,
  "number": "1234567920",
  "products": [
    {
      "count": 3,
      "id": 126,
      "name": "Хлеб",
      "price": 12.0
    }
  ],
  "store": "Новосибирск"
}
```
* Статус ответа:
  * 404
  * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```

<h2>/store/api/sale/get/number/{saleNumber}</h2>

* Получение документа "Продажа" из БД **по номеру документа**
* Пример запроса: http://localhost:8080/store/api/sale/get/number/1234567920
* Тип запроса **GET**
* Тело запроса отсутствует

**Варианты ответов сервера:**

* Статус ответа:
  * 200
  * Тело ответа
```json
{
  "id": 170,
  "number": "1234567920",
  "products": [
    {
      "count": 3,
      "id": 126,
      "name": "Хлеб",
      "price": 12.0
    }
  ],
  "store": "Новосибирск"
}
```
* Статус ответа:
  * 404
  * Тело ответа
```json
{
  "code": 404,
  "message": "Запись не найдена"
}
```