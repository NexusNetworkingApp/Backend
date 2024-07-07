# Backend

## Backend system Architecture
DB and backend are separate services in docker compose. 

## Initialization directions
1. Start MySQL server: ```mysql.server start```
2. ```docker compose up --build``` (this will initialize database on local machine through volume)


## WebSocket Chat Feature

### Overview
This project includes a WebSocket-based real-time chat feature, allowing users to send and receive messages instantly. The chat system leverages Spring WebSocket and STOMP for message handling.

### Key Components
- **WebSocket Configuration**: Configures the WebSocket endpoints and message broker.
- **WebSocket Controller**: Handles incoming WebSocket messages and routes them to the appropriate users.
- **Message Service**: Manages message persistence and retrieval.

### Configuration

**File**: `src/main/java/com/nexus/api/config/WebSocketConfig.java`

The WebSocket configuration includes:
- Setting up the endpoint at `/ws` with SockJS fallback.
- Configuring message broker with `/queue` for user-specific destinations and `/app` for application-specific destinations.
- Allowing origins using `setAllowedOriginPatterns` to handle CORS issues.

### Controller

**File**: `src/main/java/com/nexus/api/web/WebSocketController.java`

The WebSocket controller:
- Receives and processes messages sent to `/app/sendMessage`.
- Validates and retrieves sender and receiver information from the database.
- Saves the message to the database.
- Sends the message to the recipient's WebSocket queue.

### Common Issues and Solutions

#### Issue: CORS Policy Blocking Requests
**Problem**: During development, requests to the WebSocket endpoint were blocked by the browser due to CORS policy.

**Solution**:
- Initially, using `.setAllowedOrigins("*")` did not work because it conflicts with `allowCredentials`.
- Resolved by using `.setAllowedOriginPatterns("*")`, which allows flexible origin patterns including wildcards.


