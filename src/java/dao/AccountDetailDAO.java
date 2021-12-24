/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.AccountDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author dell
 */
public class AccountDetailDAO implements ICommonDAO<AccountDetail>{

    @Override
    public List<AccountDetail> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDetail getOne(int id) {
        String query = "SELECT * FROM account_detail WHERE id = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs != null && rs.next()) {
                    AccountDetail accountDetail = AccountDetail.builder()
                            .id(rs.getInt(1))
                            .name(rs.getString(2))
                            .mobile(rs.getString(3))
                            .gender(rs.getInt(4))
                            .address(rs.getString(5))
                            .build();
                    return accountDetail;
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
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
    public int addAccountReturnId(AccountDetail obj) {
        String query = "INSERT INTO [dbo].[account_detail]\n"
                + "           ([name]\n"
                + "           ,[mobile]\n"
                + "           ,[gender]\n"
                + "           ,[address])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getName());
                ps.setObject(2, obj.getMobile());
                ps.setObject(3, obj.getGender());
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
