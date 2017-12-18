# PA165 Photography club REST API

API allows manipulation of entities.

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
ETag: "0a347114d2ac9a83132c38130c1ac6b5b"
Content-Type: application/json;charset=UTF-8
Content-Length: 193

[{"name":"Louvre","theme":"PORTRAITS","date":"2015-05-05","reviews":[],"participants":[],"id":4},{"name":"Ufizzi","theme":"LANDSCAPE","date":"2016-03-06","reviews":[],"participants":[],"id":5}]
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
ETag: "01a1d67c3badcbd78d42f5631624e7f8f"
Content-Type: application/json;charset=UTF-8
Content-Length: 99

{"name":"Testovaci","theme":"PORTRAITS","date":"2017-11-11","reviews":[],"participants":[],"id":14}
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
curl -i -X POST -H 'Content-Type: application/json' -d '{\"name\":\"TestEquipment\",\"type\":\"CAMERA\"}' 'http://localhost:8080/pa165/rest/equipment'
```

```
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Content-Length: 0
```
       
## Single equipment

### Get a equipment by ID

```
curl -i 'http://localhost:8080/pa165/rest/equipment/5'
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "01a1d67c3badcbd78d42f5631624e7f8f"
Content-Type: application/json;charset=UTF-8
Content-Length: 99

{"name":"TestEquipment","type":"CAMERA","date":"2017-11-11","reviews":[],"participants":[],"id":5}
```

### Delete Equipment

```
curl -i -X DELETE 'http://localhost:8080/pa165/rest/equipment/5'
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Content-Length: 0
```