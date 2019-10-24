package com.sdei.sdeiarchitecture.utils.common.recyclerviewbase;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class MyRecyclerAdapter extends BindingBaseAdapter {

    private int resource;
    private ArrayList<?> objects;
    private RecyclerCallback recyclerCallback;
    private Object viewModel;
    private Object subViewAdapter;
    private Context context;

    public MyRecyclerAdapter(int resource,
                             ArrayList<?> objects,
                             RecyclerCallback recyclerCallback, Object viewModel, Object subViewAdapter, Context context) {
        this.resource = resource;
        this.objects = objects;
        this.recyclerCallback = recyclerCallback;
        this.viewModel = viewModel;
        this.subViewAdapter = subViewAdapter;
        this.context = context;

        Log.e("insideBaseAdapter", "" + resource);
        Log.e("insideBaseAdapter", "" + objects);
        Log.e("insideBaseAdapter", "" + recyclerCallback);
    }

    @Override
    public int getItemCount() {
        if (objects != null) {
            return objects.size();
        } else {
            return 0;
        }
    }

    @Override
    protected Object getObjForPosition(int position) {
        return objects.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return resource;
    }

    @Override
    protected RecyclerCallback getRecyclerCallback() {
        return recyclerCallback;
    }

    @Override
    protected Object getViewModel() {
        return viewModel;
    }

    @Override
    public Object getSubViewAdapter() {
        return subViewAdapter;
    }

    @Override
    public Context getMyContext() {
        return context;
    }
}
