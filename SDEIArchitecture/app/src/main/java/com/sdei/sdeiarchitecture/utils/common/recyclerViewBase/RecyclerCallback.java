package com.sdei.sdeiarchitecture.utils.common.recyclerViewBase;

import android.view.View;

import java.io.Serializable;

public interface RecyclerCallback extends Serializable {

    void onItemClick(View view, int position);

    void onChildItemClick(View view, int parentIndex, int childIndex);

    void itemAction(String type, int position);

}
