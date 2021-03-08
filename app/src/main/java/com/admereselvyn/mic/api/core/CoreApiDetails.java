package com.admereselvyn.mic.api.core;
import com.admereselvyn.mic.api.Api;


import java.util.HashMap;

public class CoreApiDetails implements Api {

    final HashMap<String,String> apiRoutes = new HashMap<>();
    private final String login = "login";
    private final String register = "register";
    private final String logout = "logout";
    private final String editProfile = "editProfile";
    private final String uploadUserProfileImage = "uploadUserProfileImage";
    private final String forgotPassword = "forgotPassword";
    private final String resetPassword = "resetPassword";
    private final String getAllContests = "getAllContests";
    private final String allContestsUserParticipated = "allContestsUserParticipated";
    private final String allContestsVideosUploadedByUser = "allContestsVideosUploadedByUser";
    private final String uploadVideoToContest = "uploadVideoToContest";

    final String BASE_URL = "https://onlinemic.in/api/";

    //constructor
    public CoreApiDetails(){
        setupRoutes();
    }

    private void setupRoutes(){
        apiRoutes.put(login,BASE_URL + "user/login/");
        apiRoutes.put(register,BASE_URL + "user/register/");
        apiRoutes.put(logout,BASE_URL+ "user/logout/");
        apiRoutes.put(editProfile,BASE_URL+ "edit/");
        apiRoutes.put(uploadUserProfileImage,BASE_URL + "profileImage/");
        apiRoutes.put(forgotPassword,BASE_URL + "forgotpassword/");
        apiRoutes.put(resetPassword,BASE_URL + "resetPassword/");
        apiRoutes.put(getAllContests,BASE_URL+"admin/getAllContests/");
        apiRoutes.put(allContestsUserParticipated,BASE_URL + "sendAllContestsParticipated/");
        apiRoutes.put(allContestsVideosUploadedByUser,BASE_URL + "sendAllVideos/");
        apiRoutes.put(uploadVideoToContest,BASE_URL + "submit/contest/");
    }

    @Override
    public String getLoginUrl() {
        return apiRoutes.get(login);
    }

    @Override
    public String getLogoutUrl() {
        return apiRoutes.get(logout);
    }

    @Override
    public String getRegisterUrl() {
        return apiRoutes.get(register);
    }

    @Override
    public String getEditProfileUrl(String id) {
        return apiRoutes.get(editProfile) + id;
    }

    @Override
    public String getUploadUserProfileUrl(String id) {
        return apiRoutes.get(uploadUserProfileImage) + id;
    }

    @Override
    public String getForgetPasswordUrl() {
        return apiRoutes.get(forgotPassword);
    }

    @Override
    public String getUpdatePasswordUrl(String id) {
        return apiRoutes.get(resetPassword) + id;
    }

    @Override
    public String getAllContestsUrl() {
        return apiRoutes.get(getAllContests);
    }

    @Override
    public String getAllContestVideosUploadedByUser(String id) {
        return apiRoutes.get(allContestsVideosUploadedByUser) + id;
    }

    @Override
    public String getAllContestsUserParticipated(String id) {
        return apiRoutes.get(allContestsUserParticipated) + id;
    }

    @Override
    public String getUploadVideoToContest(String id) {
        return apiRoutes.get(uploadVideoToContest) + id;
    }
}

