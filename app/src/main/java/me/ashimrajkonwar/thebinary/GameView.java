package me.ashimrajkonwar.thebinary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by cypher606 on 20/7/17.
 */

public class GameView extends SurfaceView implements Runnable{

    // to track if the game is playing or not
    volatile boolean playing;

    // game thread
    private Thread gameThread = null;


    private Player player;

    //Adding the BallFrames to this class
    private BallFrame[] ballFrames;
    private int ballFrameCount = 3;

    // define objects used for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;


    // Adding enemy object array;
    private Ball[][] balls;
    private int ballRows = 3;
    private int ballCols = 3;

    private Context context;
    // Adding start
//    private ArrayList<Star> stars = new ArrayList<Star>();

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.context = context;

        //init player object
        player = new Player(context);

        ballFrames = new BallFrame[ballFrameCount];
        int ballFramePos = screenX/3;
        for (int i=0; i<ballFrameCount; i++) {
            ballFrames[i] = new BallFrame(context, screenX, screenY, i);
        }

        // initializing objects used for drawing
        surfaceHolder = getHolder();
        paint = new Paint();

        // Adding 100 stars
//        int starNums = 100;
//        for (int i=0; i<starNums; i++) {
//            Star s =new Star(screenX, screenY);
//            stars.add(s);
//        }

        balls = new Ball[ballRows][ballCols];
        for (int i=0; i<ballRows; i++) {
            for (int j=0; j<ballCols; j++) {
                balls[i][j] = new Ball(context, screenX, screenY, j, i);
            }

        }

    }

    @Override
    public void run() {
        while (playing) {
            // to update the frame
            update();

            //draw the frame
            draw();

            // control
            control();

        }
    }

    private void update() {
        for (int i=0; i<ballFrameCount; i++) {
            ballFrames[i].update();
        }
//        // Update the stars
//        for (Star s : stars) {
//            s.update(player.getSpeed());
//        }

        // Update enemies
        for (int i=0; i<ballRows; i++) {
            for (int j=0; j<ballCols; j++) {
                balls[i][j].update(player.getSpeed());

                // If collision occurs with Ballframe
                if (Rect.intersects(ballFrames[i].getDetectCollision(), balls[i][j].getDetectCollision())) {

                    Log.d("xx", "collision encountered");
                    if (ballFrames[i].getBallFrameID() == balls[i][j].getBallFrameID()) {
                        // Increase the score
                    }
                    else {
                        // Game over
                        context.startActivity(new Intent(context.getApplicationContext(), GameOver.class));
                    }

//                Log
//                boom.setX(enemies[i].getX());
//                boom.setY(enemies[i].getY());

//                enemies[i].setX(-400);
                }
            }



        }
    }

    private void draw() {
        // Check if surface is valid or not
        if (surfaceHolder.getSurface().isValid()) {
            // Locking the surface
            canvas = surfaceHolder.lockCanvas();

            // Drawing a background color for canvas
            canvas.drawColor(Color.BLACK);


            // Set stars color WHite
//            paint.setColor(Color.WHITE);

            //Drawing stars
//            for (Star s : stars) {
//                paint.setStrokeWidth(s.getStarWidth());
//                canvas.drawPoint(s.getX(), s.getY(), paint);
//            }



            // Drawing the Ball frames
            for (int i=0; i<ballFrameCount; i++) {
                canvas.drawBitmap(
                        ballFrames[i].getBitmap(),
                        ballFrames[i].getX(),
                        ballFrames[i].getY(),
                        paint
                );
            }


            // Drawing the enemies
            for (int i=0; i<ballRows; i++) {
                for (int j=0; j<ballCols; j++) {
                    canvas.drawBitmap(
                            balls[i][j].getBitmap(),
                            balls[i][j].getX(),
                            balls[i][j].getY(),
                            paint
                    );
                }

            }




            // Unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        // when the game is paused, setting the variable to pause
        playing = false;

        try {
            // Stop the thread
            gameThread.join();
        } catch (InterruptedException e) {

        }
    }

    public void resume() {
        // game is resumed, star the thread again
        playing =true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        int action = motionEvent.getAction();
        float x = motionEvent.getX();  // or getRawX();
        float y = motionEvent.getY();


        switch(action){
            case MotionEvent.ACTION_DOWN:
                for (int i=0; i<ballFrameCount; i++) {
                    if (x >= ballFrames[i].getX() && x < (ballFrames[i].getX() + ballFrames[i].getBitmapWidth())
                            && y >= ballFrames[i].getY() && y < (ballFrames[i].getY()+ ballFrames[i].getBitmapHeight())) {
                        Log.d("qqq", Integer.toString(i));
                        ballFrames[i].toggleBitmap();
                    }
                }

                break;
        }



//        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_UP:
//                // When the user touches the screen
////                player.stopBoosting();
//                break;
//            case MotionEvent.ACTION_DOWN:
//                // When the user releases the touch
////                player.setBoosting();
//                break;
//        }

        return true;
    }

}
