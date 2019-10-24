package com.sdei.sdeiarchitecture.utils.common.recyclerviewbase;


import android.content.Context;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomBindingAdapter {

    String[] arr = {"recyclerLinearAdapter", "layout", "onItemClickListener", "viewModel", "itemDecor"};



    @BindingAdapter(value = {"recyclerViewAdapter", "layout", "onItemClickListener", "viewModel",
            "itemDecor", "horizontal", "grid", "numColumn", "subViewAdapter", "context", "layoutManager"}, requireAll = false)
    public static void setRecyclerLinearAdapter(RecyclerView view,
                                                RecyclerBindingList object,
                                                int layout,
                                                final RecyclerCallback callback,
                                                Object viewModel,
                                                boolean itemDecor,
                                                boolean horizontal,
                                                boolean isGrid,
                                                int numColumn,
                                                Object subViewAdapter,
                                                Context context,
                                                LinearLayoutManager manager) {

        LinearLayoutManager layoutManager = null;
        if (manager != null) {
            layoutManager = manager;
        } else {
            if (horizontal) {
                layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
            } else if (isGrid) {
                layoutManager = new GridLayoutManager(view.getContext(), numColumn);
            } else {
                layoutManager = new LinearLayoutManager(view.getContext());
            }
        }
        view.setLayoutManager(layoutManager);
        if (itemDecor) {
            view.addItemDecoration(new SimpleDividerItemDecoration(view.getContext()));
        }
        view.setItemAnimator(new DefaultItemAnimator());

        if (object != null) {
            MyRecyclerAdapter adapter = new MyRecyclerAdapter(layout, object.getItemsList(), callback, viewModel,
                    subViewAdapter, context);
            view.setAdapter(adapter);
            object.setAdapter(adapter);
        }
    }
}