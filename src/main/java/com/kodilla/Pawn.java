package com.kodilla;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Pawn {

    private final boolean ifPlayer;

    public Pawn(boolean ifPlayer) {
        this.ifPlayer = ifPlayer;
    }

    public Node getImage() {
        ImageView img = new ImageView();
        if (ifPlayer) {
            img = new ImageView("file:src/main/resources/white pawn.png");
        } else {
            img = new ImageView("file:src/main/resources/black pawn.png");
        }
        return img;
    }


}
