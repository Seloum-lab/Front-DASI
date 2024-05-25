/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.service.Service;

/**
 *
 * classe pour l initialisation de la page de consultation 
 */
public class ConsultationInitAction extends Action {

    @Override
    public void execute(HttpServletRequest req){
        Service service = new Service();
        Long idClient = (Long) req.getAttribute("idClient"); 
        
        Client c = service.rechercherClientParID(idClient); // on trouve le client dans la base de donnees
        req.setAttribute("client", c);
    }
}
