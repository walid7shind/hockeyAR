package com.example.curlinggame;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int score;
    private List<Token> tokens;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.tokens = new ArrayList<>();
    }

    public Token throwToken(Rectangle boundaries) {
       
        Token token = new Token(this);
        token.moveRandom(boundaries);
        tokens.add(token);
        return token;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public String getName() {
        return this.name;
    }
}
