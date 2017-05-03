package com.skylerreimer.hackathon2016;

import android.util.SparseArray;
import android.graphics.Rect;

class EmojiGenerator {
    private SparseArray<Rect> emojiList;

    /**
     * Constructor in that creates sprite sheet for the game
     */
    EmojiGenerator() {
        this.emojiList = new SparseArray<>();
        //calling populateList which populates the sprite sheet
        populateList();
    }

    /*
     *  populates a SparseArray of Rectangles to later be returned from the sprite png.
     *  Values are cut of based on height and width specifications of the emojis
     */
    private void populateList() {
        //dimensions of individual emoji icons
        int emojiHeight = 128;
        int emojiWidth = 128;

        //distance between two emojis from the top of one emoji to the top of the next emoji
        int verticalDistanceToNewRow = emojiHeight;
        //distance between two emojis from the left of one emoji to the left of the next emoji
        int horizontalDistanceToNewColumn = emojiWidth;

        //the starting index and initial variables for the loop
        int index = 0;
        int currentYPosition;
        int currentXPosition;

        //for loop in order to calculate the new starting position of each emoji in the sprite sheet
        for(int y = 0; y < 5; y++){
            currentYPosition = y * verticalDistanceToNewRow;
            for(int x = 0; x < 16; x++){
                currentXPosition = x * horizontalDistanceToNewColumn;

                //creating new rectangle coordinate and adding that coordinate to the HashMap
                this.emojiList.put(index, new Rect(currentXPosition,
                        currentYPosition, currentXPosition + emojiWidth, currentYPosition + emojiHeight));
                index++;
            }
        }

        int y = 5;

            currentYPosition = y * verticalDistanceToNewRow;
            for(int x = 0; x < 12; x++) {
                currentXPosition = x * horizontalDistanceToNewColumn;

                //creating new rectangle coordinate and adding that coordinate to the HashMap
                this.emojiList.put(index, new Rect(currentXPosition,
                        currentYPosition, currentXPosition + emojiWidth, currentYPosition + emojiHeight));
                index++;
            }
    }

    /**
     * Retuns the emoji SparseArray to the call
     * @return a Map of all the current emojis
     */
    SparseArray<Rect> getSparseArray(){
        return this.emojiList;
    }
}