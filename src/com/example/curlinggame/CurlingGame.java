package com.example.curlinggame;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurlingGame {
    private List<Player> players;
    private Player currentTurn;
    private GameBoard gameBoard;
    private ImageProcessor imageProcessor;

    public CurlingGame() {
        players = new ArrayList<>();
    }

    public void startGame() {
    	
    	System.out.println("Player 1 choose your name ");
    	Scanner myObj = new Scanner(System.in);
    	String name1 = myObj.nextLine(); 
    	System.out.println("Player 2 choose you name ");
    	String name2 = myObj.nextLine();
    	Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        players.add(player1);
        players.add(player2);

        //player1 commence 
        currentTurn = player1;

        Rectangle boundaries = new Rectangle(0, 0, 100, 100); 
        Target target = new Target(new Point(50, 50), 10);
        gameBoard = new GameBoard(boundaries, target);
    	System.out.println("Set the number of turns ");
    	Scanner myObj2 = new Scanner(System.in);
    	
    	int numberOfTurns = myObj2.nextInt();
        for (int i = 0; i < numberOfTurns; i++) {
        	if(i<numberOfTurns-1) {
        		System.out.println("Turn " + (i + 1) + ": " + currentTurn.getName());
        		
        		System.out.println("throw your Token " + currentTurn.getName());
        		String ok=myObj.nextLine();
        		
        	}
        	else {
        		 System.out.println("Last turn " + (i + 1) + ": " + currentTurn.getName());
        		 System.out.println("Last one! " + currentTurn.getName());
        		 String ok=myObj.nextLine();
        	}
            
            
            Token token = currentTurn.throwToken(gameBoard.getBoundaries());
            System.out.println("distance from target : "+ target.displayproximity(token));
            gameBoard.addToken(token);
            updateScore();
            switchTurn();
        }

 
        Player winner = determineWinner();
        if (winner != null) {
            System.out.println("Winner is: " + winner.getName() + " with score: " + winner.getScore());
        }
    }

    public void switchTurn() {
        int index = players.indexOf(currentTurn);
        index = (index + 1) % players.size();
        currentTurn = players.get(index);
    }

    public void updateScore() {
        // get le dernier jeton
        List<Token> tokens = currentTurn.getTokens();
        Token token = tokens.get(tokens.size() - 1);

        if (gameBoard.getTarget().detectProximity(token)) {
            currentTurn.addScore(1);
            System.out.println(currentTurn.getName() + " scored! Total score: " + currentTurn.getScore());
        } else {
            System.out.println(currentTurn.getName() + " did not score.");
        }
    }

    private Player determineWinner() {
        int score1 = players.get(0).getScore();
        int score2 = players.get(1).getScore();
        if (score1 > score2) {
            return players.get(0);
        } else if (score2 > score1) {
            return players.get(1);
        } else {
            System.out.println("It's a tie!");
            return null;
        }
    }
}
