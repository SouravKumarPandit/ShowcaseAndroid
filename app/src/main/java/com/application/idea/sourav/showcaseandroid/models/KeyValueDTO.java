package com.application.idea.sourav.showcaseandroid.models;

public class KeyValueDTO {

    String sKey;
    int iValue;

    public KeyValueDTO(String sKey, int iValue)
    {
        this.sKey = sKey;
        this.iValue = iValue;
    }

    public String getKey()
    {
        return sKey;
    }

    public void setKey(String sKey)
    {
        this.sKey = sKey;
    }

    public int getValue()
    {
        return iValue;
    }

    public void setValue(int iValue)
    {
        this.iValue = iValue;
    }


}
