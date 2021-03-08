package com.admereselvyn.mic.api;



public interface Api {
    String getLoginUrl();
    String getLogoutUrl();
    String getRegisterUrl();
    String getEditProfileUrl(String id);
    String getUploadUserProfileUrl(String id);
    String getForgetPasswordUrl();
    String getUpdatePasswordUrl(String id);
    String getAllContestsUrl();
    String getAllContestVideosUploadedByUser(String id);
    String getAllContestsUserParticipated(String id);
    String getUploadVideoToContest(String id);
}
