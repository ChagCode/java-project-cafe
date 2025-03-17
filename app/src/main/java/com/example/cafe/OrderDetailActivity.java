package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USERNAME = "username";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_ADDITIVES = "resultAdditives";
    private static final String EXTRA_DRINK_TYPE = "drinkType";
    private TextView textViewName, textViewDrink, textViewDrinkType, textViewAdditives;
    private String username, drink, additives, drinkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initViews();
        setupLabel();
    }
    private void setupLabel() {
        // из Intent получаем по ключу - EXTRA_USERNAME значение
        username = getIntent().getStringExtra(EXTRA_USERNAME);
        drink = getIntent().getStringExtra(EXTRA_DRINK);
        additives = getIntent().getStringExtra(EXTRA_ADDITIVES);
        drinkType = getIntent().getStringExtra(EXTRA_DRINK_TYPE);

        // выводим резульат результат
        textViewName.setText(username);
        textViewDrink.setText(drink);
        textViewDrinkType.setText(drinkType);
        textViewAdditives.setText(additives);
    }
    private void initViews() {
        textViewName = findViewById(R.id.textViewName);
        textViewDrink = findViewById(R.id.textViewDrink);
        textViewDrinkType = findViewById(R.id.textViewDrinkType);
        textViewAdditives = findViewById(R.id.textViewAdditives);
    }
    public static Intent newIntent(
            Context context,
            String username,
            String drink,
            String drinkType,
            String resultAdditives
    ) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_ADDITIVES, resultAdditives);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        return intent;
    }
}