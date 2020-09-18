
package com.application.fertilizercalculator.model;

import com.google.gson.annotations.SerializedName;

public class FertilizerRequest {

    @SerializedName("cropId")
    private Integer mCropId;
    @SerializedName("k_amount_in_soil")
    private String mKAmountInSoil;
    @SerializedName("n_amount_in_soil")
    private String mNAmountInSoil;
    @SerializedName("organic_matter_in_soil")
    private String mOrganicMatterInSoil;
    @SerializedName("p_amount_in_soil")
    private String mPAmountInSoil;
    @SerializedName("ph")
    private Double mPh;
    @SerializedName("region")
    private String mRegion;
    @SerializedName("soil_texture_in_soil")
    private String mSoilTextureInSoil;

    public FertilizerRequest() {
    }

    public FertilizerRequest(Integer mCropId, String mKAmountInSoil, String mNAmountInSoil, String mOrganicMatterInSoil,
                             String mPAmountInSoil, Double mPh, String mRegion, String mSoilTextureInSoil) {
        this.mCropId = mCropId;
        this.mKAmountInSoil = mKAmountInSoil;
        this.mNAmountInSoil = mNAmountInSoil;
        this.mOrganicMatterInSoil = mOrganicMatterInSoil;
        this.mPAmountInSoil = mPAmountInSoil;
        this.mPh = mPh;
        this.mRegion = mRegion;
        this.mSoilTextureInSoil = mSoilTextureInSoil;
    }

    public Integer getCropId() {
        return mCropId;
    }

    public void setCropId(Integer cropId) {
        mCropId = cropId;
    }

    public String getKAmountInSoil() {
        return mKAmountInSoil;
    }

    public void setKAmountInSoil(String kAmountInSoil) {
        mKAmountInSoil = kAmountInSoil;
    }

    public String getNAmountInSoil() {
        return mNAmountInSoil;
    }

    public void setNAmountInSoil(String nAmountInSoil) {
        mNAmountInSoil = nAmountInSoil;
    }

    public String getOrganicMatterInSoil() {
        return mOrganicMatterInSoil;
    }

    public void setOrganicMatterInSoil(String organicMatterInSoil) {
        mOrganicMatterInSoil = organicMatterInSoil;
    }

    public String getPAmountInSoil() {
        return mPAmountInSoil;
    }

    public void setPAmountInSoil(String pAmountInSoil) {
        mPAmountInSoil = pAmountInSoil;
    }

    public Double getPh() {
        return mPh;
    }

    public void setPh(Double ph) {
        mPh = ph;
    }

    public String getRegion() {
        return mRegion;
    }

    public void setRegion(String region) {
        mRegion = region;
    }

    public String getSoilTextureInSoil() {
        return mSoilTextureInSoil;
    }

    public void setSoilTextureInSoil(String soilTextureInSoil) {
        mSoilTextureInSoil = soilTextureInSoil;
    }

}
