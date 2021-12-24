/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author dell
 */
public class OrderDAO implements ICommonDAO<Order>{

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM     [order]";

        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<Order> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Order p = Order.builder()
                            .id(rs.getInt(1))
                            .customerInforId(rs.getInt(2))
                            .totalPrice(rs.getDouble(3))
                            
                            .createDate(rs.getString(4))
                            .note(rs.getString(5))
                            .status(rs.getInt(6))
                            
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

    @Override
    public Order getOne(int id) {
        String sql = "select *from  [order] where id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Order p = Order.builder()
                            .id(rs.getInt(1))
                            .customerInforId(rs.getInt(2))
                            .totalPrice(rs.getDouble(3))
                            .createDate(rs.getString(4))
                            .note(rs.getString(5))
                            .status(rs.getInt(6))
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
    public boolean add(Order obj) {
        String sql = "INSERT INTO [dbo].[order]\n"
                + "           ([customer_info_id]\n"
                + "           ,[total_price]\n"
                + "           ,[note]\n"
                + "           ,[create_date]\n"
                + "           ,[status]) values(?,?,?,?,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getCustomerInforId());
                ps.setObject(2, obj.getTotalPrice());
                ps.setObject(3, obj.getNote());
                ps.setObject(4, obj.getCreateDate());
                ps.setObject(5, obj.getStatus());
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean update(int id, Order obj) {
        String sql = "UPDATE [dbo].[order] SET [status] = 1 WHERE id=?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {

                ps.setObject(1, obj.getStatus());
                ps.setObject(2, id);
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int addOrderReturnId(Order obj) {
        String query = "insert into [order](customer_info_id,total_price,create_date,note,status) values(?,?,?,?,?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getCustomerInforId());
                ps.setObject(2, obj.getTotalPrice());
                ps.setObject(3, obj.getCreateDate());
                ps.setObject(4, obj.getNote());
                ps.setObject(5, obj.getStatus());
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
    public Order getOnebyeA(int aid) {
        String sql = "SELECT TOP 1000 [id]\n" +
"      ,[customer_info_id]\n" +
"      ,[total_price]\n" +
"      ,[note]\n" +
"      ,[create_date]\n" +
"      ,[status]\n" +
"  FROM [Wine].[dbo].[order]\n" +
"  where id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, aid);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Order p = Order.builder()
                            .id(rs.getInt(1))
                            .customerInforId(rs.getInt(2))
                            .totalPrice(rs.getDouble(3))
                            .note(rs.getString(4))
                            .createDate(rs.getString(5))
                            .status(rs.getInt(6))
                            .build();
                    return p;

                }

            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;
    }
}
