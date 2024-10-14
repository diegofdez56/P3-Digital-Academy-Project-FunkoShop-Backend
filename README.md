# FunkoShop E-Commerce Project

The FunkoShop project is an e-commerce platform for Funko Pop collectors in Europe. Users can browse and purchase Funko Pop collectibles, with secure JWT-based authentication for managing accounts.

Key features include a product catalog with search and filtering, a persistent shopping cart, secure payments, and user profiles. The platform is built with Spring Boot (backend) and Vue.js (frontend), ensuring a smooth and responsive experience.

The admin panel allows for easy management of products, orders, and discounts, making FunkoShop a reliable destination for collectors.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributors](#contributors)
- [Disclaimer](#disclaimer)

## Installation

Instructions on how to install and set up the project.

```bash
git clone https://github.com/FactoriaF5-Asturias/P3-Digital-Academy-Project-FunkoShop-Backend.git
```

### .env

```bash
DATABASE_DB_NAME= (database name)
DATABASE_USERNAME = (database username)
DATABASE_PASSWORD = (database password)
SPRING_PROFILES_ACTIVE= mysql
API_ENDPOINT= /api/v1
API_JWT_KEY= (your jwt key) 
STRIPE_SECRET_KEY= your.stripe.secret.key
MAILGUN_USERNAME= your.mailgun.username
MAILGUN_PASSWORD= your.mailgun.password
```
### Database
You need to set up the following environment variables to connect to the database:

- <strong>DATABASE_DB_NAME:</strong> This is the name of your database. It should be the name of the database where all the data for the FunkoShop will be stored. Replace (database name) with the actual name of your database. For example: funko_shop_db.

- <strong>DATABASE_USERNAME: </strong> This is the username used to access the database. Replace (database username) with the username that has permission to access and manage the database. For example: admin_user.

- <strong>DATABASE_PASSWORD:</strong> This is the password for the database username. Replace (database password) with the actual password that corresponds to the username. For example: supersecretpassword.

Make sure to set these environment variables correctly before running the application so that it can connect to the database successfully.

### Firebase

To use Firebase in this project, follow these easy steps:

- <strong>Create a Firebase Account:</strong> If you don't have a Firebase account, go to [Firebase](https://firebase.google.com) and sign up. 

- <strong>Create a Firebase Project:</strong> Once you have an account, create a new project in Firebase. This is where your app will connect to Firebase services.

- <strong>Get Your Firebase Key:</strong> After setting up your project, Firebase will give you a key in the form of a JSON file. This file contains important information your app needs to connect to Firebase.

- <strong>Save the JSON File:</strong> Download this JSON file and place it in the ``resources`` folder of your project. Without this file, the app won’t be able to connect to Firebase.

That's it! Now your app will be able to talk to Firebase and use its services.



### Stripe

- Create an account on [Stripe](https://stripe.com) if you don't already have one.
- After setting up your account, you’ll get a secret key from Stripe.

- Add this secret key to your .env file as an environment variable, like this:

```
STRIPE_SECRET_KEY=your_secret_key_here
```
This key is needed to securely process payments through Stripe.

### JWT Key (for Windows, Linux and Mac)

To secure your application with JWT (JSON Web Token), you need a secret key that is at least 256 bits (32 bytes) long. Here’s how to generate one based on your operating system:

#### Windows (PowerShell)
- <strong>Generate a Key:</strong> Open PowerShell and run the following command to generate a secure, random key:

``` powershell
[System.Convert]::ToBase64String((1..32 | ForEach-Object {Get-Random -Maximum 256}) -join '')
```
- <strong>Copy the Generated Key:</strong> After running the command, a random Base64 string will be output. This is your JWT secret key.

#### Linux or Mac (Terminal)
- <strong>Generate a Key:</strong> Open the terminal and use the following command to generate a 256-bit random key:

```bash
openssl rand -base64 32
```
- <strong>Copy the Generated Key:</strong> The terminal will display a random Base64 string. This is your JWT secret key.

- <strong>Add the Key to Your .env File (for all systems)</strong>


Once you have generated the key, add it to your .env file in your project like this:

```
JWT_SECRET_KEY=your_generated_key_here
```
This key will be used to sign and verify the JWT tokens in your application. Be sure to keep it secure and never share it publicly.

### Mailgun

To send emails through Mailgun, follow these steps:

- <strong>Create an Account:</strong> Go to [Mailgun](https://www.mailgun.com) and sign up for an account.

- <strong>Set Up a Domain:</strong> After creating your account, you’ll need to set up a domain in Mailgun. This will be used to send emails. Follow the Mailgun setup guide to verify your domain and configure DNS settings.

- <strong>Get Your API Key:</strong> Once your domain is set up, go to the Mailgun dashboard and find your API key. This key will be used to authenticate your application with Mailgun’s servers.

- <strong>Add Credentials to Your .env File:</strong> Add the following variables to your .env file with the values you obtained from Mailgun:

```
MAILGUN_API_KEY=your_api_key_here
MAILGUN_DOMAIN=your_domain_here
MAILGUN_API_KEY: This is the private API key you got from your Mailgun account.
MAILGUN_DOMAIN: This is the domain you set up in Mailgun to send emails.
```
<strong>Configure Mailgun in Your Application:</strong> Make sure your application is configured to read these environment variables and use them to send emails via Mailgun’s API.

By completing these steps, your application will be able to send emails using Mailgun.

## Usage

To run app:
```bash
mvn spring-boot:run
```

## UML Diagram

![uml](https://i.imgur.com/NbBvMZP.png)

## Endpoints

api-endpoint= /api/v1

### Swagger

Swagger - API Documentation
We use Swagger (OpenAPI) to provide clear, interactive documentation for our API. Swagger automatically generates up-to-date documentation based on the API code, allowing developers to explore and test endpoints directly in their browser.

#### Key Benefits:
- <strong>Comprehensive Documentation:</strong> All API routes, parameters, and responses are clearly displayed.
- <strong>Interactive Testing:</strong> Test API calls directly through the Swagger UI without needing external tools.
- <strong>Seamless Collaboration:</strong> Provides a common reference for developers and integrators to easily understand the API structure.
#### How to Access:
- Run the application.
- Open your browser and go to:
```bash
http://localhost:8080/swagger-ui.html
```
Swagger helps streamline the development process, ensuring our API is well-documented and accessible.

### Login

- admin: admin@gmail.com
- password: password


## Tools

- Visual Studio Code
- Swagger
- Postman
- Docker
- MySQL Workbench

## Contributors


#### GitHub:
- Aitor Garcia: [aitorgarciadev](https://github.com/aitorgarciadev)
- Bego Blanco: [begoblanco](https://github.com/begoblanco)
- Javier Martinez: [devjmv](https://github.com/devjmv)
- Diego Fernandez: [diegofdez56](https://github.com/diegofdez56)
- Olena Andrushchenko: [OlenaAndrushchenko](https://github.com/OlenaAndrushchenko)
- Vero Doel: [Miharu669](https://github.com/Miharu669)

## FrontEnd

- Frontend Repo: [FunkoShop-Frontend](https://github.com/FactoriaF5-Asturias/P3-Digital-Academy-Project-FunkoShop-Frontend)

## Disclaimer

This project is developed as part of a bootcamp learning experience and is intended for educational purposes only. The creators and contributors are not responsible for any issues, damages, or losses that may occur from using this code.

This project is not meant for commercial use, and any trademarks or references to third-party services (such as Funko) belong to their respective owners. By using this code, you acknowledge that it is a work in progress, created by learners, and comes without warranties or guarantees of any kind.

Use at your own discretion and risk.