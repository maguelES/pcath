/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package retain;

/**
 *
 * @author maguelES
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StartRetain {

	public StartRetain(){

	}

	public void retainData(double h, double t, String sol)
	{
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "";
            try{
                    //
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);

                //
                Statement stt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stt.execute("USE pcath");

                String insertQuery = "INSERT INTO casebase (count, humidity, temperature, solution) values (?, ?, ?, ?)";

                PreparedStatement statement = con.prepareStatement(insertQuery);
                statement.setInt(1, 0);
                statement.setDouble (2, h);
                statement.setDouble (3, t);
                statement.setString (4, sol);

                //Statement execution
                statement.execute();

                con.close();

        }
            catch(Exception e){
                    e.printStackTrace();
        }

	}
    
}
