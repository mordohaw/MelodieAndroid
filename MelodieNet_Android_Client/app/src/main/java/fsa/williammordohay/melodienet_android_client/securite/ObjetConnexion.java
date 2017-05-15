package fsa.williammordohay.melodienet_android_client.securite;

/**
 * Created by william.mordohay on 12/05/2017.
 */

public class ObjetConnexion {
    private String nomUtilisateur;
    private boolean accord;

    public ObjetConnexion(String nomUtilisateur, boolean accord)
    {
        this.nomUtilisateur = nomUtilisateur;
        this.accord = accord;
    }

    public String getNomUtilisateur()
    {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur)
    {
        this.nomUtilisateur = nomUtilisateur;
    }

    public boolean isAccord()
    {
        return accord;
    }

    public void setAccord(boolean accord)
    {
        this.accord = accord;
    }
}
