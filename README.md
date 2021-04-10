## About
This project is a Rest API you can use for creating loans, storing them, auto creating and calculating installments etc.

All of that between many currencies including BitCoin

## Front End
You can use my Front End project to run it

https://github.com/wezik/cryptoloan-frontend

## Requirements
Java 11

Gradle

MySQL

Exchangeratesapi.io basic subscription

## How to run
Setup your MySQL database in [application.properties](https://github.com/wezik/cryptoloan/blob/main/src/main/resources/application.properties#L11-L14) file

Provide your API key by setting environment variable called `EXCHANGERATES_API_KEY`

Build your gradle with `gradlew build` in terminal

Run the project

## Note
Reevaluation of installments as well as data tracking entities are set-up by scheduler, it will always run on application start or every day at 4AM by default, if you want to see it work just re-run the project.

## How to configure
You can change amount of days API uses to calculate time between installments, punishing old ones or setting up final date of a loan in [application.properties](https://github.com/wezik/cryptoloan/blob/main/src/main/resources/application.properties#L16-L17)

You can also customize your port but it requires you to change it as well in frontend project if you want to use it.
