/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Consultation;
import metier.service.Service;

/**
 *
 * Classe pour indiquer la fin de la consultation
 */
class FinirConsultationAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        Service service = new Service(); 
        Long idConsultation = (Long) req.getAttribute("idConsultation");
        Consultation cons = service.rechercherConsultationById(idConsultation);
        
        service.finirConsultation(cons);
    }
    
}
