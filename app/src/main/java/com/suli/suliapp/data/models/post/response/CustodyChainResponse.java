package com.suli.suliapp.data.models.post.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustodyChainResponse implements Serializable {

    @SerializedName("id")
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}