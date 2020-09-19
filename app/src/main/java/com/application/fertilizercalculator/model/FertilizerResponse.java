
package com.application.fertilizercalculator.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FertilizerResponse implements Serializable {

    @SerializedName("DAP")
    private Double mDAP;
    /*@SerializedName("fertilizer_data")
    private List<FertilizerDatum> mFertilizerData;*/
    @SerializedName("lime")
    private Long mLime;
    @SerializedName("MOP")
    private Double mMOP;
    @SerializedName("organic_manure")
    private String mOrganicManure;
    @SerializedName("ph_result")
    private String mPhResult;
    @SerializedName("Urea")
    private Double mUrea;

    public Double getDAP() {
        return mDAP;
    }

    public void setDAP(Double dAP) {
        mDAP = dAP;
    }

    /*public List<FertilizerDatum> getFertilizerData() {
        return mFertilizerData;
    }

    public void setFertilizerData(List<FertilizerDatum> fertilizerData) {
        mFertilizerData = fertilizerData;
    }*/

    public Long getLime() {
        return mLime;
    }

    public void setLime(Long lime) {
        mLime = lime;
    }

    public Double getMOP() {
        return mMOP;
    }

    public void setMOP(Double mOP) {
        mMOP = mOP;
    }

    public String getOrganicManure() {
        return mOrganicManure;
    }

    public void setOrganicManure(String organicManure) {
        mOrganicManure = organicManure;
    }

    public String getPhResult() {
        return mPhResult;
    }

    public void setPhResult(String phResult) {
        mPhResult = phResult;
    }

    public Double getUrea() {
        return mUrea;
    }

    public void setUrea(Double urea) {
        mUrea = urea;
    }

}
