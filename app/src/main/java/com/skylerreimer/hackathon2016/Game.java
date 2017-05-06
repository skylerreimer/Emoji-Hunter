package com.skylerreimer.hackathon2016;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import java.util.Set;
import java.util.HashSet;

import java.util.Random;

public class Game extends View {

    private Bitmap emojis;
    private Rect finalRect;
    private int x;
    private int y;
    private int score;
    private int textSize;
    private int totalSquares;
    private int totalSquareRoot;
    private int chosenEmoji;
    private double time;
    private Rect topHalf = new Rect();
    private Paint centerText, timeText, scoreText, bgColor;
    private Handler handler;

    private Context end;
    private boolean point;
    private SparseArray<Rect> emojiArray;

    private Rect[] sourceHolder, destinationHolder;
    private Set<Integer> usedEmojis;

    //total time in ms the game will run
    private int TOTALTIME = 5000;

    /**
     * Constructor that sets initial states
     * @param context the context context being passed
     */
    Game(Context context) {
        //backend context items
        super(context);
        this.end = context;

        //getting sprite sheet for context and setting the exact size
        this.emojis = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_sprits);
        this.emojis = Bitmap.createScaledBitmap(emojis, 2048, 2048, true);

        //creating sparseArray of emojis
        EmojiGenerator emojiGen = new EmojiGenerator();
        this.emojiArray = emojiGen.getSparseArray();

        //seting the initial visuals
        initialVisuals();

        //setting up times and scores
        this.time = this.TOTALTIME;
        this.score = 0;
        this.point = true;
        this.handler = new Handler();
    }

    /**
     * Initial visual values the game class will hold for the application
     */
    private void initialVisuals() {
        //creating new paint of text areas
        this.centerText = new Paint();
        this.timeText = new Paint();
        this.scoreText = new Paint();
        this.bgColor = new Paint();

        //setting center text area color and fill
        this.centerText.setColor(Color.argb(255, 240, 248, 255));
        this.centerText.setStyle(Paint.Style.FILL);

        //setting the text area prefaces which are all monospace
        this.timeText.setTypeface(Typeface.MONOSPACE);
        this.scoreText.setTypeface(Typeface.MONOSPACE);
        this.centerText.setTypeface(Typeface.MONOSPACE);

        //setting each text color
        this.timeText.setColor(Color.argb(255, 0, 0, 0));
        this.scoreText.setColor(Color.argb(255, 0, 0, 0));
        this.centerText.setColor(Color.argb(255, 0, 0, 0));
        this.bgColor.setColor(Color.argb(255,255,255,255));

        //getting and setting the textsize for each of the text areas
        getStatusBarSize();
        this.centerText.setTextSize(this.textSize);
        this.timeText.setTextSize(this.textSize);
        this.scoreText.setTextSize(this.textSize);

        //setting each text area alignment
        this.scoreText.setTextAlign(Paint.Align.LEFT);
        this.timeText.setTextAlign(Paint.Align.RIGHT);
        this.centerText.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * Gets the text size based on the status bar values
     */
    private void getStatusBarSize() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            this.textSize = getResources().getDimensionPixelSize(resourceId);
        }else{
            this.textSize = 0;
        }
    }

    /**
     * Sets the games difficulty
     * @param difficulty game difficulties are easy = 0; medium = 1; hard = 2
     */
    public void setDifficulty(int difficulty) {
        if(difficulty == 0){
            this.totalSquares = (int)Math.pow(2, 2);
        }else if(difficulty == 1){
            this.totalSquares = (int)Math.pow(3, 2);
        }else if(difficulty == 2){
            this.totalSquares = (int)Math.pow(4, 2);
        }else{
            ((Activity)end).finish();
        }

        this.totalSquareRoot = (int)Math.sqrt(this.totalSquares);
    }

    /**
     * Starts the game
     */
    public void startGame(){
        invalidate();
        this.handler.postDelayed(new startTimer(), 100);
    }

    /**
     * Game timer that will terminate the game once it hits time 0
     */
    private class startTimer implements Runnable{
        @Override
        public void run() {
            try{
                //if there is still time, redraw
                if((time - .0001) > 0.0000) {
                    time = time - 100;
                    handler.postDelayed(this, 100);
                    invalidate();
                }else{ //otherwise end the game
                    ((Activity)end).finish();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param touch the motion event
     * @return always returns true upon a succesful motion event touching anywhere in the screen
     */
    @Override
    public boolean onTouchEvent(MotionEvent touch) {
        switch (touch.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //gets on screen x and y touch event
                this.x = (int)touch.getX();
                this.y = (int)touch.getY();

                ObserveTouchAction();
                break;
            }
        }
        return true;
    }

    /**
     * Checks to see if two coordinates x and y are within the final rectangle area of the desired emoji
     */
    private void ObserveTouchAction() {
        //if we touched the right emoji
        if (this.x <= this.finalRect.right && this.x >= this.finalRect.left &&
                this.y <= this.finalRect.bottom && this.y >= this.finalRect.top) {
            //increase score
            this.score++;
            //redraw; give more time
            this.point = true;
            this.time += 3000;
            invalidate();
        }
    }

    /**
     * Draws the emoji game to the screen
     * @param canvas the current canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draws the top half color to the screen
        this.topHalf.set(0, 0, canvas.getWidth(), canvas.getHeight());

        //draws the top half and centerText to the canvas
        canvas.drawRect(this.topHalf, this.bgColor);

        //draws the current score to the screen
        canvas.drawText("Score: " + this.score, 0, this.textSize, this.scoreText);

        //draws the current time to the screen
        canvas.drawText("Time Left: " + this.time / 1000, canvas.getWidth(), this.textSize, this.timeText);

        //draws the prompt to ask the user to find and cick the current emoji on the screen
        canvas.drawText("Find this Emoji", canvas.getWidth() / 2, canvas.getHeight() / 2 - this.textSize / 2, this.centerText);

        //redraw the emoji canvas based on if a point is gained or not
        if(this.point){
            DrawNewEmojis(canvas);
            this.point = false;
        }else{
            DrawCurrentEmojis(canvas);
        }
    }

    private void DrawSelectedEmojiUpTop(Canvas canvas, SparseArray<Rect> emojisList, int indexOfEmoji){
        //gets the source rectangle of the emoji from the input emoji
        Rect sourceRect = emojisList.get(indexOfEmoji);

        //gets the coordinates of the emoji at the top of the screen
        int left = canvas.getWidth() / 4;
        int top = canvas.getHeight() / 4 - canvas.getWidth() / 4;
        int right = canvas.getWidth() * 3 / 4;
        int bottom = canvas.getHeight() / 4 + canvas.getWidth() / 4;

        //Draws the current emoji to find to the bitmap at the top of the game screen
        canvas.drawBitmap(emojis, sourceRect, new Rect(left, top, right, bottom), null);
    }

    private void DrawNewEmojis(Canvas canvas) {
        //declare containers
        this.sourceHolder = new Rect[this.totalSquares];
        this.destinationHolder = new Rect[this.totalSquares];
        this.usedEmojis = new HashSet<>();

        //divide the bottom half of screen into grid
        int startingXPosition = 0;
        int startingYPosition = canvas.getHeight() / 2;
        int verticalDistanceToNewRow = canvas.getHeight() / (2 * this.totalSquareRoot);
        int horizontalDistanceToNewColumn = canvas.getWidth() / this.totalSquareRoot;

        int currentXPosition;
        int currentYPosition;

        //randomly select an emoji
        Random random = new Random();

        //choose random emoji to be put at the top of the page
        this.chosenEmoji = random.nextInt(this.emojiArray.size());
        int chosenIndex = random.nextInt(this.totalSquares);

        //draw selected emoji at the top of the page
        DrawSelectedEmojiUpTop(canvas, this.emojiArray, chosenEmoji);

        //create an index counter
        int currentIndex = 0;

        //draw all the emojis for each row and colum position
        for (int row = 0; row < this.totalSquares / this.totalSquareRoot; row++) {
            currentYPosition = startingYPosition + row * verticalDistanceToNewRow;

            for (int col = 0; col < this.totalSquares / this.totalSquareRoot; col++) {
                Rect sourceRect;
                //use the emoji located at the choosen index
                if (currentIndex == chosenIndex) {
                    sourceRect = this.emojiArray.get(this.chosenEmoji);
                } else { //otherwise pick a random one that's not the chosen
                    int randomEmojiToDisplay = random.nextInt(this.emojiArray.size());
                    //check to make sure the random emoji is not the chosenEmoji and and for no repeats
                    while (randomEmojiToDisplay == this.chosenEmoji || this.usedEmojis.contains(randomEmojiToDisplay)) {
                        randomEmojiToDisplay = random.nextInt(this.emojiArray.size());
                    }
                    sourceRect = this.emojiArray.get(randomEmojiToDisplay);
                    //add the random emoji to a set so it is not selected twice
                    this.usedEmojis.add(randomEmojiToDisplay);
                }
                //draw the emoji
                currentXPosition = startingXPosition + horizontalDistanceToNewColumn * col;
                Rect destinationRect = new Rect(currentXPosition, currentYPosition,
                        currentXPosition + horizontalDistanceToNewColumn, currentYPosition + verticalDistanceToNewRow);
                if (currentIndex == chosenIndex) {
                    this.finalRect = destinationRect;
                }

                this.sourceHolder[currentIndex] = sourceRect;
                this.destinationHolder[currentIndex] = destinationRect;

                //draw the current emoji on the bitmap
                canvas.drawBitmap(this.emojis, sourceRect, destinationRect, null);
                currentIndex++;
            }
        }
    }

    //redraw all the emoji
    private void DrawCurrentEmojis(Canvas canvas) {
        DrawSelectedEmojiUpTop(canvas, this.emojiArray, this.chosenEmoji);

        for (int i = 0; i < this.totalSquares; i++) {
            Rect sourceRect = this.sourceHolder[i];
            Rect destinationRect = this.destinationHolder[i];

            canvas.drawBitmap(this.emojis, sourceRect, destinationRect, null);
        }
    }

    public int getScore(){
        return this.score;
    }
}
