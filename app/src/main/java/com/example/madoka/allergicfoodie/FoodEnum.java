package com.example.madoka.allergicfoodie;

import android.content.Context;
import android.support.annotation.IntegerRes;

/**
 * Created by madoka on 2017/07/26.
 */

public enum FoodEnum {
    BUCKWHEAT(R.string.buckwheat,R.string.buckwheat_jp, R.drawable.food_buckwheat),
    DAIRY(R.string.dairy,R.string.dairy_jp, R.drawable.food_dairy),
    EGGS(R.string.eggs,R.string.eggs_jp, R.drawable.food_eggs),
    FISH(R.string.fish,R.string.fish_jp, R.drawable.food_fish),
    GLUTEN(R.string.gluten,R.string.gluten_jp, R.drawable.food_gluten),
    PEANUTS(R.string.peanuts,R.string.peanuts_jp, R.drawable.food_peanuts),
    SHELLFISH(R.string.shellfish,R.string.shellfish_jp, R.drawable.food_shellfish),
    SOY(R.string.soy,R.string.soy_jp, R.drawable.food_soy),
    TREE_NUTS(R.string.tree_nuts,R.string.tree_nuts_jp, R.drawable.food_tree_nuts);

    int name;
    int jpname;
    int image;

    FoodEnum(int name, int jpname, int image) {
        this.name = name;
        this.jpname = jpname;
        this.image = image;
    }

    public static FoodEnum byName(Context context, String name) {
        if (name == null) return null;

        for (FoodEnum value : values()) {
            if (context.getString(value.name).equals(name)) {
                return value;
            }
        }

        return null;
    }
}
