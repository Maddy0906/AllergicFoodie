package com.example.madoka.allergicfoodie;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;

public class FoodListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        System.out.println("FoodListActivity^^^^^^^^^");

        //receive from MainActivity
        String data = getIntent().getStringExtra("keyword");
        System.out.println("FoodListActivity" + data );

        //make fragment
        Bundle bundle = new Bundle();
        FoodListFragment fragment = new FoodListFragment();
        bundle.putString("keyword",data);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.root_layout, fragment);
        transaction.commit();

        System.out.println("Send to FoodListFragment" + bundle );

    }
}
