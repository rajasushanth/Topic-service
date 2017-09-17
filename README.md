# Topic Service

Topic service is a REST API providing service to Topic web - an UI module by encapsulating business and persistence logic. It utilizes access tokens (JWT) to authenticate the consumers of this API without storing the state of them making it 100% RESTful.

## Getting Started

Clone the repository to your local by executing the command in git bash.

```git clone https://github.com/rajasushanth/Topic-service.git```

## Prerequisites

* JDK v1.8 or above
* Maven v3.0.5 or above
* [MongoDB v3.0.0](https://www.mongodb.com/) or above (OR) if you don't wish to install mongodb use [mLab](https://mlab.com/) Database-as-a-service

## Installing

1. Verify following conditions are met
* [Topic Config](https://rajasushanth.github.io/Topic-config/) service running
* MongoDB server running
2. Navigate to the project root where pom.xml resides
3. Built the WAR module by executing ```mvn clean install```

## Continous Integration / Continous Deployment - Travis CI

**Deployment steps**
1. Sign in to [Travis CI](https://travis-ci.org/) with your GitHub account.
2. Add the Topic-service repository in Travis CI
3. Execute ```git push``` for GitHub repository, which triggers the build process and deploys the application in Pivotal cloud foundry

## Manual Deployment 
**Running it in local**

1. Navigate tho the path where WAR build resides
2. Execute the command in the command line
```java -jar topic-service-0.0.1-SNAPSHOT.jar```

**Running in Pivotal cloud foundry**
1. Create an account in [Pivotal cloud foundry](https://login.run.pivotal.io/login)
2. Install the [Cloud Foundry CLI](https://docs.cloudfoundry.org/cf-cli/install-go-cli.html)
3. Get the [manifest.yml](https://github.com/rajasushanth/Topic-manifest/blob/master/topic-service/manifest.yml)
4. Execute the command ```cf push```

## Author

* **RajaSushanth** - [GitHub RajaSushanth](https://github.com/rajasushanth)
