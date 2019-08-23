package com.application.idea.sourav.showcaseandroid.models;

public class SocialPlanInfoDTO {

    private final  String sUserName;
    private final  String sUserPlans;
    private final  String sTimeAgo;

    public SocialPlanInfoDTO(String sUserName, String sUserPlans, String sTimeAgo) {
        this.sUserName = sUserName;
        this.sUserPlans = sUserPlans;
        this.sTimeAgo = sTimeAgo;
    }

    public String getsUserName() {
        return sUserName;
    }



    public String getsUserPlans() {
        return sUserPlans;
    }

    public String getsTimeAgo() {
        return sTimeAgo;
    }

}
