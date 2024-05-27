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

/**
 *
 * @author aabisaleh
 */
public class FinConsultationSerialization extends Serialization{

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();   
        
        String status = (String) req.getAttribute("status");
        container.addProperty("status", status); 
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        out = res.getWriter(); // on met la consultation dans la requete    
        out.println(gson.toJson(container));
        out.close();
    }
    
}
