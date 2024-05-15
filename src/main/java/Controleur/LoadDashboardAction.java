/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.ProfilAstro;
import metier.service.Service;

/**
 *
 * @author sbenbouzid
 */
public class LoadDashboardAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        Service service = new Service();
        Long id = (Long) session.getAttribute("ID");
        Client client = service.rechercherClientParID(id);
        ProfilAstro profilAstro = client.getProfilAstro();
        
        req.setAttribute("profil_astro", profilAstro);
        req.setAttribute("name", client.getNom());
        
        
    }
}
