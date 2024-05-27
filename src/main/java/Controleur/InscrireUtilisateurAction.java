/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * classe utilise pour inscrire un utilisateur
 */
public class InscrireUtilisateurAction extends Action{

    @Override
    public void execute(HttpServletRequest req) {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String genre = req.getParameter("genre");
        String dateString = req.getParameter("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(req.getParameter("date"));
        } catch (ParseException ex) {
            Logger.getLogger(InscrireUtilisateurAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        String addresse = req.getParameter("addresse");
        String code = req.getParameter("code");
        String ville = req.getParameter("ville");
        String tel = req.getParameter("tel");
        String mail = req.getParameter("mail");
        String mdp = req.getParameter("mdp");
        
        Service service = new Service();
        
        System.out.println(addresse);
       Client client = new Client(nom, prenom, mail, mdp, addresse, tel, genre, date);
        
        Boolean ret = false;
        
        
        try {
            ret = service.inscrireClient(client);
            System.out.println(ret + "Inscription du client");
           
        } catch (Exception ex) {
            Logger.getLogger(InscrireUtilisateurAction.class.getName()).log(Level.SEVERE, null, ex);
            

        } finally {
            req.setAttribute("inscription", ret);
            
        }
        
        
    }
    
}
