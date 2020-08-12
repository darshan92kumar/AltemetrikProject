# AltemetrikProject
Project created for company purpose

# Get Token

 
URL : localhost:8080/authenticate


Body:  
{
    "username": "darshan",
    "password": "password"
}

Response:
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXJzaGFuIiwiZXhwIjoxNTk3Mjc3NTgLmvZQjWs9QbIjnntw4jXmO8m5BpSOmPpAhK2EOagQtTKlABY-u71mojKhy_lLw"
}


# Validate token by accessing a restricted API (Note it is a bearer based Token)

URL : localhost:8080/hello
under headers :
Key : Authorization
Value : "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXJzaGFuIiwiZXhwIjoxNTk3Mjc3NTgLmvZQjWs9QbIjnntw4jXmO8m5BpSOmPpAhK2EOagQtTKlABY-u71mojKhy_lLw"


Reponse : 
{
    "Authenticated-User": "darshan",
    "Message": "Welcome!"
}
