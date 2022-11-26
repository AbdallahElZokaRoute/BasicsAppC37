package com.route.basicsappc37;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    TextView resultTextView;
    Button equalButton;
    TextView title;
    String operatorString = "";
    String res = "";
    boolean onClickEqual = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        title = findViewById(R.id.title_text_view);
        title.setText(getIntent().getStringExtra("title"));
        resultTextView = findViewById(R.id.result_text_view);
        equalButton = findViewById(R.id.button_equal);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Tag : ", "Result 1 = " + res);
                res = calculate(res, operatorString, resultTextView.getText().toString());
                Log.e("Tag : ", "Result 2= " + res);
                resultTextView.setText(res);
                onClickEqual = true;
                res = "";
                operatorString = "";
            }
        });

    }

    public void onDigitClick(View view) {
        Button clickedButton;//null
        if (view instanceof Button) {
            clickedButton = ((Button) view);
            String digit = clickedButton.getText().toString();
            if (onClickEqual) {
                resultTextView.setText(digit);
                onClickEqual = false;

            } else {
                resultTextView.append(digit);
            }
        }
    }

    public void onOperatorClick(View view) {
        if (view instanceof Button) {
            Button operatorButton = ((Button) view);
            String operation = operatorButton.getText().toString();
            onClickEqual = false;
            if (operatorString.isEmpty()) { // First Case
                res = resultTextView.getText().toString();
                operatorString = operation;
                resultTextView.setText(null);
            } else {//Second Case
                Log.e("Tag : ", "Result 1 = " + res);
                res = calculate(res, operatorString, resultTextView.getText().toString());
                Log.e("Tag : ", "Result 2= " + res);
                operatorString = operation;
                Log.e("Tag : ", "Operation = " + operation);
                resultTextView.setText(null);

            }
        }

    }

    public String calculate(String LHS, String OP, String RHS) {
        double num1 = Double.parseDouble(LHS);
        double num2 = Double.parseDouble(RHS);
        double result = 0.0;
        if (OP.equals("+")) {
            result = num1 + num2;
        } else if (OP.equals("-")) {
            result = num1 - num2;
        } else if (OP.equals("*")) {
            result = num1 * num2;
        } else if (OP.equals("/")) {
            result = num1 / num2;

        }
        return result + "";


    }
}