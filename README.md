# E-Commerce-API

This project provides the API for an E-Commerce application that supports both admin and user functionalities. 
The API enables various operations like user login, product management, reviews, reports, and more.

## Project Objectives

The main goal of this project is to provide an API that allows both users and admins to manage e-commerce operations efficiently. 
Users can log in, sign up, manage bookmarks, products, reviews, and reports. Admins can manage product categories and perform administrative tasks.

## Features

### **AuthService**
Handles user authentication:
- **signUpUser**: Allows new users to register with their details.
- **loginUser**: Authenticates users and returns a JWT token.
- **refreshToken**: Refreshes the JWT token when it expires.

### **BookmarksService**
Manages user bookmarks for products:
- **showAllMarkedProducts**: Retrieves all the products that a user has marked as bookmarks.
- **deleteProductsMark**: Removes a product from the user's bookmarks.

### **CategoriesService**
Handles product category management:
- **getAllCategories**: Retrieves all product categories.
- **addCategories**: Adds a new category.
- **deleteCategories**: Removes an existing category.

### **ProductsService**
Handles product-related operations:
- **getAllProducts**: Retrieves all products in the system.
- **getAllProductsByCategory**: Retrieves products filtered by category.
- **newProduct**: Adds a new product to the system.
- **addProductsToBookmarks**: Allows users to add products to their bookmarks.
- **editProduct**: Edits the details of an existing product.
- **deleteProduct**: Deletes a product from the system.

### **ReportsService**
Handles product reports:
- **newReport**: Allows users to create a new report for a product.
- **getAllReports**: Retrieves all reports in the system.
- **setStatusReport**: Updates the status of a report.
- **deleteReport**: Deletes a report.

### **ReviewsService**
Handles product reviews:
- **showReviews**: Displays reviews for a specific product.
- **addReview**: Allows users to add a review to a product.

### **UsersService**
Handles user account management:
- **editUserName**: Allows users to change their username.
- **editUserNumber**: Allows users to update their contact number.
- **changePassword**: Allows users to change their password.
- **deleteAccount**: Allows users to delete their account.

## Technologies Used

- **Java 17**: Programming language for building the application.
- **Spring Boot**: Backend framework for creating RESTful APIs.
- **Spring Data JPA**: Simplifies database interaction and object-relational mapping.
- **Spring Security**: Provides authentication and authorization mechanisms.
- **JWT (JSON Web Token)**: Used for secure token-based authentication.
- **PostgreSQL**: Relational database for storing users, roles, and order data.
- **MongoDB**: NoSQL database for storing products, reviews, bookmarks and reports.
- **Pagination**: For efficient retrieval of large datasets.
- **Swagger UI**: For generating API documentation and testing endpoints.

