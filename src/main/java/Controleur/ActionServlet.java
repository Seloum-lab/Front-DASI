/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import View.InscriptionSerialization;
import View.ProfilAstroSerialization;
import View.ProfilUtilisateurSerialization;
import dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sbenbouzid
 */




@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
   
            /* TODO output your page here. You may use following sample code. */
            String todo = request.getParameter("todo");
            System.out.println("-------------------------------- todo = " + todo);
           
            
            switch (todo){
                case "connecter":
                    System.out.println("------------------------------------ Connecter ");
                    AuthentifierUtilisateurAction authentifier = new AuthentifierUtilisateurAction();
                    authentifier.execute(request);
                    ProfilUtilisateurSerialization serial = new ProfilUtilisateurSerialization();
                    serial.serialize(request, response);
                    break;
                    
                case "inscrire":
                    System.out.println("------------------------------------ Inscrire ");
                    InscrireUtilisateurAction inscrire = new InscrireUtilisateurAction();
                    inscrire.execute(request);
                    InscriptionSerialization serialinscr = new InscriptionSerialization();
                    serialinscr.serialize(request, response);
                    break;
                    
                case "load_dashboard":
                    System.err.println("------------------------------------ Page accueil");
                    LoadDashboardAction loaddashboard = new LoadDashboardAction();
                    loaddashboard.execute(request);
                    ProfilAstroSerialization serialprofil = new ProfilAstroSerialization();
                    serialprofil.serialize(request, response);
                    break;
                    
                    
                    
                    
            }
            
            
            System.out.println(" [TEST] Appel de l’ActionServlet : todo = " + todo);
          }
    
    
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        JpaUtil.fermerFabriquePersistance();
        super.destroy();
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
