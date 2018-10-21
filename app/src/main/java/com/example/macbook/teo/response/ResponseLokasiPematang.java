package com.example.macbook.teo.response;

import com.example.macbook.teo.model.ModelLokasiPematang;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLokasiPematang {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private List<ModelLokasiPematang> data = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ModelLokasiPematang> getData() {
        return data;
    }

    public void setData(List<ModelLokasiPematang> data) {
        this.data = data;
    }

}
