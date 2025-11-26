package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Proba {

	public static void main(String[] args) {
		
		Connection con = null;
		try {
			
			con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/projekat1","root","1234");
			
			if(con!=null) {
				System.out.println("connected");
			}
		} catch (Exception e) {
			System.out.println("not conn");
		}

	}

}
