package com.kodilla;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Application;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

    private Image imageBoard = new Image("file:src/main/resources/little+mill.png");
  //  private Image whitePawn = new Image("file:src/main/resources/white pawn.png");
 //   private Image blackPawn = new Image("file:src/main/resources/black pawn.png");

    private FlowPane pawns = new FlowPane(Orientation.HORIZONTAL);

    private boolean playerTurn;

    private Cell[][] cell = new Cell[3][3];

    private Label playerLbl = new Label("Your pawns");
    private Label computerLbl = new Label("Computer pawns");
    private Label resultLbl = new Label();


    private void handleMouseClick() {

    }

    public class Cell extends Pane {
        private Pawn pawn;

        public Cell() {
            this.setOnMouseClicked(e -> {
                System.out.println("Clicked");
                pawn = new Pawn(true);
                handleMouseClick();
            });
        }


        ///  public String getPlace(Pawn pawn) {

        //  }

    }

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

        computerLbl.setFont(new Font("Arial", 24));
        computerLbl.setTextFill(Color.web("#FFF"));

        resultLbl.setFont(new Font("Arial", 24));
        resultLbl.setTextFill(Color.web("#FFF"));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setPadding(new Insets(5, 10, 10, 10));
        grid.setHgap(168);
        grid.setVgap(168);
        grid.setBackground(background);

        grid.add(computerLbl, 1, 0, 2, 1);
        grid.add(playerLbl, 1, 4, 2, 1);
        grid.add(resultLbl, 0, 3);

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
        Scene scene = new Scene(grid, 760, 760);

        primaryStage.setTitle("Little Mill");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}