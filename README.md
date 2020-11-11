# Coffee Shop System
An API to manage orders in a coffee shop

##System Requirements
* Jdk 11
* docker 18.09.9
* docker-compose
* Postman (for testing)

##How to deploy
Execute following commands in project base directory
* mvnw clean package
* sudo docker build --tag=coffee-shop --rm=true .
* sudo docker-compose up

##How to test
Use Postman to test collection in  docs/Coffeshop API.postman_collection.json

##API documentation
https://web.postman.co/collections/13436747-535eaf00-3388-4660-b439-957d59b2d4d7?workspace=b6aff9c2-08eb-499a-889e-5d4003789597