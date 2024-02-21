package org.utils;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.starmap.controller.StarMapController;
import org.starmap.model.Constellation;
import org.starmap.model.Star;
import org.starmap.utils.DataLoader;
import org.starmap.utils.DataWriter;

import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DataWriterTest {

    @TempDir
    Path tempDir;
    private Path testFilePath;

    StarMapController controller = new StarMapController("src/main/resources/stars.json");


    @BeforeEach
    void setUp() {
        testFilePath = tempDir.resolve("test.json");
        // utworz gwiazdy i konstelacje
        controller.deleteAllStars();
        controller.deleteAllConstellations();
    }

    @Test
    void testWriteStars() {
        // utworz nowe gwiazdy
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Star star = new Star("Star" + i, random.nextInt(1000), random.nextInt(1000), random.nextDouble());
            controller.getStars().add(star);
        }

        // utworz konstelacje
        Constellation constellation1 = new Constellation("Constellation1", List.of(controller.getStars().get(0), controller.getStars().get(1)));
        Constellation constellation2 = new Constellation("Constellation2", List.of(controller.getStars().get(2), controller.getStars().get(3)));

        // zapisz gwiazdy do pliku
        DataWriter.writeStarsAndConstellation(testFilePath.toString(), controller.getStars(), List.of(constellation1, constellation2));


        // sprawdz czy plik zawiera poprawne dane
        List<Star> stars = DataLoader.loadStars(testFilePath.toString());
        assertEquals(10, stars.size());
    }

    @Test
    void testCorrectnessStarParameters() {
        // utworz gwiazde
        String starName = "Star";
        int xPosition = 100;
        int yPosition = 200;
        double brightness = 1.46;

        Star star = new Star(starName, xPosition, yPosition, brightness);
        controller.setStars(List.of(star));

        String constellationName = "Constellation";
        Constellation constellation = new Constellation(constellationName, List.of(star));
        controller.setConstellations(List.of(constellation));


        // zapisz gwiazde do pliku
        DataWriter.writeStarsAndConstellation(testFilePath.toString(), controller.getStars(), controller.getConstellations());

        // sprawdz czy plik zawiera poprawne dane gwiazdy
        List<Star> stars = DataLoader.loadStars(testFilePath.toString());

        assertEquals(starName, stars.get(0).getName());
        assertEquals(xPosition, stars.get(0).getXPosition());
        assertEquals(yPosition, stars.get(0).getYPosition());
        assertEquals(brightness, stars.get(0).getBrightness(), 0.01);

        // sprawdz czy plik zawiera poprawne dane konstelacji
        assertEquals(constellationName, controller.getConstellations().get(0).getName());
        assertEquals(starName, controller.getConstellations().get(0).getStars().get(0).getName());
    }

}
