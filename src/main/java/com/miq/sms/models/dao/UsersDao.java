package com.miq.sms.models.dao;

import com.miq.sms.models.vo.UsersType;
import com.miq.sms.models.vo.UsersVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

public class UsersDao extends Dao implements DaoList<UsersVo> {

    private static UsersDao usersDao;

    private UsersDao() {

    }

    public static UsersDao getInstance() {
        if (usersDao == null) {
            return usersDao = new UsersDao();
        }
        return usersDao;
    }

    @Override
    public ObservableList<UsersVo> loadAll() throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UsersVo usersVo = null;
        ObservableList<UsersVo> Users = FXCollections.observableArrayList();
        try {
            con = getConnection();

            String sql = "SELECT id,user_name,user_fullname,password,user_type FROM users";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                usersVo = new UsersVo();
                usersVo.setId(rs.getInt("id"));
                usersVo.setUserName(rs.getString("user_name"));
                usersVo.setUserFullName(rs.getString("user_fullname"));
                usersVo.setPassword(rs.getString("password"));
                UsersType usersType = UsersType.getUsersTypeById(rs.getInt("user_type"));
                usersVo.setUsersType(usersType);

                Users.add(usersVo);
//                System.err.println(usersVo.getUserName());
//                System.err.println(usersVo.getUsersType().getType());
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
        } finally {
            CloseConnection(con);
        }

        return Users;
    }

    @Override
    public int insert(UsersVo uv) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        int isInsert = 0;
        try {
            con = getConnection();
            String usersql = "INSERT INTO users(id,user_name,user_fullname,password,user_type)VALUES(?,?,?,?,?)";
            ps = con.prepareStatement(usersql);
            ps.setInt(1, uv.getId());
            ps.setString(2, uv.getUserName());
            ps.setString(3, uv.getUserFullName());
            ps.setString(4, uv.getPassword());
            ps.setInt(5, uv.getUsersType().getId());
            ps.executeUpdate();
            isInsert = 1;
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            CloseConnection(con);
        }
        return isInsert;
    }

    @Override
    public int update(UsersVo uv) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        int isUpdate = 0;
        try {
            con = getConnection();
            String usersql = "UPDATE users SET user_name=?,user_fullname=?,password=?,user_type=? WHERE id=?";
            ps = con.prepareStatement(usersql);
            ps.setString(1, uv.getUserName());
            ps.setString(2, uv.getUserFullName());
            ps.setString(3, uv.getPassword());
            ps.setInt(4, uv.getUsersType().getId());
             ps.setInt(5, uv.getId());
            ps.executeUpdate();
            isUpdate = 1;
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            CloseConnection(con);
        }
        return isUpdate;
    }

    @Override
    public int delete(UsersVo uv) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        int isDelete = 0;
        try {
            con = getConnection();
            String usersql = "DELETE FROM users WHERE id=?";
            ps = con.prepareStatement(usersql);
            ps.setInt(1, uv.getId());
            ps.executeUpdate();

            isDelete = 1;
             ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            
            CloseConnection(con);
        }
        return isDelete;
    }

    @Override
    public UsersVo getData(UsersVo uv) throws Exception {

        Connection con = null;
        UsersVo usersVo = null;
        ResultSet rs = null;
        PreparedStatement ps=null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM users WHERE user_name='" + uv.getUserName() + "' AND password='" + uv.getPassword() + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                usersVo = new UsersVo();
                usersVo.setId(rs.getInt("id"));
                usersVo.setUserName(rs.getString("user_name"));
                usersVo.setUserFullName(rs.getString("user_fullname"));
                usersVo.setPassword(rs.getString("password"));
                UsersType usersType = UsersType.getUsersTypeById(rs.getInt("user_type"));
                usersVo.setUsersType(usersType);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            
            CloseConnection(con);
        }
        return usersVo;
    }

    @Override
    public UsersVo getDataById(int id) throws Exception {

        Connection con = null;
        UsersVo usersVo = null;
        ResultSet rs = null;
        PreparedStatement ps=null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM users WHERE id=?";
             ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                usersVo = new UsersVo();
                usersVo.setId(rs.getInt("id"));
                usersVo.setUserName(rs.getString("user_name"));
                usersVo.setUserFullName(rs.getString("user_fullname"));
                usersVo.setPassword(rs.getString("password"));
                UsersType usersType = UsersType.getUsersTypeById(rs.getInt("user_type"));
                usersVo.setUsersType(usersType);

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            CloseConnection(con);
        }
        return usersVo;
    }

    @Override
    public UsersVo getDataByName(String name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getLastUserId(){
    ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        int id = 0;
        try {
            con = getConnection();
            String sql = "SELECT `id` FROM `users` ORDER BY `id` DESC LIMIT 1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("خطأ");
                       alert.setHeaderText(ex.getMessage());
                       alert.setContentText(ex.toString());
                       alert.showAndWait();
//JOptionPane.showMessageDialog(null, ex.getMessage());   
        }
        return id;
    }

}
