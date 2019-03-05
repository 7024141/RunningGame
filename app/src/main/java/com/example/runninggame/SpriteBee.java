package com.example.runninggame;

import android.graphics.Bitmap;

public class SpriteBee {
    private Bitmap bitmap;
    private int beeX;
    private int beeY;

    public SpriteBee(int x, int y){
        this.beeX = x;
        this.beeY = y;
    }

    public void setBeeX(int beeX) {
        this.beeX = beeX;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setBeeY(int beeY) {
        this.beeY = beeY;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getBeeX() {
        return beeX;
    }

    public int getBeeY() {
        return beeY;
    }

    public boolean isCatchRole(int roleX){
        if(this.beeX <= roleX){return true;}
        else return false;
    }

    public void fly(){
        if(this.beeX > 100) this.beeX = this.beeX - 15;
        else this.beeX = 100;
    }

    public void stop(int x){
        this.beeX = x;
    }
}
