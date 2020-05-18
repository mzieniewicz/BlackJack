package com.kodilla;


import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.List;
import java.util.Random;

import static javafx.application.Application.launch;

public class LittleMill extends Application {

    private Image imageBoard = new Image("file:src/main/resources/little+mill2.png");
    // private Image whitePawn = new Image("file:src/main/resources/white pawn.png");
    //private Image blackPawn = new Image("file:src/main/resources/black pawn.png");

    //ImageView img1 = new ImageView("file:src/main/resources/white pawn.png");
    //  ImageView img2 = new ImageView("file:src/main/resources/black pawn.png");

    //   private FlowPane pawns = new FlowPane(Orientation.HORIZONTAL);

    private boolean playerTurn;

    private Label playerLbl = new Label("Your pawns");
    private Label resultLbl = new Label("The game continues");


    private Cell[][] cell = new Cell[3][3];


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
        if (cellSum() >= 12) {
            return true;
        }
        return false;
    }

    public boolean isWinner(int numberPlayer) {
        System.out.println("Checking if somebody win."+numberPlayer);
        for (int i = 0; i < 3; i++) {
            if (cell[i][0].getNumberPlayer() == numberPlayer
                    && cell[i][1].getNumberPlayer() == numberPlayer
                    && cell[i][2].getNumberPlayer() == numberPlayer){
                System.out.println(numberPlayer+ "WINN!");
                return true;}
        }
        for (int j = 0; j < 3; j++) {
            if (cell[0][j].getNumberPlayer() == numberPlayer
                    && cell[1][j].getNumberPlayer() == numberPlayer
                    && cell[2][j].getNumberPlayer() == numberPlayer){
                System.out.println(numberPlayer+ "WINN!");
                return true;}
        }

        return false;
    }


    public class Cell extends Pane {
        private Pawn pawn;
        private int numberPlayer = 0;

        public Cell() {
            setStyle("-fx-border-color: rgba(114,226,10,0.91)");
            this.setPrefSize(50, 50);
            this.setOnMouseClicked(e -> {
                if (!isFull()) {
                    System.out.println("Clicked");
                    if(handleMouseClick()){
                        return;
                    }

                    if (cellSum() == 1 || cellSum() == 4 || cellSum() == 7 || cellSum() == 10) {
                        if(computerMoves()){
                            return;
                        }
                    }
                    System.out.println("The cell sum = " + cellSum());
                    resultLbl.setText("Your turn");
//                } else {
//                    if (numberPlayer == 0) {
//                        resultLbl.setText(" Nobody won ! The game is over!");
//                    } else if (numberPlayer == 1) {
//                        resultLbl.setText("You won! The game is over!");
//                    } else if (numberPlayer == 2) {
//                        resultLbl.setText("The computer won! The game is over!");
//                    }
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
                ;
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
                resultLbl.setText("The computer won! The game is over!");
                return true;
            }
            return false;
        }

        public boolean handleMouseClick() {

            if (getNumberPlayer() == 0) {
                setNumberPlayer(1);
                if (isWinner(1)) {
                    resultLbl.setText("You won! The game is over!");
                    return true;
                }

            } else {
                System.out.println("This cell is taken!");
            }
            return false;

        }


    }

//public int randomNumber(){}
    // public void startGame

    // {
    //  Random whoseFirst =new Random();
    //   if (whoseFirst.nextInt(10)<4){


    // }


    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        playerLbl.setFont(new Font("Arial", 24));
        playerLbl.setTextFill(Color.web("#FFF"));

        resultLbl.setFont(new Font("Arial", 24));
        resultLbl.setTextFill(Color.web("#FFF"));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setPadding(new Insets(5, 15, 5, 5));
        grid.setHgap(120);
        grid.setVgap(120);
        grid.setBackground(background);

        grid.add(playerLbl, 1, 0, 2, 1);
        grid.add(resultLbl, 1, 4, 3, 1);

        Button newbtn = new Button("Nowe rozdanie");
        newbtn.setOnAction((e) -> {
            System.out.println("Losowanie gracza i nowa gra");
        });
        grid.add(newbtn, 0, 0, 1, 1);

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                grid.add(cell[i][j] = new Cell(), i + 1, j + 1);
            }
        }


    /* ImageView img = new ImageView(blackPawn);
     pawns.getChildren().add(img);

     grid.add(pawns,1, 1);
     */
        Scene scene = new Scene(grid, 860, 710);

        primaryStage.setTitle("Little Mill");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
