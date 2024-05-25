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
 * classe pour signaler au client le debut de l appel
 */
class SignalerClientAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        Service service = new Service(); 
        Long idConsultation = (Long) req.getAttribute("idConsultation");
        Consultation cons = service.rechercherConsultationById(idConsultation);
        
        service.indiquerPretClient(cons);
    }
    
}
