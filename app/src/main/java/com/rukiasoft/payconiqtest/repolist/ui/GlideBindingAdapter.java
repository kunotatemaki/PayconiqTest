package com.rukiasoft.payconiqtest.repolist.ui;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Roll on 11/8/17.
 */

public class GlideBindingAdapter {
    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        //square images
        if(url != null){
           Glide.with(view.getContext())
                    .load(url)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(view);
        }
    }
}

    