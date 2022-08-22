package com.example.mad_t2_and_t3_bmi_calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressWarnings("FieldCanBeLocal")
public class ThirdActivity_Get_Input extends AppCompatActivity
{
    private static final String CHOICE = "UserChoice";
    private String choice = "";
    private TextView textTitle;
    private TextView textViewWeight;
    private TextView textViewHeight;
    private Button buttonNext;
    private EditText textWeight;
    private EditText textHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_matric_input);

        textTitle = findViewById(R.id.textTitle);
        textViewWeight = findViewById(R.id.textViewWeight);
        textViewHeight = findViewById(R.id.textViewHeight);
        buttonNext = findViewById(R.id.buttonNext);
        textWeight = findViewById(R.id.textWeight);
        textHeight = findViewById(R.id.textHeight);


        Intent intent = getIntent(); // Get the Intent

        choice = intent.getStringExtra(CHOICE); // get the data from second Activity

        /* Set the User interface based on the user choice */

        setUserInterface();

        buttonNext.setOnClickListener(view -> {
            /* Get the input from User */
            double weight = Double.parseDouble(textWeight.getText().toString());
            double height = Double.parseDouble(textHeight.getText().toString());

            Intent intent1 = FourthActivity_FinalResult.getIntent(ThirdActivity_Get_Input.this,
                    weight,height,choice);

            startActivity(intent1);
        });
    }


    public static Intent getIntent(Context context, String choice)
    {
        Intent intent = new Intent(context,ThirdActivity_Get_Input.class);
        intent.putExtra(CHOICE,choice);

        return intent;
    }

    @SuppressLint("SetTextI18n")
    private void setUserInterface()
    {
        if(choice.equals("Metric"))
        {
            String strTitle = getResources().getString(R.string.Title1);

            textTitle.setText(strTitle);
            textViewWeight.setText("KG");
            textViewHeight.setText("CM");
        }
        else
        {
            /* Imperial */

            String strTitle = getResources().getString(R.string.Title2);
            textTitle.setText(strTitle);
            textViewWeight.setText("LB");
            textViewHeight.setText("Inch");
        }
    }
}