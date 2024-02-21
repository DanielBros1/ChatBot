package org.studentresource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentResourceManagerTest {
    private StudentResourceManager<Course> manager;
    private StudentResourceManager<StudyMaterial> manager2;

    @BeforeEach
    void setUp() {
        manager = new StudentResourceManager<>();
        manager2 = new StudentResourceManager<>();
    }

    @Test
    void addAndRetrieveResourceTest() {
        Course course = new Course("CS101", "Introduction to Computer Science");
        manager.addResource(course);

        Course retrieved = manager.getResource("CS101");
        assertNotNull(retrieved, "Retrieved course should not be null.");
        assertEquals("Introduction to Computer Science", retrieved.getName(), "Course name should match.");
    }

    @Test
    void removeResourceTest() {
        Course course1 = new Course("CS101", "Introduction to Computer Science", "This course is an introduction to computer science.");
        Course course2 = new Course("CS102", "Introduction to Programming", "This course is an introduction to programming.");
        Course course3 = new Course("CS103", "Introduction to Software Engineering", "This course is an introduction to software engineering.");

        manager.addResource(course1);
        manager.addResource(course2);
        manager.addResource(course3);

        Course retrieved = manager.getResource("CS102");
        assertNotNull(retrieved);
        manager.removeResource(retrieved);

        retrieved = manager.getResource("CS102");
        assertNull(retrieved, "Retrieved course should be null.");
    }

    @Test
    void retrieveAllResources() {
        Course course1 = new Course("CS101", "Introduction to Computer Science");
        Course course2 = new Course("CS102", "Introduction to Programming");
        Course course3 = new Course("CS103", "Introduction to Software Engineering");

        manager.addResource(course1);
        manager.addResource(course2);
        manager.addResource(course3);

        assertEquals(3, manager.getAllResources().size(), "Size should match");

        // check if in manager.getAllResources() contains course1, course2, course3
        assertTrue(manager.getAllResources().contains(course1), "Course1 should be in the list");
        assertTrue(manager.getAllResources().contains(course2), "Course2 should be in the list");
        assertTrue(manager.getAllResources().contains(course3), "Course3 should be in the list");

    }
    @Test
    void studyMaterialTest() {
        StudyMaterial studyMaterial = new StudyMaterial("CS101", "Introduction to Computer Science");
        StudyMaterial studyMaterial2 = new StudyMaterial("CS102", "Introduction to Programming");

        manager2.addResource(studyMaterial);
        manager2.addResource(studyMaterial2);

        assertEquals(2, manager2.getAllResources().size(), "Size should match");

        assertTrue(manager2.getAllResources().contains(studyMaterial), "StudyMaterial1 should be in the list");
        assertTrue(manager2.getAllResources().contains(studyMaterial2), "StudyMaterial2 should be in the list");
    }
}

