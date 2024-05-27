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
        Long idConsultation = Long.parseLong(req.getParameter("idCons"));
        System.out.println("dans la consultation init action, id:" + idConsultation);

        Consultation cons = service.rechercherConsultationById(idConsultation);
        
        Client c = cons.getClient();
        
        req.setAttribute("client", c);
        req.setAttribute("prenomMedium", cons.getMedium().getDenomination());
    }
}
