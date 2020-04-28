package com.joaquim.chucknorrisio.presentation;

import com.joaquim.chucknorrisio.JokerActivity;
import com.joaquim.chucknorrisio.datasource.JokeRemoteDataSource;
import com.joaquim.chucknorrisio.model.Joke;

public class JokerPresenter implements JokeRemoteDataSource.JokeCallBack {


    private final JokerActivity view;
    private final JokeRemoteDataSource dataSource;

    public JokerPresenter(JokerActivity jokerActivity, JokeRemoteDataSource dataSource) {
        this.view = jokerActivity;
        this.dataSource = dataSource;
    }

    public void findJokeBy(String category) {
        this.view.showProgressBar();
        this.dataSource.findJokeBy(this, category);
    }

    @Override
    public void onSuccess(Joke response) {
        this.view.showJoke(response);
    }

    @Override
    public void onError(String message) {
        this.view.showFailure(message);
    }

    @Override
    public void onComplete() {
        this.view.hideProgressBar();
    }
}
