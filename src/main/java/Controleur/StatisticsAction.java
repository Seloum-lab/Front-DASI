/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Medium;
import metier.service.Service;

/**
 *
 * @author sbenbouzid
 */
public class StatisticsAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("ID");
        Service service = new Service();
        List<Client>  clientList = null;
        try {
            clientList = service.consulterListeClients();
        } catch (Exception ex) {
            Logger.getLogger(StatisticsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Medium> mediumList = service.top5Medium();
        List<Map<Long, Integer>> repartEmploye = service.repartitionConsultationsEmployees();
        List<Map<Long, Integer>> repartMedium = service.repartitionConsultationsMediums();
        
        req.setAttribute("liste_client", clientList);
        req.setAttribute("liste_medium", mediumList);
        req.setAttribute("repartition_employe", repartEmploye);
        req.setAttribute("repartition_medium", repartMedium);
        req.setAttribute("nom", service.rechercherEmployeeParID(id).getNom());
        
          
        
    }
    
}
