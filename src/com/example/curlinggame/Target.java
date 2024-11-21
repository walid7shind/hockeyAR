package com.example.curlinggame;
import java.awt.Point;

public class Target {
    private Point position;
    private float radius;

    public Target(Point position, float radius) {
        this.position = position;
        this.radius = radius;
    }

    public Point getCenter() {
        return position;
    }

    public boolean detectProximity(Token token) {
     
        double distance = position.distance(token.getPosition());
        int error = 20; // erreur pour augumenter la proba of scoring
        return distance <= radius+error;
    }
    public double displayproximity(Token token) {
    	return position.distance(token.getPosition());
    }

    public float getRadius() {
        return radius;
    }
}
