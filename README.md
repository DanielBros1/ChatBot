# 3. Star Map

Star Map is a JavaFX application that visualizes a star map. The project is built with Maven and demonstrates key concepts such as data loading, data writing, model-view-controller (MVC) pattern, and JavaFX UI design.

### Key Features

- **Star and Constellation Management**: The `StarMapController` class manages the stars and constellations. It provides methods to add, remove, and retrieve stars and constellations. It also allows adding a star to a constellation, removing a star from a constellation, and moving a star to another constellation.

- **Data Loading and Writing**: The `DataLoader` and `DataWriter` classes handle loading and saving of star map data from and to a JSON file. This demonstrates how to read and write data from and to a file, and handle potential exceptions.

- **JavaFX UI Design**: The `StarMapView` class represents the visual part of the application. It includes a canvas to draw the star map and a context menu to edit the stars and constellations. It also includes a popup menu to show grid options and save and load star map data.

- **Model-View-Controller (MVC) Pattern**: The project follows the MVC pattern, with `Star` and `Constellation` as the model, `StarMapView` as the view, and `StarMapController` as the controller.

The project includes a suite of JUnit tests to ensure the correct functionality of the classes. The tests cover scenarios such as loading and writing data, and managing stars and constellations.


- **Chatbot Logic**: The chatbot logic processes user input and generates appropriate responses. It supports commands for making a reservation, viewing all reservations, and deleting a reservation.

- **Database Connection**: The system uses a MySQL database to store reservations. The `DatabaseConnection` class provides methods for adding a reservation, deleting a reservation, and listing all reservations.

