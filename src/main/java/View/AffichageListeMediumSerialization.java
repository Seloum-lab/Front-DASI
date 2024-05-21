/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Medium;

/**
 *
 * classe utilise pour afficher la liste des mediums dans la page demander consultation
 */
public class AffichageListeMediumSerialization extends Serialization {
        public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException{
            List<Medium> mediums = (List<Medium>) req.getAttribute("listeMedium");
            
            Gson gson = new GsonBuilder().serializeNulls().create();
            JsonObject container = new JsonObject();
            JsonArray mediumsListJson = new JsonArray();

            if(mediums != null) { // donc pas d'exeption pendant l'execution du service dans le back
                // on parcours la liste des mediums pour les mettre dans le JSON array
                for(Medium med : mediums) {
                    // pour chaque medium dans la liste
                    JsonObject mediumJson = new JsonObject();
                    mediumJson.addProperty("nom", med.getDenomination());
                    mediumJson.addProperty("presentation", med.getPresentation());
                    // TODO ajouter encore des propriete s'il en manque*

                    mediumsListJson.add(mediumJson); // on ajoute les mediums a la liste 
                }
            }  
            container.add("listeMediums", mediumsListJson); // la liste des mediums ajouter au conteneur, si la liste est vide c'est que le tab medium est null
            res.setContentType("application/json;charset=UTF-8");
            PrintWriter out;

            out = res.getWriter();
            out.println(gson.toJson(container));
            out.close();
        }

}
