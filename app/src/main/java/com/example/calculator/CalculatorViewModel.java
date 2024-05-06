package com.example.calculator;

import android.util.Log;

import androidx.annotation.LongDef;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorViewModel extends ViewModel {

    private String localVariable;
    private String localVariableToScreen;
    private final MutableLiveData<String> input = new MutableLiveData<>();

    public LiveData<String> getInput() {
        return input;
    }

    private final ArrayList<Double> doublesSecondLine = new ArrayList<>();

    public void setInput(String text) {

        // 1 - show equation method
        // 2 - calculate method
        // 3 - show result method

        if (text.equals("=")) {
            if (localVariableToScreen == null) {
                localVariableToScreen = "0";
                localVariable = "0";
                Log.d("!!!!!", "1 SET INPUT. localVariableToScreen = " + localVariableToScreen);
                Log.d("!!!!!", "1 SET INPUT. localVariable = " + localVariable);
            } else if (localVariableToScreen.endsWith("+") || localVariableToScreen.endsWith("-") ||
                    localVariableToScreen.endsWith("*") || localVariableToScreen.endsWith("/") ||
                    localVariableToScreen.endsWith(".")) {
                localVariableToScreen = localVariableToScreen.substring(0, localVariableToScreen.length() - 1);
                localVariable = localVariableToScreen;
            } else if (localVariableToScreen.isEmpty()) {
                localVariableToScreen = "0";
                localVariable = "0";
                Log.d("!!!!!", "!!! 13 SET INPUT. localVariableToScreen = " + localVariableToScreen);
                Log.d("!!!!!", "!!! 13 SET INPUT. localVariable = " + localVariable);
                Log.d("!!!!!", "!!! 13 SET INPUT. localVariableToScreen length = " + localVariableToScreen.length());
                Log.d("!!!!!", "!!! 13 SET INPUT. localVariable length = " + localVariable.length());
            }
            Log.d("!!!!!", "3 SET INPUT: localVariableToScreen that is passed to calculateEquation method = " + localVariableToScreen);
            Log.d("!!!!!", "3 SET INPUT: localVariable = " + localVariable);
            calculateEquation(localVariableToScreen);
        } else if (text.equals("C")) {
            localVariableToScreen = "";
            localVariable = "";
            showResultOnScreen();
            Log.d("!!!!!", "11 SET INPUT. localVariableToScreen = " + localVariableToScreen);
            Log.d("!!!!!", "11 SET INPUT. localVariable = " + localVariable);
        } else if (text.equals("B")) {
            if (localVariableToScreen != null) {
                if (!localVariableToScreen.isEmpty()) {
                    Log.d("!!!!!", "12 SET INPUT. localVariableToScreen before = " + localVariableToScreen);
                    Log.d("!!!!!", "12 SET INPUT. localVariable before = " + localVariable);
                    localVariableToScreen = localVariableToScreen.substring(0, localVariableToScreen.length() - 1);
                    localVariable = localVariableToScreen;
                    Log.d("!!!!!", "12 SET INPUT. localVariableToScreen after = " + localVariableToScreen);
                    Log.d("!!!!!", "12 SET INPUT. localVariable after = " + localVariable);
                }
            }
            showResultOnScreen();
        } else {
            Log.d("!!!!!", "2 SET INPUT. localVariableToScreen = " + localVariableToScreen);
            Log.d("!!!!!", "2 SET INPUT. localVariable = " + localVariable);
            showEquationOnScreen(text);
        }
    }

    private void showResultOnScreen() {
        input.setValue(localVariableToScreen);
    }

    private void showEquationOnScreen(String text) {

        if (localVariableToScreen == null) {
            if (!text.equals(".") && !text.equals("=") && !text.equals("+") &&
                    !text.equals("*") && !text.equals("/")) {
                localVariableToScreen = text;
                localVariable = text;
                Log.d("!!!!!", "4 SET INPUT. localVariableToScreen = " + localVariableToScreen);
                Log.d("!!!!!", "4 SET INPUT. localVariable = " + localVariable);
            }
        } else {

            if (localVariableToScreen.equals("Infinity")) {
                localVariableToScreen = "";
                localVariable = "";
            }

            if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")) {
                if (localVariableToScreen.endsWith("+") || localVariableToScreen.endsWith("-") ||
                        localVariableToScreen.endsWith("*") || localVariableToScreen.endsWith("/") ||
                        localVariableToScreen.endsWith(".")) {
                    localVariableToScreen = localVariableToScreen.substring(0, localVariableToScreen.length() - 1);
                } else if (localVariableToScreen.endsWith("0") && localVariable.contains(".")) {
                    localVariableToScreen = localVariableToScreen
                            .replaceAll("0*$", "").replaceAll("\\.$", "");

                }
                localVariableToScreen = localVariableToScreen + text;
                localVariable = "";
                Log.d("!!!!!", "5 SET INPUT. localVariableToScreen = " + localVariableToScreen);
                Log.d("!!!!!", "5 SET INPUT. localVariable = " + localVariable);
            } else if (text.equals(".")) {
                Log.d("!!!!!", "6 SET INPUT. localVariableToScreen = " + localVariableToScreen);
                Log.d("!!!!!", "6 SET INPUT. localVariable = " + localVariable);
                if (!localVariable.isEmpty() && !localVariable.contains(".")) {
                    localVariableToScreen = localVariableToScreen + text;
                    localVariable = localVariable + text;
                }
                Log.d("!!!!!", "7 SET INPUT. localVariableToScreen = " + localVariableToScreen);
                Log.d("!!!!!", "7 SET INPUT. localVariable = " + localVariable);
            } else {
                Log.d("!!!!!", "8 SET INPUT. localVariableToScreen = " + localVariableToScreen);
                Log.d("!!!!!", "8 SET INPUT. localVariable = " + localVariable);
                if (localVariableToScreen.endsWith("0") && localVariableToScreen.length() == 1) {
                    Log.d("!!!!!", "9 SET INPUT. localVariableToScreen = " + localVariableToScreen);
                    Log.d("!!!!!", "9 SET INPUT. localVariable = " + localVariable);
                    localVariableToScreen = text;
                    localVariable = text;
                } else {
                    localVariableToScreen = localVariableToScreen + text;
                    localVariable = localVariable + text;
                }
                Log.d("!!!!!", "10 SET INPUT. localVariableToScreen = " + localVariableToScreen);
                Log.d("!!!!!", "10 SET INPUT. localVariable = " + localVariable);
            }
        }

        showResultOnScreen();
    }

    private void calculateEquation(String inputEquation) {

        final ArrayList<String> multDivVarStringsArray = new ArrayList<>();
        final ArrayList<String> addSubtVarStringsArray = new ArrayList<>();

        Log.d("!!!!!", "1----------------------------------------------------------------------------------START");
        Log.d("!!!!!", "1: input string that received = " + inputEquation);

        String[] splittedString = inputEquation.split("(?=[+-])(?<=[0-9])");
        Log.d("!!!!!", "1: splitted string = " + Arrays.toString(splittedString));
        Log.d("!!!!!", "1----------------------------------------------------------------------------------END");
        Log.d("!!!!!", "                                                              ");

        Log.d("!!!!!", "2----------------------------------------------------------------------------------START");
        for (int i = 0; i < splittedString.length; i++) {
            String item = splittedString[i];
            if (item.contains("*") || item.contains("/")) {
                multDivVarStringsArray.add(item);
            } else {
                addSubtVarStringsArray.add(item);
            }
        }
        Log.d("!!!!!", "2: multDivVarStringsArray = " + multDivVarStringsArray);
        Log.d("!!!!!", "2: addSubtVarStringsArray = " + addSubtVarStringsArray);
        Log.d("!!!!!", "2: multDivVarStringsArray size = " + multDivVarStringsArray.size());
        Log.d("!!!!!", "2: addSubtVarStringsArray size = " + addSubtVarStringsArray.size());
        Log.d("!!!!!", "2----------------------------------------------------------------------------------END");
        Log.d("!!!!!", "                                                              ");

        Log.d("!!!!!", "3----------------------------------------------------------------------------------START");

        if (!multDivVarStringsArray.isEmpty()) {
            for (int i = 0; i < multDivVarStringsArray.size(); i++) {
                final ArrayList<Double> doublesFirstLine = new ArrayList<>();
                final ArrayList<Character> operatorsMulDiv = new ArrayList<>();

                String multDivVarString = multDivVarStringsArray.get(i);
                String[] splittedMultDivVars = multDivVarString.split("(?=[*/])(?<=[0-9])");

                for (int j = 0; j < splittedMultDivVars.length; j++) {
                    String item = splittedMultDivVars[j];
                    if (item.startsWith("*")) {
                        operatorsMulDiv.add('*');
                        item = item.substring(1);
                    } else if (item.startsWith("/")) {
                        operatorsMulDiv.add('/');
                        item = item.substring(1);
                    }

                    Double doubleItem = Double.parseDouble(item);
                    doublesFirstLine.add(doubleItem);
                }

                Log.d("!!!!!", "3: doublesFirstLine = " + doublesFirstLine);
                Log.d("!!!!!", "3: operatorsMulDiv = " + operatorsMulDiv);

                Double secondLineVariable = doublesFirstLine.get(0);
                for (int j = 0; j < operatorsMulDiv.size(); j++) {
                    if (operatorsMulDiv.get(j) == '*') {
                        secondLineVariable = secondLineVariable * doublesFirstLine.get(j + 1);
                    } else if (operatorsMulDiv.get(j) == '/') {
                        secondLineVariable = secondLineVariable / doublesFirstLine.get(j + 1);
                    }
                    Log.d("!!!!!", "3: secondLineVariable = " + secondLineVariable);
                }
                doublesSecondLine.add(secondLineVariable);
                Log.d("!!!!!", "3: doublesSecondLine = " + doublesSecondLine);
            }

            if (!addSubtVarStringsArray.isEmpty()) {
                Log.d("!!!!!", "6-------------------------------------------convertAddSubVarStringToDoubles() START");
                for (int i = 0; i < addSubtVarStringsArray.size(); i++) {
                    if (addSubtVarStringsArray.get(i).startsWith("+")) {
                        String subStringVar = addSubtVarStringsArray.get(i).substring(1); //test if complex number. will it write a whole number, or only index 1
                        Double doubleVariable = Double.parseDouble(subStringVar);
                        doublesSecondLine.add(doubleVariable);
                        // Log.d("!!!!!", "6 : doublesSecondLine = " + doublesSecondLine);
                    } else {
                        Double doubleVariable = Double.parseDouble(addSubtVarStringsArray.get(i));
                        doublesSecondLine.add(doubleVariable);
                        // Log.d("!!!!!", "6 : doublesSecondLine = " + doublesSecondLine);
                    }
                }

                Log.d("!!!!!", "6: FINAL doublesSecondLine = " + doublesSecondLine);
                Log.d("!!!!!", "6-------------------------------------------convertAddSubVarStringToDoubles() END");
                Log.d("!!!!!", "                                                              ");
            }

            calculateSecondLineDoubles();
        } else if (!addSubtVarStringsArray.isEmpty()) {
            Log.d("!!!!!", "6-------------------------------------------convertAddSubVarStringToDoubles() START");
            for (int i = 0; i < addSubtVarStringsArray.size(); i++) {
                if (addSubtVarStringsArray.get(i).startsWith("+")) {
                    String subStringVar = addSubtVarStringsArray.get(i).substring(1); //test if complex number. will it write a whole number, or only index 1
                    Double doubleVariable = Double.parseDouble(subStringVar);
                    doublesSecondLine.add(doubleVariable);
                } else {
                    Double doubleVariable = Double.parseDouble(addSubtVarStringsArray.get(i));
                    doublesSecondLine.add(doubleVariable);
                }
            }

            Log.d("!!!!!", "6: FINAL doublesSecondLine = " + doublesSecondLine);
            Log.d("!!!!!", "6-------------------------------------------convertAddSubVarStringToDoubles() END");
            Log.d("!!!!!", "                                                              ");

            calculateSecondLineDoubles();
        }

        Log.d("!!!!!", "3----------------------------------------------------------------------------------END");
        Log.d("!!!!!", "                                                              ");
    }

    private void calculateSecondLineDoubles() {
        Double outputResult = 0d;
        Log.d("!!!!!", "7-------------------------------------------calculateSecondLineDoubles() START");
        Log.d("!!!!!", "7: doublesSecondLine = " + doublesSecondLine);
        Log.d("!!!!!", "7: doublesSecondLine size = " + doublesSecondLine.size());

        if (doublesSecondLine.size() > 1) {
            for (int i = 0; i < doublesSecondLine.size(); i++) {
                outputResult = outputResult + doublesSecondLine.get(i);
            }
        } else {
            outputResult = doublesSecondLine.get(0);
        }

        Log.d("!!!!!", "7: outputResult = " + outputResult);
        Log.d("!!!!!", "7: localVariableToScreen before = " + localVariableToScreen);
        Log.d("!!!!!", "7: localVariable before = " + localVariable);
        localVariableToScreen = outputResult.toString();
        localVariable = outputResult.toString();
        Log.d("!!!!!", "7: localVariableToScreen after = " + localVariableToScreen);
        Log.d("!!!!!", "7: localVariable after = " + localVariable);

        if (localVariableToScreen.endsWith("0") && localVariable.contains(".")) {
            localVariableToScreen = localVariableToScreen
                    .replaceAll("0*$", "").replaceAll("\\.$", "");
            localVariable = localVariableToScreen;
        }

        Log.d("!!!!!", "7: localVariableToScreen to show as output = " + localVariableToScreen);
        Log.d("!!!!!", "7: localVariable to show as output = " + localVariable);

        showResultOnScreen();
        doublesSecondLine.clear();
        Log.d("!!!!!", "7-------------------------------------------calculateSecondLineDoubles() END");
        Log.d("!!!!!", "                                                              ");

    }

}
