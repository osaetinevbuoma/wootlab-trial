# Wootlab Marketplace

## Overview

A fictitious e-commerce website developed as a trial project for Wootlab.

## Technology Stack

* Spring Boot
* MySQL
* VueJS

### Third party services
* Paystack
* Mailgun
* Telebit

## Installation
* Create a MySQL database called `wootlab-trial` (preferably of type `InnoDB` and collation 
`utf8_general_ci`).
* Import the seed data from the `wootlab-trial.sql` file into the created database.
* Change the values of `spring.datasource.username` and `spring.datasource.password` 
in the `src/main/resources/application.properties` file to your database username and password 
respectively.
* Open a terminal into the root folder of the project and type `./gradlew build` to build the 
project and download dependencies.
* Run the Tomcat server by typing `./gradlew bootRun`. This creates the database table and starts 
the Tomcat server.
* The application will be accessible on `http://localhost:8080`.

### Note

You can expose localhost as a remote URL by using a tool such as [telebit](https://telebit.cloud/). 
This is useful to test the order confirmation email that is sent to a customer after a successful 
purchase. Images will be displayed as expected, which will not happen if the email is sent from 
localhost.

## Assumptions

* Wootlab Marketplace is owned by a company and this singular entity owns the products in the 
platform’s catalogue.
* An inventory management system is in place that manages the products sold by the company on 
Wootlab Marketplace. The platform communicates with this inventory management system or has a shared 
database.
* Only products that are currently in stock are made available on the Wootlab Marketplace.
* Products with customer reviews are moderated by an employed administrator of the owning company.
* Customers have a means of rating products and giving reviews but that is not covered in the 
current scope.
* Seed data (files, images, videos and text content) are for demonstration purposes only. They have 
no meaning and should have no meaning read to them other than representations of actual data.  