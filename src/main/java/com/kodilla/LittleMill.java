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

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class LittleMill extends Application {

    private Image imageBoard = new Image("file:src/main/resources/little+mill2.png");

    //   private boolean playerTurn;

    private Label playerLbl = new Label("Your pawns: 4");
    private Label resultLbl = new Label("       The game continues");
    private Label gameRules = new Label("There are two \nplayers who\n have 4 pawn\n each \n(black and \nwhite).\n" +
            "\nThe players \narrange \ntheir pawn on \nthe board \nspaces \nin turns (black \ncircles),\n the player\n with white pawn \nbegins (YOU!).\n" +
            "\nThe winner is \nthe one who \nsets three pawn \nin a line \nhorizontally or \nvertically.");

    private Cell[][] cell = new Cell[3][3];

//    File savedHashMaps = new File("file:src/main/ranking.list");
//    Map<Long, Long> map = new HashMap<>();
//
//    public void saveMap() {
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(savedHashMaps));
//            oos.writeObject(map);
//            oos.close();
//        } catch (Exception e) {
//            // obsługa błędów
//            System.out.println("The game is not saved.");
//        }
//    }
//
//    public void loadMap() {
//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedHashMaps));
//            Object readMap = ois.readObject();
//            if(readMap instanceof HashMap) {
//                map.putAll((HashMap) readMap);
//            }
//            ois.close();
//        } catch (Exception e) {
//            // obsługa błędów
//            System.out.println("The game results are not shown.");
//        }
//    }


    public int cellSum() {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum += cell[i][j].getNumberPlayer();
            }
        }
        return sum;
    }

    public boolean isFull() {
        if (cellSum() == 12) {

            return true;

        }
        return false;
    }

    public void textResultLbl() {

        if (cellSum() == 1 || cellSum() == 3) {
            playerLbl.setText("Your pawns: 3");
        }
        if (cellSum() == 4 || cellSum() == 6) {
            playerLbl.setText("Your pawns: 2");
        }
        if (cellSum() == 7 || cellSum() == 9) {
            playerLbl.setText("Your pawns: 1");
        }
        if (cellSum() == 10 || cellSum() == 12) {
            playerLbl.setText("Your pawns: 0");
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


    public class Cell extends Pane {
        private Pawn pawn;
        private int numberPlayer = 0;

        public Cell() {
       //   setStyle("-fx-border-color: rgba(84,220,26,0.91)");
            this.setPrefSize(70, 70);
            this.setOnMouseClicked(e -> {

                if (!isFull()) {
                    System.out.println("Clicked");
                    if (handleMouseClick()) {
                        return;
                    }

                    if (cellSum() == 1 || cellSum() == 4 || cellSum() == 7 || cellSum() == 10 && !isWinner(1)) {
                        if (computerMoves()) {
                            return;
                        }
                    }
                    System.out.println("The cell sum = " + cellSum());
                    resultLbl.setText("     Your turn");

                    if (isWinner(1)) {
                        resultLbl.setText(" You won! The game is over!");
                    }

                }

            });
        }

        public int getNumberPlayer() {
            return numberPlayer;
        }

        public void setNumberPlayer(int occupied) {
            numberPlayer = occupied;
            pawn = new Pawn(true);
            if (numberPlayer == 1) {
                pawn = new Pawn(true);
                getChildren().add(pawn.getImage());
            } else if (numberPlayer == 2) {
                pawn = new Pawn(false);
                getChildren().add(pawn.getImage());

            } else if (numberPlayer == 0) {

                getChildren().removeAll();
            }
        }


        public boolean computerMoves() {
            boolean isEmpty = true;

            while (isEmpty) {
                Random vertical = new Random();
                int i = vertical.nextInt(3);
                Random horizontal = new Random();
                int j = horizontal.nextInt(3);

                if (cell[i][j].getNumberPlayer() == 0) {
                    cell[i][j].setNumberPlayer(2);
                    System.out.println("The black pawn placed!");
                    isEmpty = false;
                }
            }

            if (isWinner(2)) {
                resultLbl.setText(" The computer won! The game is over!");
                return true;
            } else if (isFull()) {
                resultLbl.setText(" Nobody won ! The game is over!");
                return true;
            }
            return false;
        }

        public boolean handleMouseClick() {

            if (isWinner(1)) {
                return true;

            } else {

                if (getNumberPlayer() == 0) {
                    setNumberPlayer(1);

                    textResultLbl();

                } else {
                    System.out.println("This cell is taken!");
                }
                return false;
            }
        }

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
                grid.add(cell[i][j] = new Cell(), i + 1, j + 1);
            }
        }

        Scene scene = new Scene(grid, 860, 690);

        primaryStage.setTitle("Little Mill");
        primaryStage.setScene(scene);
        primaryStage.show();
        //     while(true){
//
//        }
//
    }

    public static void main(String[] args) {
        launch(args);
    }
}
