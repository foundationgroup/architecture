-Creating Department Service
-----------------------------------
	GET - http://localhost:9001/departments/1
	{
    "departmentId": 1,
    "departmentName": "finance",
    "departmentAddress": "usa",
    "departmentCode": "001"
	}
	POST - http://localhost:9001/departments/
	Body:
	{
	"departmentName":"finance",
	"departmentAddress":"usa",
	"departmentCode":"001"
	}
-Creating a User Service
------------------------------------
	GET - http://localhost:9002/users/1
	response - {
    "user": {
        "userId": 1,
        "firstName": "Tapan",
        "lastName": "Parida",
        "email": "tapankparida@gmail.com",
        "departmentId": 1
    },
    "department": {
        "departmentId": 1,
        "departmentName": "finance",
        "departmentAddress": "usa",
        "departmentCode": "001"
    }
	}
	POST - http://localhost:9002/users/
	Request Body:
	{
	"firstName":"Tapan",
	"lastName":"Parida",
	"email":"tapankparida@gmail.com",
	"departmentId":"1"
	}

	
-Implementing Service Registry
------------------------------------
	This is the serviceregistry/discoveryservice/eurekaserver by using @EnableEurekaServer with below properties - All the services will register with this service. 
	This can be accessed by - http://localhost:8761/
	
-Implementing API Gateway
------------------------------------
	This is gateway service by usting @EnableEurekaClient. All the requests would pass through this gateway service which is handled by below way - 
	@EnableHystrix is used for circuit breaker with fallback method to handle in case any dependent service is down
	
	POST - http://localhost:9191/departments/
	{
	"departmentName":"finance",
	"departmentAddress":"usa",
	"departmentCode":"001"
	}
	POST - http://localhost:9191/users/
	{
	"firstName":"Tapan",
	"lastName":"Parida",
	"email":"tapankparida@gmail.com",
	"departmentId":"1"
	}
	GET - http://localhost:9191/users/1
	{
    "user": {
        "userId": 1,
        "firstName": "Tapan",
        "lastName": "Parida",
        "email": "tapankparida@gmail.com",
        "departmentId": 1
    },
    "department": {
        "departmentId": 1,
        "departmentName": "finance",
        "departmentAddress": "usa",
        "departmentCode": "001"
    }
	}
	GET - http://localhost:9191/departments/1
	{
    "departmentId": 1,
    "departmentName": "finance",
    "departmentAddress": "usa",
    "departmentCode": "001"
	}
	
	Actuator - http://localhost:9191/actuator, http://localhost:9191/actuator/hystrix.stream

-Creating Hystrix Dashboard
------------------------------------
	Hystrix dashboard is used for finding - Success | Short-Circuited | Bad Request | Timeout | Rejected | Failure | Error %
	The dashboard can be accessed by - http://localhost:9295/hystrix, 
	This dashboard uses the hystrix stream - http://localhost:9191/actuator/hystrix.stream


-Cloud Config Server
------------------------------------
	Config server can be accessed by - http://localhost:9296

	application properties can be accessed by  - http://localhost:9296/application/default

-GitHub Repo for Config Server
------------------------------------
	Config repo is located in - https://github.com/foundationgroup/cloud-config-repo
	
-Zipkin and Sleuth
------------------------------------
	zipkin is used for distributed log tracing tracing between the services...
	Zipkin is running in - http://127.0.0.1:9411/zipkin/ from an executable jar file  - java -jar zipkin-server-2.23.14-exec.jar


Connecting H2 database from the browser - 
------------------------------------
http://localhost:9001/h2-console, http://localhost:9002/h2-console
jdbc url - jdbc:h2:mem:testdb
username - sa
password - 


ELK--
------------------------------------
------------------------------------
Centralized logging in the microservice architecture...

elasticsearch - 
--------------
D:\elk\elasticsearch-7.16.1\bin>elasticsearch.bat
http://localhost:9200/
http://localhost:9200/_cat/indices

kibana - 
---------
D:\elk\kibana-7.16.1-windows-x86_64\bin>kibana.bat
http://localhost:5601/app/home#/

create indices - 
PUT tapan 
{
  "settings": {
    "index": {
      "number_of_shards": 3,
      "number_of_replicas": 2
      }
  }
}
result - 
{
  "acknowledged" : true,
  "shards_acknowledged" : true,
  "index" : "tapan"
}
add document - 
POST tapan/default/ 
{
  "name": "event processing",
  "instructor":{
    "firstname":"tapan",
    "lastname":"parida"
  }
}

logstash - 
----------
D:\elk\logstash-7.16.1\bin>logstash.bat logstash.conf
Extract all the logs from the log file, send it to elasticsearch, then elasticsearch sends it to kibana.



Spring Security - Oauth2
------------------------------------
------------------------------------
google
-----------
client id - 16590295129-35vae39bhufcib2dkgebiq4ipd80u8ee.apps.googleusercontent.com
client secret - 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

facebook
--------------
client id - 681402486564884
client secret - e446e93fcacb8ac8e15539b074fb9fa2

github
--------------
Client ID - c4aab2c7abf1d2b1a1ca
Client secret - e308db0dc856d297d8bafef20f75a63f6ee7706f


