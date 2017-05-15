package com.example.williammordohay.melodieandroidv44.Security;

/**
 * Created by william.mordohay on 11/05/2017.
 */

public class ConnectionObject {

    private String name;
    private boolean agreement;

    public ConnectionObject(boolean agreement, String name)
    {
        this.name = name;
        this.agreement = agreement;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isAgreement()
    {
        return agreement;
    }

    public void setAgreement(boolean agreement)
    {
        this.agreement = agreement;
    }

}
