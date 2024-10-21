# Diabetes App Service Backend

## Project Description

This project is a REST API for user management developed using **Spring Boot**. It offers services for creating, validating, updating, and deleting users, ensuring information security by encrypting passwords with **BCryptPasswordEncoder**.

The project is part of a larger system that will include a **React frontend** and an **artificial intelligence** component for enhanced user interaction and analytics. Currently, only the backend is implemented and documented.

The backend allows common operations such as adding new users, verifying their existence, validating credentials, updating passwords, and deleting accounts. The API is built using the **Spring Boot** framework, with **JPA** for data persistence.

## Technologies Used

- **Java 17**: Main programming language.
- **Spring Boot 3**: Framework for building the application.
- **Spring Data JPA**: For data persistence and database access.
- **BCryptPasswordEncoder**: For password encryption.
- **Lombok**: Code simplification using annotations.
- **MySQL** (or any JPA-compatible database): Database for user persistence.

## Project Structure

The project is divided into different packages for better code organization:

- `model`: Contains the `User` class that represents the user entity in the database.
- `repository`: Contains the `UserRepository` interface for accessing user data.
- `service`: Contains the business logic implemented in `UserServiceImpl` and the `UserService` interface.
- `controller`: Contains the `UserController` that exposes the API REST endpoints.
- `config`: Contains security configuration, including `BCryptPasswordEncoder`.

## Features

The API provides the following features:

### 1. Create a New User

- **Endpoint**: `/user/add`
- **HTTP Method**: `POST`
- **Description**: Adds a new user by providing a `username` and a `password`.
- **Request Body**:
  ```json
  {
    "username": "testuser",
    "password": "testpassword"
  }
  ```
- **Response**: `New user created`

### 2. Validate User Password

- **Endpoint**: `/user/validate`
- **HTTP Method**: `POST`
- **Description**: Validates if the provided password is correct for the specified user.
- **Parameters**: `username`, `password`
- **Response**: `true` or `false`

### 3. Check if User Exists

- **Endpoint**: `/user/exists`
- **HTTP Method**: `GET`
- **Description**: Checks if a user with the provided username exists in the database.
- **Parameter**: `username`
- **Response**: `true` or `false`

### 4. Update User Password

- **Endpoint**: `/user/update-password`
- **HTTP Method**: `PUT`
- **Description**: Updates the password of a user.
- **Parameters**: `username`, `newPassword`
- **Response**: `Password updated successfully` or `User not found`

### 5. Delete a User

- **Endpoint**: `/user/delete`
- **HTTP Method**: `DELETE`
- **Description**: Deletes a user if the provided credentials are correct.
- **Parameters**: `username`, `password`
- **Response**: `User deleted successfully` or `Invalid username or password`

## Main Classes

### 1. `User`
The `User` class is the entity representing a user in the database. It includes attributes such as `id`, `username`, and `password`, and uses **JPA** annotations for entity mapping.

- **Attributes**:
  - `id`: Unique identifier of the user (auto-generated).
  - `username`: Username, must be unique and not null.
  - `password`: Encrypted user password.

### 2. `UserRepository`
Interface that extends `JpaRepository` and provides data access methods for users. It includes a custom method to find users by username (`findByUsername`).

### 3. `UserService` and `UserServiceImpl`
The `UserService` interface defines the service methods for user operations such as creating, validating, verifying, updating, and deleting users. The implementation `UserServiceImpl` contains the business logic, including the use of **BCryptPasswordEncoder** for password encryption.

### 4. `UserController`
REST controller that exposes the endpoints defined above. It uses `UserService` to handle incoming requests and manage responses.

### 5. `SecurityConfig`
Configuration class that defines a `BCryptPasswordEncoder` bean to be used for encrypting user passwords.

## Testing with Postman

To test the different endpoints using **Postman**:

1. **Create a User**: 
   - **Method**: `POST`
   - **URL**: `http://localhost:8085/user/add`
   - **Body**: 
     ```json
     {
       "username": "testuser",
       "password": "testpassword"
     }
     ```

2. **Validate Password**:
   - **Method**: `POST`
   - **URL**: `http://localhost:8085/user/validate`
   - **Params**: `username=testuser`, `password=testpassword`

3. **Check User Existence**:
   - **Method**: `GET`
   - **URL**: `http://localhost:8085/user/exists?username=testuser`

4. **Update Password**:
   - **Method**: `PUT`
   - **URL**: `http://localhost:8085/user/update-password`
   - **Params**: `username=testuser`, `newPassword=newpassword123`

5. **Delete User**:
   - **Method**: `DELETE`
   - **URL**: `http://localhost:8085/user/delete`
   - **Params**: `username=testuser`, `password=newpassword123`

## Security Configuration

The project uses **BCryptPasswordEncoder** to secure passwords. The encoder is defined in the `SecurityConfig` class and is used to encrypt passwords before storing them and to validate user-provided passwords.

## Prerequisites

- **Java 17**
- **Maven**
- **MySQL Database** (or another compatible database)

## Instructions to Run the Project

1. **Clone the Repository**.
2. **Configure the Database**: Modify the properties in `application.properties` to connect to your MySQL database.
3. **Build and Run**: Use Maven to build and run the project:
   ```bash
   mvn spring-boot:run
   ```
4. **Test with Postman**: Use the URLs and methods provided to test the functionalities.

## License

This project is under the **MIT License**. You are free to use it for personal and commercial purposes.
