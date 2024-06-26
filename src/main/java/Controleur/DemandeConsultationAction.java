/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();
        System.out.println(session);
        System.out.println("Demande de consultation action pour :" + (Long) session.getAttribute("ID") + " " + Long.parseLong(req.getParameter("idMedium")));
        Service service = new Service();
        
        Client c = service.rechercherClientParID((Long) session.getAttribute("ID")); // retrouver l'objet client grace a l'identifiant ajouter a la session
        Medium m = service.rechercherMediumParID(Long.parseLong(req.getParameter("idMedium"))); // recuperer le medium demander qui sera ajouter dans la request lorsque l'utilisateur clique dessus dans la page demander un medium
        
        Consultation consultation = service.demanderConsultation(c, m); // demande de consultation lancee dans le back
        if(consultation == null) // donc la demande de consultation n'a pas aboutie
            req.setAttribute("demandeConsultation", false);
        else   
            req.setAttribute("demandeConsultation", true); // donc il y a bien une consultation qui a ete enregistree
    }
    
}
