# Topic Service

Topic service is a REST API providing service to Topic web - an UI module by encapsulating business and persistence logic. It utilizes access tokens (JWT) to authenticate the consumers of this API without storing the state of them making it 100% RESTful architecture.

## Getting Started

Clone the repository to your local by executing the command in git bash.

```git clone https://github.com/rajasushanth/topic-service.git```

## Prerequisites

* JDK v1.8 or above
* Maven v3.0.5 or above
* [MongoDB v3.0.0](https://www.mongodb.com/) or above (OR) if you don't wish to install mongodb use [mLab](https://mlab.com/) Database-as-a-service

## Technology stack
* Database - MongoDB
* Logging - Logback
* JWT - Access tokens

**Spring modules**

* Auto configuration - Spring boot
* Server - Tomcat embedded server
* Persistence - Spring Data [MongoRepository]
* API - Spring REST
* AOP - Spring AOP
* Security - Spring Security

> ### Why MongoDB?
> 1. High reads without any joins as in relational database.
> 2. Easy for development as it often involves modification of data structure(schema)
> 3. More understandable No-SQL queries.

> ### Why Spring framework?
> Spring has highly organised modules for various software architecture concerns.

> #### AOP  - Spring
> 1. Application's cross cutting concerns like logging and refresh tokens handling, has been flesh out from core business logic.
> 2. Used CGlib proxies for logging, since JDK Proxies can be applied only on interface implementations.

> #### Security -  Spring Security
> 1. Spring Security provides out of the box functionalities for securing endpoints based on filters.
> 2. It also provides Bcrypt password hashing which is been persisted in the database.
> 3. Decryption of the received AES - 256 bits encrypted password.

> #### Persistence - Spring Data 
> 1. MongoRepository provide high level abstraction on the MongoClient from Mongo driver jar.
> 2. Boiler plate code reduction.

> #### Spring boot
> 1. Most preferred framework providing cloud native support.
> 2. Easy kick start of application by providing auto configuration. 
> 3. Embedded servers support

> ### Logging - Logback
> Simple logging configuration

## Installing

1. Verify following conditions are met
* [Topic Config](https://github.com/rajasushanth/topic-config.git) service running
* MongoDB server running
2. Navigate to the project root where pom.xml resides
3. Built the WAR module by executing ```mvn clean install```

## Deployment 
**Running it in local**

1. Navigate tho the path where WAR build resides
2. Execute the command in the command line
```java -jar topic-service-0.0.1-SNAPSHOT.jar```

**Running in [Pivotal cloud foundry](https://login.run.pivotal.io/login)**
1. Create an account in PCF
2. Install the [Cloud Foundry CLI](https://docs.cloudfoundry.org/cf-cli/install-go-cli.html)
3. Get the [manifest.yml](https://github.com/rajasushanth/topic-manifest/blob/master/topic-service/manifest.yml)
4. Execute the command ```cf push```

## Author

* **RajaSushanth** - [Github RajaSushanth](https://github.com/rajasushanth)
