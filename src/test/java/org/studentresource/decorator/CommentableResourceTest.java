package org.studentresource.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.studentresource.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommentableResourceTest {
    private CommentableResource commentableResource;

    @BeforeEach
    void setUp() {
        Course course = new Course("CS101", "Introduction to Computer Science");
        commentableResource = new CommentableResource(course);
    }

    @Test
    void addAndGetCommentTest() {
        String comment = "Excellent course for beginners.";
        commentableResource.addComment(comment);

        assertEquals(comment, commentableResource.getComment(), "The comment should match the added one.");
    }
    @Test
    void getAllCommentsTest() {
        String comment1 = "Excellent course for beginners.";
        String comment2 = "Very informative course.";
        commentableResource.addComment(comment1);
        commentableResource.addComment(comment2);

        assertEquals(2, commentableResource.getAllComments().size(), "The size of the comments should match.");
        assertTrue(commentableResource.getAllComments().contains(comment1), "The comment should be in the list.");
        assertTrue(commentableResource.getAllComments().contains(comment2), "The comment should be in the list.");
    }
}
