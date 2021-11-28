# overactive
Project oriented to develop a Rest WS to calculate rewarded points.
## WS Rest endpoints 
The WS offers the different endpoints which could be consume from a client application
as curl, SoapUI or Postman.

   
** Http-method: POST **      
** URL: /pointsAppWS/transaction **

This endpoint is responsible of creating a new transaction. 

In case of success would return a status ** 201 **, a code ** 0 ** and the message ** "The transaction was successfully created" **. If the transaction to record already exist and would return a status ** 400 **, a code ** -8 ** and the message ** "The entered transAmountalready exist" **


______
** Http-method: GET **      
** URL: /pointsAppWS/transaction **

This endpoint is responsible of consulting the transactions that were recorded in before operations. In case of no exist any transactions the service would return an error indicating it.

______
** Http-method: DELETE **      
** URL: /pointsAppWS/transaction/{id} **

This endpoint is responsible of deleting a existing transaction according to the transaction ** {id} ** indicated
as parameter. If the transaction id exist would return the information in relation to the transaction consulted. In case of not having information would return an message error. 
 
______

** Http-method: PUT **      
** URL: /pointsAppWS/transaction/{id} **

This endpoint is responsible of consuming a new transaction.
______


