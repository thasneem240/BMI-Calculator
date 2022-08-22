package com.example.mad_t2_and_t3_bmi_calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

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
    private Slider sliderWeight;
    private Slider sliderHeight;
    private ConstraintLayout myConstraintLayout;

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
        sliderWeight = findViewById(R.id.sliderWeight);
        sliderHeight = findViewById(R.id.sliderHeight);
        myConstraintLayout = findViewById(R.id.myConstraintLayout);


        Intent intent = getIntent(); // Get the Intent

        choice = intent.getStringExtra(CHOICE); // get the data from second Activity

        /* Set the User interface based on the user choice */

        setUserInterface();

        buttonNext.setOnClickListener(view -> {

            /* Check the Field is Empty or not */
            if(!checkEmpty(textWeight) && !checkEmpty(textHeight))
            {
                    /* Get the input from User */
                double weight = Double.parseDouble(textWeight.getText().toString());
                double height = Double.parseDouble(textHeight.getText().toString());

                /* Check the input whether it is valid or not */
                if(!checkValue(weight,height))
                {
                    Intent intent1 = FourthActivity_FinalResult.getIntent(ThirdActivity_Get_Input.this,
                            weight,height,choice);

                    startActivity(intent1);
                }

            }


        });

        sliderWeight.addOnSliderTouchListener(touchListener1);
        sliderHeight.addOnSliderTouchListener(touchListener2);

        textWeight.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                updateSlider(textWeight,sliderWeight);
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        textHeight.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                updateSlider(textHeight,sliderHeight);
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
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

    private final Slider.OnSliderTouchListener touchListener1 =
            new Slider.OnSliderTouchListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onStartTrackingTouch(Slider slider)
                {

                }

                @SuppressLint("RestrictedApi")
                @Override
                public void onStopTrackingTouch(Slider slider)
                {
                    double value = slider.getValue();
                    String str = String.format("%.2f",value);

                    textWeight.setText(str);
                }
            };


    private final Slider.OnSliderTouchListener touchListener2 =
            new Slider.OnSliderTouchListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onStartTrackingTouch(Slider slider)
                {

                }

                @SuppressLint("RestrictedApi")
                @Override
                public void onStopTrackingTouch(Slider slider)
                {
                    double value = slider.getValue();
                    String str = String.format("%.2f",value);

                    textHeight.setText(str);
                }
            };


    /* Validate Edit Text field is Empty or not */

    public boolean checkEmpty(EditText editText)
    {
        boolean isEmpty = false;

        if(editText.length() == 0)
        {
            isEmpty = true;
            editText.setError("Empty field!! Please Enter the input or select through slider ");
        }

        return isEmpty;
    }


    public boolean checkValue(double pWeight, double pHeight)
    {
        boolean invalidValue = false;

        if( pWeight <= 0 || pHeight <= 0)
        {
            invalidValue = true;
            String errorMessage = " Please input Positive Numbers as input";
            displayPopUpMessage(errorMessage);
            clearFields();
        }
        else
        {
            if( pWeight > 300 || pHeight > 300)
            {
                invalidValue = true;
                String errorMessage = " Please input values between 0-300";
                displayPopUpMessage(errorMessage);
                clearFields();
            }
        }

        return  invalidValue;
    }


    private void displayPopUpMessage(String errorMessage)
    {
        Snackbar snackbar = Snackbar.make(myConstraintLayout,errorMessage,Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    private void clearFields()
    {
        textWeight.setText("");
        sliderWeight.setValue(0);

        textHeight.setText("");
        sliderHeight.setValue(0);
    }

    /* Check Whether the String is Numeric or not */
    private boolean isNumeric(String str)
    {
        try
        {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    private void updateSlider(EditText editText, Slider slider)
    {
        String str = String.valueOf(editText.getText());

        if(isNumeric(str))
        {
            float newValue = (float)Double.parseDouble(str);

            if( newValue >= 0 && newValue <= 300)
            {
                slider.setValue((float)newValue);
                slider.setValue(newValue);
            }
            else
            {
                slider.setValue(0);
            }
        }
    }

}