package com.application.idea.sourav.showcaseandroid.models;

public class ShowcaseClassDTO {

    private String activityName;

    private Class activityClass;


    public ShowcaseClassDTO()
    {
    }

    public String getActivityName()
    {
        return activityName;
    }

    public void setActivityName(String activityName)
    {
        this.activityName = activityName;
    }

    public Class getActivityClass()
    {
        return activityClass;
    }

    public void setActivityClass(Class activityClass)
    {
        this.activityClass = activityClass;
        activityName=activityClass.getSimpleName();
    }

}
