package ru.gb.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NightActivity extends AppCompatActivity implements View.OnClickListener {

    EditText calculation_field;
    EditText result_field;
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    Button button_0;
    Button button_clear;
    Button button_backspace;
    Button button_divide;
    Button button_sqrt;
    Button button_multiplication;
    Button button_percentage;
    Button button_minus;
    Button button_dot;
    Button button_plus;
    Button button_equal;
    Button button_switch_theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);

        calculation_field = findViewById(R.id.calculation_field);
        result_field = findViewById(R.id.result_field);
        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_clear = findViewById(R.id.button_clear);
        button_backspace = findViewById(R.id.button_backspace);
        button_divide = findViewById(R.id.button_divide);
        button_sqrt = findViewById(R.id.button_sqrt);
        button_multiplication = findViewById(R.id.button_multiplication);
        button_percentage = findViewById(R.id.button_percentage);
        button_minus = findViewById(R.id.button_minus);
        button_dot = findViewById(R.id.button_dot);
        button_plus = findViewById(R.id.button_plus);
        button_equal = findViewById(R.id.button_equal);
        button_switch_theme = findViewById(R.id.button_switch_theme);

        calculation_field.setShowSoftInputOnFocus(false);
        result_field.setShowSoftInputOnFocus(false);

        calculation_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
            }
        });

        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                updateText("0");
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                updateText("1");
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                updateText("2");
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                updateText("3");
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                updateText("4");
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                updateText("5");
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                updateText("6");
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                updateText("7");
            }
        });

        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                updateText("8");
            }
        });

        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                updateText("9");
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculation_field.setText("");
            }
        });

        button_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cursorPosition = calculation_field.getSelectionStart();
                int textLength = calculation_field.getText().length();
                if (cursorPosition != 0 && textLength != 0) {
                    SpannableStringBuilder modifiedText = (SpannableStringBuilder) calculation_field.getText();
                    modifiedText.replace(cursorPosition - 1, cursorPosition, "");
                    calculation_field.setText(modifiedText);
                    calculation_field.setSelection(cursorPosition - 1);
                }
            }
        });

        button_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculation_field.getText().toString())) {
                    cursorPlace();
                }
                updateText("\u00F7");
            }
        });

        button_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText("\u221A");
            }
        });

        button_multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculation_field.getText().toString())) {
                    cursorPlace();
                }
                updateText("\u00D7");
            }
        });

        button_percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculation_field.getText().toString())) {
                    cursorPlace();
                }
                updateText("%");
            }
        });

        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculation_field.getText().toString())) {
                    cursorPlace();
                }
                updateText("-");
            }
        });

        button_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculation_field.getText().toString())) {
                    cursorPlace();
                }
                updateText(".");
            }
        });

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculation_field.getText().toString())) {
                    cursorPlace();
                }
                updateText("+");
            }
        });

        button_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculation_field.getText().toString())) {
                    cursorPlace();
                }
                updateText("=");
            }
        });

        button_switch_theme.setOnClickListener(this);
    }

    private void updateText(String stringToAdd) {
        String oldString = calculation_field.getText().toString();
        int cursorPosition = calculation_field.getSelectionStart();
        String leftString = oldString.substring(0, cursorPosition);
        String rightString = oldString.substring(cursorPosition);
        calculation_field.setText(String.format("%s%s%s", leftString, stringToAdd, rightString));
        calculation_field.setSelection(cursorPosition + 1);
    }

    private void emptyPlace() {
        if (getString(R.string.calculation_field).equals(calculation_field.getText().toString())) {
            calculation_field.setText("");
        }
    }

    private void cursorPlace() {
        int cursorPosition = calculation_field.getSelectionStart();
        calculation_field.setSelection(cursorPosition + 1);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}