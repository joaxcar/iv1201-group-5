# Recruitment application
> Recruitment app created and maintained by Group 5

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Status](#status)
* [Contact](#contact)

## General info
This is "The Amusements Parks" recruitment application. The goal is to build a maintainable and scalable application that can replace the old and outdated "Recruitment one".


## Technologies
### Infrastructure
* Azure Cloud App for Docker
* Azure SQL

The application runs in Azure cloud with an Azure SQL database. The application is automaticly redeployed when a new docker image is uploaded to our Azure Image Reposetpry.


### Backend
* Java - 15
* Spring Boot - 2.3.0

The backend is a REST API created with Spring Boot and written in JAVA. The backend can be built and deployed as a stand alone web service. In production we deploy it contanerized with a built in client.

### Frontend (Client)
* ECMAScript - 16
* React - 17

The client is a component based SPA written in REACT and ECMAScript.

## Setup
### Clone project
`git clone https://github.com/joaxcar/iv1201-group-5.git`
### Build
First build the frontend, the build script will also move the static files to the static directory of the Spring App. Then build the backend and package it as a container.
```sh
cd ../frontend
npm ci
npm run build --if-present
cd ../backend
mvn -B package
docker build . -t regapp:latest

```
### Run
Run the built container.
```
sudo docker run -p 8080:8080 regapp:latest
```

## Features
Done:
* Sign up as a user

To-do list:
* Apply for jobs
* Handle applications as an administrator

## Status
Project is: _in progress_


## Contact
Group 5 - johacarl@kth.se
