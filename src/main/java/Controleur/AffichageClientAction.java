/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * classe utilise pour afficher la liste des clients
 */
public class AffichageClientAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        Service service = new Service();
        
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("ID");
        String nom = service.rechercherEmployeeParID(id).getNom();
        
        List<Client> clientList = null;
        try {
            clientList = service.consulterListeClients();
        } catch (Exception ex) {
            Logger.getLogger(AffichageClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        req.setAttribute("liste_client", clientList);
        
        
        
    }
    
}
