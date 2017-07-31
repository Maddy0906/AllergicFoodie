package com.example.madoka.allergicfoodie;


/**
 * Created by madoka on 2017/07/18.
 */

public class AllergenFood {
    private String mName;
    private int mImageResIds;

    public AllergenFood(String mName, int mImageResIds) {
        this.mName = mName;
        this.mImageResIds = mImageResIds;
    }

    public AllergenFood(String assetPath) {
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".png", "");
    }

    public AllergenFood(int mImageResIds) {
        this.mImageResIds = mImageResIds;
    }


    public String getName() {
        return mName;
    }

    public int getImageResIds() {

            return mImageResIds;
    }


    public void setName(String mName) {
        this.mName = mName;
    }

    public void setImageResIds(int mImageResIds) {
        this.mImageResIds = mImageResIds;
    }



}


