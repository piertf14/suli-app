package com.suli.suliapp.data.models.post.request;

import com.google.gson.annotations.SerializedName;

public class CustodyChainRequest {

    @SerializedName("contributor_name")
    public String contributorName;
    @SerializedName("contributor_last_name")
    public String contributorLastName;
    @SerializedName("job_position")
    public String jobPosition;
    @SerializedName("detail_project")
    public Integer detailProject;
    @SerializedName("area")
    public String area;
    @SerializedName("agent")
    public Integer agent;
    @SerializedName("instrument")
    public Integer instrument;
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


    public CustodyChainRequest(String contributorName, String contributorLastName, String jobPosition, Integer detailProject, String area, Integer agent, Integer instrument, String dateEvaluation, String descriptionActivity, Integer startHour, Integer endHour, Boolean isOfficeWork) {
        this.contributorName = contributorName;
        this.contributorLastName = contributorLastName;
        this.jobPosition = jobPosition;
        this.detailProject = detailProject;
        this.area = area;
        this.agent = agent;
        this.instrument = instrument;
        this.dateEvaluation = dateEvaluation;
        this.descriptionActivity = descriptionActivity;
        this.startHour = startHour;
        this.endHour = endHour;
        this.isOfficeWork = isOfficeWork;
    }

    public String getContributorName() {
        return contributorName;
    }

    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }

    public String getContributorLastName() {
        return contributorLastName;
    }

    public void setContributorLastName(String contributorLastName) {
        this.contributorLastName = contributorLastName;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public Integer getDetailProject() {
        return detailProject;
    }

    public void setDetailProject(Integer detailProject) {
        this.detailProject = detailProject;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAgent() {
        return agent;
    }

    public void setAgent(Integer agent) {
        this.agent = agent;
    }

    public Integer getInstrument() {
        return instrument;
    }

    public void setInstrument(Integer instrument) {
        this.instrument = instrument;
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
}
