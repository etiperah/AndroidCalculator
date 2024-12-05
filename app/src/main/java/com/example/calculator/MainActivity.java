package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private double num1;
    private double num2;
    private char ch;
    private boolean isResultShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        result = findViewById(R.id.textViewResult);
        result.setText("");
    }

    public void numFunc(View view) {
        Button button = (Button) view;

        if (isResultShown) {
            result.setText("");
            isResultShown = false;
        }
        result.append(button.getText().toString());
    }

    public void actionFunc(View view) {
        try {
            num1 = Double.parseDouble(result.getText().toString());
        } catch (NumberFormatException e) {
            result.setText("Invalid Input");
            return;
        }

        ch = ((Button) view).getText().toString().charAt(0);
        result.setText("");
    }

    public void equalsFunc(View view) {
        try {
            num2 = Double.parseDouble(result.getText().toString());
        } catch (NumberFormatException e) {
            result.setText("Invalid Input");
            return;
        }

        double calculationResult;
        switch (ch) {
            case '+':
                calculationResult = num1 + num2;
                break;
            case '-':
                calculationResult = num1 - num2;
                break;
            case 'x':
                calculationResult = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    calculationResult = num1 / num2;
                } else {
                    result.setText("Cannot divide by zero");
                    return;
                }
                break;
            case '%':
                if (num2 != 0) {
                    calculationResult = num1 % num2;
                } else {
                    result.setText("Cannot divide by zero");
                    return;
                }
                break;
            default:
                result.setText("Invalid operation");
                return;
        }
        if (calculationResult == (int) calculationResult) {
            result.setText(String.valueOf((int) calculationResult));
        } else {
            result.setText(String.valueOf(calculationResult));
        }
        isResultShown = true;
    }

    public void clearFunc(View view) {
        num1 = 0;
        num2 = 0;
        ch = '\0';
        isResultShown = false;
        result.setText("");
    }
}
