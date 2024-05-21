/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sbenbouzid
 */
public class DeconnexionAction extends Action {

    @Override
    public void execute(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.setAttribute("ID", null);
    }
    
}
