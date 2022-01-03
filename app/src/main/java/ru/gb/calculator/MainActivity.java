package ru.gb.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String curr;
    private String resultVariable;
    private boolean dotInserted;
    private boolean operatorInserted;
    private boolean equalOperator;
    private boolean resultIsFilled;

    EditText calculationField;
    EditText resultField;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;
    Button buttonClear;
    Button buttonBackSpace;
    Button buttonDivide;
    Button buttonSqrt;
    Button buttonMultiplication;
    Button buttonPercentage;
    Button buttonMinus;
    Button buttonDot;
    Button buttonPlus;
    Button buttonEqual;
    Button buttonSwitchTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculationField = (EditText) findViewById(R.id.calculation_field);
        resultField = (EditText) findViewById(R.id.result_field);

        curr = "";
        resultVariable = "";
        dotInserted = false;
        operatorInserted = false;
        equalOperator = false;
        resultIsFilled = false;

        button0 = (Button) findViewById(R.id.button_0);
        button1 = (Button) findViewById(R.id.button_1);
        button2 = (Button) findViewById(R.id.button_2);
        button3 = (Button) findViewById(R.id.button_3);
        button4 = (Button) findViewById(R.id.button_4);
        button5 = (Button) findViewById(R.id.button_5);
        button6 = (Button) findViewById(R.id.button_6);
        button7 = (Button) findViewById(R.id.button_7);
        button8 = (Button) findViewById(R.id.button_8);
        button9 = (Button) findViewById(R.id.button_9);
        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonBackSpace = (Button) findViewById(R.id.button_backspace);
        buttonDivide = (Button) findViewById(R.id.button_divide);
        buttonSqrt = (Button) findViewById(R.id.button_sqrt);
        buttonMultiplication = (Button) findViewById(R.id.button_multiplication);
        buttonPercentage = (Button) findViewById(R.id.button_percentage);
        buttonMinus = (Button) findViewById(R.id.button_minus);
        buttonDot = (Button) findViewById(R.id.button_dot);
        buttonPlus = (Button) findViewById(R.id.button_plus);
        buttonEqual = (Button) findViewById(R.id.button_equal);
        buttonSwitchTheme = (Button) findViewById(R.id.button_switch_theme);

        calculationField.setShowSoftInputOnFocus(false);
        resultField.setShowSoftInputOnFocus(false);

        calculationField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                if (!equalOperator) {
                    updateText("0");
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                if (!equalOperator) {
                    updateText("1");
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                if (!equalOperator) {
                    updateText("2");
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                if (!equalOperator) {
                    updateText("3");
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                if (!equalOperator) {
                    updateText("4");
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                if (!equalOperator) {
                    updateText("5");
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                if (!equalOperator) {
                    updateText("6");
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                if (!equalOperator) {
                    updateText("7");
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                if (!equalOperator) {
                    updateText("8");
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyPlace();
                if (!equalOperator) {
                    updateText("9");
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculationField.setText("");
                resultField.setText("");
                operatorInserted = false;
                dotInserted = false;
                equalOperator = false;
                resultIsFilled = false;
            }
        });

        buttonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!resultIsFilled) {
                    backspaceMethod();
                }
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculationField.getText().toString())) {
                    cursorPlace();
                }
                if (calculationField.getText().toString().substring(calculationField.length() - 1, calculationField.length()).equals(".")) {
                    backspaceMethod();
                }
                if (!operatorInserted && !equalOperator) {
                    updateText(" ");
                    updateText("\u00F7");
                    updateText(" ");
                    dotInserted = false;
                    operatorInserted = true;
                }
            }
        });

        buttonSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calculationField.getText().toString().substring(calculationField.length() - 1, calculationField.length()).equals(".")) {
                    backspaceMethod();
                }
                if (!operatorInserted && !equalOperator) {
                    updateText("\u221A ");
                    dotInserted = false;
                    operatorInserted = true;
                }
            }
        });

        buttonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculationField.getText().toString())) {
                    cursorPlace();
                }
                if (calculationField.getText().toString().substring(calculationField.length() - 1, calculationField.length()).equals(".")) {
                    backspaceMethod();
                }
                if (!operatorInserted && !equalOperator) {
                    updateText(" ");
                    updateText("\u00D7");
                    updateText(" ");
                    dotInserted = false;
                    operatorInserted = true;
                }
            }
        });

        buttonPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculationField.getText().toString())) {
                    cursorPlace();
                }
                if (calculationField.getText().toString().substring(calculationField.length() - 1, calculationField.length()).equals(".")) {
                    backspaceMethod();
                }
                if (!equalOperator) {
                    updateText(" ");
                    updateText("%");
                    updateText(" ");
                    dotInserted = false;
                    operatorInserted = true;
                    equalOperator = true;
                }

                // percentage result
                if (resultIsFilled == false) {
                    String[] toResultNumbers = calculationField.getText().toString().split(" ");

                    switch (toResultNumbers[1]) {
                        case "+":
                            // A + B % -> A + (A * B / 100)
                            resultVariable = Double.toString(Double.parseDouble(toResultNumbers[0]) + (Double.parseDouble(toResultNumbers[0]) * Double.parseDouble(toResultNumbers[2]) / 100));
                            break;
                        case "-":
                            // A - B % -> A - (A * B / 100)
                            resultVariable = Double.toString(Double.parseDouble(toResultNumbers[0]) - (Double.parseDouble(toResultNumbers[0]) * Double.parseDouble(toResultNumbers[2]) / 100));
                            break;
                        case "\u00D7":
                            // A * B % -> A * B / 100
                            resultVariable = Double.toString(Double.parseDouble(toResultNumbers[0]) * Double.parseDouble(toResultNumbers[2]) / 100);
                            break;
                        case "\u00F7":
                            // A / B % -> A / B / 100
                            resultVariable = Double.toString(Double.parseDouble(toResultNumbers[0]) / Double.parseDouble(toResultNumbers[2]) / 100);
                            break;
                    }

                    resultField.setText(resultVariable);
                    resultIsFilled = true;
                }

            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculationField.getText().toString())) {
                    cursorPlace();
                }
                if (calculationField.getText().toString().substring(calculationField.length() - 1, calculationField.length()).equals(".")) {
                    backspaceMethod();
                }
                if (!operatorInserted && !equalOperator) {
                    updateText(" ");
                    updateText("-");
                    updateText(" ");
                    dotInserted = false;
                    operatorInserted = true;
                }
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculationField.getText().toString())) {
                    cursorPlace();
                } else if (!dotInserted && !equalOperator) {
                    String prevSymbol = calculationField.getText().toString().substring(calculationField.length() - 1, calculationField.length());
                    if (prevSymbol.equals("\u00F7") || prevSymbol.equals("\u00D7") || prevSymbol.equals("-") || prevSymbol.equals("+")) {
                        updateText("0");
                    }
                    updateText(".");
                    dotInserted = true;
                }
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculationField.getText().toString())) {
                    cursorPlace();
                }
                if (calculationField.getText().toString().substring(calculationField.length() - 1, calculationField.length()).equals(".")) {
                    backspaceMethod();
                }
                if (!operatorInserted && !equalOperator) {
                    updateText(" ");
                    updateText("+");
                    updateText(" ");
                    dotInserted = false;
                    operatorInserted = true;
                }
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.calculation_field).equals(calculationField.getText().toString())) {
                    cursorPlace();
                }
                if (calculationField.getText().toString().substring(calculationField.length() - 1, calculationField.length()).equals(".")) {
                    backspaceMethod();
                }
                if (!equalOperator) {
                    updateText(" ");
                    updateText("=");
                    updateText(" ");
                    dotInserted = false;
                    equalOperator = true;
                }

                //result
                if (resultIsFilled == false) {
                    String[] toResultNumbers = calculationField.getText().toString().split(" ");

                    if (toResultNumbers[0].equals("\u221A")) {
                        resultVariable = Double.toString(Math.sqrt(Double.parseDouble(toResultNumbers[1])));
                    }

                    switch (toResultNumbers[1]) {
                        case "+":
                            resultVariable = Double.toString(Double.parseDouble(toResultNumbers[0]) + Double.parseDouble(toResultNumbers[2]));
                            break;
                        case "-":
                            resultVariable = Double.toString(Double.parseDouble(toResultNumbers[0]) - Double.parseDouble(toResultNumbers[2]));
                            break;
                        case "\u00D7":
                            resultVariable = Double.toString(Double.parseDouble(toResultNumbers[0]) * Double.parseDouble(toResultNumbers[2]));
                            break;
                        case "\u00F7":
                            resultVariable = Double.toString(Double.parseDouble(toResultNumbers[0]) / Double.parseDouble(toResultNumbers[2]));
                            break;
                    }

                    resultField.setText(resultVariable);
                    resultIsFilled = true;
                }

            }
        });

        buttonSwitchTheme.setOnClickListener(this);
    }

    private void backspaceMethod() {
        int cursorPosition = calculationField.getSelectionStart();
        int textLength = calculationField.getText().length();
        String wholeString = calculationField.getText().toString();

        if (cursorPosition != 0 && textLength != 0) {
            SpannableStringBuilder modifiedText = (SpannableStringBuilder) calculationField.getText();
            String lastSymbol = wholeString.substring(cursorPosition - 1, cursorPosition);
            if (lastSymbol.equals(".")) {
                dotInserted = false;
            } else if (lastSymbol.equals("\u00F7") || lastSymbol.equals("\u00D7") || lastSymbol.equals("-") || lastSymbol.equals("+") || lastSymbol.equals("%") || lastSymbol.equals("\u221A ")) {
                operatorInserted = false;
            } else if (lastSymbol.equals("=")) {
                equalOperator = false;
            }
            modifiedText.replace(cursorPosition - 1, cursorPosition, "");
            calculationField.setText(modifiedText);
            calculationField.setSelection(cursorPosition - 1);
        }
    }

    private void updateText(String stringToAdd) {

        String oldString = calculationField.getText().toString();
        int cursorPosition = calculationField.getSelectionStart();

        String leftString = oldString.substring(0, cursorPosition);
        String rightString = oldString.substring(cursorPosition);

        if (stringToAdd.equals("\u221A ")) {
            calculationField.setText(String.format("%s%s", stringToAdd, oldString));
            calculationField.setSelection(cursorPosition + 2);
        } else {
            calculationField.setText(String.format("%s%s%s", leftString, stringToAdd, rightString));
            calculationField.setSelection(cursorPosition + 1);
        }

    }

    private void emptyPlace() {
        if (getString(R.string.calculation_field).equals(calculationField.getText().toString())) {
            calculationField.setText("");
        }
    }

    private void cursorPlace() {
        int cursorPosition = calculationField.getSelectionStart();
        calculationField.setSelection(cursorPosition + 1);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, NightActivity.class);
        startActivity(intent);
    }
}