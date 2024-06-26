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
import metier.modele.Client;

/**
 *
 * @author sbenbouzid
 */
public class AffichageClientSerialization extends Serialization{

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();  
        
        container.addProperty("connected", (String) req.getAttribute("connected"));
        container.addProperty("nom", (String) req.getAttribute("nom"));
        
        List<Client> clientList = (List<Client>) req.getAttribute("liste_client");
        JsonArray clientListJson = new JsonArray();
        
        for (Client client : clientList) {
            JsonObject clientJson = new JsonObject();
            clientJson.addProperty("id", client.getId());
            clientJson.addProperty("nom", client.getNom());
            clientJson.addProperty("prenom", client.getPrenom());
            clientJson.addProperty("genre", client.getGenre());
            clientJson.addProperty("age", client.getAge());
            clientJson.addProperty("mail", client.getMail());
            clientJson.addProperty("telephone", client.getTelephone());
            clientJson.addProperty("adresse", client.getAdressePostale());
                    
                    
            clientListJson.add(clientJson);
        }
        
        container.add("client_list", clientListJson);
        
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();
        
        
    
    
    }
    
}
