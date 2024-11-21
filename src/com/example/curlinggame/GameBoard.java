package com.example.curlinggame;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<Token> tokens;
    private Target target;
    private Rectangle boundaries;

    public GameBoard(Rectangle boundaries, Target target) {
        this.boundaries = boundaries;
        this.target = target;
        this.tokens = new ArrayList<>();
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public void removeToken(Token token) {
        tokens.remove(token);
    }

    public void resetBoard() {
        tokens.clear();
    }

    public Rectangle getBoundaries() {
        return boundaries;
    }

    public Target getTarget() {
        return target;
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
