package org.studentresource.decorator;

import org.studentresource.StudentResource;

public class RateableResource extends ResourceDecorator {

    public RateableResource(StudentResource decoratedResource) {
        super(decoratedResource);
    }

    private double rating = 0.0;

    // set rating. Rating should be and double between. 0.0 and 5.0
    public void setRating(double rating){
        if (rating >= 0.0 && rating <= 5.0) this.rating = rating;
        else throw new IllegalArgumentException("Rating should be between 0.0 and 5.0");
    }

    public double getRating(){
        return this.rating;
    }

    @Override
    public String toString() {
        return decoratedResource.toString() + " rating: " + rating;
    }
}
