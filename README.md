# SWIFT-RESTful

RESTful api to use api for SWIFT codes

## Usage

Setup postgreSQL database with:
- port: 5432
- name: banks_database
- username: server_username
- password: server_password

Example usage:
```
sudo docker run --name postgres-db -e POSTGRES_USER=server_username -e POSTGRES_PASSWORD=server_password -e POSTGRES_DB=banks_database -p 5432:5432 -d postgres
```

Then start Spring Boot process:
```
cd SWIFT/ && ./gradlew bootRun
```

Or start Tests:
```
cd SWIFT/ && ./gradlew test
```

Tests are running on in memory h2 db so there is no need to create database container.

## Endpoins

Every endopoint is available on http://localhost:8080 by default

The app exposes:
### GET: /v1/swift-codes/{swift-code}

Endpoint for retrieving details of a single SWIFT code whether for a headquarters or branches.


#### Response Structure:

for headquarter:
```json
{
    "address": string,
    "bankName": string,
    "countryISO2": string,
    "countryName": string,
    "isHeadquarter": bool,
    "swiftCode": string
    “branches”: [
        {
            "address": string,
            "bankName": string,
            "countryISO2": string,
            "isHeadquarter": bool,
            "swiftCode": string
        },
        {
            "address": string,
            "bankName": string,
            "countryISO2": string,
            "isHeadquarter": bool,
            "swiftCode": string
        }, . . .
    ]   
}
```

And for Branch:
```json
{
    "address": string,
    "bankName": string,
    "countryISO2": string,
    "countryName": string,
    "isHeadquarter": bool,
    "swiftCode": string
}
```

### GET: /v1/swift-codes/country/{countryISO2code}

endpoint returns all SWIFT codes with details for a specific country

#### Response Structure:
```json
{
    "countryISO2": string,
    "countryName": string,
    "swiftCodes": [
        {
            "address": string,
    		 "bankName": string,
    		 "countryISO2": string,
    		 "isHeadquarter": bool,
    		 "swiftCode": string
        },
        {
            "address": string,
    		 "bankName": string,
    		 "countryISO2": string,
    		 "isHeadquarter": bool,
    		 "swiftCode": string
        }, . . .
    ]
}
```

### POST: /v1/swift-codes

Endpoint for adding new SWIFT code entry to the db for a specific country

#### Request Body:
```json
	{
    "address": string,
    "bankName": string,
    "countryISO2": string,
    "countryName": string,
    “isHeadquarter”: bool,
    "swiftCode": string,
}
```

#### Response Structure:
```json
{
    "message": string,
}
```

### DELETE: /v1/swift-codes/{swift-code}

Endpoint for deleting swift-code data if swiftCode matches the one in the database.

**Note**: The endpoint does not remove information about stuff that could be used by other banks (e.g. Address, Name Time zone)

#### Response Structure:
```json
{
    "message": string,
}
```
