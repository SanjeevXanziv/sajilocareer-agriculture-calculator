package com.application.fertilizercalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private Integer cropID = 3;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendFertilizerElementsRequest();
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


