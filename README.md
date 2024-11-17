
# Task Management API

A RESTful API for managing tasks built with Spring Boot.

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/2504pratik/TaskManager.git
cd TaskManager-main
```

2. Build the project:
```bash
mvn clean install
```
3. Run tests:
```bash
mvn test
```
This will execute all tests from the TaskControllerTest file located in the src/test/java/controller folder.
4. Run the application:
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8081`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/login | Generate JWT token for authentication |
| GET | /tasks | Get all tasks |
| GET | /tasks/{id} | Get task by ID |
| POST | /tasks | Create a new task |
| PUT | /tasks/{id} | Update an existing task |
| DELETE | /tasks/{id} | Delete a task |
| PATCH | /tasks/{id}/complete | Mark a task as complete |

### Request/Response Examples

#### Generate JWT token for authentication

```http
POST http://localhost:8081/auth/login
Content-Type: application/json

{
    "username": "admin",
    "password": "password"
}
```
#### Response
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Using the Token for Protected Endpoints:

- Copy the token from the login response
- Add it to the Authorization header for all other requests:
    - Header Key: Authorization
    - Header Value: Bearer eyJhbGciOiJIUzI1NiJ9...

#### Create a Task
```http
POST /tasks
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...

{
    "title": "Complete project",
    "description": "Finish the task management API",
    "dueDate": "2024-12-31"
}
```

#### Response
```json
{
    "id": 1,
    "title": "Complete project",
    "description": "Finish the task management API",
    "dueDate": "2024-12-31",
    "status": "PENDING",
    "createdAt": "2024-03-17T10:30:00",
    "updatedAt": "2024-03-17T10:30:00"
}
```

## Design Decisions

1. **Architecture**: Used a layered architecture (Controller -> Service -> Repository) for better separation of concerns.
2. **JWT Authentication**: Stateless authentication using JWT tokens
3. **DTOs**: Implemented DTOs to separate API contracts from internal domain models.
4. **Validation**: Used Jakarta Validation for input validation.
5. **Exception Handling**: Implemented global exception handling for consistent error responses.
6. **Testing**: Added unit tests