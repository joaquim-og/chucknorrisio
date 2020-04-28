package com.joaquim.chucknorrisio;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.joaquim.chucknorrisio.datasource.JokeRemoteDataSource;
import com.joaquim.chucknorrisio.model.Joke;
import com.joaquim.chucknorrisio.presentation.JokerPresenter;

public class JokerActivity extends AppCompatActivity {

    static final String CATEGORY_KEY = "category_key";
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // catching extras infos from previous activity
        if (getIntent().getExtras() != null) {
            String category = getIntent().getExtras().getString(CATEGORY_KEY);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(category);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                final JokeRemoteDataSource dataSource = new JokeRemoteDataSource();
                JokerPresenter presenter = new JokerPresenter(this, dataSource);

                presenter.findJokeBy(category);

                FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(view -> {
                            presenter.findJokeBy(category);
                        }
                );
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return true;
        }
    }

    public void showJoke(Joke joke) {

        TextView txtJoke = findViewById(R.id.txt_joke);
        txtJoke.setText(joke.getValue());
    }

    public void showFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressBar() {
        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setMessage(getString(R.string.loading));
            progress.setIndeterminate(true);
            progress.setCancelable(false);
        }
        progress.show();
    }

    public void hideProgressBar() {
        if (progress != null) {
            progress.hide();
        }
    }

}
