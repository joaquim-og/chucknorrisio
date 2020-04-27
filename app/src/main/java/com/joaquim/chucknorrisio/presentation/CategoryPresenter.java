package com.joaquim.chucknorrisio.presentation;

import com.joaquim.chucknorrisio.Colors;
import com.joaquim.chucknorrisio.MainActivity;
import com.joaquim.chucknorrisio.datasource.CategoryRemoteDataSource;
import com.joaquim.chucknorrisio.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter implements CategoryRemoteDataSource.ListCategoriesCallBack {

    private final MainActivity view;
    private static List<CategoryItem> items = new ArrayList<>();

    private final CategoryRemoteDataSource dataSource;


    public CategoryPresenter(MainActivity mainActivity, CategoryRemoteDataSource dataSource) {
        this.view = mainActivity;
        this.dataSource = dataSource;
    }

    //call server data
    public void requestAll() {
        view.showProgressBar();
        this.dataSource.findAll(this);
    }

    //response ok, get data
    @Override
    public void onSuccess(List<String> response) {
        List<CategoryItem> categoryItems = new ArrayList<>();

        for (String val : response){
            categoryItems.add(new CategoryItem(val, Colors.randomColor()));
        }
        view.showCategories(categoryItems);
    }

    @Override
    public void onComplete() {
        view.hideProgressBar();
    }

    //response error, error message
    @Override
    public void onError(String message) {
        view.showFailure(message);
    }

}
