package com.application.fertilizercalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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


    private Double phValue = 6.5;
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

    private TextView cropName;
    private ImageView cropImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooseButton = (Button)findViewById(R.id.chooseCropButton);
        calculateButton = (Button)findViewById(R.id.btnCalculate);
        nitrogenRadioG = (RadioGroup) findViewById(R.id.nitrogenRadioG);

        // top crop part.
        cropName = findViewById(R.id.cropName);
        cropImage = findViewById(R.id.cropImage);

//        sendFertilizerElementsRequest();
        clickChooseCropButton();

    }


    private void clickNitrogenRadioGroup(){
        nitrogenRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {
                int radioButtonID = arg0.getCheckedRadioButtonId();
                View view = arg0.findViewById(radioButtonID);
                int idx = arg0.indexOfChild(view);
                RadioButton r = (RadioButton) arg0.getChildAt(idx);
                nitrogenLevel = r.getText().toString();

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
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
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
                if (crop.getPhoto() != null && crop.getPhoto().equals("")){
                    Picasso.with(MainActivity.this).load(crop.getPhoto()).into(cropImage);
                }

            }



        });

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 3);
        cropRecyclerView.setLayoutManager(layoutManager);
        cropRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

        FertilizerRequest request = new FertilizerRequest(cropID,potassiumLevel,nitrogenLevel, omLevel,phosphorousLevel,phValue,region,soilTexture);

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


