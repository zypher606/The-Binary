package me.ashimrajkonwar.thebinary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by cypher606 on 20/7/17.
 */

public class BallFrame {
    //bitmap to get characters from image
    private Bitmap bitmap;
    private String ballFrameID;
    private int bitmapWidth;
    private int bitmapHeight;

    //Coordinates;
    private int x;
    private int y;

    // Motion speed of the character
    private int speed = 0;

    // Variable to track the boosting mode
    private boolean boosting;

    // Gravity value declare to add gravity to the ship
    private final int GRAVITY = -10;

    // Control ship from moving outside the view frame
    private int minY;
    private int maxY;

    // Limit bound Ship speed
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;


    private Rect detectCollision;

    private Context context;

    // construct player
    public BallFrame(Context context, int screenX, int screenY, int ballIndex) {

        this.context = context;

        speed = 1;
        ballFrameID = "zero";

        // get bitmap from drawable resources
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_zero);
//        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_one);
        bitmap = Bitmap.createScaledBitmap(b, 150, 150, false);

        maxY = screenY - bitmap.getHeight();
        minY = 0;

        x = ballIndex * (screenX - 3*bitmap.getWidth()/2)/3 + bitmap.getWidth();
        y = maxY - 100;

        // setting the boosting value to false initially
        boosting = false;

        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();

        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }

    // method to update the coordinates of the player
    public void update() {

//        // Control boost
//        if (boosting) {
//            speed += 2;
//        } else {
//            speed -= 5;
//        }
//
//        // Control the top speed
//        if (speed > MAX_SPEED) {
//            speed = MAX_SPEED;
//        }
//
//        // Control the min speed
//        if (speed < MIN_SPEED) {
//            speed = MIN_SPEED;
//        }
//
//        // Moving the ship down
//        y -= speed + GRAVITY;
//
//
//        // limiting the moving frame
//        if (y < minY) {
//            y = minY;
//
//        }
//        if (y > maxY) {
//            y = maxY;
//        }

        // Updating X coordinate
//        x++;


        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }


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

    public int getBitmapWidth() {
        return bitmapWidth;
    }

    public int getBitmapHeight() {
        return bitmapHeight;
    }

    public void toggleBitmap() {
        if (ballFrameID == "zero") {
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_one);
            bitmap = Bitmap.createScaledBitmap(b, 150, 150, false);
            ballFrameID = "one";
        }
        else {
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball_zero);
            bitmap = Bitmap.createScaledBitmap(b, 150, 150, false);
            ballFrameID = "zero";
        }
    }


}
