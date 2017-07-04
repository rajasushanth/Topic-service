# Topic Service

Topic service is a REST API providing service to Topic web - a UI module by encapsulating business and persistence logic. It utilizes access tokens (JWT) to authenticate the consumers of this API without storing the state of them making it 100% RESTful architecture.

## Getting Started

Clone the repository to your local by executing the command in git bash. ```sh git clone <https://github.com/rajasushanth/topic-service.git>```

## Prerequisites

* JDK 1.8 or above
* Maven 3.0.5 or above
* [MongoDB 3.0.0](https://www.mongodb.com/) or above (OR) if you don't wish to install mongodb use [mLab](https://mlab.com/) Database-as-a-service providing limited free storage

## Installing

1. Verify you prerequisites
**Prerequisites**
* [Topic Config](https://github.com/rajasushanth/topic-config.git) service running
* MongoDB server running
2. Navigate to the project root where pom.xml resides
3. Built the WAR module by executing ```sh mvn clean install```

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
