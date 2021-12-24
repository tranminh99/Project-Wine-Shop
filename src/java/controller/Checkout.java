/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDetailDAO;
import entity.Cart;
import entity.CustomerInfo;
import entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.CustomerInfoService;
import service.OrderService;

/**
 *
 * @author dell
 */
@WebServlet(name = "Checkout", urlPatterns = {"/Checkout"})
public class Checkout extends HttpServlet {

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
            String name = request.getParameter("name");
            String mobie = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("adress");
            CustomerInfo cf = CustomerInfo.builder()
                    .name(name)
                    .mobile(mobie)
                    .email(email)
                    .address(address)
                    .build();
            int customerID;
            customerID = new CustomerInfoService().addCustomerInfoReturnId(cf);
            if (customerID > 0) {
                String node = request.getParameter("ghichu");
                System.out.println(node);
                HttpSession session = request.getSession(true);
                List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
                double totalPrice = 0;
                for (Cart c : listCart) {
                    totalPrice += c.getTotalPrice();
                }
                Order o = Order
                        .builder()
                        .customerInforId(customerID)
                        .totalPrice(totalPrice)
                        .note(node)
                        .status(0)
                        .build();
                int orderId = new OrderService().addOrderReturnId(o);
                if (orderId > 0) {
                    boolean isCheck = new OrderDetailDAO().addListCart(listCart, orderId);
                    if (isCheck) {
                        session.removeAttribute("listCart");
                        response.sendRedirect("thank.jsp");
                    } else {
                        response.sendRedirect("Error.jsp");
                    }
                } else {
                    //delete customer info
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
