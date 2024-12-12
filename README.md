# PlayerManagement
A Spring Boot application designed to manage players and their social connections. This project provides RESTful APIs for creating, updating, retrieving, and deleting player data. It also supports adding and managing friendships between players and automatically calculates player levels based on their total points.

# Features

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
Maven: Dependency and build management.

# Installation
Clone the repository:
```
git clone https://github.com/your-username/player-management.git
```
```
cd player-management
```

### Configure the database connection in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shared_db
spring.datasource.username=alain
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
```

# Player Management Service Documentation

## Table of Contents
1. [Overview](#overview)
2. [API Endpoints](#api-endpoints)
3. [Business Logic](#business-logic)
4. [Database Schema](#database-schema)
5. [System Flow](#system-flow)
6. [Technical Implementation](#technical-implementation)

## Overview

The Player Management Service is responsible for handling player profiles, friend relationships, and player statistics in a gaming platform. It manages player progression through different levels based on their performance in games.

### Key Features
- Player profile management
- Friend relationship handling
- Player statistics tracking
- Automatic level progression
- Integration with Game Service

## API Endpoints

### Player Management

#### Create Player
```http
POST /api/players
Content-Type: application/json
{
"name": "John Doe",
"username": "johndoe",
"email": "john@example.com"
}
```

**Response** (200 OK):
```json
{
"id": 1,
"name": "John Doe",
"username": "johndoe",
"email": "john@example.com",
"level": "BEGINNER",
"totalPoints": 0,
"totalWins": 0
}
```

#### Get Player
```http
GET /api/players/{id}
```

**Response** (200 OK):
```json
{
"id": 1,
"name": "John Doe",
"username": "johndoe",
"email": "john@example.com",
"level": "BEGINNER",
"totalPoints": 0,
"totalWins": 0
}
```

#### Update Player Statistics
```http
PUT /api/players/{id}/stats
Content-Type: application/json
{
"score": 25,
"victory": true
}
```

**Response** (200 OK)

### Friend Management

#### Add Friend
```http
POST /api/friends/{playerId}/{friendId}
```

**Response** (200 OK):
```json
{
"id": 1,
"player": {
"id": 1,
"name": "John Doe"
},
"friendPlayer": {
"id": 2,
"name": "Jane Doe"
}
}
```

#### Get Player's Friends
```http
GET /api/friends/{playerId}
```

**Response** (200 OK):
```json
[
{
"id": 1,
"friendPlayer": {
"id": 2,
"name": "Jane Doe"
}
}
]
```

## Business Logic

### Player Management
- Players start at **BEGINNER** level.
- Automatic level progression based on total points:
  - **BEGINNER:** 0-50 points
  - **ADVANCED:** 51-100 points
  - **EXPERT:** 100+ points
- Statistics are updated after each game completion.
- Username and email must be unique.

### Friend System
- Bidirectional friendship relationships.
- Prevents self-friending.
- Validates both players exist.
- No duplicate friendships allowed.

### Level Progression
The system automatically updates player levels using JPA lifecycle hooks:

```java
@PrePersist
@PreUpdate
public void updateLevel() {
    this.level = LevelType.getLevelByPoints(this.totalPoints);
}
```

## Database Schema

### Player Table
```sql
CREATE TABLE player (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    level VARCHAR(20) NOT NULL DEFAULT 'BEGINNER',
    total_points INT DEFAULT 0,
    total_wins INT DEFAULT 0
);
```

### Friend Table
```sql
CREATE TABLE friend (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    player_id BIGINT NOT NULL,
    friend_player_id BIGINT NOT NULL,
    FOREIGN KEY (player_id) REFERENCES player(id),
    FOREIGN KEY (friend_player_id) REFERENCES player(id),
    UNIQUE KEY unique_friendship (player_id, friend_player_id)
);
```

### Schema Design Rationale
1. **Player Table**
   - Unique constraints on `username` and `email` ensure identity.
   - Default values for new players.
   - Level stored as a string for enum flexibility.
   - Statistics columns for progression tracking.

2. **Friend Table**
   - Composite unique key prevents duplicate friendships.
   - Foreign keys ensure data integrity.
   - Simple structure enables efficient querying.

## System Flow

### Player Creation Flow
1. Client sends `PlayerDTO` with basic information.
2. System validates unique constraints.
3. Creates player with default level and stats.
4. Returns complete player information.

### Statistics Update Flow
1. Game Service sends completion statistics.
2. System updates player's points and wins.
3. Level is automatically recalculated.
4. Changes are persisted to the database.

### Friend Addition Flow
1. Request to add a friend is received.
2. System validates both players exist.
3. Checks for existing friendship.
4. Creates new friendship record.
5. Returns friendship details.

## Technical Implementation

### Key Components

#### Entity Layer
```java
@Entity
@Data
public class Player {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private LevelType level;

    private int totalPoints;
    private int totalWins;

    @OneToMany(mappedBy = "player")
    private List<Friend> friends;
}
```

#### DTO Layer
```java
@Data
public class PlayerDTO {
    private String name;
    private String username;
    private String email;
}
```

#### Repository Layer
```java
public interface PlayerRepository extends JpaRepository<Player, Long> {}
```

### Design Patterns
- **DTO Pattern:** For data transfer.
- **Repository Pattern:** For data access.
- **Service Layer:** For business logic.
- **Entity Lifecycle Hooks:** For automatic updates.

### Error Handling
- Runtime exceptions for business rule violations.
- HTTP status codes for API responses.
- Validation constraints at the entity level.

---

### Contact
For questions or feedback, please contact:
```info
Email: alain.nitunga@helb-prigogine.be
