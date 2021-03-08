# Recruitment application
> Recruitment app created and maintained by Group 5

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Status](#status)
* [Contact](#contact)

## General info
Add more general information about project. What the purpose of the project is? Motivation?


## Technologies
### Infrastructure
* Azure Cloud App for Docker
* Azure SQL

### Backend
* Java
* Spring Boot

### Frontend
* JavaScript
* React

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
List of features ready and TODOs for future development
* Awesome feature 1
* Awesome feature 2
* Awesome feature 3

To-do list:
* Wow improvement to be done 1
* Wow improvement to be done 2

## Status
Project is: _in progress_


## Contact
Group 5 - johacarl@kth.se
