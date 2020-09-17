
package com.application.fertilizercalculator.model;

import com.google.gson.annotations.SerializedName;


public class FertilizersPerPhase {

    @SerializedName("fertilizer")
    private Long mFertilizer;
    @SerializedName("fertilizerCalcItemId")
    private Long mFertilizerCalcItemId;
    @SerializedName("fertilizer_name")
    private String mFertilizerName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("instruction")
    private String mInstruction;
    @SerializedName("phase_info")
    private Long mPhaseInfo;
    @SerializedName("quantity")
    private String mQuantity;
    @SerializedName("unit")
    private String mUnit;

    public Long getFertilizer() {
        return mFertilizer;
    }

    public void setFertilizer(Long fertilizer) {
        mFertilizer = fertilizer;
    }

    public Long getFertilizerCalcItemId() {
        return mFertilizerCalcItemId;
    }

    public void setFertilizerCalcItemId(Long fertilizerCalcItemId) {
        mFertilizerCalcItemId = fertilizerCalcItemId;
    }

    public String getFertilizerName() {
        return mFertilizerName;
    }

    public void setFertilizerName(String fertilizerName) {
        mFertilizerName = fertilizerName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getInstruction() {
        return mInstruction;
    }

    public void setInstruction(String instruction) {
        mInstruction = instruction;
    }

    public Long getPhaseInfo() {
        return mPhaseInfo;
    }

    public void setPhaseInfo(Long phaseInfo) {
        mPhaseInfo = phaseInfo;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
        mQuantity = quantity;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

}
