package org.starmap.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.starmap.model.Constellation;
import org.starmap.model.Star;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/**
 *  Class for writing stars and constellation data to a JSON file
 *
 */

public class DataWriter {

    // SONARLINT: Add a private constructor to hide the implicit public one.
    private DataWriter() {
        throw new IllegalStateException("Utility class");
    }

    public static void writeStarsAndConstellation(String filePath, List<Star> stars, List<Constellation> constellations) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("stars", writeStars(stars));
            jsonObject.put("constellations", writeConstellations(constellations));
            writer.write(jsonObject.toString(2));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    private static JSONArray writeStars(List<Star> stars) {
        JSONArray starArray = new JSONArray();
        for (Star star : stars) {
            JSONObject starObject = new JSONObject();
            starObject.put("name", star.getName());
            starObject.put("xPosition", star.getXPosition());
            starObject.put("yPosition", star.getYPosition());
            starObject.put("brightness", star.getBrightness());
            starArray.put(starObject);
        }
        return starArray;
    }

    private static JSONArray writeConstellations(List<Constellation> constellations) {
        JSONArray constellationArray = new JSONArray();
        for (Constellation constellation : constellations) {
            JSONObject constellationObject = new JSONObject();
            constellationObject.put("name", constellation.getName());

            // save constellation in following format:
            /**
             *     {
             *       "name": "Orion",
             *       "stars": [
             *         "Betelgeuse",
             *         "Rigel",
             *         "Bellatrix"
             *       ]
             *     },
             */

            JSONArray starArray = new JSONArray();
            for (Star star : constellation.getStars()) {
                starArray.put(star.getName());
            }

            constellationObject.put("stars", starArray);
            constellationArray.put(constellationObject);
        }
        return constellationArray;
    }
}
