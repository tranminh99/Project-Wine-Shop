/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.CustomerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author dell
 */
public class CustomerInfoDAO implements ICommonDAO<CustomerInfo>{

    @Override
    public List<CustomerInfo> getAll() {
        String query = "SELECT * FROM customer_info";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<CustomerInfo> list = new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        CustomerInfo p = CustomerInfo.builder()
                                .id(rs.getInt(1))
                                .name(rs.getString(2))
                                .mobile(rs.getString(5))
                                .email(rs.getString(4))
                                .address(rs.getString(3))
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
    public CustomerInfo getOne(int id) {
        String sql = "select *from  customer_info where id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    CustomerInfo p = CustomerInfo.builder()
                            .id(rs.getInt(1))
                            .name(rs.getString(2))
                            .mobile(rs.getString(3))
                            .email(rs.getString(4))
                            .address(rs.getString(5))
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
    
    public int addCustomerInfoReturnId(CustomerInfo obj) {
        String query = "INSERT INTO customer_info(name, mobile, email, address) VALUES(?, ?, ?, ?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getName());
                ps.setObject(2, obj.getMobile());
                ps.setObject(3, obj.getEmail());
                ps.setObject(4, obj.getAddress());
                int isCheck = ps.executeUpdate();
                if (isCheck > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
}
