# Cryptoloan API

This project provides a REST API for creating loans, storing them, auto-creating and calculating installments, and supports multiple currencies, including Bitcoin.

## Front End

You can use the [Cryptoloan Frontend](https://github.com/wezik/cryptoloan-frontend) project to interact with the API.

## Requirements

- Java 11
- Gradle
- MySQL
- Exchangeratesapi.io basic or higher subscription

## How to Run

1. Set up your MySQL database in the `application.properties` file.
2. Provide your API key by setting an environment variable called `EXCHANGERATES_API_KEY` or by pasting it into `application.properties`.
3. Build the project with `gradlew build` in the terminal.
4. Run the project with `./gradlew run`.

## Note

Reevaluation of installments and data tracking entities are set up by a scheduler, which runs on application start or every day at 4 AM by default. If you want to see it work, re-run the project.

## How to Configure

You can customize the following settings in the `application.properties` file:
- Amount of days API uses to calculate time between installments
- Punishing old installments
- Setting up the final date of a loan

You can also customize the port, but remember to update it in the frontend project if you want to use it.

## Updates

- **10.04.2021**: Fixed compatibility issue with Exchangerates.io API's new policy. Cryptoloan can still work with at least basic subscription plan. However, some currencies may not be accessible due to the policy change. Default currencies are unaffected.
