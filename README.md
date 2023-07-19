Automated Irrigation Service
This service provides automated irrigation for plots based on their moisture levels. It includes 4 REST APIs for adding, editing, listing, and retrieving plots, as well as a method to irrigate plots based on their moisture levels. The service uses a map to hold the plots and their moisture levels, and a scheduled method to open and close valves to irrigate the plots.

Technologies
Java 11
Spring Boot 2.5.3
Maven
JUnit 5
PostegresSQL server
Installation and Setup
The used Database is PostegresSQL server
Clone the repository.
Build the project using Maven: mvn clean install.
Start the application: java -jar target/automated-irrigation-service.jar.
The application will start on port 8083 by default.

Usage
Add Plot
Adds a new plot to the map.

URL: /plots
Method: POST
Request Body:
json
Copy
{
"name": "Plot 1",
"moistureThreshold": 50,
"sensorId": 1,
"valveId": 1,
"timeSlot": 5
}
```
Response Body:
json
Copy
{
  "id": 1,
  "name": "Plot 1",
  "moistureThreshold": 50,
  "sensorId": 1,
  "valveId": 1,
  "timeSlot": 5,
  "needsIrrigation": false
}
Edit Plot
Edits an existing plot in the map.

URL: /plots/{id}
Method: PUT
Request Body:
Copy
{
  "name": "Plot 2",
  "moistureThreshold": 60,
  "timeSlot": 10
}
Response Body:
json
Copy
{
  "id": 1,
  "name": "Plot 2",
  "moistureThreshold": 60,
  "sensorId": 1,
  "valveId": 1,
  "timeSlot": 10,
  "needsIrrigation": false
}
List Plots
Lists all plots in the map.

URL: /plots
Method: GET
Response Body:
json
Copy
[
  {
    "id": 1,
    "name": "Plot 2",
    "moistureThreshold": 60,
    "sensorId": 1,
    "valveId": 1,
    "timeSlot": 10,
    "needsIrrigation": false
  }
]
Get Plot by ID
Retrieves a plot from the map by ID.

URL: /plots/{id}
Method: GET
Response Body:
json
Copy
{
  "id": 1,
  "name": "Plot 2",
  "moistureThreshold": 60,
  "sensorId": 1,
  "valveId": 1,
  "timeSlot": 10,
  "needsIrrigation": false
}
Irrigate Plots
Sets the needsIrrigation flag for each plot based on its moisture level, and irrigates the plots as needed using the scheduled method.

URL: /irrigate
Method: POST
Response Body:
Copy
{
  "message": "Irrigation started"
}

License
This project is licensed under the MIT License.

Acknowledgements
Third-party libraries: Spring Boot, JUnit 5.
Contributors: Sohail Salem <Sohailsalem93@gmail.com>
