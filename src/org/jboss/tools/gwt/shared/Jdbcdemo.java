package org.jboss.tools.gwt.shared;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbcdemo{

public static void main(String args[]){
String dbtime;
String dbUrl = "jdbc:mysql://199.85.212.227:3306/connect2_telos";
@SuppressWarnings("unused")
String dbClass = "com.mysql.jdbc.Driver";
String query = "Select * FROM account";
String uid = "connect2";
String pwd = "12narkar"; 

try {

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection (dbUrl,uid,pwd);
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);

while (rs.next()) {
dbtime = rs.getString(1);
System.out.println(dbtime);
} //end while

con.close();
} //end try

catch(ClassNotFoundException e) {
e.printStackTrace();
}

catch(SQLException e) {
e.printStackTrace();
}

}  //end main

}  //end class
