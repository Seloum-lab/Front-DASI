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
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;
import metier.modele.Medium;
import metier.service.Service;

/**
 *
 * @author sbenbouzid
 */
public class StatisticsSerialization extends Serialization {

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Service service = new Service();
        
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();
        
        
        List<Client> clientList = (List<Client>) req.getAttribute("liste_client");
        JsonArray clientListJson = new JsonArray();
        
        List<Medium> mediumList = (List<Medium>) req.getAttribute("liste_medium");
        JsonArray mediumListJson = new JsonArray();
        
        List<Map<Long, Integer>> repartMedium = (List<Map<Long, Integer>>) req.getAttribute("repartition_medium");
        JsonArray repartMediumJson = new JsonArray();
        
        List<Map<Long, Integer>> repartEmploye = (List<Map<Long, Integer>>) req.getAttribute("repartition_employe");
        JsonArray repartEmployeJson = new JsonArray();
        
        for (Client client : clientList) {
            JsonObject clientJson = new JsonObject();
            clientJson.addProperty("id", client.getId());
            
            clientListJson.add(clientJson);
        }
        container.add("client_liste", clientListJson);
        
        for (Medium medium : mediumList) {
            JsonObject mediumJson = new JsonObject();
            mediumJson.addProperty("id", medium.getId());
            mediumJson.addProperty("nom", medium.getDenomination());
            
            mediumListJson.add(mediumJson);           
        }
        container.add("medium_liste", mediumListJson);
        
        for (Map<Long, Integer> coupleMedium : repartMedium) {
            JsonObject coupleMediumJson = new JsonObject();
            Long id = coupleMedium.keySet().iterator().next();
            coupleMediumJson.addProperty("id", id );
            coupleMediumJson.addProperty("nombre_consultations", coupleMedium.get(id));
            coupleMediumJson.addProperty("nom", service.rechercherMediumParID(id).getDenomination());
            
            repartMediumJson.add(coupleMediumJson);                       
        }
        container.add("repartition_medium", repartMediumJson);
        
        for (Map<Long, Integer> coupleEmploye : repartEmploye) {
            JsonObject coupleEmployeJson = new JsonObject();
            Long id = coupleEmploye.keySet().iterator().next();
            coupleEmployeJson.addProperty("id", id);
            coupleEmployeJson.addProperty("nombre_consultations", coupleEmploye.get(id));
            coupleEmployeJson.addProperty("nom", service.rechercherEmployeeParID(id).getNom());
            
            repartEmployeJson.add(coupleEmployeJson);
        }
        container.add("repartition_employe", repartEmployeJson);
        
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();    
        
        
    }
    
}
