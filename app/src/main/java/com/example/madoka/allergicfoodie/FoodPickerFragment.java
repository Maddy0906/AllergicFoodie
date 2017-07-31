package com.example.madoka.allergicfoodie;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by park on 2017-06-13.
 */

public class FoodPickerFragment extends DialogFragment {
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //get items
        listItems = new String[FoodEnum.values().length];
        for (int i = 0; i < FoodEnum.values().length; i++) {
            listItems[i] = getContext().getString(FoodEnum.values()[i].name);
        }
//        listItems = getResources().getStringArray(R.array.food_item);
        checkedItems = new boolean[listItems.length];

    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        //create alertDialog

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setTitle(R.string.dialog_title);
        mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                //remember how many item checked
                if(isChecked){
                    mUserItems.add(position);
                }else{
                    mUserItems.remove((Integer.valueOf(position)));
                }
            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //update item text
                String item = "";
                for (int i = 0; i < mUserItems.size(); i++) {
                    item = item + listItems[mUserItems.get(i)];
                    if (i != mUserItems.size() - 1) {
                        item = item + ", ";
                    }
                }

                MainActivity main = (MainActivity) getActivity();
                main.onReturnValue(item);

                dialogInterface.dismiss();
            }
        });

        //click dismiss button -> close dialog
        mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;
                    mUserItems.clear();

                    MainActivity main = (MainActivity) getActivity();
                    main.onReturnValue(null);

                    dialogInterface.dismiss();
                }
            }
        });

        return mBuilder.create();
    }





}












