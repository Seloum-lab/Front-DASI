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
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.modele.Consultation;



/**
 *
 * @author sbenbouzid
 */
public class AfficherConsultationClient extends Serialization{

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();
        
        container.addProperty("connected", (String) req.getAttribute("connected"));
        
        JsonArray consultationListJson = new JsonArray();
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("ID");
        
        List<Consultation> consultationList = (List<Consultation>) req.getAttribute("consultation_liste");
        String pattern = "MM/dd/yyyy HH:mm";


        DateFormat df = new SimpleDateFormat(pattern);
        
        for (Consultation consultation : consultationList) {
            if (Objects.equals(consultation.getEmployee().getId(), id)) {
                String date = df.format(consultation.getDateConsultation());
                JsonObject consultationJson = new JsonObject();
                //consultationJson.addProperty("date", consultation.getDateConsultation()); //TODO : gérer la date en la convertissant n une string
                consultationJson.addProperty("medium", consultation.getMedium().getDenomination());
                consultationJson.addProperty("date", date);

                consultationListJson.add(consultationJson);
            } else {
            }
        }
        
        container.add("consultation_liste", consultationListJson);
        
        
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();
        
    }
    
}
