/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author
 */
public class ServletImagen extends HttpServlet {

    private static Logger log = Logger.getLogger(ServletImagen.class);

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

        String url = request.getParameter("url");
        System.out.print("processRequest ");
        //PrintWriter out = response.getWriter();
        try {
            File file = new File(url);
            if (!file.exists()) {
                System.out.print("Fotos DEFAULT ");
                return;
            }
            if (url.contains(".pdf")) {
                response.setContentType("application/pdf");
            } else {
                response.setContentType("image/jpeg");
            }
            // Set content size
            //File file = new File("path/to/image.jpg");
            response.setContentLength((int) file.length());

            // Open the file and output streams
            FileInputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();

            // Copy the contents of the file to the output stream
            byte[] buf = new byte[1024];
            int count = 0;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            in.close();
            out.close();

        } catch (Exception e) {
            if (log == null) {
                log = Logger.getLogger(ServletImagen.class);
            }
            log.error(this.getClass().getName() + " - ", e);
            e.printStackTrace();
        }
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
