package com.example.mad_t2_and_t3_bmi_calculator;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button buttonMetric = findViewById(R.id.buttonMetric);
        Button buttonImperial = findViewById(R.id.buttonImperial);


        buttonMetric.setOnClickListener(view -> startActivity("Metric"));

        buttonImperial.setOnClickListener(view -> startActivity("Imperial"));
    }

    public static Intent getIntent(Context context)
    {
        return new Intent(context,SecondActivity.class);
    }

    /* Start the Third Activity  Activity And pass the Data between them */

    public void startActivity(String choice)
    {
        Intent intent = ThirdActivity_Get_Input.getIntent(SecondActivity.this,choice);

        startActivity(intent);
    }

}