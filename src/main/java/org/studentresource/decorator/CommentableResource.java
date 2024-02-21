package org.studentresource.decorator;

import org.studentresource.StudentResource;

import java.util.ArrayList;
import java.util.List;

// This class should allow adding comments to the resource
public class CommentableResource extends ResourceDecorator {

    public CommentableResource(StudentResource decoratedResource) {
        super(decoratedResource);
    }

    private final ArrayList<String> comments = new ArrayList<>();

    public void addComment(String comment) {
        comments.add(comment);
    }

    // get last comment
    public String getComment() {
        return comments.get(comments.size() - 1);
    }

    public List<String> getAllComments() {
        return comments;
    }
    @Override
    public String toString() {
        return decoratedResource.toString() + " comments: " + comments;
    }

}
