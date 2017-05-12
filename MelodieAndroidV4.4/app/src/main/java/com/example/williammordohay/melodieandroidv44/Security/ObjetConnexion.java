package com.example.williammordohay.melodieandroidv44.Security;

/**
 * Created by william.mordohay on 11/05/2017.
 */

public class ObjetConnexion {

    private String name;
    private boolean agreement;

    public ObjetConnexion(String name, boolean agreement)
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
