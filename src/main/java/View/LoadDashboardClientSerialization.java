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
import metier.modele.ProfilAstro;

/**
 *
 * @author sbenbouzid
 */
public class LoadDashboardClientSerialization extends Serialization {

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //Initialisations nécessaire pour la gestion du Json
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();
        JsonObject profilAstroJson = new JsonObject();
        
        container.addProperty("connected", (String) req.getAttribute("connected"));
        
        //Récupération des attributs depuis la requête
        String name = (String) req.getAttribute("name");
        ProfilAstro profilAstro = (ProfilAstro) req.getAttribute("profil_astro");
        
        //Bien construire le profilAstro
        profilAstroJson.addProperty("signe_zodiac", profilAstro.getSigneZodiaque());
        profilAstroJson.addProperty("signe_chinois", profilAstro.getSigneChinois());
        profilAstroJson.addProperty("couleur_bonheur", profilAstro.getCouleurPorteBonheur());
        profilAstroJson.addProperty("animal", profilAstro.getAnimalTotem());
        
        //Bien construire le conteneur global
        container.addProperty("name", name);
        container.add("profil_astro", profilAstroJson);
        
        
        
        //Ajout du conteneur dans la response
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();
        
        
        
        
        
        
        
    }
    
}
