package com.example.runninggame;

import android.graphics.Bitmap;

public class SpriteRole {
    private int roleX;
    private int roleY;
    private int score = 0;
    private boolean front = false;
    private boolean behind = false;
    private int type = 0;
    private Bitmap bitmap;

    public SpriteRole(int x, int y){
        this.roleX = x;
        this.roleY = y;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBehind(boolean behind) {
        this.behind = behind;
        if(this.front && this.behind) type = 3; //前进
        else if(!this.front && this.behind) type = 4; //倒退完成一半
    }

    public void setFront(boolean front) {
        this.front = front;
        if(this.front && !this.behind) type = 1;  //前进完成一半
        else if(this.front && this.behind) type = 2; //倒退
    }

    public int getRoleX() {
        return roleX;
    }

    public void setRoleX(int roleX) {
        this.roleX = roleX;
    }

    public int getRoleY() {
        return roleY;
    }

    public void setRoleY(int roleY) {
        this.roleY = roleY;
    }

    public void run(){
        if(type == 3){
            roleX -= 100;
            front = false;
            behind = false;
            type = 0; //停止
        }
        else if(type == 2){
            roleX += 100;
            front = false;
            behind = false;
            type = 0; //停止
        }
    }

    public boolean isEscape(int beeX){
        if(roleX < 400 && roleX < beeX)
            return true;
        else return false;
    }

    public int getScore(int beeDistance){
        double curScore = (beeDistance - this.roleX) * 0.1;
        score = (int) curScore;
        if(score<0) score = 0;
        return score;
    }
}
