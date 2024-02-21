package org.starmap.view;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.util.Duration;
import org.starmap.controller.StarMapController;
import org.starmap.model.Constellation;
import org.starmap.model.Star;
import org.starmap.utils.DataLoader;
import org.starmap.utils.DataWriter;

import java.util.*;

import static org.starmap.view.EditConstellationMenuView.initializeConstellationMenu;
import static org.starmap.view.EditStarMenuView.initializeStarContextMenu;

public class StarMapView extends Canvas {
    private final StarMapController controller;
    private final PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
    private Star currentHoveredStar = null;
    private final Map<String, Color> constellationColors = new HashMap<>();
    private final ContextMenu starContextMenu = new ContextMenu();

    private final ContextMenu constellationContextMenu = new ContextMenu();

    private final Popup gridMenuPopup = new Popup();
    private final CheckBox showGridCheckBox = new CheckBox("Show grid");
    private final CheckBox showAxisLabelsCheckBox = new CheckBox("Show axis labels");
    private static final String FONT_FAMILY = "Arial";


    public StarMapView(StarMapController controller) {
        this.controller = controller;
        this.setWidth(1024); // Set canvas width
        this.setHeight(768); // Set canvas height
        drawMap();
        initializeConstellationColors();
        initializePopupMenu();
        addMouseMotionListener();
        addMouseClickListener();

    }

    private void drawAssistance() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(0.3);
        if (showGridCheckBox.isSelected()) {
            drawGrid(gc);
        }
        if (showAxisLabelsCheckBox.isSelected()) {
            drawAxisLabels(gc);
        }
    }

    private void drawGrid(GraphicsContext gc) {
        // Draw vertical lines
        for (double i = 0; i < getWidth(); i += 50) {
            gc.strokeLine(i, 0, i, getHeight());
        }
        // Draw horizontal lines
        for (double i = 0; i < getHeight(); i += 50) {
            gc.strokeLine(0, i, getWidth(), i);
        }
    }

    private void drawAxisLabels(GraphicsContext gc) {
        // Draw the coordinate system. (0,0) in the top left corner and label every 100 pixels
        gc.strokeLine(0, getHeight(), getWidth(), getHeight()); // OX
        gc.strokeLine(0, 0, 0, getHeight()); // OY
        gc.setFont(new Font(FONT_FAMILY, 16));

        for (int i = 0; i < getWidth(); i += 100) {
            gc.strokeLine(i, getHeight(), i, getHeight() - 5);
            gc.fillText(i + "", i, getHeight() - 10);
        }
        for (int i = 0; i < getHeight(); i += 100) {
            gc.strokeLine(0, i, 5, i);
            gc.fillText(i + "", 5, i + 16.0);
        }
    }

    private void initializeConstellationColors() {
        List<Constellation> constellations = controller.getConstellations();
        for (Constellation constellation : constellations) {
            int hash = constellation.getName().hashCode();
            Random rand = new Random(hash); // Use hash as a seed for random generator
            Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
            constellationColors.put(constellation.getName(), color);
        }
    }

    private void initializePopupMenu() {
        VBox popupContent = getPopupContent();

        VBox titleBox = new VBox();
        VBox commandVBox = new VBox();
        VBox gridOptionsVBox = new VBox();

        Label label = new Label("Grid Options:");
        // ustaw padding z gory
        label.setPadding(new javafx.geometry.Insets(20, 0, 0, 0));

        // defaultowe ustawienia
        showGridCheckBox.setSelected(true);
        showAxisLabelsCheckBox.setSelected(false);


        showGridCheckBox.setOnAction(e -> {
            if (showGridCheckBox.isSelected()) {
                drawAssistance();
            } else {
                clearCanvas();
                drawMap(); // Rysuj wszystko od nowa
            }
        });
        showAxisLabelsCheckBox.setOnAction(e -> {
            if (showAxisLabelsCheckBox.isSelected()) {
                drawAssistance();
            } else {
                clearCanvas();
                drawMap(); // Rysuj wszystko od nowa
            }
        });

        // Save button setup
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #AFE1AF;-fx-text-fill: #ffffff;-fx-font-size: 16px;-fx-font-family: Arial;");
        saveButton.setPrefSize(100, 20);
        saveButtonMethod(saveButton);

        // Load button setup
        Button loadButton = new Button("Load");
        loadButton.setStyle("-fx-background-color: #89CFF0;-fx-text-fill: #ffffff;-fx-font-size: 16px;-fx-font-family: Arial;");
        loadButton.setPrefSize(100, 20);
        loadButtonMethod(loadButton);

        // Title box setup
        titleBox.getChildren().add(new Label("MENU"));
        titleBox.setStyle("-fx-font-size: 24px;-fx-font-family: Arial;-fx-font-weight: bold;-fx-text-fill: #ff1493;");
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new javafx.geometry.Insets(0, 0, 10, 0));

        commandVBox.getChildren().addAll(saveButton, loadButton);
        commandVBox.setSpacing(8);
        commandVBox.setAlignment(Pos.CENTER);
        gridOptionsVBox.getChildren().addAll(showGridCheckBox, showAxisLabelsCheckBox);

        popupContent.getChildren().addAll(titleBox, commandVBox, label, gridOptionsVBox);
        gridMenuPopup.getContent().add(popupContent);
    }

    private VBox getPopupContent() {
        VBox popupContent = new VBox();
        popupContent.setPrefSize(170, 70);
        popupContent.setSpacing(6);
        popupContent.setAlignment(Pos.CENTER_LEFT);
        popupContent.setStyle(
                "-fx-background-color: #fffacd; " +
                        "-fx-border-color: #d0d0d0; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-padding: 10px;" +
                        "-fx-font-size: 14px; " +
                        "-fx-font-family: fontfamily; " +
                        "-fx-text-fill: #555555;"
        );
        return popupContent;
    }

    private void saveButtonMethod(Button saveButton) {
        saveButton.setOnAction(e -> {

            // zamknij popupMenu
            gridMenuPopup.hide();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Resource File");
            // tylko pliki json
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JSON Files", "*.json"));

            // ustaw folder domyslny na src/main/resources
            System.out.println("Current directory: " + System.getProperty("user.dir"));
            System.out.println("Default initial directory: " + fileChooser.getInitialDirectory());
            fileChooser.setInitialDirectory(new java.io.File("src/main/resources"));
            java.io.File selectedFile = fileChooser.showSaveDialog(gridMenuPopup.getOwnerWindow());


            if (selectedFile != null) {
                System.out.println(selectedFile.getAbsolutePath());

                // Save the stars and constellations to the file
                DataWriter.writeStarsAndConstellation(selectedFile.getAbsolutePath(), controller.getStars(), controller.getConstellations());

                System.out.println("Saved " + controller.getStars().size() + " stars and " + controller.getConstellations().size() + " constellations");
            }
        });
    }

    private void loadButtonMethod(Button loadButton) {
        loadButton.setOnAction(e -> {

            // zamknij popupMenu
            gridMenuPopup.hide();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            // tylko pliki json
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JSON Files", "*.json"));

            // ustaw folder domyslny na src/main/resources
            System.out.println("Current directory: " + System.getProperty("user.dir"));
            System.out.println("Default initial directory: " + fileChooser.getInitialDirectory());
            fileChooser.setInitialDirectory(new java.io.File("src/main/resources"));
            java.io.File selectedFile = fileChooser.showOpenDialog(gridMenuPopup.getOwnerWindow());


            if (selectedFile != null) {
                System.out.println(selectedFile.getAbsolutePath());

                // Load the stars and constellations from the file
                List<Star> newStars = DataLoader.loadStars(selectedFile.getAbsolutePath());
                List<Constellation> newConstellations = DataLoader.loadConstellations(selectedFile.getAbsolutePath(), newStars);

                // Update the controller with the new data
                controller.setStars(newStars);
                controller.setConstellations(newConstellations);

                clearCanvas();
                drawMap();

                System.out.println("Loaded " + newStars.size() + " stars and " + newConstellations.size() + " constellations");
            }
        });
    }


    public void drawMap() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight()); // Set background to black
        drawStars();
        drawConstellations();
        drawAssistance();
    }

    private void drawStars() {
        GraphicsContext gc = getGraphicsContext2D();
        List<Star> stars = controller.getStars();
        for (Star star : stars) {
            double brightnessScale = star.getBrightness() / 2.0; // Scale brightness
            double starSize = 2 + (5 - brightnessScale); // Calculate star size
            Color starColor = Color.hsb(60, 0.5, 1 - 0.2 * brightnessScale); // Color based on brightness
            drawStar(gc, star.getXPosition(), star.getYPosition(), starSize, starColor);
        }
    }

    private void drawStar(GraphicsContext gc, double x, double y, double size, Color color) {
        double[] xPoints = new double[10];
        double[] yPoints = new double[10];
        for (int i = 0; i < 10; i++) {
            double angle = Math.PI / 5 * i;
            double radius = i % 2 == 0 ? size : size / 2;
            xPoints[i] = x + radius * Math.sin(angle);
            yPoints[i] = y - radius * Math.cos(angle);
        }
        gc.setStroke(color);
        gc.strokePolyline(xPoints, yPoints, 10);
    }

    private void drawConstellations() {
        GraphicsContext gc = getGraphicsContext2D();
        List<Constellation> constellations = controller.getConstellations();

        for (Constellation constellation : constellations) {
            Color lineColor = constellationColors.getOrDefault(constellation.getName(), Color.BLUE);
            gc.setStroke(lineColor);
            gc.setLineWidth(1);
            gc.setFill(lineColor);
            gc.setFont(new Font(FONT_FAMILY, 14));

            List<Star> starsInConstellation = constellation.getStars();
            for (int i = 0; i < starsInConstellation.size() - 1; i++) {
                Star start = starsInConstellation.get(i);
                Star end = starsInConstellation.get(i + 1);
                gc.strokeLine(start.getXPosition(), start.getYPosition(), end.getXPosition(), end.getYPosition());
            }

            // Rysuj nazwę konstelacji obok pierwszej gwiazdy
            if (!starsInConstellation.isEmpty()) {
                Star firstStar = starsInConstellation.get(0);

                // Set the constellation position above the first star
                constellation.setxPosition(firstStar.getXPosition());
                constellation.setyPosition(firstStar.getYPosition() - 15);
                gc.fillText(constellation.getName(), constellation.getxPosition(), constellation.getyPosition());
            }
        }
    }

    private void addMouseMotionListener() {
        this.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            Star foundStar = null;

            foundStar = getFoundStar(mouseX, mouseY, foundStar);

            if (foundStar != null && foundStar != currentHoveredStar) {
                currentHoveredStar = foundStar;
                pause.stop(); // Zatrzymaj poprzednie opóźnienie
                drawStarName(foundStar);
            } else if (foundStar == null && currentHoveredStar != null) {
                pause.setOnFinished(e -> {
                    hideStarName();
                    currentHoveredStar = null;
                });
                pause.playFromStart();
            }
        });
    }


    private void addMouseClickListener() {
        this.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            Star foundStar = null;
            Constellation foundConstellation = null;


            foundStar = getFoundStar(mouseX, mouseY, foundStar);
            foundConstellation = getFoundConstellation(mouseX, mouseY, foundConstellation);

            if (foundStar != null) {
                initializeStarContextMenu(this.starContextMenu, foundStar, this);
                starContextMenu.show(this, event.getScreenX(), event.getScreenY());

                // hide the other menus
                constellationContextMenu.hide();
                gridMenuPopup.hide();
            } else if (foundConstellation != null) {
                initializeConstellationMenu(controller, foundConstellation, constellationContextMenu, this);
                constellationContextMenu.show(this, event.getScreenX(), event.getScreenY());

                // hide the other menus
                starContextMenu.hide();
                gridMenuPopup.hide();
            } else if (event.getButton() == MouseButton.SECONDARY) {
                gridMenuPopup.show(this, event.getScreenX(), event.getScreenY());

                // hide the other menus
                starContextMenu.hide();
                constellationContextMenu.hide();

            } else {
                // hide the context menu if the user clicks on empty space
                starContextMenu.hide();
                constellationContextMenu.hide();
                gridMenuPopup.hide();
            }
        });
    }

    private Star getFoundStar(double mouseX, double mouseY, Star foundStar) {
        List<Star> stars = controller.getStars();
        for (Star star : stars) {
            if (Math.abs(mouseX - star.getXPosition()) < 10 && Math.abs(mouseY - star.getYPosition()) < 10) {
                foundStar = star;
                break;
            }
        }
        return foundStar;
    }

    private Constellation getFoundConstellation(double mouseX, double mouseY, Constellation foundConstellation) {
        List<Constellation> constellations = controller.getConstellations();
        for (Constellation constellation : constellations) {
            if (Math.abs(mouseX - constellation.getxPosition()) < 10 && Math.abs(mouseY - constellation.getyPosition()) < 10) {
                foundConstellation = constellation;
                break;
            }
        }
        return foundConstellation;
    }

    private void drawStarName(Star star) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillText(star.getName(), star.getXPosition() + 10, star.getYPosition() - 10);
    }

    private void hideStarName() {
        if (currentHoveredStar != null) {
            pause.setOnFinished(e -> {
                clearCanvas();
                drawMap(); // Rysuj wszystko od nowa
            });
            pause.playFromStart();
        }
    }

    public void clearCanvas() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
    }
}
