package com.kodilla;

import javafx.scene.layout.Pane;


public class Cell extends Pane {
    private Pawn pawn;
    private int numberPlayer = 0;

    public Cell() {
        setStyle("-fx-border-color: rgba(84,220,26,0.91)");
        this.setPrefSize(70, 70);
    }

    public int getNumberPlayer() {
        return numberPlayer;
    }

    public void setNumberPlayer(int occupied) {
        numberPlayer = occupied;

        if (numberPlayer == 1) {
            pawn = new Pawn(true);
            getChildren().add(pawn.getImage());
        } else if (numberPlayer == 2) {
            pawn = new Pawn(false);
            getChildren().add(pawn.getImage());
        } else {
            getChildren().removeAll();
        }
    }

    public void computerMoves() {
        if (getNumberPlayer() == 0) {
            setNumberPlayer(2);
        } else {
            System.out.println("Computer - this cell is taken!");
        }
    }

    public void handleMouseClick() {

        if (getNumberPlayer() == 0) {
            setNumberPlayer(1);
        } else {
            System.out.println("This cell is taken!");
        }
    }
}
