# flightagg
Simple Springboot application with RestAPI for flight providers. This application aggregates data from these sources:
- https://obscure-caverns-79008.herokuapp.com/cheap
- https://obscure-caverns-79008.herokuapp.com/business

And adding some additional functions (filtering, pagination and order).

After starting application this can be used on 
  http://localhost:8080/flights
context path.

Resulting flight record example:<br>
[{"id":"2231887273634720768",<br>
  "departure":"El Palomar",<br>
  "arrival":"Chacarita",<br>
  "departureTime":1554578966044,<br>
  "arrivalTime":1554579032743}]<br>

RestAPI description:<br>
1. Get all flights
- http://localhost:8080/flights/all

2. Query with parameters
- http://localhost:8080/flights/q?sorted=arrivalTime;asc&f_departure=like;Berlin&limit=10&offset=5

2.1. Ordering data. 
 [sorted=arrivalTime;asc]<br>
 Format - [field_name;asc|desc]<br>
 
2.2. Filtering data.
 [f_departure=like;Berlin]<br>
 Format - [f_field_name=like;|gte;|lte;value]<br>
where "like;" - like operator<br>
      "gte;" - grater or equas then<br>
      "lte;" - lower or equals then.<br>
Filter is case sensitive.<br>

2.3. Pagination data.
  [offset=5]<br>
  Format - [offset=n]<br>
 where "n" - starting with n record.<br>
 <br>
  [limit=10]<br>
  Format - [limit=n]<br>
 where "n" - limit with n records total.<br>
