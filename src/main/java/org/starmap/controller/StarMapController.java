package org.starmap.controller;

import org.starmap.model.Constellation;
import org.starmap.model.Star;
import org.starmap.utils.DataLoader;
import java.util.List;
import java.util.Optional;

// Controller for managing the star map
public class StarMapController {
    private List<Star> stars;
    private List<Constellation> constellations;

    private static final String INCORRECT_VALUE = "Star or constellation does not exist";

    public StarMapController(String dataFilePath) {
        this.stars = DataLoader.loadStars(dataFilePath);
        this.constellations = DataLoader.loadConstellations(dataFilePath, stars);
    }

    public void deleteAllStars() {
        stars.clear();
    }

    public void deleteAllConstellations() {
        constellations.clear();
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public List<Constellation> getConstellations() {
        return constellations;
    }

    public void setConstellations(List<Constellation> constellations) {
        this.constellations = constellations;
    }

    // Get a star by its name
    public Optional<Star> getStarByName(String name) {
        return stars.stream().filter(star -> star.getName().equalsIgnoreCase(name)).findFirst();
    }

    // Get a constellation by its name
    public Optional<Constellation> getConstellationByName(String name) {
        return constellations.stream().filter(constellation -> constellation.getName().equalsIgnoreCase(name)).findFirst();
    }


    // Get constellation by star
    public Optional<Constellation> getConstellationByStar(Star star) {
        return constellations.stream().filter(constellation -> constellation.getStars().contains(star)).findFirst();
    }


    // Add a star to a constellation
    public void addStarToConstellation(String starName, Constellation constellation) {
        // check if star and constellation exist
        Optional<Star> star = getStarByName(starName);
        // Call "star.isPresent()" or "!star.isEmpty()" before accessing the value.
        Optional<Constellation> oldConstellation = getConstellationByStar(star.orElse(null));

        if (constellation != null) {
            // firstly remove star from any constellation it is in
            oldConstellation.ifPresent(value -> value.getStars().removeIf(s -> s.getName().equalsIgnoreCase(starName)));
            constellation.addStar(star.orElse(null));
        }
        else {
            throw new IllegalArgumentException(INCORRECT_VALUE);
        }
    }

    // Remove a star from a constellation
    public void removeStarFromConstellation(String starName, Constellation constellation) {
        Optional<Star> star = getStarByName(starName);

        if (star.isPresent() && constellation != null) {
            constellation.getStars().removeIf(s -> s.getName().equalsIgnoreCase(starName));
        }
        else {
            throw new IllegalArgumentException(INCORRECT_VALUE);
        }
    }

    // Move a star to another constellation
    public void moveStarToConstellation(String starName, Constellation oldConstellation, Constellation newConstellation) {
        Optional<Star> star = getStarByName(starName);

        if (star.isPresent() && oldConstellation != null && newConstellation != null) {
            removeStarFromConstellation(starName, oldConstellation);
            addStarToConstellation(starName, newConstellation);
        }
        else {
            throw new IllegalArgumentException(INCORRECT_VALUE);
        }
    }

    public void changeConstellationName(String oldName, String newName) {
        Optional<Constellation> constellation = getConstellationByName(oldName);

        constellation.ifPresent(value -> value.setName(newName));
    }

}
