/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.ProductDAO;
import entity.Account;
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
import javax.servlet.http.HttpSession;
import service.CategoryService;
import service.ImageService;

/**
 *
 * @author dell
 */
@WebServlet(name = "GetListProduct", urlPatterns = {"/get-list-product"})
public class GetListProduct extends HttpServlet {

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
            Account account = new AccountDAO().getOne(1);
            HttpSession session = request.getSession();
            session.setAttribute("cusID", account);
            ProductDAO db = new ProductDAO();
            int pagesize = 8;
            String raw_pageindex = request.getParameter("page1");
            if (raw_pageindex == null) {
                raw_pageindex = "1";
            }
            int pageindex = Integer.parseInt(raw_pageindex);
            int count = db.count();
            int pagecount = (count % pagesize == 0) ? count / pagesize : count / pagesize + 1;
            ArrayList<Product> listProduct = db.paging(pageindex, pagesize);
            
            List<Images> listImage = new ImageService().getAll();
//            List<Product> listProduct = new ProductDAO().getAll();
            List<Images> listImageCover = new ArrayList<>();
            for (Images img : listImage) {
                if(img.isCover()){
                    listImageCover.add(img);
                }
            }
            List<Images> listImageCoverMapping = new ImageService().listImageCoverMapping(listImageCover, listProduct);
            List<Category> listCate = new CategoryService().getAll();
            request.setAttribute("pageCount", pagecount);
            request.setAttribute("pageIndex", pageindex);
            request.setAttribute("result", listProduct);
            request.setAttribute("listImageCoverMapping", listImageCoverMapping);
            request.setAttribute("listCate", listCate);
            request.setAttribute("htmlHelper", new util.HtmlHelper());
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
