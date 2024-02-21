# Chatbot Reservation System

This project is a chatbot reservation system implemented in Java. It allows users to interact with a chatbot to make, view, and delete reservations. The system is built using a client-server architecture where the client communicates with the server using sockets.

## Features

- **Chat Client**: The chat client is responsible for sending user messages to the server and receiving responses from the server. It connects to the server using a socket and communicates using `BufferedReader` and `PrintWriter` for input and output respectively.

- **Chat Server**: The chat server listens for incoming client connections and spawns a new thread to handle each client. Each client is handled by a `ClientHandler` which processes client messages and sends responses.

- **Chatbot Logic**: The chatbot logic processes user input and generates appropriate responses. It supports commands for making a reservation, viewing all reservations, and deleting a reservation.

- **Database Connection**: The system uses a MySQL database to store reservations. The `DatabaseConnection` class provides methods for adding a reservation, deleting a reservation, and listing all reservations.

## Getting Started

To get a local copy up and running, follow these steps:

1. Clone the repository.
2. Import the project into your preferred IDE (The project is set up for IntelliJ IDEA).
3. Ensure you have a MySQL server running and update the database connection details in `DatabaseConnection.java`.
4. Run `ChatServer.java` to start the server.
5. Run `ChatClient.java` to start the client.

## Usage

Once the client is running, you can interact with the chatbot using the following commands:

- `Rezerwuj`: Make a reservation.
- `Pokaż rezerwacje`: View all reservations.
- `Usuń rezerwację [ID]`: Delete a reservation with the given ID.
