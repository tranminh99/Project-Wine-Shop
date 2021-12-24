/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ICommonDAO;
import dao.OrderDetailDAO;
import entity.Cart;
import entity.OrderDetail;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dell
 */
public class OrderDetailService implements ICommonservice<OrderDetail>{
    ICommonDAO<OrderDetail> commonDao = new OrderDetailDAO();
    @Override
    public List<OrderDetail> getAll() {
        return commonDao.getAll();
    }

    @Override
    public OrderDetail getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(OrderDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, OrderDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int addOrderInfoReturnId(OrderDetail obj) throws SQLException
    {
        return new OrderDetailDAO().addOrderDetailInfoReturnId(obj);
    }
    
    public boolean addListCart(List<Cart> list, int orderId){
        return new OrderDetailDAO().addListCart(list, orderId);
    }
}
