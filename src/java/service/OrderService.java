/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ICommonDAO;
import dao.OrderDAO;
import entity.Order;
import java.util.List;

/**
 *
 * @author dell
 */
public class OrderService implements ICommonservice<Order>{
    ICommonDAO<Order> commonDao = new OrderDAO();
    @Override
    public List<Order> getAll() {
        return commonDao.getAll();
    }

    @Override
    public Order getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Order obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, Order obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int addOrderReturnId(Order obj)
    {
        return new OrderDAO().addOrderReturnId(obj);
    }
}
