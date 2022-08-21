package com.example.mad_t2_and_t3_bmi_calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity_Get_Input extends AppCompatActivity
{
    private static final String CHOICE = "UserChoice";
    private String choice = "";
    private TextView textTitle;

    private TextView textViewWeight;
    private TextView textViewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_matric_input);

        textTitle = findViewById(R.id.textTitle);
        textViewWeight = findViewById(R.id.textViewWeight);
        textViewHeight = findViewById(R.id.textViewHeight);

        Intent intent = getIntent(); // Get the Intent

        choice = intent.getStringExtra(CHOICE); // get the data from second Activity

        /* Set the User interface based on the user choice */

        setUserInterface(textTitle,textViewWeight,textViewHeight);
    }


    public static Intent getIntent(Context context, String choice)
    {
        Intent intent = new Intent(context,ThirdActivity_Get_Input.class);
        intent.putExtra(CHOICE,choice);

        return intent;
    }

    @SuppressLint("SetTextI18n")
    private void setUserInterface(TextView title, TextView weight, TextView height)
    {
        if(choice.equals("Metric"))
        {
            String strTitle = getResources().getString(R.string.Title1);

            title.setText(strTitle);
            weight.setText("KG");
            height.setText("CM");
        }
        else
        {
            /* Imperial */

            String strTitle = getResources().getString(R.string.Title2);
            title.setText(strTitle);
            weight.setText("LB");
            height.setText("Inch");
        }
    }
}