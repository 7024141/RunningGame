package com.example.runninggame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private MyThread myThread;
    private Context context;
    private boolean isBeeOutFlag = false;
    private boolean winFlag = false;
    private boolean loseFlag = false;
    private SpriteRole spriteRole;
    private SpriteBee spriteBee;

    public MySurfaceView(Context context) {
        super(context);
        this.context = context;
        holder = this.getHolder();
        holder.addCallback(this);
        myThread = new MyThread(holder);//创建一个绘图线程
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myThread.isRun = true;
        myThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        myThread.isRun = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if(touchX > 1550 && touchX < 1750 && touchY > 500 && touchY < 750){
            isBeeOutFlag = true;
        }
        if(touchX > 700 && touchX < 850 && touchY > 700 && touchY < 850){
            spriteRole.setFront(true);
        }
        if(touchX > 900 && touchX < 1050 && touchY > 700 && touchY < 850){
            spriteRole.setBehind(true);
        }

        if(winFlag && event.getAction() == MotionEvent.ACTION_DOWN){
            Intent intent = new Intent("com.example.runninggame.ACTION_START");
            intent.putExtra("score", spriteRole.getScore(spriteBee.getBeeX()));
            this.context.startActivity(intent);
            System.exit(0);
        }

        if(loseFlag && event.getAction() == MotionEvent.ACTION_DOWN){
            Intent intent = new Intent("com.example.runninggame.ACTION_LOSE");
            this.context.startActivity(intent);
            System.exit(0);
        }

        return super.onTouchEvent(event);
    }

    class MyThread extends Thread {
        private SurfaceHolder holder;
        public boolean isRun;
        private Bitmap sun;
        private Bitmap flower;
        private Bitmap house;
        private Bitmap left;
        private Bitmap leftDark;
        private Bitmap right;
        private Bitmap rightDark;

        public MyThread(SurfaceHolder holder) {
            this.holder = holder;
            isRun = true;
        }

        public void loadImgSource(){
            sun = AssetsSource.getImageFromAssetsFile(MySurfaceView.this.context,
                    "png/sun/256w/sunArtboard 1xxxhdpi.png");
            house = AssetsSource.getImageFromAssetsFile(MySurfaceView.this.context,
                    "png/house/512w/houseArtboard 1512.png");
            flower = AssetsSource.getImageFromAssetsFile(MySurfaceView.this.context,
                    "png/flower/256w/flowerArtboard 1xxxhdpi.png");
            Bitmap spriteBeeMap = AssetsSource.getImageFromAssetsFile(MySurfaceView.this.context,
                    "png/honey/256w/honeyArtboard 1xxxhdpi.png");
            Bitmap spriteRoleMap = AssetsSource.getImageFromAssetsFile(MySurfaceView.this.context,
                    "png/kangaroo/256w/kangarooArtboard 1xxxhdpi.png");
            left = AssetsSource.getImageFromAssetsFile(MySurfaceView.this.context,
                    "png/zuojiantou.png");
            leftDark = AssetsSource.getImageFromAssetsFile(MySurfaceView.this.context,
                    "png/zuojiantou_dark.png");
            right = AssetsSource.getImageFromAssetsFile(MySurfaceView.this.context,
                    "png/youjiantou.png");
            rightDark = AssetsSource.getImageFromAssetsFile(MySurfaceView.this.context,
                    "png/youjiantou_dark.png");

            spriteBee = new SpriteBee(1600, 520);
            spriteBee.setBitmap(spriteBeeMap);

            spriteRole = new SpriteRole(1400, 520);
            spriteRole.setBitmap(spriteRoleMap);
        }

        @Override
        public void run() {
            int sunX = 120; int sunY = 100;
            int count = 10;
            int direc = 1;
            loadImgSource();
            while (isRun) {
                Canvas c = null;
                try {
                    synchronized (holder) {
                        c = holder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
                        c.drawColor(Color.WHITE);//设置画布背景颜色
                        Paint p = new Paint(); //创建画笔
                        p.setColor(Color.BLACK);
                        p.setTextSize(100);

                        String strScore = "Score: ";
                        c.drawText(strScore, 750, 300, p);

                        if(count>0 && count<=10){count--; sunX+=5; sunY-=count;}
                        else if(count>-10 && count<=0){sunX-=5; sunY-=count; count--;}
                        if(count == -10) count = 10;

                        c.drawBitmap(sun, sunX, sunY, null);
                        c.drawBitmap(house, 0, 350, null);
                        c.drawBitmap(flower, 1550, 500, null);

                        c.drawBitmap(spriteRole.getBitmap(), spriteRole.getRoleX(), spriteRole.getRoleY(), null);

                        if(isBeeOutFlag){
                            c.drawBitmap(spriteBee.getBitmap(), spriteBee.getBeeX(), spriteBee.getBeeY(), null);
                            if(spriteRole.isEscape(spriteBee.getBeeX())){
                                spriteBee.stop(spriteBee.getBeeX());
                                winFlag = true;
                            }
                            else if(spriteBee.isCatchRole(spriteRole.getRoleX())){
                                spriteBee.stop(spriteBee.getBeeX());
                                loseFlag = true;
                            }
                            else spriteBee.fly();
                            if(direc == 1){spriteBee.setBeeY(spriteBee.getBeeY()+direc*3); direc = -1;}
                            else {spriteBee.setBeeY(spriteBee.getBeeY()+direc*3); direc = 1;}

                            strScore = "Score: " + spriteRole.getScore(spriteBee.getBeeX());
                            c.drawText(strScore, 750, 300, p);
                        }

                        c.drawBitmap(left, 700, 700, null);
                        c.drawBitmap(right, 900, 700, null);

                        p.setTextSize(50);

                        if(!isBeeOutFlag) {
                            c.drawText("Touch the flower...", 1350, 400, p);
                        }
                        else c.drawText("Touch arrow each in turn, let kagroo get home", 400, 960, p);

                        spriteRole.run();

                        Thread.sleep(20);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        holder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。
                    }
                }
            }
        }
    }
}
