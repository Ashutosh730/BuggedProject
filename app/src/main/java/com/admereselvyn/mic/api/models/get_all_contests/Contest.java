package com.admereselvyn.mic.api.models.get_all_contests;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contest implements Serializable {

    @SerializedName("videos")
    @Expose
    private List<String> videos = null;
    @SerializedName("fees")
    @Expose
    private Integer fees;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("start_contest")
    @Expose
    private String startContest;
    @SerializedName("end_contest")
    @Expose
    private String endContest;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("users_participated")
    @Expose
    private List<ParticipatedUser> usersParticipated = null;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("link_to_contest_img")
    @Expose
    private String linkToContestImg;

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartContest() {
        return startContest;
    }

    public void setStartContest(String startContest) {
        this.startContest = startContest;
    }

    public String getEndContest() {
        return endContest;
    }

    public void setEndContest(String endContest) {
        this.endContest = endContest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ParticipatedUser> getUsersParticipated() {
        return usersParticipated;
    }

    public void setUsersParticipated(List<ParticipatedUser> usersParticipated) {
        this.usersParticipated = usersParticipated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinkToContestImg() {
        return linkToContestImg;
    }

    public void setLinkToContestImg(String linkToContestImg) {
        this.linkToContestImg = linkToContestImg;
    }

}