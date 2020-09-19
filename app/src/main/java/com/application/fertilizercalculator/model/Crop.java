
package com.application.fertilizercalculator.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crop {

    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private String photo;
    @SerializedName("product_type")
    private String productType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
