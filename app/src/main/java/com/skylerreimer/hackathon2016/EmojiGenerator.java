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

        //Initalizing starting index
        int index = 0;

        //for loop in order to calculate the new starting position of each emoji in the sprite sheet
        for(int y = 0; y < 5; y++){
            int currentYPosition = y * emojiHeight;
            for(int x = 0; x < 16; x++){
                int currentXPosition = x * emojiWidth;

                //creating new rectangle coordinate and adding that coordinate to the HashMap
                this.emojiList.put(index, new Rect(currentXPosition,
                        currentYPosition, currentXPosition + emojiWidth, currentYPosition + emojiHeight));
                index++;
            }
        }

        //last row does not hold a full row of emoji, so cut out custom row length
        int y = 5;

            int currentYPosition = y * emojiHeight;
            for(int x = 0; x < 12; x++) {
                int currentXPosition = x * emojiWidth;

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