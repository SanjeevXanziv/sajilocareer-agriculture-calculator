package com.application.fertilizercalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.application.fertilizercalculator.adapter.CropAdapter;
import com.application.fertilizercalculator.model.Crop;
import com.application.fertilizercalculator.model.FertilizerRequest;
import com.application.fertilizercalculator.model.FertilizerResponse;
import com.application.fertilizercalculator.retrofit.GetDataService;
import com.application.fertilizercalculator.retrofit.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private Integer cropID;
    /*private String nitrogenLevel = this.getResources().getString(R.string.high);
    private String phosphorousLevel = this.getResources().getString(R.string.high);
    private String potassiumLevel = this.getResources().getString(R.string.high);
    private String omLevel = this.getResources().getString(R.string.high);
    private String soilTexture = this.getResources().getString(R.string.loam);
    private String region = this.getResources().getString(R.string.hill);*/

    private String nitrogenLevel = "High";
    private String phosphorousLevel = "High";
    private String potassiumLevel = "High";
    private String omLevel = "High";
    private String soilTexture = "Loam";
    private String region = "Hill";

    private Button chooseButton;
    private Button calculateButton;
    private RadioGroup nitrogenRadioG;
    private RadioGroup phosphorousRadioG;
    private RadioGroup potassiumRadioG;
    private RadioGroup omRadioG;
    private RadioGroup regionRadioG;
    private RadioGroup soilTextureRadioG;

    private TextView cropName;
    private ImageView cropImage;

    private EditText etPhValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooseButton = (Button)findViewById(R.id.chooseCropButton);
        calculateButton = (Button)findViewById(R.id.btnCalculate);
        nitrogenRadioG = (RadioGroup) findViewById(R.id.nitrogenRadioG);
        phosphorousRadioG = (RadioGroup) findViewById(R.id.phosphorousRadioG);
        potassiumRadioG = (RadioGroup) findViewById(R.id.potassiumRadioG);
        omRadioG = (RadioGroup) findViewById(R.id.omRadioG);
        regionRadioG = (RadioGroup) findViewById(R.id.regionRadioG);
        soilTextureRadioG = (RadioGroup) findViewById(R.id.soilTextureRadioG);

        // edittext
        etPhValue = findViewById(R.id.phValue);

        // top crop part.
        cropName = findViewById(R.id.cropName);
        cropImage = findViewById(R.id.cropImage);

        clickAllRadioGroup();
        clickChooseCropButton();
        clickCalculateButton();

    }


    private void clickCalculateButton(){
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onValid()){
                    sendFertilizerElementsRequest();
                }

            }
        });

    }

    private Boolean onValid(){
        if (cropID == null){
            Toast.makeText(MainActivity.this,"Choose Crop first", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etPhValue.getText().toString().equals("")){
            Toast.makeText(MainActivity.this,"PhValue should be given", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (nitrogenLevel.isEmpty()){
            return false;
        }

        if (phosphorousLevel.isEmpty()){
            return false;
        }

        if (potassiumLevel.isEmpty()){
            return false;
        }
        if (omLevel.isEmpty()){
            return false;
        }
        if (soilTexture.isEmpty()){
            return false;
        }

        if (region.isEmpty()){
            return false;
        }

        return true;

    }

    private void clickAllRadioGroup(){
        clickNitrogenRadioGroup();
        clickPhosPhorousRadioGroup();
        clickPotassiumRadioGroup();
        clickOMRadioGroup();
        clickSoilTextureRadioGroup();
        clickRegionRadioGroup();
    }


    private void clickNitrogenRadioGroup(){
        nitrogenRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View view = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(view);
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(idx);
                nitrogenLevel = radioButton.getText().toString();

                Log.d("NitrogenLevel: ", nitrogenLevel);

            }
        });
    }

    private void clickPhosPhorousRadioGroup(){
        phosphorousRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View view = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(view);
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(idx);
                phosphorousLevel = radioButton.getText().toString();


            }
        });
    }

    private void clickPotassiumRadioGroup(){
        potassiumRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View view = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(view);
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(idx);
                potassiumLevel = radioButton.getText().toString();

            }
        });
    }
    private void clickOMRadioGroup(){
        omRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View view = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(view);
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(idx);
                omLevel = radioButton.getText().toString();

            }
        });
    }
    private void clickSoilTextureRadioGroup(){
        soilTextureRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View view = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(view);
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(idx);
                soilTexture = radioButton.getText().toString();

            }
        });
    }
    private void clickRegionRadioGroup(){
        regionRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View view = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(view);
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(idx);
                region = radioButton.getText().toString();


            }
        });
    }


    private void clickChooseCropButton(){
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCropListRequest();
            }
        });
    }

    private void showCropDialog(List<Crop> listOfCrop){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = (View) inflater.inflate(R.layout.dialog_crop_list, null);
        alertDialog.setView(dialogView);
        alertDialog.setTitle("Select a Crop");


        RecyclerView cropRecyclerView = dialogView.findViewById(R.id.cropRecyclerView);
        cropRecyclerView.setHasFixedSize(true);
        CropAdapter adapter = new CropAdapter(listOfCrop,this,new CropAdapter.OnProvideCrop(){
            @Override
            public void selectedCrop(Crop crop) {
                cropID = crop.getId();
                cropName.setText(crop.getName());
                if (crop.getPhoto() != null && !crop.getPhoto().equals("")){
                    Picasso.with(MainActivity.this).load(crop.getPhoto()).into(cropImage);
                }

            }


        });



//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 3);
        cropRecyclerView.setLayoutManager(layoutManager);
        cropRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setCancelable(true);
        alertDialog.show();

    }

    private void sendFertilizerElementsRequest(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        String phValueString = etPhValue.getText().toString();
        Double phValueDouble = Double.parseDouble(phValueString);

        FertilizerRequest request = new FertilizerRequest(cropID,potassiumLevel,nitrogenLevel, omLevel,
                phosphorousLevel,phValueDouble,region,soilTexture);

        Call<FertilizerResponse> call = service.getFertilizerElementsRequest(request);
        call.enqueue(new Callback<FertilizerResponse>() {
            @Override
            public void onResponse(Call<FertilizerResponse> call, Response<FertilizerResponse> response) {
                Integer code = response.code();
                Log.d("MA Code", code.toString());


                Toast.makeText(MainActivity.this, "Success : "+call.toString(), Toast.LENGTH_SHORT).show();
                Log.d("MA Success", call.toString());
                if (response.code() == 200){
                    // go to Fertilizer activity.
                    FertilizerResponse fertilizerResponse = (FertilizerResponse)response.body();
                    goToFertilizerActivity(fertilizerResponse);
                }
                else {
                    Toast.makeText(MainActivity.this, "There is something error: "+ response.message(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<FertilizerResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error : "+t.toString(), Toast.LENGTH_SHORT).show();
                Log.e("MA Error: ", t.toString());
            }


        });
    }

    private void goToFertilizerActivity(FertilizerResponse response){
        Intent intent = new Intent(this,FertilizerActivity.class);
        intent.putExtra("Fertilizer",response);
        this.startActivity(intent);
    }


    private void sendCropListRequest(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Crop>> call = service.getCropListRequest();
        call.enqueue(new Callback<List<Crop>>() {
            @Override
            public void onResponse(Call<List<Crop>> call, Response<List<Crop>> response) {

                if (response.code() == 200){
                    // show cropdialog , print list siz
                    Log.d("CropSize : ", response.body().size()+"");

                    List<Crop> list = (List<Crop>)response.body();
                    showCropDialog(list);

                }else {
                    Toast.makeText(MainActivity.this,"CropList cant be retrieved.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Crop>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}


