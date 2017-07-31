package com.example.madoka.allergicfoodie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class FoodListFragment extends Fragment {

    private RecyclerView mFoodlistRecyclerView;
    private int[] mImageResIds;
    private String[] mNames;
    private String[] mlistItems;

    private String resultAllergenData;
    private AllergenFood mAllergenFood;
    private AllergenFoodLab mFoodLab;


    public FoodListFragment() {
        // Required empty public constructor
    }

    public static FoodListFragment newInstance() {
        return new FoodListFragment();
    }

    @Override
    //get assets
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFoodLab = new AllergenFoodLab(getActivity());


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);
        final TextView textOutput;

        textOutput = (TextView) view.findViewById(R.id.text_ex);

        //receive data from Activity
        Bundle args = getArguments();
        System.out.println("FoodListFragment" + args + "--------");

        this.resultAllergenData = getArguments().getString("keyword");
        String[] mlistItems = this.resultAllergenData.split(", ", 0);

        ArrayList<AllergenFood> list = new ArrayList<>();
        for (AllergenFood food : mFoodLab.getFoods()) {
            for (String item : mlistItems) {
                if (food.getName().equals(item))
                    list.add(food);
            }
        }

        //push button
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textOutput.setText(R.string.ex_allergy_jp);

                // at first you have to change parameter of adapter for translate
                FoodListAdapter adapter = (FoodListAdapter) mFoodlistRecyclerView.getAdapter();
                adapter.translate = true;

                // second, update list view
                mFoodlistRecyclerView.getAdapter().notifyDataSetChanged();
            }
        });

        mFoodlistRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mFoodlistRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mFoodlistRecyclerView.setAdapter(new FoodListAdapter(list));

        return view;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mNameTextView;

        private ViewHolder(View itemView) {
            super(itemView);

            //Get reference to image and name
            mImageView = (ImageView) itemView.findViewById(R.id.food_image);
            mNameTextView = (TextView) itemView.findViewById(R.id.name);
        }

        private void setData(AllergenFood allergenFood, boolean translate) {
            mAllergenFood = allergenFood;
            try {
                InputStream inputStream = getResources().getAssets().open("food_icons");
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            String displayName = mAllergenFood.getName();
            if (translate) {
                // japanese
                FoodEnum value = FoodEnum.byName(getContext(), displayName);
                displayName = getString(value.jpname);
            }
            mNameTextView.setText(displayName);
            mImageView.setImageResource(mAllergenFood.getImageResIds());
        }
    }


    class FoodListAdapter extends RecyclerView.Adapter<ViewHolder> {

        private List<AllergenFood> mAllergenFood;
        public boolean translate = false;

        public FoodListAdapter(List<AllergenFood> allergenFood) {
            mAllergenFood = allergenFood;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new ViewHolder(inflater
                    .inflate(R.layout.recycler_item_food, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewholder, int position) {
            AllergenFood allergenFood = mAllergenFood.get(position);
            viewholder.setData(allergenFood, translate);

        }

        @Override
        public int getItemCount() {
            return mAllergenFood.size();
        }
    }


}
