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
<<<<<<< HEAD
        Long idConsultation = Long.parseLong(req.getParameter("idCons"));
        System.out.println("dans la consultation init action, id:" + idConsultation);

        Consultation cons = service.rechercherConsultationById(idConsultation);
=======
        Long idConsultation = (Long) req.getAttribute("idConsultation");
        Consultation cons = service.rechercherConsultationParID(idConsultation);
>>>>>>> 5b2200f323e7017e5a6c2d4d3006220d5b46ad83
        
        Client c = cons.getClient();
        
        req.setAttribute("client", c);
        req.setAttribute("prenomMedium", cons.getMedium().getDenomination());
    }
}
