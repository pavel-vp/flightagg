# flightagg
Simple Springboot application with RestAPI for flight providers. This application aggregates data from these sources:
- https://obscure-caverns-79008.herokuapp.com/cheap
- https://obscure-caverns-79008.herokuapp.com/business

And adding some additional functions (filtering, pagination and order).

After starting application this can be used on 
  http://localhost:8080/flights
context path.

Resulting flight record example:
[{"id":"2231887273634720768",
  "departure":"El Palomar",
  "arrival":"Chacarita",
  "departureTime":1554578966044,
  "arrivalTime":1554579032743}]

RestAPI description:
1. Get all flights
- http://localhost:8080/flights/all

2. Query with parameters
- http://localhost:8080/flights/q?sorted=arrivalTime;asc&f_departure=like;Berlin&limit=10&offset=5

2.1. Ordering data. 
 [sorted=arrivalTime;asc]
 Format - [field_name;asc|desc]
 
2.2. Filtering data.
 [f_departure=like;Berlin]
 Format - [f_field_name=like;|gte;|lte;value]
where "like;" - like operator
      "gte;" - grater or equas then
      "lte;" - lower or equals then.
Filter is case sensitive.

2.3. Pagination data.
  [offset=5]
  Format - [offset=n]
 where "n" - starting with n record.
 
  [limit=10]
  Format - [limit=n]
 where "n" - limit with n records total.
