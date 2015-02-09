/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vítor
 */
public class ConnectionFactory {

    public Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/s2aeij_beta", "root", "mysqldb");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
