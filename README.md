# Password Manager  

## Overview  
The **Password Manager** is a secure and efficient application designed to generate, evaluate, store, and manage passwords with strong encryption. It ensures seamless credential management through a user-friendly interface while prioritizing security.  

## Features  
- ✅ **Generate Strong Passwords** – Automatically create secure passwords.  
- ✅ **Evaluate Password Strength** – Check the security level of user-generated passwords.  
- ✅ **Secure Storage** – Encrypt and store credentials safely.  
- ✅ **User-Friendly Interface** – Simple and intuitive design for easy management.  
- ✅ **Efficient Backend** – Robust functionality using Java and Spring Boot.  

## Tech Stack  
- **Backend:** Java, Spring Boot, MySQL, Maven  
- **Frontend:** HTML, CSS  
- **Security:** Encryption for password storage  

## Installation  

### Prerequisites  
Ensure you have the following installed:  
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
- [Spring Boot](https://spring.io/projects/spring-boot)  
- [MySQL](https://www.mysql.com/)  
- [Maven](https://maven.apache.org/)  

### Setup  
1. **Clone the repository:**  
   ```sh
   git clone https://github.com/your-username/password-manager.git
   cd password-manager

## Configure MySQL Database  
Update `application.properties` with your database credentials:  

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/password_manager
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
## Build and Run the Application  

```sh
mvn clean install
mvn spring-boot:run
```

## Access the Application
Open http://localhost:8080 in your browser.

## Usage
- Generate a Secure Password: Click "Generate" to create a strong password.
- Evaluate Password Strength: Enter a password to check its security score.
- Store Credentials Securely: Save and manage passwords with encryption.
