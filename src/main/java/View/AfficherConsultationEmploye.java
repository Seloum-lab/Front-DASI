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
import metier.modele.Consultation;

/**
 *
 * @author sbenbouzid
 */
public class AfficherConsultationEmploye extends Serialization{

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();
        
        JsonArray consultationListJson = new JsonArray();
        
        List<Consultation> consultationList = (List<Consultation>) req.getAttribute("consultation_liste");
        
        for (Consultation consultation : consultationList) {
            JsonObject consultationJson = new JsonObject();
            //consultationJson.addProperty("date", consultation.getDateConsultation()); //TODO : gérer la date en la convertissant n une string
            consultationJson.addProperty("medium", consultation.getMedium().getDenomination());
            consultationJson.addProperty("commentaire", consultation.getCommentaire());
            
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
