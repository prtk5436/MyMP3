package com.example.mymp3;

public class Songs {

    public int mSoundId;
    // String value
    private String mSong;
    // Text color of the text
    private String mSongNo;
    // Drawable resource ID
    private int mImageResourceId;

    /**
     * Constructs a new TextView with initial values for text and text color.
     */
    public Songs(String Name, String no, int imageResourceId, int soundResource) {
        mSong = Name;
        mSongNo = no;
        mImageResourceId = imageResourceId;
        mSoundId = soundResource;
    }

    public Songs(String name, String mSoundId) {
        this.mSong = name;
        this.mSongNo = mSoundId;
    }


    /**
     * Gets the string value in the TextView.
     *
     * @return current text in the TextView.
     */
    public String getmSong() {
        return mSong;
    }

    /**
     * Gets the text color of the TextView.
     *
     * @return current text color.
     */
    public String getmSongNo() {
        return mSongNo;
    }

    /**
     * Get the image resource ID
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getmSound() {
        return mSoundId;
    }

}
