package com.baseactiviti.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLTest {

  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/activiti", "root",
          "root");
      // here sonoo is database name, root is username and password
      Statement stmt = con.createStatement();
      boolean rs1 = stmt.execute("insert into ACT_ID_USER"
          + "( EMAIL_, FIRST_, PICTURE_ID_, PWD_, REV_, ID_, LAST_) "
          + "values ( '12', '12', '2', '2', '122', '1012', '测试公司')");
      ResultSet rs = stmt.executeQuery("select * from ACT_ID_USER");
      while (rs.next())
        System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " "
            + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6));
      con.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
