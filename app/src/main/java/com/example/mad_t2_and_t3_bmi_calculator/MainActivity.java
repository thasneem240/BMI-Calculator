package com.example.mad_t2_and_t3_bmi_calculator;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    private Button buttonYes;
    private Button buttonNo;
    private CoordinatorLayout myCoordinatorLayot;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonYes = findViewById(R.id.buttonMetric);
        buttonNo =  findViewById(R.id.buttonNo);
        myCoordinatorLayot = findViewById(R.id.myCoordinatorLayout);

        buttonNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                displayPopUpMessage();
            }
        });

        buttonYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = SecondActivity.getIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

    private void displayPopUpMessage()
    {
        String message  = "The User Age Must be more than 20 In order to   Calculate the BMI ";
        Snackbar snackbar = Snackbar.make(myCoordinatorLayot,message,Snackbar.LENGTH_LONG);

        snackbar.show();
    }


}