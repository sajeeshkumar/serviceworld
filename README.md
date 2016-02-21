### Installation steps for heroku

Prerequisites are maven, heroku, git clients to be installed.

Run the following steps
```
git clone https://github.com/sajeeshkumar/serviceworld.git
cd serviceworld
heroku create
git push heroku master
```

The above steps will install the REST service on heroku. Please not the service URL.

#### Testing the service using CURL

##### Get list of all companies

curl -H "Accept:application/json" heroku-server-url/company

eg: ``` curl -H "Accept:application/json" https://gentle-ridge-88824.herokuapp.com/company ```

#####  Get a company by id

curl -H "Accept:application/json" heroku-server-url/company/{id}

eg: ``` curl -H "Accept:application/json" https://gentle-ridge-88824.herokuapp.com/company/1 ```

#####  Create a new company

curl -i -X POST -H "Content-Type : application/json" heroku-server-url/company -d '{ "name" : "Test company",  "address" : "Test address",   "city" : "Vancouver",  "country" : "canada"}'

eg; ``` curl -i -X POST -H "Content-Type : application/json" https://gentle-ridge-88824.herokuapp.com/company -d '{ "name" : "Test company",  "address" : "Test address",   "city" : "Vancouver",  "country" : "canada"}'  ```

#####  Update a  company

curl -i -X POST -H "Content-Type : application/json" heroku-server-url/company/{id} -d '{ "name": "Test company",   "address": "Test address",   "city": "Vancouver",  "country": "canada",  "email": "abc@abc.com",    "phoneNum": "604-509-8274",    "owners": ["owner1","owner2"]}'

eg; ``` curl -i -X PUT -H "Content-Type : application/json" https://gentle-ridge-88824.herokuapp.com/company/1 -d '{ "name": "Test company",   "address": "Test address",   "city": "Vancouver",  "country": "canada",  "email": "abc@abc.com",    "phoneNum": "604-509-8274",    "owners": ["owner1","owner2"]}'  ```

#####  Add owner(s) to a company

curl -i -X POST -H "Content-Type : application/json" heroku-server-url/company/{id}/owner -d '["owner3","owner4"]'

eg; ``` curl -i -X POST -H "Content-Type : application/json" https://gentle-ridge-88824.herokuapp.com/company/1/owner -d '["owner3","owner4"]'  ```

##### Angular JS client

Launch test.html in a browser to view REST API calls to the heroku instance. The REST API calls are done using angularjs libraries.
