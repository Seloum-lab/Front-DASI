/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Medium;
import metier.service.Service;

/**
 *
 * @author aabisaleh
 */
public class DemandeConsultationAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        Service service = new Service();
        
        Client c = service.rechercherClientParID((Long) req.getAttribute("ID")); // retrouver l'objet client grace a l'identifiant ajouter a la session
        Medium m = (Medium) req.getAttribute("Medium"); // recuperer le medium demander qui sera ajouter dans la request lorsque l'utilisateur clique dessus dans la page demander un medium
        
        Consultation consultation = service.demanderConsultation(c, m); // demande de consultation lancee dans le back
        if(consultation == null) // donc la demande de consultation n'a pas aboutie
            req.setAttribute("demandeConsultation", false);
        else   
            req.setAttribute("demandeConsultation", true); // donc il y a bien une consultation qui a ete enregistree
    }
    
}
