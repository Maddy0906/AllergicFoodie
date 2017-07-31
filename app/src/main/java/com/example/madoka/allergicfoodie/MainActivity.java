package com.example.madoka.allergicfoodie;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {

    Button mOrder;
    Button mNext;
    TextView mItemSelected;
    private static final String DIALOG_Food = "Item";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set animation
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.open_page);
        View v = findViewById(R.id.activity_main);
        v.startAnimation(animation);


        mOrder = (Button) findViewById(R.id.btnOrder);
        mNext = (Button) findViewById(R.id.btnNext);

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create alertDialog
                FragmentManager fm = getSupportFragmentManager();
                FoodPickerFragment dialogFragment = new FoodPickerFragment();
                dialogFragment.show(fm, DIALOG_Food);

            }
        });

        mNext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, FoodListActivity.class);

                //send date to FoodListActivity
                intent.putExtra("keyword", mItemSelected.getText().toString());
                System.out.println("Selected keywords@" + mItemSelected.getText());

                startActivity(intent);
            }

        });
    }

    //get back date from dialog fragment
    public void onReturnValue(String item){
        mItemSelected = (TextView) findViewById(R.id.tvItemSelected);
        mItemSelected.setText(item);

    }
}


