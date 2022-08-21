package com.example.mad_t2_and_t3_bmi_calculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FourthActivity_FinalResult extends AppCompatActivity
{

    private static final String WEIGHT = "Weight";
    private static final String Height = "Height";
    private static final String CHOICE = "Choice";

    private double weight;
    private double height;
    private String choice;

    private TextView inputWeight;
    private TextView inputHeight;
    private TextView resultBox;
    private TextView resultBoxMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_final_result);

        inputWeight = findViewById(R.id.textWeight);
        inputHeight = findViewById(R.id.textHeight);
        resultBox = findViewById(R.id.resultBox);
        resultBoxMessage = findViewById(R.id.resultBoxMessage);

        /* Get the Intent Object */

        Intent intent = getIntent();

        /* Get the values from Previous Activity */

        weight = intent.getDoubleExtra(WEIGHT,0);
        height = intent.getDoubleExtra(Height,0);
        choice = intent.getStringExtra(CHOICE);


        setUserInterface();
    }

    public static Intent getIntent(Context context, double pWeight, double pHeight, String choice)
    {
            Intent intent = new Intent(context,FourthActivity_FinalResult.class);
            intent.putExtra(WEIGHT,pWeight);
            intent.putExtra(Height,pHeight);
            intent.putExtra(CHOICE,choice);

            return intent;
    }

    private void setUserInterface()
    {
        String weightUnit = "KG";
        String heightUnit = "CM";
        String str;

        if(!choice.equals("Metric"))
        {
            weightUnit = "LB";
            heightUnit = "Inch";
        }

        str = String.format("Your Weight is %lf %s",weight,weightUnit);
        inputWeight.setText(str);

        str = String.format("Your height is %lf %s",height,heightUnit);
        inputHeight.setText(str);

        double result = calculateBMI();

        resultBox.setText(String.valueOf(calculateBMI()));
    }


    private double calculateBMI()
    {
        double bmi = 0.0;

        if(choice.equals("Metric"))
        {
            bmi = weight / Math.pow(height/100,2);
        }
        else
        {
            bmi = ( weight / Math.pow(height,2) ) * 703;
        }

        return bmi;
    }

}

