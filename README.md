# 3. Star Map

Star Map is a JavaFX application that visualizes a star map. The project is built with Maven and demonstrates key concepts such as data loading, data writing, model-view-controller (MVC) pattern, and JavaFX UI design.

<img src="https://github.com/DanielBros1/StarMap/assets/112472952/a2fe6e6f-b2e7-45f6-a866-c0ea6812b6b7" width="420">
<img src="https://github.com/DanielBros1/StarMap/assets/112472952/ad942a55-0fa5-4a55-9cb5-f64d8a69dde4" width="420">


### Key Features

- **Star and Constellation Management**: The `StarMapController` class manages the stars and constellations. It provides methods to add, remove, and retrieve stars and constellations. It also allows adding a star to a constellation, removing a star from a constellation, and moving a star to another constellation.

- **Data Loading and Writing**: The `DataLoader` and `DataWriter` classes handle loading and saving of star map data from and to a JSON file. This demonstrates how to read and write data from and to a file, and handle potential exceptions.

- **JavaFX UI Design**: The `StarMapView` class represents the visual part of the application. It includes a canvas to draw the star map and a context menu to edit the stars and constellations. It also includes a popup menu to show grid options and save and load star map data.

- **Model-View-Controller (MVC) Pattern**: The project follows the MVC pattern, with `Star` and `Constellation` as the model, `StarMapView` as the view, and `StarMapController` as the controller.

The project includes JUnit tests to ensure the correct functionality of the classes such as loading and writing data

### Additional Screenshots:

<img src="https://github.com/DanielBros1/StarMap/assets/112472952/d2c09ef1-4cb0-4a2e-b175-770531580f79" width="420">
<img src="https://github.com/DanielBros1/StarMap/assets/112472952/16451141-f802-4a57-82ef-1dae0bdca02d" width="420">


