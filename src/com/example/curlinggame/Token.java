package com.example.curlinggame;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Token {
    private Point position;
    private Player owner;
    private static Random random = new Random();
    
   // private static String  color;

    public Token(Player owner ) {
        this.owner = owner;
        this.position = new Point(0, 0); 
        //this.color = color;
    }

    public void move(Point newPosition) {
        this.position = newPosition;
    }

    public void moveRandom(Rectangle boundaries) {
        int x = random.nextInt(boundaries.width) + boundaries.x;
        int y = random.nextInt(boundaries.height) + boundaries.y;
        this.position = new Point(x, y);
    }
 
    public Point getPosition() {
        return this.position;
    }

    public Player getOwner() {
        return this.owner;
    }
}
