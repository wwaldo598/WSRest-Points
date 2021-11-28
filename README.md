# overactive
Project oriented to develop a Rest WS to calculate rewarded points.
## WS Rest endpoints 
The WS offers the different endpoints which could be consume from a client application
as curl, SoapUI or Postman.

   
** Http-method: POST **      
** URL: /pointsAppWS/transaction **

This endpoint is responsible of creating a new transaction.
______
** Http-method: GET **      
** URL: /pointsAppWS/transaction **

This endpoint is responsible of consulting the transactions that were recorded. In case of no exist any transaction
would return a error indicating it. 

______
** Http-method: DELETE **      
** URL: /pointsAppWS/transaction/{id} **

This endpoint is responsible of delete a existed transaction.
______

** Http-method: PUT **      
** URL: /pointsAppWS/transaction/{id} **

This endpoint is responsible of consuming a new transaction.
______


