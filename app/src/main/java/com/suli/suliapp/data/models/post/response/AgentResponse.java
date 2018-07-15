package com.suli.suliapp.data.models.post.response;

import com.google.gson.annotations.SerializedName;

public class AgentResponse {

    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;

    public AgentResponse() {
    }

    public AgentResponse(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return name;
    }
}