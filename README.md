# Widget

## Prerequisite:

JDK - 14

Redis-cli 5.0.7

# Build and run integration test with memory profile
mvn clean verify

# Build and Run Integration with db profile
mvn clean verify -Pdb

# Run with in memory implementation  
mvn spring-boot:run -Pmemory

# Application URL
   http://localhost:8080/swagger-ui/#/

# Run with database implementation  
mvn spring-boot:run -Pdb

## Set default rate limit in  redis
    SET widget.default 1000
## Override default rate limit in redis
#### Widget Create API
    SET widget.create [Override Value]
#### Widget Update API
    SET widget.update [Override Value]
#### Widget Delete API
    SET widget.delete [Override Value]
#### Widget Get API
    SET widget.get [Override Value]
#### Widget List API
    SET widget.list [Override Value]
#### Widget List Paging API
    SET widget.list.paging [Override Value]
