/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Cart;
import entity.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author dell
 */
public class OrderDetailDAO implements ICommonDAO<OrderDetail>{

    @Override
    public List<OrderDetail> getAll() {
        String query = "select * from order_detail";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<OrderDetail> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        OrderDetail p = OrderDetail.builder()
                                .id(rs.getInt(1))
                                .oderId(rs.getInt(2))
                                .productName(rs.getString(3))
                                .quantity(rs.getInt(3))
                                .productPrice(rs.getDouble(4))
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
    public OrderDetail getOne(int id) {
        String sql = "select *from  order_detail where id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    OrderDetail p = OrderDetail.builder()
                            .id(rs.getInt(1))
                            .oderId(rs.getInt(2))
                            .productId(rs.getInt(3))
                            .productName(rs.getString(4))
                            .productPrice(rs.getDouble(5))
                            .quantity(rs.getInt(6))
                            .build();
                    return p;

                }

            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;
    }

    @Override
    public boolean add(OrderDetail obj) {
        String sql = "INSERT INTO [dbo].[order_detail]\n"
                + "           ([order_id]\n"
                + "           ,[product_id]\n"
                + "           ,[product_name]\n"
                + "           ,[product_price]\n"
                + "           ,[quantity])\n"
                + "     values(?,?,?,?,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getId());
                ps.setObject(2, obj.getProductId());
                ps.setObject(3, obj.getProductName());
                ps.setObject(4, obj.getQuantity());
                ps.setObject(5, obj.getProductPrice());
                ps.setObject(6, obj.getQuantity());
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean update(int id, OrderDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int addOrderDetailInfoReturnId(OrderDetail obj) throws SQLException {
        String query = "insert into order_detail(order_id,product_id,product_name,product_price,quantity) values(?,?,?,?,?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getOderId());
                ps.setObject(2, obj.getProductId());
                ps.setObject(3, obj.getProductName());
                ps.setObject(4, obj.getProductPrice());
                ps.setObject(5, obj.getQuantity());
                int isCheck = ps.executeUpdate();
                if (isCheck > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
    
    public boolean addListCart(List<Cart> list, int orderId) {
        String query = "insert into order_detail(order_id,product_id,product_name,product_price,quantity) values(?,?,?,?,?)";
        int[] arr = {};
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                for (Cart c : list) {
                    ps.setObject(1, orderId);
                    ps.setObject(2, c.getProductId());
                    ps.setObject(3, c.getProductName());
                    ps.setObject(4, c.getProdictPrice());
                    ps.setObject(5, c.getQuantity());
                    ps.addBatch();

                }
                arr = ps.executeBatch();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return arr.length > 0;
    }
}
