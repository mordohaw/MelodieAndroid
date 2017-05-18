package fsa.williammordohay.melodienet_android_client.connexionserviceweb;

/**
 * Created by william.mordohay on 15/05/2017.
 */

public class ConstructeurUrl
{

    private String baseUrl;

    public ConstructeurUrl(String baseUrl)
    {
        this.baseUrl=baseUrl;
    }

    public String obtenirAccordLogin(String utilisateur, String motDePasse)
    {
        return(baseUrl+"GetLoginAgreement/"+utilisateur+"/"+motDePasse);
    }
    public String obtenirListeCellules(String numLigne)
    {
        return(baseUrl+"GetCellsList/"+numLigne);
    }

    public String obtenirListeLignes()
    {
        return(baseUrl+"GetLinesList/");
    }

    public String obtenirModesMarche(String numLigne, String numCellules)
    {
        return(baseUrl+"GetRunningMode/"+numLigne+"/"+numCellules);
    }
    public String obtenirProdHeure(String numLigne)
    {
        return(baseUrl+"GetHourProduction/"+numLigne);
    }
    public String obtenirProdJour(String numLigne)
    {
        return(baseUrl+"GetDayProduction/"+numLigne);
    }
    public String obtenirProdSemaine(String numLigne)
    {
        return(baseUrl+"GetWeekProduction/"+numLigne);
    }

    public String envoyerLang(String langue)
    {
        return(baseUrl+"PostLanguage/"+langue);
    }

}
