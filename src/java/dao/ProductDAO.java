/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.SQLServerConnection;

/**
 *
 * @author dell
 */
public class ProductDAO implements ICommonDAO<Product> {

    @Override
    public List<Product> getAll() {
        String query = "select * from Product";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Product> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Product p = Product.builder().id(rs.getInt(1))
                                .cateid(rs.getInt(2))
                                .name(rs.getString(3))
                                .price(rs.getDouble(4))
                                .quantity(rs.getInt(5))
                                .size(rs.getString(6))
                                .color(rs.getString(7))
                                .descripton(rs.getString(8))
                                .status(rs.getInt(9))
                                .build();
                        list.add(p);
                    }
                    return list;
                } else {
                    return null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public Product getOne(int id) {
        String query = "select * from product where id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        Product p = Product.builder().id(rs.getInt(1))
                                .cateid(rs.getInt(2))
                                .name(rs.getString(3))
                                .price(rs.getDouble(4))
                                .quantity(rs.getInt(5))
                                .size(rs.getString(6))
                                .color(rs.getString(7))
                                .descripton(rs.getString(8))
                                .status(rs.getInt(9))
                                .build();
                        return p;
                    }
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean add(Product obj) {
        String query = "INSERT INTO product(cate_id,name,price,quantity,size,color,description,status) VALUES(?,?,?,?,?,?,?,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getCateid());
                ps.setObject(2, obj.getName());
                ps.setObject(3, obj.getPrice());
                ps.setObject(4, obj.getQuantity());
                ps.setObject(5, obj.getSize());
                ps.setObject(6, obj.getColor());
                ps.setObject(7, obj.getDescripton());
                ps.setObject(8, obj.getStatus());
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(int id, Product obj) {
        String query = "update product set cate_id=?,name=?,price=?,quantity=?,size=?,color=?,description=?,status =? where id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getCateid());
                ps.setObject(2, obj.getName());
                ps.setObject(3, obj.getPrice());
                ps.setObject(4, obj.getQuantity());
                ps.setObject(5, obj.getSize());
                ps.setObject(6, obj.getColor());
                ps.setObject(7, obj.getDescripton());
                ps.setObject(8, obj.getStatus());
                ps.setObject(9, id);
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from product where id=?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public List<Product> getAllByBrand(int idCate) {
        String query = "select * from product";
        String newQuery = idCate > 0 ? query += " where cate_id = ?" : query;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(newQuery) : null;) {
            if (idCate > 0) {
                ps.setObject(1, idCate);
            }
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Product> list = new ArrayList<>();
                if (rs != null) {

                    while (rs.next()) {
                        Product p = Product.builder().id(rs.getInt(1))
                                .cateid(rs.getInt(2))
                                .name(rs.getString(3))
                                .price(rs.getDouble(4))
                                .quantity(rs.getInt(5))
                                .size(rs.getString(6))
                                .color(rs.getString(7))
                                .descripton(rs.getString(8))
                                .status(rs.getInt(9))
                                .build();
                        list.add(p);
                    }
                    return list;
                } else {
                    return null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

//    public List<Product> search(String text, int type) {
//        String query = "select * from product ";
//        if (type == 1) {
//            query += "where name like ?";
//        } else {
//            query += "where cate_id =?";
//        }
//        try (Connection con = SQLServerConnection.getConnection();
//                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
//            ps.setObject(1, "%"+text+"%");
//            if (ps != null) {
//                ResultSet rs = ps.executeQuery();
//                List<Product> list = new ArrayList<>();
//                if (rs != null) {
//                    while (rs.next()) {
//                        Product p = Product.builder()
//                                .id(rs.getInt(1))
//                                .cateid(rs.getInt(2))
//                                .name(rs.getString(3))
//                                .price(rs.getDouble(4))
//                                .quantity(rs.getInt(5))
//                                .size(rs.getString(6))
//                                .color(rs.getString(7))
//                                .descripton(rs.getString(8))
//                                .status(rs.getInt(9))
//                                .build();
//                        list.add(p);
//                    }
//                    return list;
//                } else {
//                    return null;
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return null;
//    }
    public List<Product> search(String text) {
        String sql = "select *from product where name like  ?";

        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            ps.setObject(1, "%" + text + "%");
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<Product> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Product p = Product.builder()
                            .id(rs.getInt(1))
                            .cateid(rs.getInt(2))
                            .name(rs.getString(3))
                            .price(rs.getDouble(4))
                            .quantity(rs.getByte(5))
                            .size(rs.getString(6))
                            .color(rs.getString(7))
                            .descripton(rs.getString(8))
                            .status(rs.getInt(9))
                            .build();
                    list.add(p);
                }

                return list;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;
    }

    public int count() {
        String sql = "SELECT COUNT(*) as rownum FROM product";
        try {
            Connection connection = SQLServerConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("rownum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public ArrayList<Product> paging(int pageindex, int pagesize) {
        ArrayList<Product> dummies = new ArrayList<>();
        String sql = "SELECT * FROM 	(SELECT *,ROW_NUMBER() OVER (ORDER BY ID ASC) as row_num FROM  product) tblDummy \n"
                + "WHERE row_num >= (? - 1)*? +1 AND row_num<= ? * ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pageindex);
            ps.setInt(2, pagesize);
            ps.setInt(3, pageindex);
            ps.setInt(4, pagesize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .cateid(rs.getInt(2))
                        .name(rs.getString(3))
                        .price(rs.getDouble(4))
                        .quantity(rs.getInt(5))
                        .size(rs.getString(6))
                        .color(rs.getString(7))
                        .descripton(rs.getString(8))
                        .status(rs.getInt(9))
                        .build();
                dummies.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dummies;
    }
}
