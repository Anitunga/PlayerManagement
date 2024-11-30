# PlayerManagement
A Spring Boot application designed to manage players and their social connections. This project provides RESTful APIs for creating, updating, retrieving, and deleting player data. It also supports adding and managing friendships between players and automatically calculates player levels based on their total points.

Features

Player Management:
Add new players with attributes such as name, username, email, and level.
Automatically calculate player levels (Beginner, Advanced, Expert) based on total points.
Friendship Management:
Add and manage friendships between players.
Retrieve a player's friends list.
Statistics:
Track player statistics, including total points and wins.
RESTful APIs:
Easy-to-use endpoints for seamless integration with other services or frontend applications.

Technologies Used
Spring Boot:
Spring Web
Spring Data JPA
Spring Security (future integration)
MySQL: Database for storing player and friendship data.
Java 21: Ensures modern features and performance.
Spring Cloud OpenFeign: For inter-service communication (future feature).
Maven: Dependency and build management.

Installation
Clone the repository:
git clone https://github.com/your-username/player-management.git
cd player-management

Configure the database connection in src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/shared_db
spring.datasource.username=alain
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update

Contact
For questions or feedback, please contact:
Alain Michel
Email: alain.nitunga@helb-prigogine.be
