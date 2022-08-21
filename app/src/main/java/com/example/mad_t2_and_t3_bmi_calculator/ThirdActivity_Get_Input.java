package com.example.mad_t2_and_t3_bmi_calculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity_Get_Input extends AppCompatActivity
{
    private static final String CHOISE = "UserChoise";
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

        choice = intent.getStringExtra(CHOISE); // get the data from second Activity

        /* Set the User interface based on the user choice */

        setuserInterface(textTitle,textViewWeight,textViewHeight);
    }


    public static Intent getIntent(Context context, String choice)
    {
        Intent intent = new Intent(context,ThirdActivity_Get_Input.class);
        intent.putExtra(CHOISE,choice);

        return intent;
    }

    private void setuserInterface(TextView title, TextView weight, TextView height)
    {
        if(choice.equals("Metric"))
        {
            title.setText("You have selected Metric system");
            weight.setText("KG");
            height.setText("CM");
        }
        else
        {
            /* Imperial */

            title.setText("You have selected Imperial system");
            weight.setText("LB");
            height.setText("Inch");
        }
    }
}