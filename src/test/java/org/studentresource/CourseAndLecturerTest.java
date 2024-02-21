package org.studentresource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.studentresource.enums.AcademicTitle;
import org.studentresource.people.Lecturer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseAndLecturerTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void courseDescription() {
        String course1Description = "This course is an introduction to computer science.";
        Course course1 = new Course("CS101", "Introduction to Computer Science", course1Description);

        assertEquals(course1.getDescription(), course1Description, "Description should match");
    }

    @Test
    void createCourseWithLecturer() {

        AcademicTitle lecturer1Title = AcademicTitle.PROFESSOR;
        Lecturer lecturer1 = new Lecturer("L001", "John Doe", lecturer1Title);
        Lecturer lecturer2 = new Lecturer("L002", "Alice Smith", AcademicTitle.DOCTOR);

        Course course1 = new Course("CS101", "Introduction to Computer Science", "This course is an introduction to computer science.", lecturer1);
        Course course2 = new Course("CS102", "Introduction to Programming", "This course is an introduction to programming.", lecturer2);

        assertEquals(course2.getLecturer(), lecturer2, "Lecturer should match");

        assertEquals(course1.getLecturer().getAcademicTitle(), lecturer1Title, "Lecturer title should match");
    }

    @Test
    void setAndGetCourseProperties() {

        String oldDescription = "This course is an introduction to computer science.";
        String newDescription = "This course is an introduction to programming.";

        Lecturer oldLecturer = new Lecturer("L001", "John Doe", AcademicTitle.PROFESSOR);
        Lecturer newLecturer = new Lecturer("L002", "Alice Smith", AcademicTitle.DOCTOR);

        Course course;
        course = new Course("CS101", "Introduction to Computer Science", oldDescription, oldLecturer);

        course.setLecturer(newLecturer);
        course.setDescription(newDescription);

        assertEquals(course.getLecturer(), newLecturer, "Lecturer should match");
        assertEquals(course.getDescription(), newDescription, "Description should match");
    }
    @Test
    void setAndGetLecturerProperties() {

        String newFirstName = "Robert";
        String newLastName = "Smith";
        AcademicTitle newTitle = AcademicTitle.ASSOCIATE_PROFESSOR;

        Lecturer lecturer = new Lecturer("L001", "John Doe");

        lecturer.setFirstName(newFirstName);
        lecturer.setLastName(newLastName);
        lecturer.setAcademicTitle(newTitle);

        assertEquals(lecturer.getFirstName(), newFirstName, "First name should match");
        assertEquals(lecturer.getLastName(), newLastName, "Last name should match");
        assertEquals(lecturer.getAcademicTitle(), newTitle, "Title should match");
    }



}
