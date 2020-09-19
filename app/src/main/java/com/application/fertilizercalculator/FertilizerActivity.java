package com.application.fertilizercalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.application.fertilizercalculator.model.FertilizerResponse;

public class FertilizerActivity extends AppCompatActivity {

    private FertilizerResponse response;
    private AppCompatTextView ureaAmount;
    private AppCompatTextView dapAmount;
    private AppCompatTextView mopAmount;
    private AppCompatTextView omAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer);
        setToolBar();

        response = (FertilizerResponse) getIntent().getSerializableExtra("Fertilizer");

        ureaAmount = findViewById(R.id.ureaAmount);
        dapAmount = findViewById(R.id.dapAmount);
        mopAmount = findViewById(R.id.mopAmount);
        omAmount = findViewById(R.id.omAmount);

        if (response != null){
            if (response.getUrea() != null){
                ureaAmount.setText(response.getUrea().toString());
            }

            if (response.getDAP() != null){
                dapAmount.setText(response.getDAP().toString());
            }

            if (response.getMOP() != null){
                mopAmount.setText(String.valueOf(response.getMOP().toString()));
            }

            if (response.getOrganicManure() != null){
                omAmount.setText(response.getOrganicManure());
            }


        }


    }

    private void setToolBar(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}