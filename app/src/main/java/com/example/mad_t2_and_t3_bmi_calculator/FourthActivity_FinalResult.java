package com.example.mad_t2_and_t3_bmi_calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;


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

        inputWeight = findViewById(R.id.inputWeight);
        inputHeight = findViewById(R.id.inputHeight);
        resultBox = findViewById(R.id.resultBox);
        resultBoxMessage = findViewById(R.id.resultBoxMessage);
        Button plusButton = findViewById(R.id.plusButton);
        Button minusButton = findViewById(R.id.minusButton);

        /* Get the Intent Object */

        Intent intent = getIntent();

        /* Get the values from Previous Activity */

        weight = intent.getDoubleExtra(WEIGHT,0);
        height = intent.getDoubleExtra(Height,0);
        choice = intent.getStringExtra(CHOICE);


        setUserInterface();

        /* Check Whether large device or small device Only large device will contains the button */

        if(plusButton != null &&  minusButton != null)
        {
            plusButton.setOnClickListener(view -> increaseTheView());
            minusButton.setOnClickListener(view -> decreaseTheView());
        }


    }

    /* Increases the entire view by 10% */
    private void increaseTheView()
    {
        increaseTheTextSize(inputWeight);
        increaseTheTextSize(inputHeight);
        increaseTheTextSize(resultBox);
        increaseTheTextSize(resultBoxMessage);
    }

    private void increaseTheTextSize(TextView textView)
    {
        float size = textView.getTextSize();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,size*1.1F);
    }

    /* Decreases the entire view by 10% */
    private void decreaseTheView()
    {
        decreaseTheTextSize(inputWeight);
        decreaseTheTextSize(inputHeight);
        decreaseTheTextSize(resultBox);
        decreaseTheTextSize(resultBoxMessage);
    }

    private void decreaseTheTextSize(TextView textView)
    {
        float size = textView.getTextSize();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,size*0.9F);
    }


    public static Intent getIntent(Context context, double pWeight, double pHeight, String choice)
    {
            Intent intent = new Intent(context,FourthActivity_FinalResult.class);
            intent.putExtra(WEIGHT,pWeight);
            intent.putExtra(Height,pHeight);
            intent.putExtra(CHOICE,choice);

            return intent;
    }

    @SuppressLint("DefaultLocale")
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

        str = String.format("Your Weight is %.3f %s",weight,weightUnit);
        //str = "Your Weight is " + weight + weightUnit;
        inputWeight.setText(str);

        str = String.format("Your height is %.3f %s",height,heightUnit);
        inputHeight.setText(str);

        double result = calculateBMI(); // Get the BMI result

        str = String.format("%.3f",result);
        resultBox.setText(str);

       /* Get the Message and Background color from pair Object */
        Pair<String, Integer> messageAndColor = getMessageAndBacColor(result);

        String message = String.valueOf(messageAndColor.first);
         int color = messageAndColor.second;

        resultBoxMessage.setText(message);
        resultBoxMessage.setBackgroundColor(color);
    }


    private double calculateBMI()
    {
        double bmi;

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


    /* Return Multiple values Through pair Object class */
    @SuppressWarnings("UnnecessaryLocalVariable")
    private Pair <String,Integer> getMessageAndBacColor(double bmi)
    {
        String message;
        int color;


        if(bmi < 18.5)
        {
            message = "Underweight";
            color = getResources().getColor(android.R.color.holo_purple);
        }
        else
        {
            if(bmi <= 24.9)
            {
                message = "Healthy Weight";
                color = getResources().getColor(R.color.Healthyweight);
            }
            else
            {
                if(bmi <= 29.9)
                {
                    message = "Overweight but not obese";
                    color = getResources().getColor(R.color.Overweight);
                }
                else
                {
                    if(bmi <= 34.9)
                    {
                        message = "Obese class I";
                        color = getResources().getColor(R.color.ObeseI);
                    }
                    else
                    {
                        if(bmi <= 39.9)
                        {
                            message = "Obese class II";
                            color = getResources().getColor(R.color.ObeseII);
                        }
                        else
                        {
                            message = "Obese class III";
                            color = getResources().getColor(R.color.ObeseIII);
                        }
                    }
                }
            }
        }

        Pair <String,Integer> pair = new Pair<>(message,color);

        return pair;
    }


}

