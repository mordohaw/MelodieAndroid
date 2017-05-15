package fsa.williammordohay.melodienet_android_client.securite;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by william.mordohay on 12/05/2017.
 */

public class CryptageMdp
{
    public static String hashMdp(String mdp) throws NoSuchAlgorithmException
    {

        String md5Mdp = null;

        if(mdp ==null) return null;

        try
        {

            //On crée un MessageDigest utilisant l'algorithme de hashage "MD5"
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //On met la chaine mdp comme entrée au MessageDigest
            digest.update(mdp.getBytes(), 0, mdp.length());

            //On crypte le mot de passe et on le converti en chaine de caractères
            md5Mdp = new BigInteger(1, digest.digest()).toString(16);

        }
        catch (NoSuchAlgorithmException e)
        {

            e.printStackTrace();
        }
        return md5Mdp;
    }
}
