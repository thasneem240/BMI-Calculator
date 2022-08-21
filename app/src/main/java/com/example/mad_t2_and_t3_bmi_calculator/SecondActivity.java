package com.example.mad_t2_and_t3_bmi_calculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public static Intent getIntent(Context context)
    {
        Intent intent = new Intent(context,SecondActivity.class);

        return intent;
    }
}