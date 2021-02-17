# Miro Widget

## Prerequisite:
- Java JDK 15
- Docker (in the case you directly want to run application)

## Running The Application:
The application can be run from within a modern Java IDE with the entry class being WidgetApplication.java. 
The application can also be run using Maven by executing the command: mvn spring-boot:run in the root folder of the application.
The application is dockerized, and you can run dockerrun.sh file if you have docker installed on machine.

## REST Information

The application starts up on port 8080. By using http://localhost:8080/widget and applying proper http method you can perform different operation like create, update, delete, get, get all or delete all as requested.
When a new widget is created a new UUID (unique universal identifier) assigned to that widget and send back to client with information of widget successful creation. Client the can use this UUID to perform certain operations
like update, get by an id, delete by an id etc. The application is build on HTTP standards using appropriate Http methods
which put our Rest application on maturity level of 2 of Richardson maturity model. 

## MIRO QUEUE
Miro queue is a customized smart datastructures build while using a combination MIN Heap and HashMap
for solving our Z-INDEX problem in minimal manner. MIRO Queue is intelligent enough to predict first if there
is a new widget without Gap it will make itself update and shift widgets accordingly with time complexity of Log(N).
The prediction for existing values in the case of removal and update requests are faster O(1).

## Thread safety
As I am using my own custom datastructures to solve the problem, so on repository layer I applied ReentrantReadWrite locks 
which help us to apply exclusive write locks and shared read locks (in the case write lock is not active).
Another better approach which I can apply is to use StampedLock optimistic locking for read locks, but as 
they are non-reentrant, so I decided to move with my first choice.

## Map structs
The application is build while keeping single responsibility principle in mind, and I kept concerns of request and responses
separately from domain objects. Due to that I need to convert the request objects to domain/entities which is not a good to do
manually, so I decided to use map structs which are super flexible to help and germinate implementations on run-time.

## Docker
The application contain docker file and run shell script as well so in the case if docker is running on your system.
You just need to run dockerrun.sh file and boom application can be accessible on port 8080

## Testing
The application is made with the spring of TDD, and that is the reason it contains a lot of unit tests.
Integration test are also added. Unit and integration tests both are added in seperate directories you can find.

## Technologies
- Java 15
- Spring boot & Rest
- DDD & TDD
- Junit5
- Mockito And MockMVC
- Openpojo
- Apache commons
- Lombok
- MapStruct  
- Reentrant Read And Write Locking 
- Initialization On Demand Holder Pattern
- Builder Pattern
- Method Chaining
- Single Responsibilities
- Docker

## Clean And Build
mvn clean install

### Build And Test
mvn clean test

#### Swagger URL
please see the link for detail of all APIs
http://localhost:8080/swagger-ui/#/


