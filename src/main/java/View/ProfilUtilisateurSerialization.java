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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException; 
import metier.modele.Client;
import metier.modele.Employee;

/**
 *
 * @author sbenbouzid
 */
public class ProfilUtilisateurSerialization extends Serialization {

    @Override
    public void serialize(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject container = new JsonObject();
        if (req.getAttribute("utilisateur") instanceof Client) {
            System.out.println("Authentification du Client");
            Client utilisateur = (Client) req.getAttribute("utilisateur");
            container.addProperty("connexion", true);
            JsonObject utilisateurJson = new JsonObject();
            utilisateurJson.addProperty("id", utilisateur.getId());
            utilisateurJson.addProperty("nom", utilisateur.getNom());
            utilisateurJson.addProperty("prenom", utilisateur.getPrenom());
            utilisateurJson.addProperty("mail", utilisateur.getMail());
            container.add("utilisateur", utilisateurJson);
        } 
        else {
            Employee utilisateur = (Employee) req.getAttribute("utilisateur");
            if (utilisateur == null) {
                container.addProperty("connexion", false); //Seul l'employe peut etre null parce qu'on a traité le cas client avant el cas employee
            }
            else {
                container.addProperty("connexion", true);
                JsonObject utilisateurJson = new JsonObject();
                utilisateurJson.addProperty("id", utilisateur.getId());
                utilisateurJson.addProperty("nom", utilisateur.getNom());
                utilisateurJson.addProperty("prenom", utilisateur.getPrenom());
                utilisateurJson.addProperty("mail", utilisateur.getAdresseMail());
                container.add("utilisateur", utilisateurJson);
        }
            
        }
        
        
        
        
        
   
        

        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        out = res.getWriter();
        out.println(gson.toJson(container));
        out.close();
        
           
        
        
        
    }
    
}
