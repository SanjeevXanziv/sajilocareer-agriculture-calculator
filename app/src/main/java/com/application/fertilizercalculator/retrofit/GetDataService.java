package com.application.fertilizercalculator.retrofit;

import com.application.fertilizercalculator.model.Crop;
import com.application.fertilizercalculator.model.FertilizerRequest;
import com.application.fertilizercalculator.model.FertilizerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetDataService {


    @POST("fertilizer/calculator/government/")
    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjkxOTI3ODIzLCJqdGkiOiI3YTRiMDkwY2Q0YWU0OTgzYmQ3NmVjYjU2YWUyMjMyMCIsInVzZXJfaWQiOjQyOX0.eK6DrmoJARPBEJeVt5UBajTQPatelYpokDfpJguBT5o")
    Call<FertilizerResponse> getFertilizerElementsRequest(@Body FertilizerRequest request);

    @GET("getcrops/list")
    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjkxOTI3ODIzLCJqdGkiOiI3YTRiMDkwY2Q0YWU0OTgzYmQ3NmVjYjU2YWUyMjMyMCIsInVzZXJfaWQiOjQyOX0.eK6DrmoJARPBEJeVt5UBajTQPatelYpokDfpJguBT5o")
    Call<List<Crop>> getCropListRequest();

}