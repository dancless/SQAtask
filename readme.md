# TypiCodeAPI API Tests
This is a sample API Test project using RestAssured, testNG, Java, Maven and
IntelliJ idea.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)

## Instalation
Clone repository from:
```
git clone https://github.com/dancless/SQAtask.git
```

## Usage
Click run icon from classes and tests.
Run TestNG configuration.
Maven commands:
```
mvn test
mvn test -Dtest="RestAPI.TypiCodeTests"
mvn test -Dtest="RestAPI.TypiCodeNegativeTests"
mvn clean test -DsuiteXmlFile=testng
```

## License
This project is licensed under the [MIT License](LICENSE).