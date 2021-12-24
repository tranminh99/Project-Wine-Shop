/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AccountDetailDAO;
import dao.ICommonDAO;
import entity.AccountDetail;
import java.util.List;

/**
 *
 * @author dell
 */
public class AccountDetailService implements ICommonservice<AccountDetail> {

    ICommonDAO<AccountDetail> commonDao = new AccountDetailDAO();

    @Override
    public List<AccountDetail> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDetail getOne(int id) {
        return commonDao.getOne(id);
    }

    @Override
    public boolean add(AccountDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, AccountDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
