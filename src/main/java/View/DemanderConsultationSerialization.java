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
import metier.modele.Consultation;

/**
 *
 * classe utilise pour serialise la demande de consultation
 * pour savoir si la demande la consultation a aboutie ou pas, il suffit de regarde la propriete consultation dans la res
 */
public class DemanderConsultationSerialization extends Serialization {

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();   
        
        Boolean statusDemandeConsultation = (Boolean) req.getAttribute("consultation");
        container.addProperty("demandeConsultation", statusDemandeConsultation); // true si la consultation est prise, faux sinon
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        out = res.getWriter(); // on met la consultation dans la requete    
        out.println(gson.toJson(container));
        out.close();
    }    
}
