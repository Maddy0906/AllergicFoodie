package com.example.madoka.allergicfoodie;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by madoka on 2017/07/19.
 */

public class AllergenFoodLab {
    private static final String TAG = "AllergenFoodLab";
    private static final String FOOD_ICONS = "food_icons";

    private ArrayList<AllergenFood> mFoods = new ArrayList<>();


    public AllergenFoodLab(Context context) {
        loadFoods(context);
    }

    private void loadFoods(Context context) {
        for (FoodEnum foodEnum : FoodEnum.values()) {
            mFoods.add(new AllergenFood(context.getString(foodEnum.name), foodEnum.image));
        }
    }


    public List<AllergenFood> getFoods() {
        return mFoods;
    }
}
