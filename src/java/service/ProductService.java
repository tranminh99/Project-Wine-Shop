/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ICommonDAO;
import dao.ProductDAO;
import entity.Product;
import java.util.List;

/**
 *
 * @author dell
 */
public class ProductService implements ICommonservice<Product>{
    ICommonDAO<Product> commonDao = new ProductDAO();
    @Override
    public List<Product> getAll() {
        return commonDao.getAll();
    }

    @Override
    public Product getOne(int id) {
        return commonDao.getOne(id);
    }

    @Override
    public boolean add(Product obj) {
        return commonDao.add(obj);
    }

    @Override
    public boolean update(int id, Product obj) {
        return commonDao.update(id, obj);
    }

    @Override
    public boolean delete(int id) {
        return commonDao.delete(id);
    }
    public List<Product> getAllByBrand(int idCate) {
        return new ProductDAO().getAllByBrand(idCate);
    }
    public List<Product> search(String text)
    {
        return new ProductDAO().search(text);
    }
}
