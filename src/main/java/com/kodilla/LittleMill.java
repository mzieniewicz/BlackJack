package com.kodilla;


import javafx.application.Application;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.Random;


public class LittleMill extends Application {

    private final Image imageBoard = new Image("file:src/main/resources/little+mill2.png");

    //   private boolean playerTurn;

    private final Label playerLbl = new Label("Your pawns: 4");
    private final Label resultLbl = new Label("       The game continues");
    private final Label gameRules = new Label("There are two \nplayers who\n have 4 pawn\n each \n(black and \nwhite).\n" +
            "\nThe players \narrange \ntheir pawn on \nthe board \nspaces \nin turns (black \ncircles),\n the player\n with white pawn \nbegins (YOU!).\n" +
            "\nThe winner is \nthe one who \nsets three pawn \nin a line \nhorizontally or \nvertically.");

    private final Cell[][] cell = new Cell[3][3];


    private long getNumberOfEmptyCells(GridPane gridPane) {
        return gridPane.getChildren().stream()
                .filter(node -> node instanceof Cell)
                .map(node -> (Cell) node)
                .filter(cell -> cell.getNumberPlayer() == 0)
                .count();
        //.filter(cell->cell.isEmpty);
    }


    public void randomComputerMoves() {
        boolean isEmpty = true;

        if (!isWinner(1)) {
            while (isEmpty) {
                Random vertical = new Random();
                int i = vertical.nextInt(3);
                Random horizontal = new Random();
                int j = horizontal.nextInt(3);

                if (cell[i][j].getNumberPlayer() == 0) {
                    cell[i][j].computerMoves();
                    System.out.println("The black pawn placed!");
                    isEmpty = false;
                }
            }
        } else {
            resultLbl.setText(" You won! The game is over!");
        }
    }


    public boolean isWinner(int numberPlayer) {
        System.out.println("Checking if somebody win." + numberPlayer);
        for (int i = 0; i < 3; i++) {
            if (cell[i][0].getNumberPlayer() == numberPlayer
                    && cell[i][1].getNumberPlayer() == numberPlayer
                    && cell[i][2].getNumberPlayer() == numberPlayer) {
                System.out.println(numberPlayer + "WINN!");
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (cell[0][j].getNumberPlayer() == numberPlayer
                    && cell[1][j].getNumberPlayer() == numberPlayer
                    && cell[2][j].getNumberPlayer() == numberPlayer) {
                System.out.println(numberPlayer + "WINN!");
                return true;
            }
        }

        return false;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        playerLbl.setFont(new Font("Arial", 30));
        playerLbl.setTextFill(Color.web("#81f"));

        resultLbl.setFont(new Font("Arial", 30));
        resultLbl.setTextFill(Color.web("e21"));

        gameRules.setFont(new Font("Arial", 16));
        gameRules.setTextFill(Color.web("#111"));
        gameRules.setStyle("-fx-background-color:#f6f3df");
        gameRules.setTextAlignment(TextAlignment.CENTER);
        gameRules.setWrapText(true);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setPadding(new Insets(5, 0, 5, 5));
        grid.setHgap(100);
        grid.setVgap(100);
        grid.setBackground(background);

        grid.add(playerLbl, 1, 0, 2, 1);
        grid.add(resultLbl, 1, 4, 4, 1);
        grid.add(gameRules, 0, 1, 1, 4);

        Button newbtn = new Button("New game");
        newbtn.setFont(new Font("Arial", 16));
        newbtn.setOnAction((e) -> {

            System.out.println("Restarting app - New game!");
            primaryStage.close();
            Platform.runLater(() -> {
                try {
                    new LittleMill().start(new Stage());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });

        });

        grid.add(newbtn, 0, 0, 1, 1);


        Button endbtn = new Button("EXIT");
        endbtn.setFont(new Font("Arial", 20));
        endbtn.setOnAction((e) -> {
            System.out.println("End of the game - exit");
            Platform.exit();
        });

        grid.add(endbtn, 4, 0, 1, 1);


        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                Cell emptyCell = new Cell();
                grid.add(cell[i][j] = emptyCell, i + 1, j + 1);
                final int[] numberOfWhitePawn = {3};
                emptyCell.setOnMouseClicked(e -> {

                    if (getNumberOfEmptyCells(grid) > 1) {
                        if (!isWinner(1)) {

                            System.out.println("Clicked");
                            System.out.println("Number of empty cells: " + getNumberOfEmptyCells(grid));

                            if (!isWinner(2)) {
                                emptyCell.handleMouseClick();
                                playerLbl.setText("Your pawns: " + numberOfWhitePawn[0]);
                                numberOfWhitePawn[0]--;


                                if (getNumberOfEmptyCells(grid) % 2 == 0) {
                                    randomComputerMoves();
                                    System.out.println("Number of empty cells: " + getNumberOfEmptyCells(grid));
                                }
                            } else {
                                resultLbl.setText(" The computer won! The game is over!");
                            }
                        }

                    } else {
                        resultLbl.setText(" Nobody won ! The game is over!");
                    }

                });
            }

        }
        Scene scene = new Scene(grid, 860, 690);

        primaryStage.setTitle("Little Mill");
        primaryStage.setScene(scene);
        primaryStage.show();


//
//        if (isWinner(1)) {
//            resultLbl.setText(" You won! The game is over!");
//        }
//
//        if (isWinner(1)) {
//
//        }
//        if (isWinner(2)) {
//            resultLbl.setText(" The computer won! The game is over!");
//
//        }
//                    else if (getNumberOfEmptyCells(grid)>1) {
//                        resultLbl.setText(" Nobody won ! The game is over!");
//
//                    }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
