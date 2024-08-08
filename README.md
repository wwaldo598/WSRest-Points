# WSRest-Points
Project aimed at developing a Rest WS to calculate rewarded points.

# How to build the pointsAppWS Web Service
It is a Java Maven project developed through the SprintBoot framework.

To generate the service corresponding to the **WS pointsAppWS** it is only necessary to clone the repository through Git or any other client tool that Git supports. The current branch corresponds to the **DEVELOP** branch to be able to develop or modify something. The **MAIN** branch should be used only when WS has been released. This version is still under development, however, the **DEVELOP** branch fulfills the purpose required by the test.

Like any maven project, once the repository has been cloned, it must be compiled and installed through the selected editor that supports maven, or through the command line, for example by executing the command: **mvn compile package** or **mvn install**.

To run the unit tests, they were developed through JUnit. It is possible to run the unit tests through the **JUniTest** incorporated in the chosen editor, or through the command line: **mvn test**

Once the .jar file corresponding to the **pointsAppWs** service has been generated, it will be available in the target directory.

For its execution, it is only necessary to execute the snapshot version as a .jar application from the command line or through the same editor if it allows executing a springboot application. Once the .jar is executed, it will be executed by default in position **8080**; however, it is possible to specify another port.

Once the WS is running, a log directory will be created in the directory where WS **pointsAppWs** is running. For this, the configured level is **INFO** and it will store the log traces according to this level; however, it is possible to set a different level in the **application.properties** file and recompile to generate a new .jar with a defined logging level. 

To consume the WS, it is only necessary to configure its different endpoints and consume them all from any application or tool that allows consuming **Rest WS**. During the explanation on how to consume the different Urls the application **curl** is used to show how to make a request. About **curl**, its use is simple and it will be similar when using other tools such as **Postman**.

# Description
The Web Service **pointsAppWS**, is a Rest WS that has the purpose of calculating the total points earned according to the amount of purchase that a customer has made.

The **pointsAppWS** offers different endpoints that can be consumed by a client application such as curl, SoapUI or Postman.
The format for sending and receiving is a Json format. 

## WS Rest endpoints 
As mentioned above, this WS offers the different endpoints that can be consumed from a client application like curl, SoapUI or Postman. It could also be consumed by any application capable of consuming a Rest WS, regardless of the programming language in which the application was created.

The available URLs are as follows:

|HTTP METHOD                |URL                          |DESCRIPTION                        |
|----------------|-------------------------------|-----------------------------|
|POST|`/pointsAppWS/transaction`            |Creates a new transaction.           |
|GET|`/pointsAppWS/transactions`            |Obtains a list the transaction recorded.           |
|DELETE|`/pointsAppWS/transaction/{id}`            |Deletes a transaction according to the transaction id.           |
|UPDATE|`/pointsAppWS/transaction/{id}`            |Updates a transaction according to the transaction id. |
|GET|`/pointsAppWS/points/month/{customer}`            |Obtains a list of the points earned accumulated monthly by the client. |
|GET|`/pointsAppWS/points/total/{customer}`            |Obtains the total of earned points accumulated by the customer. |

Next section it will explain how to use these enpoints.
   
______ 
**HTTP METHOD: POST**\
**URL: /pointsAppWS/transaction**

This endpoint is responsible for creating a new transaction. 

The information to be entered is in **Json** format and the following parameters are required:

- **id :** Corresponds to the transaction id and receives an alphanumeric value.
- **customer :** Corresponds to the customer name and receives an alphanumeric value.
- **purchaseAmount :** Corresponds to the purchase amount and receives an numeric value.
- **purchaseDate :** Corresponds to the purchase date and receives an value formatted with the structure **"DD-MM-YYYY"**.

In case of omitting a value or if the format of the value does not correspond to the expected values, it would return an error message indicating its origin. All these cases will be returned a **400** status, and the error messages are as follows:


|PARAMETER                |CODE                          |MESSAGE                        |
|----------------|-------------------------------|-----------------------------|
|id|`-1`            |The value of the parameter 'id' is require.           |
|customer          |`-2`            |The value of the parameter 'customer' is required.            |
| purchaseAmount        |`-3`|The value of the parameter 'purchaseAmount' is required.|
|purchaseDate          |`-4`|The value of the parameter 'purchaseDate' is required.|

On success, it will return a status 201. If the transaction to be recorded already exists, the WS will return an error message.

|STATUS                |CODE                          |MESSAGE                        |
|----------------|-------------------------------|-----------------------------|
|201|` 0`            |The transaction was successfully created   |
|400|`-8`            |The entered transaction already exist   |

To consume the URL **/pointAppWs/transaction** we can use the **curl** application. In this case, the Rest WS is running locally and listening on the default port **8080**.

`curl -v -d "{\"id\":19,\"customer\":\"Client-1\", \"purchaseAmount\": 100, \"purchaseDate\": \"12-10-2021\"}"   -H "Content-Type: application/json" http://localhost:8080/pointsAppWS/transaction`

The response to the above request is as follows:
	   
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

In the next run, the **id** parameter is missing, so an error message will be returned.

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

If the transaction to process was already registered then we can receive the following message.

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
**HTTP METHOD: GET**\
**URL: /pointsAppWS/transactions**

This endpoint is in charge of consulting the transactions that have already been recorded. If there is no transaction, the service will return an error indicating it.

On success, a **200** status and the list of transactions that were posted will be returned. If no transaction is recorded, WS will return an error message as shown in the table below.

|STATUS                |CODE                          |MESSAGE                        |
|----------------|-------------------------------|-----------------------------|
|400|`-9`            |There are not transactions to process.   |


Getting a list of transactions.

`curl -v http://localhost:8080/pointsAppWS/transactions`

Response received.

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


|STATUS                |CODE                          |MESSAGE                        |
|----------------|-------------------------------|-----------------------------|
|400|`-7`            |The entered transaction does not exist.   |


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


