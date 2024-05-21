/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Medium;
import metier.service.Service;


/**
 *
 * classe utilise pour afficher la liste des medium dans la page demande de consultation
 */
public class AffichageListeMediumAction extends Action{

    @Override
    public void execute(HttpServletRequest req) {
        Service service = new Service(); 
        try{
            List<Medium> mediums = service.consulterListeMediums(); 
            req.setAttribute("listeMedium", mediums);
        } catch(Exception e) {
            // donc la liste des mediums n'a pas pu etre fetch
            e.printStackTrace();
            req.setAttribute("listeMedium", null); // pour indiquer que ca c'est mal passe
        }
    }
    
    
}
