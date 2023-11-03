package com.example.lab09e02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private String currentInput = "";
    private double firstOperand = 0;
    private String operator = "";
    private boolean operatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Set click listeners for number buttons (0-9)
        setNumberButtonClickListeners();

        // Set click listeners for arithmetic operation buttons (+, -, *, /)
        setOperatorButtonClickListeners();

        // Set click listener for the equals button (=)
        setEqualsButtonClickListener();
    }

    private void setNumberButtonClickListeners() {
        for (int i = 0; i <= 9; i++) {
            int resId = getResources().getIdentifier("button" + i, "id", getPackageName());
            Button button = findViewById(resId);
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleNumberClick(finalI);
                }
            });
        }
    }

    private void setOperatorButtonClickListeners() {
        Button addButton = findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorClick("+");
            }
        });

        Button subtractButton = findViewById(R.id.buttonSubtract);
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorClick("-");
            }
        });

        Button multiplyButton = findViewById(R.id.buttonMultiply);
        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorClick("*");
            }
        });

        Button divideButton = findViewById(R.id.buttonDivide);
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorClick("/");
            }
        });
    }

    private void setEqualsButtonClickListener() {
        Button equalsButton = findViewById(R.id.buttonEqual);
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
            }
        });
    }

    private void handleNumberClick(int number) {
        if (operatorClicked) {
            currentInput = String.valueOf(number);
            operatorClicked = false;
        } else {
            currentInput += number;
        }
        updateResultText(currentInput);
    }

    private void handleOperatorClick(String newOperator) {
        if (!operator.isEmpty()) {
            calculateResult();
        }
        firstOperand = Double.parseDouble(currentInput);
        operator = newOperator;
        operatorClicked = true;
    }

    private void calculateResult() {
        if (!operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
            }

            currentInput = String.valueOf(result);
            operator = "";
            updateResultText(currentInput);
        }
    }

    private void updateResultText(String text) {
        resultTextView.setText(text);
    }
}
