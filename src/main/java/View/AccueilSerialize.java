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
 * @author sbenbouzid
 */
public class AccueilSerialize extends Serialization {

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();
        
        List<Medium> mediumList = (List<Medium>) req.getAttribute("medium_liste");
        JsonArray mediumListJson = new JsonArray();
        
        for (Medium medium : mediumList) {
            JsonObject mediumJson = new JsonObject();
            
            mediumJson.addProperty("nom", medium.getDenomination());
            mediumJson.addProperty("commentaire", medium.getPresentation());
            mediumJson.addProperty("genre", medium.getGenre());
            
            mediumListJson.add(mediumJson);
        }
        
        container.add("medium_liste", mediumListJson);
        
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();
    }
    
}
