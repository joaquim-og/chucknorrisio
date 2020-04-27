package com.joaquim.chucknorrisio.presentation;

import android.os.Handler;

import com.joaquim.chucknorrisio.MainActivity;
import com.joaquim.chucknorrisio.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter {

    private final MainActivity view;
    private static List<CategoryItem> items = new ArrayList<>();


    // fake data
    static {
        items.add(new CategoryItem("Cat1", 0xFF00FFFF));
        items.add(new CategoryItem("Cat2", 0x0F00FFFF));
        items.add(new CategoryItem("Cat3", 0xFFF0FFFF));
        items.add(new CategoryItem("Cat4", 0xFF000FFF));
        items.add(new CategoryItem("Cat5", 0xFF00FF0F));
        items.add(new CategoryItem("Cat6", 0xFF00FFF0));
    }

    public CategoryPresenter(MainActivity mainActivity) {
        this.view = mainActivity;
    }

    //call server data
    public void requestAll() {
        view.showProgressBar();
        this.request();
    }

    //getting server data
    private void request() {
        new Handler().postDelayed(() -> {
            try {
               onSuccess(items);
            } catch (Exception e) {
                onError(e.getMessage());
            } finally {
                onComplete();
            }

        }, 5000);
    }

    //response ok, get data
    private void onSuccess(List<CategoryItem> items) {
        view.showCategories(items);
    }

    private void onComplete() {
        view.hideProgressBar();
    }

    //response error, error message
    private void onError(String message) {
        view.showFailure(message);
    }

}
