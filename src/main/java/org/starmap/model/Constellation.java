package org.starmap.model;

import java.util.List;

// Represents a constellation made up of stars
public class Constellation {
    private String name;
    private final List<Star> stars;
    private double xPosition;
    private double yPosition;

    public Constellation(String name, List<Star> stars) {
        this.name = name;
        this.stars = stars;
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStar(Star star) {
        stars.add(star);
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }
}
