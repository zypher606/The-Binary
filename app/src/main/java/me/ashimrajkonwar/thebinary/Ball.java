package me.ashimrajkonwar.thebinary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

/**
 * Created by cypher606 on 20/7/17.
 */

public class Ball {

    private Bitmap bitmap;

    private int x;
    private int y;

    // enemy speed
    private int speed = 1;

    private int maxX;
    private int minX;
    private int maxY;
    private int minY;

    // Create a rect object;
    private Rect detectCollision;

    private String ballFrameID;

    private Context context;

    public Ball(Context context, int screenX, int screenY, int ballPosX, int ballPosY) {

        this.context = context;

        Random generator = new Random();
        int randBmp = generator.nextInt(2);
//        Log.d("rand", Integer.toString(randBmp));
        if (randBmp == 1) {
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_one);
            bitmap = Bitmap.createScaledBitmap(b, 150, 150, false);
            ballFrameID = "one";
        } else {
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_zero);
            bitmap = Bitmap.createScaledBitmap(b, 150, 150, false);
            ballFrameID = "zero";
        }

//        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.selector_one);

        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = -bitmap.getHeight();


        speed = 10;
        x = ballPosX * (screenX - 3*bitmap.getWidth()/2)/3 + bitmap.getWidth();
        y = - ballPosY * (screenY/3);

        // Initializing rect object
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update(int playerSpeed) {
        y += playerSpeed;
        y += speed;

        if (y > maxY + bitmap.getHeight()) {
            Random generator = new Random();
            int randBmp = generator.nextInt(2);
//        Log.d("rand", Integer.toString(randBmp));
            if (randBmp == 1) {
                Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_one);
                bitmap = Bitmap.createScaledBitmap(b, 150, 150, false);
                ballFrameID = "one";
            } else {
                Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_zero);
                bitmap = Bitmap.createScaledBitmap(b, 150, 150, false);
                ballFrameID = "zero";
            }
            speed = 10;
//            x = ;
            y = minY;
        }

        // Adding the top,left, bottom and right to the rect object
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    // Adding a setter to X - coordinate to change it after collision
    public void setX(int x) {
        this.x = x;
    }

    //getter to get collision object
    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public String getBallFrameID() {
        return ballFrameID;
    }
}
