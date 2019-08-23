package com.application.idea.sourav.showcaseandroid.models;

public class ChatUserDTO {
    private final String sSenderName;
    private final String sTimeAgo;

    private boolean isFavorite;
    private final String sDescription;
    private final String sUserRole;
    private final int profileImage;

    public ChatUserDTO(String sSenderName, String sTileAgo, boolean isFavorite, String sDescription, String sUserRole, int profileImage)
    {
        this.sSenderName = sSenderName;
        this.sTimeAgo = sTileAgo;
        this.isFavorite = isFavorite;
        this.sDescription = sDescription;
        this.sUserRole = sUserRole;
        this.profileImage = profileImage;
    }

    public String getsSenderName()
    {
        return sSenderName;
    }

    public String getsTimeAgo()
    {
        return sTimeAgo;
    }

    public boolean isFavorite()
    {
        return isFavorite;
    }

    public String getsDescription()
    {
        return sDescription;
    }

    public String getsUserRole()
    {
        return sUserRole;
    }

    public int getProfileImage()
    {
        return profileImage;
    }

    public void setFavorite(boolean favorite)
    {
        isFavorite = favorite;
    }


}

