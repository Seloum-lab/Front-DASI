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
        Long idClient = (Long) req.getAttribute("idClient"); 
        Client c = service.rechercherClientParID(idClient); 
        
        Integer amour = (Integer) req.getAttribute("amour");
        Integer sante = (Integer) req.getAttribute("sante");
        Integer travail = (Integer) req.getAttribute("travail");
        
        try {
            List<String> pred = service.genererPredictions(c, amour, sante, travail);
            req.setAttribute("listePredictions", pred);
            System.out.println("predictions bien genere");
        } catch(Exception e) {
            System.out.println("probleme lors de la generation des predictions");
        }
    }
    
}
