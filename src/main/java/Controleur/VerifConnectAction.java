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
public class VerifConnectAction extends Action{

    @Override
    public void execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("ID");
        if (id == null) {
            req.setAttribute("connected", false);
        }
        else {
            req.setAttribute("connected", true);
        }
    }
    
}
