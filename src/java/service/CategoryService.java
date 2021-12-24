/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDAO;
import dao.ICommonDAO;
import entity.Category;
import java.util.List;

/**
 *
 * @author dell
 */
public class CategoryService implements ICommonservice<Category>{
    ICommonDAO<Category> commonDao = new CategoryDAO();
    @Override
    public List<Category> getAll() {
        return commonDao.getAll();
    }

    @Override
    public Category getOne(int id) {
        return commonDao.getOne(id);
    }

    @Override
    public boolean add(Category obj) {
        return commonDao.add(obj);
    }

    @Override
    public boolean update(int id, Category obj) {
        return commonDao.update(id, obj);
    }

    @Override
    public boolean delete(int id) {
        return commonDao.delete(id);
    }
    
}
