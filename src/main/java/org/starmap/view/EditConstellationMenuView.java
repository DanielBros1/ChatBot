package org.starmap.view;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import org.starmap.controller.StarMapController;
import org.starmap.model.Constellation;
import org.starmap.model.Star;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditConstellationMenuView {

    private EditConstellationMenuView() {
        // private constructor to hide the implicit public one (SonarLint)
    }

    public static void initializeConstellationMenu(StarMapController controller, Constellation foundConstellation, ContextMenu constellationContextMenu, StarMapView starMapView) {
        if (!constellationContextMenu.getItems().isEmpty()) {
            constellationContextMenu.getItems().clear();
        }

        MenuItem addStartToConstellation = new MenuItem("Add star to constellation");
        MenuItem removeStarFromConstellation = new MenuItem("Remove star from constellation");
        MenuItem moveStarToAnotherConstellation = new MenuItem("Move star to another constellation");
        MenuItem changeConstellationName = new MenuItem("Change constellation name");

        menuAddStarToConstellation(controller, foundConstellation, addStartToConstellation, starMapView);
        menuRemoveStarFromConstellation(controller, foundConstellation, removeStarFromConstellation, starMapView);
        menuMoveStarToAnotherConstellation(controller, foundConstellation, moveStarToAnotherConstellation, starMapView);
        menuChangeConstellationName(controller, foundConstellation, changeConstellationName, starMapView);

        constellationContextMenu.getItems().addAll(addStartToConstellation, removeStarFromConstellation, moveStarToAnotherConstellation, changeConstellationName);
    }

    private static void menuChangeConstellationName(StarMapController controller, Constellation foundConstellation, MenuItem changeConstellationName, StarMapView starMapView) {
        changeConstellationName.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog(foundConstellation.getName());
            dialog.setTitle("Change constellation name");
            dialog.setHeaderText("Enter a new name for the constellation");
            dialog.setContentText("Name:");

            dialog.showAndWait().ifPresent(newName -> {
                controller.changeConstellationName(foundConstellation.getName(), newName);
                starMapView.drawMap();
            });
        });
    }

    private static void menuMoveStarToAnotherConstellation(StarMapController controller, Constellation foundConstellation, MenuItem moveStarToAnotherConstellation, StarMapView starMapView) {
        moveStarToAnotherConstellation.setOnAction(e -> {
            ChoiceDialog<String> starChoiceDialog = constructStarChoiceDialogMenu(foundConstellation,
                    "Moving star to another constellation",
                    "Choose a star to move to another constellation",
                    "Star: ");

            starChoiceDialog.showAndWait().ifPresent(starName -> {
                Optional<Star> star = controller.getStarByName(starName);
                if (star.isPresent()) {
                    ChoiceDialog<String> constellationChoiceDialog = constructConstellationChoiceDialogMenu(controller);

                    constellationChoiceDialog.showAndWait().ifPresent(constellationName -> {
                        controller.moveStarToConstellation(starName, foundConstellation, controller.getConstellationByName(constellationName).get());
                        starMapView.drawMap();
                    });
                }
            });
        });
    }


    private static void menuRemoveStarFromConstellation(StarMapController controller, Constellation foundConstellation, MenuItem removeStarFromConstellation, StarMapView starMapView) {
        removeStarFromConstellation.setOnAction(e -> {
            ChoiceDialog<String> starChoiceDialog = constructStarChoiceDialogMenu(foundConstellation,
                    "Remove star from constellation",
                    "Choose a star to remove from the constellation",
                    "Star:");

            starChoiceDialog.showAndWait().ifPresent(starName -> {
                controller.removeStarFromConstellation(starName, foundConstellation);
                starMapView.drawMap();
            });
        });
    }


    private static void menuAddStarToConstellation(StarMapController controller, Constellation foundConstellation, MenuItem addStartToConstellation, StarMapView starMapView) {
        addStartToConstellation.setOnAction(e -> {
            List<Star> stars = controller.getStars();
            List<String> starNames = new ArrayList<>();
            for (Star star : stars) {
                starNames.add(star.getName());
            }
            ChoiceDialog<String> starChoiceDialog = new ChoiceDialog<>("", starNames);
            starChoiceDialog.setTitle("Add star to constellation");
            starChoiceDialog.setHeaderText("Choose a star to add to the constellation");
            starChoiceDialog.setContentText("Star:");

            starChoiceDialog.showAndWait().ifPresent(starName -> {
                controller.addStarToConstellation(starName, foundConstellation);
                starMapView.drawMap();
            });
        });
    }

    private static ChoiceDialog<String> constructStarChoiceDialogMenu(Constellation foundConstellation, String title, String headerText, String contentText) {
        List<Star> stars = foundConstellation.getStars();
        List<String> starNames = new ArrayList<>();
        for (Star star : stars) {
            starNames.add(star.getName());
        }
        ChoiceDialog<String> starChoiceDialog = new ChoiceDialog<>("", starNames);
        starChoiceDialog.setTitle(title);
        starChoiceDialog.setHeaderText(headerText);
        starChoiceDialog.setContentText(contentText);

        return starChoiceDialog;
    }

    private static ChoiceDialog<String> constructConstellationChoiceDialogMenu(StarMapController controller) {
        List<Constellation> constellations = controller.getConstellations();
        List<String> constellationNames = new ArrayList<>();
        for (Constellation constellation : constellations) {
            constellationNames.add(constellation.getName());
        }
        ChoiceDialog<String> constellationChoiceDialog = new ChoiceDialog<>("", constellationNames);
        constellationChoiceDialog.setTitle("Move star to another constellation");
        constellationChoiceDialog.setHeaderText("Choose a constellation to move the star to");
        constellationChoiceDialog.setContentText("Constellation:");
        return constellationChoiceDialog;
    }
}
