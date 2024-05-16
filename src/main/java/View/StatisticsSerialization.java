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
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;
import metier.modele.Medium;

/**
 *
 * @author sbenbouzid
 */
public class StatisticsSerialization extends Serialization {

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();
        
        
        List<Client> clientList = (List<Client>) req.getAttribute("liste_client");
        JsonObject clientListJson = new JsonObject();
        
        List<Medium> mediumList = (List<Medium>) req.getAttribute("liste_medium");
        JsonObject mediumListJson = new JsonObject();
        
        List<Map<Long, Integer>> repartMedium = (List<Map<Long, Integer>>) req.getAttribute("repartition_medium");
        JsonObject repartMediumJson = new JsonObject();
        
        List<Map<Long, Integer>> repartEmploye = (List<Map<Long, Integer>>) req.getAttribute("repartition_employe");
        JsonObject repartEmployeJson = new JsonObject();
        
        for (Client client : clientList) {
            JsonObject clientJson = new JsonObject();
            clientJson.addProperty("id", client.getId());
            
            
        }
        
        
        
    }
    
}
