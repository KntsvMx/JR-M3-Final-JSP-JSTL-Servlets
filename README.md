# Text-Based Adventure Game

This project is a web-based text adventure game implemented using the following technologies:

- **Java Servlets** for handling HTTP requests and game logic.
- **JSP (JavaServer Pages)** for rendering dynamic HTML content.
- **JSTL (JavaServer Pages Standard Tag Library)** for simplifying view logic.
- **Log4j with SLF4J** for logging.
- **JUnit** for testing servlets and ensuring logic reliability.
- **Mockito** for mocking dependencies in unit tests.
- **Maven** for project management and dependency resolution.
- **Tomcat 10** for running the web application.

## Features

1. **Dynamic Gameplay**:
    - The game progresses based on user choices.
    - Predefined scenarios and outcomes are determined by player decisions.

2. **Welcome Page**:
    - A welcoming page introduces players to the game’s backstory and sets the mood.

3. **Session Management**:
    - Stores player information (e.g., name, ip, game score etc) using HTTP sessions.

4. **Game State**:
    - Tracks win/loss status, allowing players to restart the game after finishing or failure.

5. **Compatibility**:
    - Designed for testing on **Tomcat 10**.

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Import the project into your IDE as a Maven project.

3. Configure Tomcat 9 as the application server.

4. Build the project:
   ```bash
   mvn clean install
   ```

5. Deploy the `war` file to Tomcat or run directly from the IDE.

## Testing

- **JUnit**:
    - Test cases cover servlet logic and game functionality.
    - Run the tests with:
      ```bash
      mvn test
      ```
- **Mockito**:
    - Mocking is used to isolate dependencies and ensure that tests are reliable.
    - Run the tests with:
      ```bash
      mvn test
      ```

## Logging

- Logging is configured using **Log4j with SLF4J** for clean and manageable log output.
- Log files record key events such as player actions, session state changes, and errors.

---

Enjoy the adventure!

