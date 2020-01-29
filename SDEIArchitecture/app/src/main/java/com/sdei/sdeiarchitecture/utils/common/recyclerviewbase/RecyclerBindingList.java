package com.sdei.sdeiarchitecture.utils.common.recyclerviewbase;


import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerBindingList<T> extends BaseObservable implements Serializable {

    @Bindable
    private MyRecyclerAdapter adapter;
    private ArrayList<T> itemsList;

    @Bindable
    public MyRecyclerAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MyRecyclerAdapter adapter) {
        this.adapter = adapter;
        //notifyPropertyChanged(BR.adapter);
    }

    @Bindable
    public ArrayList<T> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<T> itemsList) {
        this.itemsList = itemsList;
        Log.e("RecyclerBindingList", "" + itemsList.size());
        //notifyPropertyChanged(BR.itemsList);
    }

    public void notifyDataChange() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

}