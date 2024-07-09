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

## Discover Feature

### Overview
The Discover feature allows users to discover other individual or organization accounts based on their zip code and a specified distance radius. It uses a third-party API (ZipCodeAPI) to find all zip codes within a given radius and then fetches eligible accounts from those locations.

### Key Components
- **Discover Endpoint**: Allows clients to request account discovery based on location and distance.
- **Account Service**: Handles the business logic for finding and returning eligible accounts.
- **Zip Code Service**: Communicates with the ZipCodeAPI to get zip codes within a specified radius.

### Configuration

**File**: `src/main/java/com/nexus/api/web/AccountController.java`

The Discover endpoint:
- Accepts `zipCode`, `distance`, and `loggedInAccountId` as request parameters.
- Fetches zip codes within the radius using `ZipCodeService`.
- Finds eligible accounts within the retrieved zip codes using `AccountService`.
- Filters out the logged-in account from the results.
- Returns a random eligible account.

**File**: `src/main/java/com/nexus/api/business/AccountService.java`

The Account service:
- Contains methods to find zip codes within a radius, find accounts by zip codes, and return a random eligible account.
- Ensures the logged-in account is excluded from the discovery results.

**File**: `src/main/java/com/nexus/api/business/ZipCodeService.java`

The Zip Code service:
- Communicates with the ZipCodeAPI to retrieve zip codes within a specified radius.
- Sends a POST request to the ZipCodeAPI with the base zip code and distance.
- Parses and returns the list of zip codes from the API response.

### Usage Example

To discover new accounts, clients can send a GET request to the discover endpoint:

