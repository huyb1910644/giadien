package com.example.electric;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;
import java.text.DecimalFormat;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                if (!input.isEmpty()) {
                    double consumption = Double.parseDouble(input);
                    double price = calculateElectricityPrice(consumption);
                    resultTextView.setText(String.format(Locale.getDefault(), "Giá tiền điện là: %.0f đồng", price));
                    resultTextView.setVisibility(View.VISIBLE);
                } else {
                    resultTextView.setText("Vui lòng nhập số lượng điện tiêu thụ");
                    resultTextView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private double calculateElectricityPrice(double consumption) {
        double price = 0;
        if (consumption <= 50) {
            price = consumption * 1484;
        } else if (consumption <= 100) {
            price = 50 * 1484 + (consumption - 50) * 1533;
        } else if (consumption <= 200) {
            price = 50 * 1484 + 50 * 1533 + (consumption - 100) * 1786;
        } else if (consumption <= 300) {
            price = 50 * 1484 + 50 * 1533 + 100 * 1786 + (consumption - 200) * 2242;
        } else if (consumption <= 400) {
            price = 50 * 1484 + 50 * 1533 + 100 * 1786 + 100 * 2242 + (consumption - 300) * 2503;
        } else {
            price = 50 * 1484 + 50 * 1533 + 100 * 1786 + 100 * 2242 + 100 * 2503 + (consumption - 400) * 2587;
        }

        return price;

    }
}
