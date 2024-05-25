/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;
import metier.modele.ProfilAstro;

/**
 *
 * Classe utilise pour ordonnes les donnes du client qui seront afficher dans la page de consultation
 */
public class ConsultationInitSerialize extends Serialization {

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();   
        JsonObject profil = new JsonObject();
        
        Client c = (Client) req.getAttribute("client"); // on reprend l objet client de la requete
        ProfilAstro prfAstro = c.getProfilAstro(); 
        
        container.addProperty("age", c.getAge());
        container.addProperty("tel", c.getTelephone());
        container.addProperty("prenom", c.getPrenom());
        
        profil.addProperty("signeZodiaque", prfAstro.getSigneZodiaque());
        profil.addProperty("signeChinois", prfAstro.getSigneChinois());
        profil.addProperty("couleurPorteBonheur", prfAstro.getCouleurPorteBonheur());
        profil.addProperty("animalTotem", prfAstro.getAnimalTotem());

        container.add("profilAstro", profil);

        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        out = res.getWriter(); 
        out.println(gson.toJson(container));
        out.close();
    }
    
}
