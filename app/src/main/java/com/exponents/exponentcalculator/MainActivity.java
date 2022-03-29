package com.exponents.exponentcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView zero, one, two, three, four, five, six, seven, eight, nine, all_clear, mod, percent, divide, multiply, minus, plus, equal, backspace;
    TextView operation, result;
    String input = "", output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Defining the hookups
        defineHooks();

        //setListeners
        setListeners();

        result.setText("");
        operation.setText("");

    }

    private void setListeners(){
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);

        all_clear.setOnClickListener(this);
//        mod.setOnClickListener(this);
//        percent.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        equal.setOnClickListener(this);
        backspace.setOnClickListener(this);
    }

    private void defineHooks(){
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);

        all_clear = findViewById(R.id.all_clear);
        mod = findViewById(R.id.mod);
        percent = findViewById(R.id.percent);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        equal = findViewById(R.id.equal);
        backspace = findViewById(R.id.backspace);

        operation = findViewById(R.id.operation);
        result = findViewById(R.id.result);
    }

    @Override
    public void onClick(View view) {
        if(view == zero){
            operation("0");
        }
        else if(view == one){
            operation("1");
        }else if(view == two){
            operation("2");
        }else if(view == three){
            operation("3");
        }else if(view == four){
            operation("4");
        }else if(view == five){
            operation("5");
        }else if(view == six){
            operation("6");
        }else if(view == seven){
            operation("7");
        }else if(view == eight){
            operation("8");
        }else if(view == nine){
            operation("9");
        }else if(view == all_clear){
            operation("all_clear");
        }else if(view == mod){
            operation("||");
        }else if(view == percent){
            operation("%");
        }else if(view == divide){
            operation(" / ");
        }else if(view == multiply){
            operation(" x ");
        }else if(view == minus){
            operation(" - ");
        }else if(view == plus){
            operation(" + ");
        }else if(view == equal){
            operation("equal");
        }else if(view == backspace){
            operation("backspace");
        }
    }

    private void operation(String value){
        switch (value) {
            case "all_clear":
                input = "";
                output = "";
                operation.setText(input);
                result.setText(output);
                return;
            case "backspace":
                if (input.length() <= 0) return;
                if (input.length() > 3 && (input.charAt(input.length() - 2) == '+' || input.charAt(input.length() - 2) == '-' || input.charAt(input.length() - 2) == 'x' || input.charAt(input.length() - 2) == '/' || input.charAt(input.length() - 2) == '%')) {
                    input = input.substring(0, input.length() - 3);
                } else {
                    input = input.substring(0, input.length() - 1);
                }
                break;
            case "equal":
                if (input.length() > 3 && input.charAt(input.length() - 1) == ' ') {
                    input = input.substring(0, input.length() - 3);
                    operation.setText(input);
                }
                getResult();
                return;
            default:
                if (input.length() <= 0 && (value.equals(" + ") || value.equals(" - ") || value.equals(" x ") || value.equals(" / ") || value.equals(" % ")))
                    return;
                if (value.length() == 3 && input.length() > 3 && (input.charAt(input.length() - 2) == '+' || input.charAt(input.length() - 2) == '-' || input.charAt(input.length() - 2) == 'x' || input.charAt(input.length() - 2) == '/' || input.charAt(input.length() - 2) == '%') && (value.charAt(1) == '+' || value.charAt(1) == '-' || value.charAt(1) == 'x' || value.charAt(1) == '/' || value.charAt(1) == '%'))
                    return;
                input += value;
                break;
        }
        operation.setText(input);
    }

    private void getResult(){
        String tempInput = input;
        String[] operandsArray = tempInput.split(" ");
        ArrayList<String> operands = new ArrayList<String>(Arrays.asList(operandsArray));

        if(operands.size() == 0){
            System.out.println("Entered wrong");
            output = input;
            result.setText(output);
            input = "";
            operation.setText(input);
            return;
        }

        if(operands.size() > 1) getAns("/", operands);
        if(operands.size() > 1) getAns("x", operands);
        if(operands.size() > 1) getAns("+", operands);
        if(operands.size() > 1) getAns("-", operands);
    }

    private void getAns(String operator, ArrayList<String> operands){
        for(int i=0; i<operands.size(); i++){
            if(operands.get(i).equals(operator)){
                switch (operands.get(i)) {
                    case "/": {
                        double temp = Double.parseDouble(operands.get(i - 1)) / Double.parseDouble(operands.get(i + 1));
                        operands.set(i - 1, "" + temp);
                        operands.remove(i);
                        operands.remove(i);
                        i--;
                        break;
                    }
                    case "x": {
                        double temp = Double.parseDouble(operands.get(i - 1)) * Double.parseDouble(operands.get(i + 1));
                        operands.set(i - 1, "" + temp);
                        operands.remove(i);
                        operands.remove(i);
                        i--;
                        break;
                    }
                    case "+": {
                        double temp = Double.parseDouble(operands.get(i - 1)) + Double.parseDouble(operands.get(i + 1));
                        operands.set(i - 1, "" + temp);
                        operands.remove(i);
                        operands.remove(i);
                        i--;
                        break;
                    }
                    case "-": {
                        double temp = Double.parseDouble(operands.get(i - 1)) - Double.parseDouble(operands.get(i + 1));
                        operands.set(i - 1, "" + temp);
                        operands.remove(i);
                        operands.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }

        if(operands.size() == 1){
            output = operands.get(0);
            result.setText(output);
            input = "";
            operation.setText(input);
        }
    }
}