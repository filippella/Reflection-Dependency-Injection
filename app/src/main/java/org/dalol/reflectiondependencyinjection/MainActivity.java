/*
 * Copyright (c) 2016 Filippo Engidashet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.reflectiondependencyinjection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Solve private Calculator calculator;

    private Spinner operator;
    private EditText numberOne;
    private EditText numberTwo;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Injector.inject(this, "Filippo");

        numberOne = (EditText) findViewById(R.id.fieldNumberOne);
        numberTwo = (EditText) findViewById(R.id.fieldNumberTwo);
        operator = (Spinner) findViewById(R.id.spinnerOperator);
        answer = (TextView) findViewById(R.id.textViewResult);

        Button calculateButton = (Button) findViewById(R.id.buttonCalculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {

        String selectedOperator = operator.getSelectedItem().toString();

        int numOne = Integer.parseInt(numberOne.getText().toString());
        int numTwo = Integer.parseInt(numberTwo.getText().toString());

        float result = 0;

        switch (selectedOperator) {
            case "+":
                result = this.calculator.add(numOne, numTwo);
                break;
            case "-":
                result = this.calculator.subtract(numOne, numTwo);
                break;
            case "/":
                result = this.calculator.divide(numOne, numTwo);
                break;
            case "*":
                result = this.calculator.multiply(numOne, numTwo);
                break;
        }

        this.answer.setText(String.format("Result = %.2f", result));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calc_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionAboutCalc:
                Toast.makeText(this, "Calculator instance name is: " + this.calculator, Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
