/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import service.AccountService;

/**
 *
 * @author CUONG
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/go-Checkout"})
public class LoginFilter implements Filter {

    int count =0;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res =(HttpServletResponse) response;
        
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        if(account != null)
        {
            chain.doFilter(request, response);
        }else{
            res.sendRedirect("login.jsp");
        }
    }

    @Override
    public void destroy() {
    }

}
