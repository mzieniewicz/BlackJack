package com.kodilla;

import javafx.scene.image.ImageView;

public class Pawn {

    private boolean ifPlayer;

    public Pawn(boolean ifPlayer) {
        this.ifPlayer = ifPlayer;
    }
public void getImage(){
        if(ifPlayer){
            ImageView img = new ImageView("file:src/main/resources/white pawn.png");
        }
        else{
            ImageView img = new ImageView("file:src/main/resources/black pawn.png");
        }
}
    public boolean isIfPlayer() {
        return ifPlayer;
    }

    public void setIfPlayer(boolean ifPlayer) {
        this.ifPlayer = ifPlayer;
    }
}
