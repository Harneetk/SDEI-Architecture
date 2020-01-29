package com.sdei.sdeiarchitecture.utils.common.recyclerviewbase;

import android.content.Context;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.sdei.sdeiarchitecture.BR;


abstract class BindingBaseAdapter extends RecyclerView.Adapter<BindingBaseAdapter.BaseViewHolder> {

    /**
     * create a new RecyclerView.ViewHolder and initializes
     * some fields to be used by RecyclerView
     *
     * @param parent
     * @param viewType
     * @return the holder
     */
    public BaseViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new BaseViewHolder(binding);
    }

    /**
     * update the RecyclerView.ViewHolder contents with the item at the given position
     *
     * @param holder   to update
     * @param position position on which holder to update
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Object obj = getObjForPosition(position);
        Object vm = getViewModel();
        Object subViewAdapter = getSubViewAdapter();
        Context mContext = getMyContext();
        holder.binding.getRoot().setTag(position);
        holder.bind(position, obj, vm, subViewAdapter, mContext);
        //  setFadeAnimation(holder.itemView);
    }

    public void setFadeAnimation(View view) {
//        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
//        anim.setDuration(500);
//        view.startAnimation(anim);

        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.RIGHT);
        TransitionManager.beginDelayedTransition((ViewGroup) view, slide);
    }

    /**
     * @param position item position
     * @return the view type of the item at position for the purposes of view recycling
     */
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract Object getObjForPosition(int position);

    protected abstract int getLayoutIdForPosition(int position);

    protected abstract RecyclerCallback getRecyclerCallback();

    protected abstract Object getViewModel();

    protected abstract Object getSubViewAdapter();

    protected abstract Context getMyContext();

    class BaseViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;
        private RecyclerCallback callback;

        BaseViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.callback = getRecyclerCallback();
        }

        void bind(int position, Object obj, Object vm, Object subViewAdapter, Context mContext) {
            binding.setVariable(BR.model, obj);
            binding.setVariable(BR.callback, callback);
            binding.setVariable(BR.position, position);
//            binding.setVariable(BR.viewModel, vm);/**/
//            binding.setVariable(BR.subViewAdapter, subViewAdapter);/**/
//            binding.setVariable(BR.context, mContext);/**/
            binding.executePendingBindings();
        }

    }
}
