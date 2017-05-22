package fsa.williammordohay.melodienet_android_client.securite;

/**
 * Created by william.mordohay on 18/05/2017.
 */

public class ObjetAuthent {
    private String name;
    private boolean agreement;

    public ObjetAuthent(boolean agreement, String name)
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
