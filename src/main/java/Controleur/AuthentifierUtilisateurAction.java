/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.security.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.modele.Employee;
import metier.service.Service;

/**
 *
 * @author sbenbouzid
 */
public class AuthentifierUtilisateurAction extends Action{

    @Override
    public void execute(HttpServletRequest req) {
        
        String login = (String) req.getParameter("login");
        String mdp = (String) req.getParameter("password");
        Service service = new Service();
        Client client = null;
        Employee employe = null;
        try {
            client = service.authentifierClient(login, mdp);
            System.out.println(" Client trouve " + client);
            if (client != null) {
                req.setAttribute("utilisateur", client);  
            } else {
                employe = service.authentifierEmployee(login, mdp);
                req.setAttribute("utilisateur", employe);  
                System.out.println(" Employee trouve " + employe);

            }
        } catch (Exception ex) {
            Logger.getLogger(AuthentifierUtilisateurAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

}
