package com.admereselvyn.mic.api.models.user_model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData implements Serializable {

    @SerializedName("payment")
    @Expose
    private Payment payment;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("whatsapp_no")
    @Expose
    private String whatsappNo;
    @SerializedName("videos")
    @Expose
    private List<Object> videos = null;
    @SerializedName("contests")
    @Expose
    private List<Object> contests = null;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("coins")
    @Expose
    private Integer coins;
    @SerializedName("referrals")
    @Expose
    private List<Object> referrals = null;
    @SerializedName("referralCount")
    @Expose
    private Integer referralCount;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("referralCode")
    @Expose
    private String referralCode;
    @SerializedName("audition_videos")
    @Expose
    private List<AuditionVideo> auditionVideos = null;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("aboutMe")
    @Expose
    private String aboutMe;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("dob")
    @Expose
    private Object dob;
    @SerializedName("link_to_facebook_profile")
    @Expose
    private String linkToFacebookProfile;
    @SerializedName("link_to_instagram_profile")
    @Expose
    private String linkToInstagramProfile;
    @SerializedName("link_to_twitter_profile")
    @Expose
    private String linkToTwitterProfile;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("link_to_profile_img")
    @Expose
    private String linkToProfileImg;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getWhatsappNo() {
        return whatsappNo;
    }

    public void setWhatsappNo(String whatsappNo) {
        this.whatsappNo = whatsappNo;
    }

    public List<Object> getVideos() {
        return videos;
    }

    public void setVideos(List<Object> videos) {
        this.videos = videos;
    }

    public List<Object> getContests() {
        return contests;
    }

    public void setContests(List<Object> contests) {
        this.contests = contests;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public List<Object> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<Object> referrals) {
        this.referrals = referrals;
    }

    public Integer getReferralCount() {
        return referralCount;
    }

    public void setReferralCount(Integer referralCount) {
        this.referralCount = referralCount;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public List<AuditionVideo> getAuditionVideos() {
        return auditionVideos;
    }

    public void setAuditionVideos(List<AuditionVideo> auditionVideos) {
        this.auditionVideos = auditionVideos;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getDob() {
        return dob;
    }

    public void setDob(Object dob) {
        this.dob = dob;
    }

    public String getLinkToFacebookProfile() {
        return linkToFacebookProfile;
    }

    public void setLinkToFacebookProfile(String linkToFacebookProfile) {
        this.linkToFacebookProfile = linkToFacebookProfile;
    }

    public String getLinkToInstagramProfile() {
        return linkToInstagramProfile;
    }

    public void setLinkToInstagramProfile(String linkToInstagramProfile) {
        this.linkToInstagramProfile = linkToInstagramProfile;
    }

    public String getLinkToTwitterProfile() {
        return linkToTwitterProfile;
    }

    public void setLinkToTwitterProfile(String linkToTwitterProfile) {
        this.linkToTwitterProfile = linkToTwitterProfile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLinkToProfileImg() {
        return linkToProfileImg;
    }

    public void setLinkToProfileImg(String linkToProfileImg) {
        this.linkToProfileImg = linkToProfileImg;
    }

}
