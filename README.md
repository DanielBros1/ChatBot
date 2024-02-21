# java_ repository
This repository contains four different projects prepared as assignments in a Java language course taught at the Jagiellonian University. Each of the assignments deals with specific features of the Java language; generic types, thread synchronisation, Networks and databases, and GUI with JavaFX

## 1. Student Resource Management System

The Student Resource Management System is a Java-based application that manages resources for students. The project is built with Maven and demonstrates key concepts such as object-oriented design, decorator design pattern, and exception handling.

### Key Features

- **Resource Management**: The `StudentResourceManager` class manages resources for students. It provides methods to add, remove, and retrieve resources. The resources can be anything that implements the `StudentResource` interface, such as `StudyMaterial` or `Course`.

- **Decorator Design Pattern**: The project uses the decorator design pattern to add additional functionalities to resources. The `CommentableResource` and `RateableResource` classes are decorators that add commenting and rating functionalities to resources, respectively.

- **Exception Handling**: The project includes a `ProductNotFoundException` class, which is an exception thrown when a requested product is not found in the inventory.

- **JUnit Testing**: The project includes a suite of JUnit tests to ensure the correct functionality of the classes. The tests cover scenarios such as adding and removing resources, and adding comments and ratings to resources.

## 2. Simple Store

Simple Store is a Java-based simulation of an online store. The project is built with Maven and demonstrates key concepts such as inventory management, shopping cart operations, exception handling, file I/O operations, and multi-threading.

### Key Features

- **Inventory Management**: The `Inventory` class represents the store's inventory, holding a collection of `Product` instances. Each `Product` has an ID, a name, and a price. The `Inventory` class provides methods to add, remove, and retrieve products.

- **Shopping Cart Operations**: The `ShoppingCart` class represents a user's shopping cart. It allows adding and removing products, and calculating the total cost of the items in the cart. The class is designed to be thread-safe, ensuring that multiple threads can perform operations on the cart concurrently without causing inconsistencies.

- **Exception Handling**: The project includes a `ProductNotFoundException` class, which is an exception thrown when a requested product is not found in the inventory.

- **File I/O Operations**: The `InventoryLoader` class is a utility class that loads products into the inventory from a CSV file. This demonstrates how to read data from a file and handle potential exceptions.

- **Multi-threading**: The project includes a suite of JUnit tests to ensure the correct functionality of the classes. The tests cover scenarios such as adding and removing items from the cart, calculating the total cost, and concurrent operations on the cart.

## 3. Star Map

Star Map is a JavaFX application that visualizes a star map. The project is built with Maven and demonstrates key concepts such as data loading, data writing, model-view-controller (MVC) pattern, and JavaFX UI design.

### Key Features

- **Star and Constellation Management**: The `StarMapController` class manages the stars and constellations. It provides methods to add, remove, and retrieve stars and constellations. It also allows adding a star to a constellation, removing a star from a constellation, and moving a star to another constellation.

- **Data Loading and Writing**: The `DataLoader` and `DataWriter` classes handle loading and saving of star map data from and to a JSON file. This demonstrates how to read and write data from and to a file, and handle potential exceptions.

- **JavaFX UI Design**: The `StarMapView` class represents the visual part of the application. It includes a canvas to draw the star map and a context menu to edit the stars and constellations. It also includes a popup menu to show grid options and save and load star map data.

- **Model-View-Controller (MVC) Pattern**: The project follows the MVC pattern, with `Star` and `Constellation` as the model, `StarMapView` as the view, and `StarMapController` as the controller.

The project includes a suite of JUnit tests to ensure the correct functionality of the classes. The tests cover scenarios such as loading and writing data, and managing stars and constellations.


## 4. Chatbot Reservation System

This project is a chatbot reservation system implemented in Java. It allows users to interact with a chatbot to make, view, and delete reservations. The system is built using a client-server architecture where the client communicates with the server using sockets.

### Features

- **Chat Client**: The chat client is responsible for sending user messages to the server and receiving responses from the server. It connects to the server using a socket and communicates using `BufferedReader` and `PrintWriter` for input and output respectively.

- **Chat Server**: The chat server listens for incoming client connections and spawns a new thread to handle each client. Each client is handled by a `ClientHandler` which processes client messages and sends responses.

- **Chatbot Logic**: The chatbot logic processes user input and generates appropriate responses. It supports commands for making a reservation, viewing all reservations, and deleting a reservation.

- **Database Connection**: The system uses a MySQL database to store reservations. The `DatabaseConnection` class provides methods for adding a reservation, deleting a reservation, and listing all reservations.

