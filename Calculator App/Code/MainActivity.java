package com.company.calcumatecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnDel,btnAC,btnDiv,btnMinus
            ,btnDot,btnEquals,btnMulti,btnPlus;

    private TextView textViewResult , textViewHistory;

    private String number = null;

    private double firstNumber = 0;
    private double lastNumber = 0;

    private String status = null;
    private boolean operator = true;

    String history , currentResult;

    DecimalFormat myFormatter = new DecimalFormat("######.######");

    boolean dot = true;
    boolean btnAcControl = true;
    boolean btnEqualsControl = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMulti = findViewById(R.id.btnMulti);
        btnDiv = findViewById(R.id.btnDiv);

        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnDot = findViewById(R.id.btnDot);
        btnEquals = findViewById(R.id.btnEquals);

        textViewResult=findViewById(R.id.textViewResult);
        textViewHistory = findViewById(R.id.textViewHistory);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("9");
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = null;
                status = null;
                textViewResult.setText("0");
                textViewHistory.setText("");
                firstNumber =0;
                lastNumber = 0;
                dot = true;

                btnAcControl = true;


            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnAcControl)
                {
                    textViewResult.setText("0");
                }
                else {

                    number = number.substring(0,number.length()-1);

                    if(number.length()==0)
                    {
                        btnDel.setClickable(false);
                    } else if(number.contains("."))
                    {
                        dot = false;
                    }
                    else dot=true;
                    textViewResult.setText(number);


                }

            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dot)
                {
                    if(number == null)
                    {
                        number = "0.";
                    }
                    else
                    {
                        number = number + ".";
                    }
                }

                textViewResult.setText(number);
                dot = false;

            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(operator)
                {
                    if(status == "subtraction")
                    {
                        minus();
                    }
                    else if(status == "sum")
                    {
                        plus();
                    }
                    else if(status == "multiplication")
                    {
                        multi();
                    }
                    else if(status == "divide")
                    {
                        div();
                    }
                    else {
                        firstNumber = Double.parseDouble(textViewResult.getText().toString());
                    }
                }

                btnEqualsControl = true;
                operator = false;
                dot = true;

            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"+");


                if(operator)
                {
                    if(status == "multiplication")
                    {
                        multi();
                    }
                    else if(status == "subtraction")
                    {
                        minus();
                    }
                    else if(status == "divide")
                    {
                        div();
                    }
                    else
                    {
                        plus();
                    }
                }


                status = "sum";
                operator = false;
                number = null;
                dot = true;

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"-");

                if(operator)
                {
                    if(status == "multiplication")
                    {
                        multi();
                    }
                    else if(status == "sum")
                    {
                        plus();
                    }
                    else if(status == "divide")
                    {
                        div();
                    }
                    else
                    {
                        minus();
                    }
                }

                status = "subtraction";
                operator = false;
                number = null;
                dot = true;

            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"X");

                if(operator)
                {
                    if(status == "subtraction")
                    {
                        minus();
                    }
                    else if(status == "sum")
                    {
                        plus();
                    }
                    else if(status == "divide")
                    {
                        div();
                    }
                    else
                    {
                        multi();
                    }
                }

                status = "multiplication";
                operator = false;
                number = null;
                dot = true;

            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"/");

                if(operator)
                {
                    if(status == "subtraction")
                    {
                        minus();
                    }
                    else if(status == "sum")
                    {
                        plus();
                    }
                    else if(status == "multiplication")
                    {
                        multi();
                    }
                    else
                    {
                        div();
                    }
                }

                status = "divide";
                operator = false;
                number = null;
                dot = true;

            }
        });



    }


    public void numberClick(String view)
    {
       if(number == null)
       {
           number = view;
       }else if (btnEqualsControl)
       {
           firstNumber = 0;
           lastNumber = 0;
           number = view;
       }
       else {
           number = number + view;
       }

       textViewResult.setText(number);

       operator = true;
       btnAcControl = false;
       btnDot.setClickable(true);
       btnEqualsControl=false;
    }

    public void plus()
    {
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber = firstNumber + lastNumber;
        textViewResult.setText(myFormatter.format(firstNumber));


    }

    public void minus() {
        if (firstNumber == 0) {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber - lastNumber;

        }
        textViewResult.setText(myFormatter.format(firstNumber));

    }

    public  void multi(){
        if(firstNumber == 0) {
            firstNumber = 1;
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }
        else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }

        textViewResult.setText(myFormatter.format(firstNumber));
    }

    public void div(){
        if(firstNumber == 0)
        {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber / 1;
        }
        else{
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber / lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
    }
}