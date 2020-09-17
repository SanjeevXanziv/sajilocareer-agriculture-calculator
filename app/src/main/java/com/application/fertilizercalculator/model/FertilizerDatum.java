
package com.application.fertilizercalculator.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class FertilizerDatum {

    @SerializedName("crop_image")
    private String mCropImage;
    @SerializedName("crop_info")
    private Long mCropInfo;
    @SerializedName("crop_info_name")
    private String mCropInfoName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("fertilizers_per_phases")
    private List<FertilizersPerPhase> mFertilizersPerPhases;
    @SerializedName("id")
    private Long mId;
    @SerializedName("order")
    private Long mOrder;
    @SerializedName("phase")
    private Long mPhase;
    @SerializedName("phase_image")
    private String mPhaseImage;
    @SerializedName("phase_name")
    private String mPhaseName;
    @SerializedName("when")
    private String mWhen;

    public String getCropImage() {
        return mCropImage;
    }

    public void setCropImage(String cropImage) {
        mCropImage = cropImage;
    }

    public Long getCropInfo() {
        return mCropInfo;
    }

    public void setCropInfo(Long cropInfo) {
        mCropInfo = cropInfo;
    }

    public String getCropInfoName() {
        return mCropInfoName;
    }

    public void setCropInfoName(String cropInfoName) {
        mCropInfoName = cropInfoName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public List<FertilizersPerPhase> getFertilizersPerPhases() {
        return mFertilizersPerPhases;
    }

    public void setFertilizersPerPhases(List<FertilizersPerPhase> fertilizersPerPhases) {
        mFertilizersPerPhases = fertilizersPerPhases;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getOrder() {
        return mOrder;
    }

    public void setOrder(Long order) {
        mOrder = order;
    }

    public Long getPhase() {
        return mPhase;
    }

    public void setPhase(Long phase) {
        mPhase = phase;
    }

    public String getPhaseImage() {
        return mPhaseImage;
    }

    public void setPhaseImage(String phaseImage) {
        mPhaseImage = phaseImage;
    }

    public String getPhaseName() {
        return mPhaseName;
    }

    public void setPhaseName(String phaseName) {
        mPhaseName = phaseName;
    }

    public String getWhen() {
        return mWhen;
    }

    public void setWhen(String when) {
        mWhen = when;
    }

}
