##URL's and function
Use Any Rest clients(REST API CLIENT,POSTMAN CLIENT etc) to access the REST end points of the application 
1.http://localhost:8080/api/inventory and POST Request method helps you to add a new inventory and persist the data in mysql db
    For example your Json input should as 
    
    REQUEST BODY:		

{
  "productName": "tv",
  "locationName": "India",
  "quantity": 3
}  


2. http://localhost:8080/api/inventory and GET Request method helps to fetch the persisted data from mysql db 
  	
			
3. http://localhost:8080/api/inventory/getByProductName/tv and GET Request method helps to fetch the persisted data from mysql db with respect to productName.

4. http://localhost:8080/api/inventory/getByLocationName/india and GET Request method helps to fetch the persisted data from mysql db with respect to locationName.  		
	
5. http://localhost:8080/api/inventory/TV/india and GET Request method helps to fetch the persisted data from mysql db with respect to productName and locationName.  		

6. http://localhost:8080/api/inventory/getQuantity/tv/india and GET Request method helps to fetch the corresponding quantity from mysql db with respect to productName and locationName.  		
	  		
7. http://localhost:8080/api/inventory/updateQuantity and PUT Request method helps to update the quantity.

   REQUEST BODY:		
{
	 "productName": "tv",
  "locationName": "India",
  "quantity": 5
}
	
#DDL SCRIPT FOR INVENTORY TABLE:

create table inventory(product_name varchar(20),location_name varchar(20),quantity bigint unsigned);
	
#Two Spring profiles are present for the app.
1. dev	- Dev Environment where RabbitMq will be active.
2. ft	- Functional Test Environment where RabbitMq will not be active and message to order will not be posted.

#Command to Run the Application with Profiles:
mvn spring-boot:run -Dspring.profiles.active="dev"

# If you want to RabbitMq,
mvn spring-boot:run -Dspring.profiles.active="dev,rmq"# inventory-app
