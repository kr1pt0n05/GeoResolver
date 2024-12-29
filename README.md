# Softwarearchitektur: Jodel-like Application

## Table of Contents

1. [About the Project](#über-das-projekt)  
2. [Technologies Used](#verwendete-technologien)  
3. [Installation](#installation)  

---

## About the Project

This Application translates latitude and longitude coordinates into human-readable city names.
The service is currently limited to Germany, providing city names within the country.

## GeoReverse Service Overview
The GeoReverse Service utilizes geo-data for Germany, published on [GeoNames][GeoNames-url]. This dataset includes various geographic features for the country, such as cities, states, regions, lakes, parks, railroads, mountains, and more.

Using the provided Python scripts, only administrative divisions are filtered and inserted into the database. When querying the database with a specific coordinate (latitude and longitude), the service will return the nearest city within a 10 km radius.

## API Calls
To query the API, make a call to the following URL:
http://localhost:8080/api/geocode/reverse?latitude=52.5165581981213954&longitude=13.402374234286734

A successful response will look like this:
```
{
    "city": "Berlin, Stadt",
    "country_code": "DE",
    "feature_code": "ADM3"
}
```
---

## Used Technologies
[![Spring Boot][Spring Boot]][Spring Boot-url]  
[![PostgreSQL][PostgreSQL]][PostgreSQL-url]  

---

## Installation

### Requirements:

- PostgreSQL Server 16 or higher and set up database
- Maven (for building yourself)

### Schritte

1. **Clone Repository**:  
```bash
git clone https://github.com/kr1pt0n05/GeoResolver.git
```

2. **Configure application.properties**:  
    Provide the correct database and user credentials in the application.properties file located at:
    georesolver/georesolver/src/main/resources/application.properties

    Additionally, ensure that you have created a proper PostgreSQL database for the application to connect to.

3. **Build Application**:
```bash
mvn clean package
```

4. **Run Application**:
```bash
java -jar SOME_NAME_VERSION-SNAPSHOT.jar
```

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[Spring Boot]: https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white
[Spring Boot-url]: https://spring.io/projects/spring-boot

[PostgreSQL]: https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white
[PostgreSQL-url]: https://www.postgresql.org/

[GeoNames-url]: https://www.geonames.org/