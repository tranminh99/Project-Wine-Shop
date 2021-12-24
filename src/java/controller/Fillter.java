/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
import entity.Category;
import entity.Images;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CategoryService;
import service.ImageService;
import service.ProductService;

/**
 *
 * @author dell
 */
@WebServlet(name = "Fillter", urlPatterns = {"/fillter"})
public class Fillter extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int cateId = Integer.valueOf(request.getParameter("cateId"));
            List<Product> listFilter = new ProductService().getAllByBrand(cateId);
            List<Product> listProduct = new ProductDAO().getAll();           
            List<Images> listImage = new ImageService().getAll();
            List<Images> listImageCover = new ArrayList<>();
            List<Images> listImageCoverFilter = new ArrayList<>();
            //List<brand> listBrand = new brandService().getAll();
            for (Images img : listImage) {
                if(img.isCover())
                {
                    listImageCover.add(img);
                }
            }
            
            for (Images img : listImageCover) {
                for (Product p : listFilter) {
                    if(img.getProductId() == p.getId())
                    {
                        listImageCoverFilter.add(img);
                    }
                }
            }
            
            List<Images> listImageCoverMapping = new ImageService().listImageCoverMapping(listImageCoverFilter, listFilter);
            List<Category> listCate = new CategoryService().getAll();
            request.setAttribute("result", listFilter);
            request.setAttribute("listCate", listCate);
            request.setAttribute("listImageCoverMapping", listImageCoverMapping);   
            request.getRequestDispatcher("newjsp.jsp").forward(request, response);
        }catch(Exception e){
            response.sendRedirect("Error.jsp");
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
