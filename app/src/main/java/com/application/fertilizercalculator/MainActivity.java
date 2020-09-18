package com.application.fertilizercalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.application.fertilizercalculator.model.FertilizerRequest;
import com.application.fertilizercalculator.model.FertilizerResponse;
import com.application.fertilizercalculator.retrofit.GetDataService;
import com.application.fertilizercalculator.retrofit.RetrofitClientInstance;

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
//    private Button calculateButton;
    private RadioGroup nitrogenRadioG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooseButton = (Button)findViewById(R.id.chooseButton);
//        calculateButton = (Button)findViewById(R.id.btnCalculate);
        nitrogenRadioG = (RadioGroup) findViewById(R.id.nitrogenRadioG);

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
                showCropDialog();
            }
        });
    }

    private void showCropDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.dialog_crop_list, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("Select a Crop");

        RecyclerView recyclerView = convertView.findViewById(R.id.cropRecyclerView);

//        ListView lv = (ListView) convertView.findViewById(R.id.lv);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
//        lv.setAdapter(adapter);
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
//                generateDataList(response.body());
                if (response.body().getUrea() != null){
                    String Urea = response.body().getUrea().toString();
                    Log.d("MA Urea", Urea);
                }

            }

            @Override
            public void onFailure(Call<FertilizerResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error : "+t.toString(), Toast.LENGTH_SHORT).show();
                Log.e("MA Error: ", t.toString());
            }


        });
    }

}


