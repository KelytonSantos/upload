import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DB;

public class App {
    public static void main(String[] args) throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
	
			st = conn.createStatement();
			
			rs = st.executeQuery(
                "SELECT untitled_table.id, untitled_table.name AS name, " +
                "func.nome AS function_name, untitled_table.created_at FROM untitled_table " + 
                "INNER JOIN func ON func.id = untitled_table.id_fun"
            );
            while (rs.next()) {
				System.out.println(
                    rs.getInt("id") + ", " + 
                    rs.getString("name") + ", " + 
                    rs.getString("function_name") + ", " + 
                    rs.getDate("created_at")
                );
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
    }
}
