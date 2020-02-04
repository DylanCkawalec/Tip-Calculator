package edu.miracosta.cs134.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //instance vars
    //bridge the view and model
    private edu.miracostacollege.cs134.tipcalculator.Bill currentBill;

    private EditText amountEditText;
    private TextView percentTextView;
    private SeekBar percentSeekBar;
    private TextView tipTextView;
    private TextView totalTextView;

    //instance variable to format output(currency and percent)
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
    NumberFormat percent = NumberFormat.getCurrencyInstance(Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //wire up" instance vairables ( initialize them)
        currentBill = new edu.miracostacollege.cs134.tipcalculator.Bill();
        amountEditText = findViewById(R.id.amountEditText);
        amountEditText = findViewById(R.id.percentTextView);
        amountEditText = findViewById(R.id.percentSeekBar);
        amountEditText = findViewById(R.id.tipTextView);
        amountEditText = findViewById(R.id.totalTextView);

        //lets set the current tip percentage
        currentBill.setTipPercent(percentSeekBar.getProgress() / 100.0);

        //implement the interface for the EditText
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //read the input from the amountEditText (view) and store in the current Bill(Model)
                try{
                double amount = Double.parseDouble(amountEditText.getText().toString());
                }
                //store the amount in the bill
                catch(NumberFormatException e)
                {
                currentBill.setAmount(0.0);
                }
                //the reason wh we do this is because the
                calculateBill();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //update the tip percent
                currentBill.setTipPercent(percentSeekBar.getProgress() / 100.0);
                //update view
                percentTextView.setText(percent.format(currentBill.getTipPercent()));
                calculateBill();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calculateBill(){

        //update the tip textView and the totalTextView
        totalTextView.setText(currency.format(currentBill.getTipAmount()));
        totalTextView.setText(currency.format(currentBill.getTipAmount()));


    }
}
