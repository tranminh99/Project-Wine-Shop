/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cart;
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
import service.ProductService;
import util.NumberHelper;

/**
 *
 * @author dell
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

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
            int productId = NumberHelper.getValidateId(request.getParameter("productId"));
            int page = NumberHelper.getValidateId(request.getParameter("page"));
            int quantity = 1;
            List<Cart> listCart = null;
            if (productId > 0) {
                Product p = new ProductService().getOne(productId);
//                List<Images> listImg = new imageService();
//                        String image="";
//                        for(images img : listImg)
//                        {
//                            
//                        }
                if (p != null) {
                    Cart c = Cart.builder().productId(p.getId()).productName(p.getName())
                            .productImg("").prodictPrice(p.getPrice())
                            .productQuantity(p.getQuantity()).Quantity(quantity)
                            .totalPrice(p.getPrice() * quantity).build();
                    //check session
                    HttpSession session = request.getSession();
                    listCart = (List<Cart>) session.getAttribute("listCart");
                    if (listCart == null) {
                        listCart = new ArrayList<>();
                        listCart.add(c);
                        session.setAttribute("listCart", listCart);
                    } else {
                        boolean flag = true;
                        for (Cart cart : listCart) {
                            if (cart.getProductId() == productId) {
                                cart.setQuantity(cart.getQuantity() + 1);
                                cart.setTotalPrice(cart.getQuantity() * cart.getProdictPrice());
                                flag = false;
                            }
                        }
                        if (flag) {
                            listCart.add(c);
                        }
                        session.setAttribute("listCart", listCart);
                    }
                    if (page > 0) {
                        switch(page)
                        {
                            case 1:
                                response.sendRedirect("get-list-product");
                                break;
                            case 2:
                                response.sendRedirect("detail?productId="+productId);
                                break;
                            default:
                                response.sendRedirect("Error.jsp");
                                break;
                        }
                    } else {
                        response.sendRedirect("Error.jsp");
                    }
                } else {
                    response.sendRedirect("Error.jsp");
                }
            } else {
                response.sendRedirect("Error.jsp");
            }
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
