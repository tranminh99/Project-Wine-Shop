/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ICommonDAO;
import dao.ImageDAO;
import entity.Images;
import entity.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class ImageService implements ICommonservice<Images>{
    ICommonDAO<Images> commonDao = new ImageDAO();
    @Override
    public List<Images> getAll() {
        return commonDao.getAll();
    }

    @Override
    public Images getOne(int id) {
        return commonDao.getOne(id);
    }

    @Override
    public boolean add(Images obj) {
        return commonDao.add(obj);
    }

    @Override
    public boolean update(int id, Images obj) {
        return commonDao.update(id, obj);
    }

    @Override
    public boolean delete(int id) {
        return commonDao.delete(id);
    }
    
    private Images getImageByProductId(int productId, List<Images> list)
    {
        for(Images img : list)
        {
            if(img.getProductId() == productId)
            {
                return img;
            }
        }
        return null;
    }
    
    public List<Images> listImageCoverMapping(List<Images> listImage,List<Product> listProduct){
        List<Images> listImageCoverMapping = new ArrayList<>();
        
        for(Product p : listProduct)
        {
            listImageCoverMapping.add(getImageByProductId(p.getId(), listImage));
        }
        return listImageCoverMapping;
    }
}
