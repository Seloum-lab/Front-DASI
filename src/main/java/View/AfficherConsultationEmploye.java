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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Consultation;

/**
 *
 * @author sbenbouzid
 */
public class AfficherConsultationEmploye extends Serialization{

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Il n'y a pas de service qui me permet d'obtenir la liste de consultation d'un employe en aprticulier et dans le rapport
        //Il n'est pas précisé que je doive le faire moi donc je vais juste afficher toutes les consultations
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();
        
        container.addProperty("connected", (String) req.getAttribute("connected"));
        
        JsonArray consultationListJson = new JsonArray();
        String pattern = "MM/dd/yyyy HH:mm";


        DateFormat df = new SimpleDateFormat(pattern);
        
        List<Consultation> consultationList = (List<Consultation>) req.getAttribute("consultation_liste");
        
        for (Consultation consultation : consultationList) {
            String date = df.format(consultation.getDateConsultation());
            JsonObject consultationJson = new JsonObject();
            //consultationJson.addProperty("date", consultation.getDateConsultation()); //TODO : gérer la date en la convertissant n une string
            consultationJson.addProperty("medium", consultation.getMedium().getDenomination());
            consultationJson.addProperty("commentaire", consultation.getCommentaire());
            consultationJson.addProperty("date", date);
            
            consultationListJson.add(consultationJson);
        }
        
        container.add("consultation_liste", consultationListJson);
        
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();
        
        
        
    }
    
}
