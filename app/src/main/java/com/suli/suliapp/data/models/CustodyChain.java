package com.suli.suliapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustodyChain implements Serializable {

    @SerializedName("id")
    public Integer id;
    @SerializedName("agent")
    public Agent agent;
    @SerializedName("area")
    public String area;
    @SerializedName("date_evaluation")
    public String dateEvaluation;
    @SerializedName("description_activity")
    public String descriptionActivity;
    @SerializedName("start_hour")
    public Integer startHour;
    @SerializedName("end_hour")
    public Integer endHour;
    @SerializedName("is_office_work")
    public Boolean isOfficeWork;
    @SerializedName("user")
    public Integer user;
    @SerializedName("detail_project")
    public Integer detailProject;
    @SerializedName("mining_unit")
    public Integer miningUnit;
    @SerializedName("instrument")
    public Integer instrument;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(String dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public String getDescriptionActivity() {
        return descriptionActivity;
    }

    public void setDescriptionActivity(String descriptionActivity) {
        this.descriptionActivity = descriptionActivity;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public Boolean getOfficeWork() {
        return isOfficeWork;
    }

    public void setOfficeWork(Boolean officeWork) {
        isOfficeWork = officeWork;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getDetailProject() {
        return detailProject;
    }

    public void setDetailProject(Integer detailProject) {
        this.detailProject = detailProject;
    }

    public Integer getMiningUnit() {
        return miningUnit;
    }

    public void setMiningUnit(Integer miningUnit) {
        this.miningUnit = miningUnit;
    }

    public Integer getInstrument() {
        return instrument;
    }

    public void setInstrument(Integer instrument) {
        this.instrument = instrument;
    }
}
