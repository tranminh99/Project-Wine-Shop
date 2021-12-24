/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CustomerInfoDAO;
import dao.ICommonDAO;
import service.ICommonservice;
import entity.CustomerInfo;
import java.util.List;

/**
 *
 * @author dell
 */
public class CustomerInfoService implements ICommonservice<CustomerInfo>{
    ICommonDAO<CustomerInfo> commonDao = new CustomerInfoDAO();
    CustomerInfoDAO cd = new CustomerInfoDAO();
    @Override
    public List<CustomerInfo> getAll() {
        return commonDao.getAll();
    }

    @Override
    public CustomerInfo getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(CustomerInfo obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, CustomerInfo obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int addCustomerInfoReturnId(CustomerInfo obj)
    {
        return cd.addCustomerInfoReturnId(obj);
    }
}
