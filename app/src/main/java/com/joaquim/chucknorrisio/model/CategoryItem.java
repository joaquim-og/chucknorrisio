package com.joaquim.chucknorrisio.model;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.joaquim.chucknorrisio.R;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

public class CategoryItem extends Item<ViewHolder> {

    private final String categoryName;
    private final int bgColor;

    public CategoryItem(String categoryName, int bgColor) {
        this.categoryName = categoryName;
        this.bgColor = bgColor;

    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        TextView txtCategory = viewHolder.itemView.findViewById(R.id.txt_category);

        txtCategory.setText(categoryName);
        viewHolder.itemView.setBackgroundColor(bgColor);

    }

    @Override
    public int getLayout() {

        return R.layout.card_category;
    }
}
