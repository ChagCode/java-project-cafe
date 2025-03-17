package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USERNAME = "username";
    private TextView textViewHello, textViewAdditives;
    private RadioGroup radioGroupDrinks;
    private RadioButton radioButtonTea, radioButtonCoffee;
    private CheckBox checkBoxSugar, checkBoxMilk, checkBoxLemon;
    private Spinner spinnerCoffee, spinnerTea;
    private Button buttonOrder;
    private String drink, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        initViews();
        setupUsername();

        radioGroupDrinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButtonTea.getId()) {
                    chooseTea();
                } else if (checkedId == radioButtonCoffee.getId()) {
                    chooseCoffee();
                }
            }
        });
        radioButtonTea.setChecked(true);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                madeOrder();
            }
        });
    }

    private void madeOrder() {
        ArrayList<String> additives = new ArrayList<>();
        if (checkBoxSugar.isChecked()) {
            additives.add("Сахар");
//            additives.add(checkBoxSugar.getText().toString()); чтобы избавиться от [] в выводе
        }
        if (checkBoxMilk.isChecked()) {
            additives.add("Молоко");
//            additives.add(checkBoxMilk.getText().toString());
        }
        if (radioButtonTea.isChecked() && checkBoxLemon.isChecked()) {
            additives.add("Лимон");
//            additives.add(checkBoxLemon.getText().toString());
        }
        String resultAdditives = String.join(", ", additives);

        String drinkType = "";
        if (radioButtonTea.isChecked()) {
            drinkType = spinnerTea.getSelectedItem().toString();
        } else if (radioButtonCoffee.isChecked()) {
            drinkType = spinnerCoffee.getSelectedItem().toString();
        }

        Intent intent = OrderDetailActivity.newIntent(
                this,
                username,
                drink,
                drinkType,
                resultAdditives
        );
        startActivity(intent);
    }

    private void chooseTea() {
        drink = getString(R.string.drinks_tea);
        String additivesDrink = getString(R.string.additives, drink);
        textViewAdditives.setText(additivesDrink);
        checkBoxLemon.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
    }
    private void chooseCoffee() {
        drink = getString(R.string.drinks_coffee);
        String additivesDrink = getString(R.string.additives, drink);
        textViewAdditives.setText(additivesDrink);
        checkBoxLemon.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
    }

    private void setupUsername() {
        // из Intent получаем по ключу - EXTRA_USERNAME значение
        username = getIntent().getStringExtra(EXTRA_USERNAME);

        // и передаем полученное значение в строку вместо %s
        String resultName = getString(R.string.hello, username);

        // выводим резульат результат
        textViewHello.setText(resultName);
    }

    private void initViews() {
        textViewHello = findViewById(R.id.textViewHello);
        textViewAdditives = findViewById(R.id.textViewAdditives);
        radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee);
        checkBoxSugar = findViewById(R.id.CheckBoxSugar);
        checkBoxMilk = findViewById(R.id.CheckBoxMilk);
        checkBoxLemon = findViewById(R.id.CheckBoxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        buttonOrder = findViewById(R.id.buttonOrder);
    }

    public static Intent newIntent(Context context, String username) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        return intent;
    }

}