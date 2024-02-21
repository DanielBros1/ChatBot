package org.studentresource;

import org.studentresource.people.Lecturer;

public class Course implements StudentResource {
    private String id;
    private String name;
    private String description;
    private Lecturer lecturer;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Course(String id, String name, String description, Lecturer lecturer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lecturer = lecturer;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name= name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}