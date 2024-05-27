/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * classe utilise pour genere une prediction
 */
class GenerePredictionAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        Service service = new Service();
        Long idClient = Long.parseLong(req.getParameter("idClient"));
        Client c = service.rechercherClientParID(idClient); 
        
        Integer amour = Integer.parseInt(req.getParameter("amour"));
        Integer sante = Integer.parseInt(req.getParameter("sante"));
        Integer travail = Integer.parseInt(req.getParameter("travail"));
        
        System.out.println("requete recue, client: " + c.getPrenom() + " amour sante travail : " + amour + sante + travail);
        
        try {
            List<String> pred = service.genererPredictions(c, amour, sante, travail);
            System.out.println("la predictions sur l amour est: " + pred.get(0));
            req.setAttribute("listePredictions", pred);
            System.out.println("predictions bien genere");
        } catch(Exception e) {
            System.out.println("probleme lors de la generation des predictions");
        }
    }
    
}
