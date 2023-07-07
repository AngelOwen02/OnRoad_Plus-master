package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sendquestionsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class requestFullCheckList {

    @SerializedName("approvement")
    @Expose
    private Boolean approvement;
    @SerializedName("cve_trip_mgm_checklist")
    @Expose
    private Integer cveTripMgmChecklist;
    @SerializedName("cve_vehicle")
    @Expose
    private Integer cveVehicle;
    @SerializedName("destination_trip")
    @Expose
    private Integer destinationTrip;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    @SerializedName("json_answer")
    @Expose
    private String jsonAnswer;
    @SerializedName("origin_trip")
    @Expose
    private Integer originTrip;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("date_begin")
    @Expose
    private String date_begin;
    @SerializedName("date_end")
    @Expose
    private String date_end;

    public requestFullCheckList(Boolean approvement, Integer cveTripMgmChecklist, Integer cveVehicle, Integer destinationTrip, String email,
                                List<Image> image, String jsonAnswer, Integer originTrip, Integer score, String token,String date_begin,String date_end) {
        super();
        this.approvement = approvement;
        this.cveTripMgmChecklist = cveTripMgmChecklist;
        this.cveVehicle = cveVehicle;
        this.destinationTrip = destinationTrip;
        this.email = email;
        this.image = image;
        this.jsonAnswer = jsonAnswer;
        this.originTrip = originTrip;
        this.score = score;
        this.token = token;
        this.date_begin=date_begin;
        this.date_end=date_end;
    }

    public Boolean getApprovement() {
        return approvement;
    }

    public void setApprovement(Boolean approvement) {
        this.approvement = approvement;
    }

    public Integer getCveTripMgmChecklist() {
        return cveTripMgmChecklist;
    }

    public void setCveTripMgmChecklist(Integer cveTripMgmChecklist) {
        this.cveTripMgmChecklist = cveTripMgmChecklist;
    }

    public Integer getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(Integer cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public Integer getDestinationTrip() {
        return destinationTrip;
    }

    public void setDestinationTrip(Integer destinationTrip) {
        this.destinationTrip = destinationTrip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public String getJsonAnswer() {
        return jsonAnswer;
    }

    public void setJsonAnswer(String jsonAnswer) {
        this.jsonAnswer = jsonAnswer;
    }

    public Integer getOriginTrip() {
        return originTrip;
    }

    public void setOriginTrip(Integer originTrip) {
        this.originTrip = originTrip;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
