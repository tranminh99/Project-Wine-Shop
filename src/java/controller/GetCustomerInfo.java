/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.AccountDAO;
import dao.AccountDetailDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import entity.Account;
import entity.AccountDetail;
import entity.CustomerInfo;
import entity.Order;
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

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "GetCustomerInfo", urlPatterns = {"/getCustomerInfo"})
public class GetCustomerInfo extends HttpServlet {

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
            int id = Integer.valueOf(request.getParameter("id"));
            List<Product> products = new ProductDAO().getAll();
            Account account = new AccountDAO().getOne(id);
            AccountDetail accountDetail = new AccountDetailDAO().getOne(account.getAccountDetailId());
            List<Account> accounts = new AccountDAO().getAll();
            List<Product> listPro = new ProductDAO().getAll();
            List<Order> listOrder = new OrderDAO().getAll();
            HttpSession session = request.getSession();
            account = (Account) session.getAttribute("loginAccount");
            
            int aid = account.getId();
            Order order = new OrderDAO().getOnebyeA(id);
          
            
            
            session = request.getSession();
            session.setAttribute("cusID", account);
                        request.setAttribute("order", order);

            request.setAttribute("listPro", listPro);
            request.setAttribute("listOrder", listOrder);
            request.setAttribute("accounts", accounts);
            request.setAttribute("products", products);
            request.setAttribute("accountDetail", accountDetail);
            request.getRequestDispatcher("cusInfo.jsp").forward(request, response);
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
