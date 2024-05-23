/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import View.AffichageClientSerialization;
import View.AffichageListeMediumSerialization;
import View.DemanderConsultationSerialization;
import View.InscriptionSerialization;
import View.LoadDashboardClientSerialization;
import View.ProfilUtilisateurSerialization;
import View.StatisticsSerialization;
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
                    
                case "load_dashboardClient":
                    System.out.println("------------------------------------ Page accueil");
                    LoadDashboardClientAction loaddashboard = new LoadDashboardClientAction();
                    loaddashboard.execute(request);
                    LoadDashboardClientSerialization serialprofil = new LoadDashboardClientSerialization();
                    serialprofil.serialize(request, response);
                    break;
                    
                case "statistics" :
                    System.out.println("------------------------------------ Page statistiques");
                    StatisticsAction stats = new StatisticsAction();
                    stats.execute(request);
                    StatisticsSerialization statisticsSerializer = new StatisticsSerialization();
                    statisticsSerializer.serialize(request, response);
                    break;
                    
                case "affichage_client" :
                    System.out.println("------------------------------------ Affichage clients");
                    AffichageClientAction affichage = new AffichageClientAction();
                    affichage.execute(request);
                    AffichageClientSerialization affichageserialization = new AffichageClientSerialization();
                    affichageserialization.serialize(request, response);
                    break;
                    
                case "afficherListeMedium" :  // dans la page de demande de medium, on a besoin de get tous les mediums pour les afficher sur la page
                    System.out.println("------------------------------------ Affichange de la liste des mediums"); 
                    AffichageListeMediumAction affichageMedium = new AffichageListeMediumAction(); 
                    affichageMedium.execute(request);
                    AffichageListeMediumSerialization affichageMediumSerialization = new AffichageListeMediumSerialization(); 
                    affichageMediumSerialization.serialize(request, response);
                    break;
                    
                case "disconnect" :
                    System.out.println("------------------------------------ deconnexion"); 
                    DeconnexionAction deconnexion = new DeconnexionAction();
                    deconnexion.execute(request);
                    
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
