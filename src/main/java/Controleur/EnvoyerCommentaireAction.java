/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Consultation;
import metier.service.Service;

/**
 *
 * @author aabisaleh
 */
class EnvoyerCommentaireAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        Service service = new Service(); 
        Long idConsultation = Long.parseLong(req.getParameter("idCons"));
        String commentaire = req.getParameter("commentaire");
        System.out.println("commentaire: " + commentaire + " consultation id: " + idConsultation); 

        Consultation cons = service.rechercherConsultationById(idConsultation); 
        try {
            service.ecrireCommentaire(cons, commentaire);
            System.out.println("envoi de commentaire avec succes"); 
            req.setAttribute("envoi", true);
        } catch (Exception ex) {
            System.out.println("envoi de commentaire echoue"); 
            req.setAttribute("envoi", false);
        }
    }    
}
