package com.example.calculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class CalculatorViewModel extends ViewModel {

    private String localVariable;
    private String localVariableToScreen;
    private final MutableLiveData<String> input = new MutableLiveData<>();

    public LiveData<String> getInput() {
        return input;
    }

    private final ArrayList<Double> doublesSecondLine = new ArrayList<>();

    public void setInput(String text) {

        if (text.equals("=")) {
            if (localVariableToScreen == null) {
                localVariableToScreen = "0";
                localVariable = "0";
            } else if (localVariableToScreen.endsWith("+") || localVariableToScreen.endsWith("-") ||
                    localVariableToScreen.endsWith("*") || localVariableToScreen.endsWith("/") ||
                    localVariableToScreen.endsWith(".")) {
                localVariableToScreen = localVariableToScreen.substring(0,
                        localVariableToScreen.length() - 1);
                localVariable = localVariableToScreen;
            } else if (localVariableToScreen.isEmpty()) {
                localVariableToScreen = "0";
                localVariable = "0";
            }
            calculateEquation(localVariableToScreen);
        } else if (text.equals("C")) {
            localVariableToScreen = "";
            localVariable = "";
            showResultOnScreen();
        } else if (text.equals("B")) {
            if (localVariableToScreen != null) {
                if (!localVariableToScreen.isEmpty()) {
                    localVariableToScreen = localVariableToScreen.substring(0,
                            localVariableToScreen.length() - 1);
                    localVariable = localVariableToScreen;
                }
            }
            showResultOnScreen();
        } else {
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
                    localVariableToScreen = localVariableToScreen.substring(0,
                            localVariableToScreen.length() - 1);
                } else if (localVariableToScreen.endsWith("0") && localVariable.contains(".")) {
                    localVariableToScreen = localVariableToScreen
                            .replaceAll("0*$", "").replaceAll("\\.$", "");

                }
                localVariableToScreen = localVariableToScreen + text;
                localVariable = "";
            } else if (text.equals(".")) {
                if (!localVariable.isEmpty() && !localVariable.contains(".")) {
                    localVariableToScreen = localVariableToScreen + text;
                    localVariable = localVariable + text;
                }
            } else {
                if (localVariableToScreen.endsWith("0") && localVariableToScreen.length() == 1) {
                    localVariableToScreen = text;
                    localVariable = text;
                } else {
                    localVariableToScreen = localVariableToScreen + text;
                    localVariable = localVariable + text;
                }
            }
        }

        showResultOnScreen();
    }

    private void calculateEquation(String inputEquation) {

        final ArrayList<String> multDivVarStringsArray = new ArrayList<>();
        final ArrayList<String> addSubtVarStringsArray = new ArrayList<>();

        String[] splittedString = inputEquation.split("(?=[+-])(?<=[0-9])");

        for (int i = 0; i < splittedString.length; i++) {
            String item = splittedString[i];
            if (item.contains("*") || item.contains("/")) {
                multDivVarStringsArray.add(item);
            } else {
                addSubtVarStringsArray.add(item);
            }
        }

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

                Double secondLineVariable = doublesFirstLine.get(0);
                for (int j = 0; j < operatorsMulDiv.size(); j++) {
                    if (operatorsMulDiv.get(j) == '*') {
                        secondLineVariable = secondLineVariable * doublesFirstLine.get(j + 1);
                    } else if (operatorsMulDiv.get(j) == '/') {
                        secondLineVariable = secondLineVariable / doublesFirstLine.get(j + 1);
                    }
                }
                doublesSecondLine.add(secondLineVariable);
            }

            if (!addSubtVarStringsArray.isEmpty()) {
                for (int i = 0; i < addSubtVarStringsArray.size(); i++) {
                    if (addSubtVarStringsArray.get(i).startsWith("+")) {
                        String subStringVar = addSubtVarStringsArray.get(i).substring(1);
                        Double doubleVariable = Double.parseDouble(subStringVar);
                        doublesSecondLine.add(doubleVariable);
                    } else {
                        Double doubleVariable = Double.parseDouble(addSubtVarStringsArray.get(i));
                        doublesSecondLine.add(doubleVariable);
                    }
                }
            }
            calculateSecondLineDoubles();
        } else if (!addSubtVarStringsArray.isEmpty()) {
            for (int i = 0; i < addSubtVarStringsArray.size(); i++) {
                if (addSubtVarStringsArray.get(i).startsWith("+")) {
                    String subStringVar = addSubtVarStringsArray.get(i).substring(1);
                    Double doubleVariable = Double.parseDouble(subStringVar);
                    doublesSecondLine.add(doubleVariable);
                } else {
                    Double doubleVariable = Double.parseDouble(addSubtVarStringsArray.get(i));
                    doublesSecondLine.add(doubleVariable);
                }
            }
            calculateSecondLineDoubles();
        }
    }

    private void calculateSecondLineDoubles() {
        Double outputResult = 0d;

        if (doublesSecondLine.size() > 1) {
            for (int i = 0; i < doublesSecondLine.size(); i++) {
                outputResult = outputResult + doublesSecondLine.get(i);
            }
        } else {
            outputResult = doublesSecondLine.get(0);
        }

        localVariableToScreen = outputResult.toString();
        localVariable = outputResult.toString();

        if (localVariableToScreen.endsWith("0") && localVariable.contains(".")) {
            localVariableToScreen = localVariableToScreen
                    .replaceAll("0*$", "").replaceAll("\\.$", "");
            localVariable = localVariableToScreen;
        }

        showResultOnScreen();
        doublesSecondLine.clear();
    }
}
