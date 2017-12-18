# PA165 Photography club REST API

API allows manipulation of entities.

Note: in Windows PowerShell, you may need to run
```
Remove-item alias:curl
```
for the true cURL to work.

## Root

### List all other endpoints

```
curl -i 'http://localhost:8080/pa165/rest/'
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "0441dec89b8d22baacf20a9b27968d775"
Content-Type: application/json;charset=UTF-8
Content-Length: 101

{"equipment_uri":"/equipment","reviews_uri":"/reviews","members_uri":"/members","tours_uri":"/tours"}
```

## Tours collection

### Get all tours

```
curl -i 'http://localhost:8080/pa165/rest/tours'
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "00c9250eef15d46dfef17c4de533e7a82"
Content-Type: application/json;charset=UTF-8
Content-Length: 167

[{"name":"Louvre","theme":"PORTRAITS","date":"2015-05-05","participants":[],"id":4},{"name":"Ufizzi","theme":"LANDSCAPE","date":"2016-03-06","participants":[],"id":5}]
```
        
### Create a tour

```
curl -i -X POST -H 'Content-Type: application/json' -d '{\"name\":\"Testovaci\",\"theme\":\"PORTRAITS\",\"date\":\"2017-11-11\"}' 'http://localhost:8080/pa165/rest/tours'
```

```
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Location: http://localhost:8080/pa165/rest/tours/14
Content-Length: 0
```

## Single tour

### Get a tour by ID

```
curl -i 'http://localhost:8080/pa165/rest/tours/14'
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "05af41b6f41ae75bc7ae97a95625b5368"
Content-Type: application/json;charset=UTF-8
Content-Length: 86

{"name":"Testovaci","theme":"PORTRAITS","date":"2017-11-11","participants":[],"id":14}
```

### Delete a tour

```
curl -i -X DELETE 'http://localhost:8080/pa165/rest/tours/14'
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Content-Length: 0
```

## Tour reviews

### Get reviews of a tour

```
curl -i 'http://localhost:8080/pa165/rest/tours/4/reviews'
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "0d751713988987e9331980363e24189ce"
Content-Type: application/json;charset=UTF-8
Content-Length: 2

[]
```

## Tour participants

### Get participants of a tour

```
curl -i 'http://localhost:8080/pa165/rest/tours/4/members'
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "0d751713988987e9331980363e24189ce"
Content-Type: application/json;charset=UTF-8
Content-Length: 2

[]
```





## Equipment collection

### Create equipment

```
curl -i -X POST -H 'Content-Type: application/json' -d '{\"name\":\"TestEquipment\",\"type\":\"CAMERA\",\"ownerId\":13}' 'http://localhost:8080/pa165/rest/equipment'
```

```
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Location: http://localhost:8080/pa165/rest/equipment/15
Content-Length: 0
```
       
## Single equipment

### Get a equipment by ID

```
curl -i 'http://localhost:8080/pa165/rest/equipment/15'
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "0582c115e333c7c3efe2ac5aabb69d9cd"
Content-Type: application/json;charset=UTF-8
Content-Length: 270

{"id":15,"owner":{"id":13,"name":"Cyril","birthDate":"1999-05-28","address":"V Praze blaze","motivation":"want to try","experience":"none","gender":"MALE","photo":"link","email":"cyrul@mail","password":"Cyrilko","userRole":"USER"},"name":"TestEquipment","type":"CAMERA"}
```

### Delete Equipment

```
curl -i -X DELETE 'http://localhost:8080/pa165/rest/equipment/15'
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Content-Length: 0
```