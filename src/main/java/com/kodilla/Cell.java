package com.kodilla;

import javafx.scene.layout.Pane;

import java.util.Random;

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
//    Cell emptyCell = new Cell();
//                grid.add(cell[i][j] = emptyCell, i + 1, j + 1);
//final int[] numberOfWhitePawn = {3};
//        emptyCell.setOnMouseClicked(e -> {
//
//        System.out.println("Number of empty cells: " + getNumberOfEmptyCells(grid));
//        if (getNumberOfEmptyCells(grid) > 1) {
//        System.out.println("Clicked");
//        System.out.println("Number of empty cells: " + getNumberOfEmptyCells(grid));
//        if (!isWinner(2)||!isWinner(2)) {
//        emptyCell.handleMouseClick();
//        playerLbl.setText("Your pawns: " + numberOfWhitePawn[0]);
//        numberOfWhitePawn[0]--;}
//        if (getNumberOfEmptyCells(grid) % 2 ==0)
//        randomComputerMoves();
//
//        } else {
//        resultLbl.setText(" Nobody won ! The game is over!");
//        }
//
//        });
//        }