package org.starmap.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import org.starmap.model.Star;


public class EditStarMenuView {

    // SONARLINT: Private constructor to hide the implicit public one
    private EditStarMenuView() {
    }
    private static final String INVALID_INPUT = "Invalid Input";

    public static void initializeStarContextMenu(ContextMenu starContextMenu, Star foundStar, StarMapView starMapView) {
        // without this, the context menu will keep adding items every time the user clicks on a star
        if (!starContextMenu.getItems().isEmpty()) {
            starContextMenu.getItems().clear();
        }

        MenuItem changeStarName = new MenuItem("Change name");
        MenuItem changeStarPosition = new MenuItem("Change position");
        MenuItem changeBrightness = new MenuItem("Change brightness");

        changeStarName(foundStar, starMapView, changeStarName);
        changeStarPosition(foundStar, starMapView, changeStarPosition);
        changeStarBrightness(foundStar, starMapView, changeBrightness);


        starContextMenu.getItems().addAll(changeStarName, changeStarPosition, changeBrightness);
    }

    private static void changeStarBrightness(Star foundStar, StarMapView starMapView, MenuItem changeBrightness) {
        changeBrightness.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog(foundStar.getBrightness() + "");
            dialog.setTitle("Change star brightness");
            dialog.setHeaderText("Enter a new brightness for the star");
            dialog.setContentText("Brightness:");

            dialog.showAndWait().ifPresent(newBrightness -> {
                try {
                    double brightnessValue = Double.parseDouble(newBrightness);

                    // check if the brightness value is between 0 and 10
                    if (brightnessValue >= 0 && brightnessValue <= 10) {
                        foundStar.setBrightness(brightnessValue);
                        foundStar.setBrightness(Double.parseDouble(newBrightness));
                        starMapView.clearCanvas();
                        starMapView.drawMap();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(INVALID_INPUT);
                        alert.setHeaderText("Invalid star brightness");
                        alert.setContentText("Brightness must be between 0 and 10");
                        alert.showAndWait();
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Invalid star brightness format");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(INVALID_INPUT);
                    alert.setHeaderText("Invalid star brightness");
                    alert.setContentText("Brightness must be a number");
                    alert.showAndWait();
                }

            });
        });
    }

    private static void changeStarPosition(Star foundStar, StarMapView starMapView, MenuItem changeStarPosition) {
        changeStarPosition.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog(foundStar.getXPosition() + ", " + foundStar.getYPosition());
            dialog.setTitle("Change star position");
            dialog.setHeaderText("Enter a new position for the star");
            dialog.setContentText("Position:");

            dialog.showAndWait().ifPresent(newPosition -> {
                String[] position = newPosition.split(",");
                try {
                    Double.parseDouble(position[0]);
                    Double.parseDouble(position[1]);

                } catch (NumberFormatException ex) {
                    System.err.println("Invalid star position format");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(INVALID_INPUT);
                    alert.setHeaderText("Invalid star position");
                    alert.setContentText("Position must be in the format: x, y");
                    alert.showAndWait();
                    return;
                }
                foundStar.setxPosition(Double.parseDouble(position[0]));
                foundStar.setyPosition(Double.parseDouble(position[1]));
                starMapView.clearCanvas();
                starMapView.drawMap();
            });
        });
    }

    private static void changeStarName(Star foundStar, StarMapView starMapView, MenuItem changeStarName) {
        changeStarName.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog(foundStar.getName());
            dialog.setTitle("Change star name");
            dialog.setHeaderText("Enter a new name for the star");
            dialog.setContentText("Name:");

            dialog.showAndWait().ifPresent(newName -> {
                foundStar.setName(newName);
                starMapView.clearCanvas();
                starMapView.drawMap();
            });
        });
    }
}
