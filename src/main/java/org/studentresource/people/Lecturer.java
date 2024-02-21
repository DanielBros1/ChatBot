package org.studentresource.people;

import org.studentresource.enums.AcademicTitle;

public class Lecturer {

    String firstName;
    String lastName;

    AcademicTitle academicTitle;

    public Lecturer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Lecturer(String firstName, String lastName, AcademicTitle academicTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicTitle = academicTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }
}
