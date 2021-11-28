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

______ 
**HTTP METHOD: GET**
**URL: /pointsAppWS/transaction**

This endpoint is responsible of consulting the transactions that were recorded in before operations. In case of no exist any transactions the service would return an error indicating it.

______  
**HTTP METHOD: DELETE**
**URL: /pointsAppWS/transaction/{id}**

This endpoint is responsible of deleting a existing transaction according to the transaction **{id}** indicated
as parameter. If the transaction id exist would return the information in relation to the transaction consulted. In case of not having information would return an message error. 
 
______
**HTTP METHOD: PUT**   
**URL: /pointsAppWS/transaction/{id}**

This endpoint is responsible of consuming a new transaction.

______
**HTTP METHOD: GET**   
**URL: /pointsAppWS/points/month/{customer}**

This endpoint is responsible of ...
______
**HTTP METHOD: GET**   
**URL: /pointsAppWS/points/total/{customer}**

This endpoint is responsible of ...

