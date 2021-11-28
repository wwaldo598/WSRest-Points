# Overactive
Project oriented to develop a Rest WS to calculate rewarded points.

# Description
The **pointsAppWS** Web Serrvice, is a Rest WS  that has as targer calculate the total of point acumulated according to the amount of puchase tha has commited by a customer. The total de points to calculate correspond to the....
The WS offers different endpoints which could be consume by a client application as curl, SoapUI or Postman.   
The format to send and receive is a Json format.


## WS Rest endpoints 
As it was mentioned above, this WS offers the different endpoints which could be consume from a client application as curl, SoapUI or Postman. It could be also consumed by any application capable of consumig a Rest WS, whitout taking account the programming lenguage in which the application was created. 

The Urls available are the followings:
|HTTP METHOD                |URL                          |DESCRIPTION                        |
|----------------|-------------------------------|-----------------------------|
|POST|`/pointsAppWS/transaction`            |Create a new transaction.           |
|GET|`/pointsAppWS/transaction`            |Get a list the transaction recorded.           |
|DELETE|`/pointsAppWS/transaction/{id}`            |Delete a transaction according to the id passed in the Urls path.           |
|UPDATE|`/pointsAppWS/transaction/{id}`            |Update a transaction information according to the id passed in the Urls path.      |
|GET|`/pointsAppWS/points/month/{customer}`            |Get a list of earned points accumaleted monthly by the customer.             |
|GET|`/pointsAppWS/points/total/{customer}`            |Get total of earned points accumalated by the customer. 

Next section it will explain how to use these enpoints.
   
______ 
**HTTP METHOD: POST**
**URL: /pointsAppWS/transaction**

This endpoint is responsible of creating a new transaction. 

The information to enter is in JSON format and the following parameters are mandatory:

- **id :** Corresponds to the transaction id and receives an alphanumeric value.
- **customer :** Corresponds to the customer name and receives an alphanumeric value.
- **purchaseAmount :** Corresponds to the purchase amount and receives an numeric value.
- **purchaseDate :** Corresponds to the purchase date and receives an formatted value with the structure ** "DD-MM-YYYY" **.

In case of omitting any values or if the format of the value does not correspond to the expected values, it would return an error message indicating the source of the same. To all these cases will return a status **400**, and the error messages are the followings:


|PARAMETER                |CODE                          |MESSAGE                        |
|----------------|-------------------------------|-----------------------------|
|id|`-1`            |The value of the parameter 'id' is require.           |
|customer          |`-2`            |The value of the parameter 'customer' is required.            |
| purchaseAmount        |`-3`|The value of the parameter 'purchaseAmount' is required.|
|purchaseDate          |`-4`|The value of the parameter 'purchaseDate' is required.|

In case of success would return a status **201**, a code **0** and the message **"The transaction was successfully created"**. If the transaction to record already exist, the WS would return a status **400**, a code **-8** and the message **"The entered transaction already exist"**.

In order to consume the URL **/pointAppWs/transaction** we can use the **curl** application. In this case the Rest WS was deployed locally and listening by the default port **8080**.

`curl -v -d "{\"id\":19,\"customer\":\"Client-1\", \"purchaseAmount\": 100, \"purchaseDate\": \"12-10-2021\"}"   -H "Content-Type: application/json" http://localhost:8080/pointsAppWS/transaction`

The response of the before request is the following:
	   
`POST /pointsAppWS/transaction HTTP/1.1`\
 `Host: localhost:8080`\
 `User-Agent: curl/7.55.1`\
 `Accept: */*`\
 `Content-Type: application/json`\
 `Content-Length: 84`

 `upload completely sent off: 84 out of 84 bytes`\
 `HTTP/1.1 201`\
 `Content-Type: application/json`\
 `Transfer-Encoding: chunked`\
`Date: Sun, 28 Nov 2021 17:56:57 GMT`

`{"status":201,"code":0,"message":"The transaction was successfully created"}`

The following execution, the parameter **id** is missing so that a message error will be returned.

`curl -v -d "{\"customer\":\"Client-1\", \"purchaseAmount\": 100, \"purchaseDate\": \"12-10-2021\"}" -H "Content-Type: application/json" http://localhost:8080/pointsAppWS/transaction`

`POST /pointsAppWS/transaction HTTP/1.1`\
`Host: localhost:8080`\
`User-Agent: curl/7.55.1`\
`Accept: */*`\
`Content-Type: application/json`\
`Content-Length: 76`

`upload completely sent off: 76 out of 76 bytes`\
`HTTP/1.1 400`\
`Content-Type: application/json`\
`Transfer-Encoding: chunked`\
`Date: Sun, 28 Nov 2021 18:45:00 GMT`\
`Connection: close`

`{"status":400,"code":-1,"message":"The value of the parameter 'id' is required"}`

If the transaction to process was already recorded so we can get the following message.

`curl -v -d "{\"id\":19,\"customer\":\"Client-1\", \"purchaseAmount\": 100, \"purchaseDate\": \"12-10-2021\"}" -H "Content-Type: application/json" http://localhost:8080/pointsAppWS/transaction` 

`POST /pointsAppWS/transaction HTTP/1.1`\
`Host: localhost:8080`\
`User-Agent: curl/7.55.1`\
`Accept: */*`\
`Content-Type: application/json`\
`Content-Length: 84`

`* upload completely sent off: 84 out of 84 bytes`\
`HTTP/1.1 400`\
`Content-Type: application/json`\
`Transfer-Encoding: chunked`\
`Date: Sun, 28 Nov 2021 19:01:07 GMT`\
`Connection: close`

`{"status":400,"code":-8,"message":"The entered transaction already exist : 19"}`
______ 
**HTTP METHOD: GET**
**URL: /pointsAppWS/transactions**

This endpoint is responsible of consulting the transactions that were already recorded. In case of no exist any transactions the service would return an error indicating it.

In case of success would return a status **200**, and the list of transactions tha were recorded. If there is not any transaction recorded the WS would return a status **400**, a code **-9** and the message **"There are not transactions to process"**.

Next, a case of getting a list of transactions.

`curl -v http://localhost:8080/pointsAppWS/transactions`

Response returned by the server.

`GET /pointsAppWS/transactions HTTP/1.1`\
`Host: localhost:8080`\
`User-Agent: curl/7.55.1`\
`Accept: */*`

`HTTP/1.1 200`\
`Content-Type: application/json`\
`Transfer-Encoding: chunked`\
`Date: Sun, 28 Nov 2021 19:23:55 GMT`

`[{`\
`  "id":"19",`\
`  "customer":"Client-1",`\
`  "purchaseAmount":100.0,`\
`  "purchaseDate":"12-10-2021"`\
`  },`\
`  {`\
`  "id":"9",`\
`  "customer":"Client-1",`\
`  "purchaseAmount":100.0,`\
`  "purchaseDate":"12-10-2021"`\
`  }]`

______  
**HTTP METHOD: DELETE**\
**URL: /pointsAppWS/transaction/{id}**

This endpoint is responsible for deleting an existing transaction according to the transaction value **{id}**. If the transaction id exists, the **WS** will return the information regarding the queried transaction.

 `curl -X DELETE http://localhost:8080/pointsAppWS/transaction/19`
 
`{`\
`"status":200,`\
`"code":0,`\
`"message":"The transaction was successfully deleted"`\
`}`

In case of not having information, it would return an error message.

`curl -X DELETE http://localhost:8080/pointsAppWS/transaction/29`

`{`\
`"status":400,`\
`"code":-7,`\
`"message":"The entered transaction does not exist : 29"`\
`}`
______
**HTTP METHOD: PUT**\
**URL: /pointsAppWS/transaction/{id}**

This endpoint is responsible for updating an existing transaction according to the transaction value **{id}**. If the transaction id exists, **WS** will update the information indicated in the request body.

In the following scenario, the transaction will be updated with respect to id **20**, returning a status **200**, a code **0** and the message **"The transaction was updated successfully"** .

First, we will create the transaction.

`curl -v -d "{\"id\":20,\"customer\":\"Client-1\", \"purchaseAmount\": 100, \"purchaseDate\": \"12-10-2021\"}" -H "Content-Type: application/json" http://localhost:8080/pointsAppWS/transaction`

Response received.

`{`\
`"status":201,`\
`"code":0,`\
`"message":"The transaction was successfully created"`\
`}`

Now, we will get the list of transactions.

`curl http://localhost:8080/pointsAppWS/transactions`

Response received.

`[{`\
`"id":"20",`\
`"customer":"Client-1",`\
`"purchaseAmount":100.0,`\
`"purchaseDate":"12-10-2021"`\
`}]`

Here we can see the registered transaction. To update the current transaction, we will change the value of the parameters **purchaseAmount** and **purchaseDate**, with the corresponding values **200** and **"02-10-2021"** respectively. The customer's name will remain the same.

`curl -X PUT  -d "{\"customer\":\"Client-2\", \"purchaseAmount\": 200, \"purchaseDate\": \"02-10-2021\"}" -H "Content-Type: application/json" http://localhost:8080/pointsAppWS/transaction/20`

Response received.

`{`\
`"status":200,`\
`"code":0,`\
`"message":"The transaction was successfully updated"`\
`}`

We get again the result and can see the new updated values.

`curl http://localhost:8080/pointsAppWS/transactions`

Response received.

`[{`\
`"id":"20",`\
`"customer":"Client-2",`\
`"purchaseAmount":200.0,`\
`"purchaseDate":"02-10-2021"`\
`}]`

In case of not existing the transaction to update, the table will show the error message to return.
 
|STATUS                |CODE                          |MESSAGE                        |
|----------------|-------------------------------|-----------------------------|
|400|`-7`            |The entered transaction does not exist.   

`curl -X PUT -d "{\"customer\":\"Client-2\", \"purchaseAmount\": 200, \"purchaseDate\": \"02-10-2021\"}" -H "Content-Type: application/json" http://localhost:8080/pointsAppWS/transaction/22`

`{`
`"status":400,`
`"code":-7,`
`"message":"The entered transaction does not exist : 22"`
`}`
______
**HTTP METHOD: GET**   
**URL: /pointsAppWS/points/month/{customer}**

This endpoint is in charge of consulting the points earned and accumulated by the client grouped by month. In case the **{customer}** does not exist, the **WS** will return a message indicating that the **{customer}** queried does not exist.

|STATUS                |CODE                          |MESSAGE                        |
|----------------|-------------------------------|-----------------------------|
|400|`-6`| The required customer does not exist| 

`curl  http://localhost:8080/pointsAppWS/points/month/Client-3`

Response received.

`{`\
`"status":400,`\
`"code":-6,`\
`"message":"The required customer does not exist : Client-3"`\
`}`

On success, it will return a **200 status** and the list of points earned grouped by month.

`curl -v http://localhost:8080/pointsAppWS/points/month/Client-2`

Response received.

`GET /pointsAppWS/points/month/Client-2 HTTP/1.1`\
`Host: localhost:8080`\
`User-Agent: curl/7.55.1`\
`Accept: */*`

`HTTP/1.1 200`\
`Content-Type: application/json`\
`Transfer-Encoding: chunked`\
`Date: Sun, 28 Nov 2021 21:57:38 GMT`

`[{`\
`"customer":"Client-2",`\
`"points":[`\
`	{`\
`	"month":7,`\
`	"points":100`\
`	},`\
`	{`\
`	"month":9,`\
`	"points":35`\
`	},`\
`	{`\
`	"month":10,`\
`	"points":250`\
`	}`\
`]}`

______
**HTTP METHOD: GET**\
**URL: /pointsAppWS/points/total/{customer}**

This endpoint is in charge of consulting the total points earned and accumulated by the client. In case the **{customer}** does not exist, the **WS** will return a message indicating that the **{customer}** queried does not exist.

|STATUS                |CODE                          |MESSAGE                        |
|----------------|-------------------------------|-----------------------------|
|400|`-6`| The required customer does not exist| 

`curl  http://localhost:8080/pointsAppWS/points/total/Client-3`

Response received.

`{`\
`"status":400,`\
`"code":-6,`\
`"message":"The required customer does not exist : Client-3"`\
`}`

On success, it will return a **200 status** and the total points earned.

`curl -v http://localhost:8080/pointsAppWS/points/total/Client-2`

Response received.

`GET /pointsAppWS/points/total/Client-2 HTTP/1.1`\
`Host: localhost:8080`\
`User-Agent: curl/7.55.1`\
`Accept: */*`

`HTTP/1.1 200`\
`Content-Type: application/json`\
`Transfer-Encoding: chunked`\
`Date: Sun, 28 Nov 2021 22:13:53 GMT`

`{`\
`"customer":"Client-2",`\
`"points":385`\
`}`


