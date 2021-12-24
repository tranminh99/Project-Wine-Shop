/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import util.NumberHelper;

/**
 *
 * @author dell
 */
@WebServlet(name = "Search", urlPatterns = {"/search"})
public class Search extends HttpServlet {

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
            String text = request.getParameter("search");
//            int type = NumberHelper.getValidateId(request.getParameter("type"));

            List<Product> resultSearch = new ProductService().search(text);

            List<Images> listImage = new ImageService().getAll();
            List<Images> listImageCover = new ArrayList<>();
            List<Images> listImageCoverFilter = new ArrayList<>();
            //List<brand> listBrand = new brandService().getAll();
            for (Images img : listImage) {
                if (img.isCover()) {
                    listImageCover.add(img);
                }
            }

            for (Images img : listImageCover) {
                for (Product p : resultSearch) {
                    if (img.getProductId() == p.getId()) {
                        listImageCoverFilter.add(img);
                    }
                }
            }
            List<Images> listImageCoverMapping = new ImageService().listImageCoverMapping(listImageCoverFilter, resultSearch);
            List<Category> listCate = new CategoryService().getAll();
            request.setAttribute("result", resultSearch);
            request.setAttribute("listCate", listCate);
            request.setAttribute("listImageCoverMapping", listImageCoverMapping);
            request.getRequestDispatcher("newjsp.jsp").forward(request, response);
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
