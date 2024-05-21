/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Consultation;
import metier.service.Service;

/**
 *
 * classe utilise pour afficher la liste des consultations
 */
public class AffichageConsultation extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        Service service = new Service();        
        List<Consultation> consultationList = null;
        try {
            consultationList = service.consulterListeConsultations();
        } catch (Exception ex) {
            Logger.getLogger(AffichageConsultation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        req.setAttribute("consultation_liste", consultationList);
    }
    
}
