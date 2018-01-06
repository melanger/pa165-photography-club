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
curl -i "http://localhost:8080/pa165/rest/"
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
curl -i "http://localhost:8080/pa165/rest/tours"
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

#### Windows Powershell
```
curl -i -X POST -H "Content-Type: application/json" -d '{\"name\":\"Testovaci\",\"theme\":\"PORTRAITS\",\"date\":\"2017-11-11\"}' "http://localhost:8080/pa165/rest/tours"
```
#### Windows command line / Linux
```
curl -i -X POST -H "Content-Type: application/json" -d "{\"name\":\"Testovaci\",\"theme\":\"PORTRAITS\",\"date\":\"2017-11-11\"}" "http://localhost:8080/pa165/rest/tours"
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
curl -i "http://localhost:8080/pa165/rest/tours/14"
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
curl -i -X DELETE "http://localhost:8080/pa165/rest/tours/14"
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
curl -i "http://localhost:8080/pa165/rest/tours/4/reviews"
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
curl -i "http://localhost:8080/pa165/rest/tours/4/members"
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

#### Powershell
```
curl -i -X POST -H "Content-Type: application/json" -d '{\"name\":\"TestEquipment\",\"type\":\"CAMERA\",\"ownerId\":13}' "http://localhost:8080/pa165/rest/equipment"
```
#### Windows command line / Linux
```
curl -i -X POST -H "Content-Type: application/json" -d "{\"name\":\"TestEquipment\",\"type\":\"CAMERA\",\"ownerId\":13}" "http://localhost:8080/pa165/rest/equipment"
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

### Get an equipment by ID

```
curl -i "http://localhost:8080/pa165/rest/equipment/15"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "0582c115e333c7c3efe2ac5aabb69d9cd"
Content-Type: application/json;charset=UTF-8
Content-Length: 270

{"id":15,"owner":{"id":13,"name":"Cyril","birthDate":"1999-05-28","address":"V Praze blaze","motivation":"want to try","experience":"none","gender":"MALE","photo":"link","email":"cyrul@mail","userRole":"MEMBER"},"name":"TestEquipment","type":"CAMERA"}
```

### Delete Equipment

```
curl -i -X DELETE "http://localhost:8080/pa165/rest/equipment/15"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Content-Length: 0
```



## Member collection

### Get all members

```
curl -i "http://localhost:8080/pa165/rest/members"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "0571230ccee99edd754422f35046010f6"
Content-Type: application/json;charset=UTF-8
Content-Length: 669
Date: Mon, 18 Dec 2017 19:55:03 GMT

[{"id":11,"name":"Anton","birthDate":"1995-11-09","address":"unknown","motivation":"to be the best","experience":"moderate","gender":"MALE","photo":"photolink","email":"anton@mail","userRole":"MANAGER"},{"id":12,"name":"Ben","birthDate":"1998-03-16","address":"address 11 Brno","motivation":"for fun","experience":"beginner","gender":"MALE","photo":"profilepic","email":"ben@mail.com","userRole":"MEMBER"},{"id":13,"name":"Cyril","birthDate":"1999-05-28","address":"V Praze blaze","motivation":"want to try","experience":"none","gender":"MALE","photo":"link","email":"cyrul@mail","userRole":"MEMBER"}]
```

### Get a member by name

```
curl -i "http://localhost:8080/pa165/rest/members?name=Anton"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "059b7ceda845d768b8f81862df1b71d09"
Content-Type: application/json;charset=UTF-8
Content-Length: 228
Date: Mon, 18 Dec 2017 20:07:18 GMT

[{"id":11,"name":"Anton","birthDate":"1995-11-09","address":"unknown","motivation":"to be the best","experience":"moderate","gender":"MALE","photo":"photolink","email":"anton@mail","userRole":"MANAGER"}]
```

### Create a member

#### Powershell
```
curl -i -X POST -H "Content-Type: application/json" -d '{\"name\":\"TestMember\",\"birthDate\":\"1995-11-11\"}' "http://localhost:8080/pa165/rest/members"
```
#### Windows command line / Linux
```
curl -i -X POST -H "Content-Type: application/json" -d "{\"name\":\"TestMember\",\"birthDate\":\"1995-11-11\"}" "http://localhost:8080/pa165/rest/members"
```


```
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Location: http://localhost:8084/pa165/rest/members/16
Content-Length: 0
Date: Mon, 18 Dec 2017 21:31:41 GMT
```



## Single member

### Get a member by ID

```
curl -i "http://localhost:8080/pa165/rest/members/11"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "0ea7860bb61f5c7fb24430338dca22704"
Content-Type: application/json;charset=UTF-8
Content-Length: 226
Date: Mon, 18 Dec 2017 19:59:26 GMT

{"id":11,"name":"Anton","birthDate":"1995-11-09","address":"unknown","motivation":"to be the best","experience":"moderate","gender":"MALE","photo":"photolink","email":"anton@mail","userRole":"MANAGER"}
```

### Remove member

```
curl -i -X DELETE "http://localhost:8080/pa165/rest/members/16"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Content-Length: 0
Date: Tue, 19 Dec 2017 00:13:33 GMT

```

### Get equipment by member

```
curl -i "http://localhost:8080/pa165/rest/members/11/equipment"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "03641bb9b3e7cfe09b8aa1caf8df58f67"
Content-Type: application/json;charset=UTF-8
Content-Length: 835
Date: Mon, 18 Dec 2017 20:38:31 GMT

[{"id":6,"owner":{"id":11,"name":"Anton","birthDate":"1995-11-09","address":"unknown","motivation":"to be the best","experience":"moderate","gender":"MALE","photo":"photolink","email":"anton@mail","userRole":"MANAGER"},"name":"Nikon 4x","type":"LENS"},{"id":7,"owner":{"id":11,"name":"Anton","birthDate":"1995-11-09","address":"unknown","motivation":"to be the best","experience":"moderate","gender":"MALE","photo":"photolink","email":"anton@mail","userRole":"MANAGER"},"name":"CanonG6","type":"CAMERA"},{"id":8,"owner":{"id":11,"name":"Anton","birthDate":"1995-11-09","address":"unknown","motivation":"to be the best","experience":"moderate","gender":"MALE","photo":"photolink","email":"anton@mail","userRole":"MANAGER"},"name":"MikonaZZ","type":"LIGHTNING"}]
```

### Get reviews by Author

```
curl -i "http://localhost:8080/pa165/rest/members/11/reviews"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "0d751713988987e9331980363e24189ce"
Content-Type: application/json;charset=UTF-8
Content-Length: 2
Date: Mon, 18 Dec 2017 20:47:37 GMT

[]
```

## Review collection

### Get all reviews

```
curl -i "http://localhost:8080/pa165/rest/reviews"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "05d8c3c94ba25739a748a138e7c8f2091"
Content-Type: application/json;charset=UTF-8
Content-Length: 189
Date: Mon, 18 Dec 2017 20:54:20 GMT

[{"id":1,"tour":null,"author":null,"comment":"great","rating":10},{"id":2,"tour":null,"author":null,"comment":"ok","rating":5},{"id":3,"tour":null,"author":null,"comment":"bad","rating":2}]
```

### Create a review

#### Powershell
```
curl -i -X POST -H "Content-Type: application/json" -d '{\"tourId\":\"4\",\"authorId\":\"12\",\"comment\":\"TestingReview\"}' "http://localhost:8080/pa165/rest/reviews"
```
#### Windows command line / Linux
```
curl -i -X POST -H "Content-Type: application/json" -d "{\"tourId\":\"4\",\"authorId\":\"12\",\"comment\":\"TestingReview\"}" "http://localhost:8080/pa165/rest/reviews"
```

```
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Location: http://localhost:8084/pa165/rest/reviews/14
Content-Length: 0
Date: Mon, 18 Dec 2017 21:12:03 GMT
```



## Single review

### Get review by ID

```
curl -i "http://localhost:8080/pa165/rest/reviews/17"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
ETag: "0140577e1554242a97308753cc26fa443"
Content-Type: application/json;charset=UTF-8
Content-Length: 60
Date: Mon, 18 Dec 2017 20:55:10 GMT

{"id":17,"tour":{"name":"Louvre","theme":"PORTRAITS","date":"2015-05-05","participants":[{"id":11,"name":"Anton","birthDate":"1995-11-09","address":"unknown","motivation":"to be the best","experience":"moderate","gender":"MALE","photo":"photolink","email":"anton@mail","userRole":"MANAGER"},{"id":13,"name":"Cyril","birthDate":"1999-05-28","address":"V Praze blaze","motivation":"want to try","experience":"none","gender":"MALE","photo":"link","email":"cyrul@mail","userRole":"MEMBER"}],"id":4},"author":{"id":12,"name":"Ben","birthDate":"1998-03-16","address":"address 11 Brno","motivation":"for fun","experience":"beginner","gender":"MALE","photo":"profilepic","email":"ben@mail.com","userRole":"MEMBER"},"comment":"TestingReview","rating":0}
```


### Delete a review

```
curl -i -X DELETE "http://localhost:8080/pa165/rest/reviews/17"
```

```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Content-Length: 0
Date: Mon, 18 Dec 2017 21:08:06 GMT
```
