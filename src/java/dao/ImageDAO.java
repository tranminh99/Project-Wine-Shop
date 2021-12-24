/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Images;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author dell
 */
public class ImageDAO implements ICommonDAO<Images>{

    @Override
    public List<Images> getAll() {
        String query = "select * from images";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Images> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Images p = Images.builder().id(rs.getInt(1))
                                .productId(rs.getInt(2))
                                .imageName(rs.getString(3))
                                .cover(rs.getBoolean(4))
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
    public Images getOne(int id) {
        String query = "select * from images where id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        Images p = Images.builder().id(rs.getInt(1))
                                .productId(rs.getInt(2))
                                .imageName(rs.getString(3))
                                .cover(rs.getBoolean(4))
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
    public boolean add(Images obj) {
        String query = "insert into images(product_id,imagename,cover) VALUES(?,?,?)";
        int check =0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getProductId());
                ps.setObject(2, obj.getImageName());
                ps.setObject(3, obj.isCover());

                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(int id, Images obj) {
        String query = "update images set product_id=?,imagename=?,cover=? where id = ?";
        int check =0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getProductId());
                ps.setObject(2, obj.getImageName());
                ps.setObject(3, obj.isCover());
                ps.setObject(4, id);
                check = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from images where id=?";
        int check =0;
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
    public List<Images> getAllByProID(int Id) {
        String sql = "select *from images where product_id =?";

        //luôn luôn cso xác suất xảy ra lỗi vs db
        //khách quan : mất internet
        //chủ quan: sai name password
        //:try():try with resource auto close thay cho finally vs close
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            ps.setObject(1, Id);
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<Images> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Images p = Images.builder()
                            .id(rs.getInt(1)).productId(rs.getInt(4))
                            .imageName(rs.getString(3)).cover(rs.getBoolean(2))
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
}
