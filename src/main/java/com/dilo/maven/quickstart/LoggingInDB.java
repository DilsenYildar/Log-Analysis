package com.dilo.maven.quickstart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoggingInDB {
	Connection connection = null;
	Statement stmnt = null;
	
	/*if (isDbTableEmpty() == 0) {	
	for (int i = 0; i < jsonStrs.size(); i++) {
		String sql = "INSERT INTO json (data) VALUES ('" + jsonStrs.get(i) + "');";
		stmnt.executeUpdate(sql);
	}
	return;
} 

String lasttsInDBsql = "select data -> 'timestamp' as lastTimestamp from json order by data-> 'timestamp' desc limit 1;";
ResultSet rs3 = stmnt.executeQuery(lasttsInDBsql);
while (rs3.next()) {
	lasttsInDB = rs3.getString("lastTimestamp");
}
if (compareLognDB(logFile, lasttsInDB) != -1) {
	//////
}
	private Integer isDbTableEmpty() throws SQLException {
		int isNull = 0;
		try {
			rs = stmnt.executeQuery("select count (*) as total from json;");
			while (rs.next()) {
				isNull = rs.getInt("total");  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isNull;
	}
	private Integer compareLognDB(String logfile, String lasttsInDB)
			throws FileNotFoundException {
		String line = "";
		int index = 0;

		FileReader file = new FileReader(logfile);
		try (BufferedReader br = new BufferedReader(file)) {
			while (((line = br.readLine()) != null)) {

				line = line.substring(line.indexOf("]") + 1);
				String lasttsInLog = "\"" + line.substring(0, line.indexOf("[")) + "\"";
				index++;
				if (lasttsInDB.equalsIgnoreCase(lasttsInLog)) {
					System.out.println("Logda:"+ lasttsInLog);
					System.out.println("Dbde:"+lasttsInDB);
					System.out.println("Logda "+ index + ".indexteyim!");
					return index;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
*/

	
	public void createOp(LogAttributes la) throws SQLException {
		
		JsonHandler jh = new JsonHandler();
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
			stmnt = connection.createStatement();
			
			String sql = "INSERT INTO json (data) VALUES ('" + jh.toJson(la) + "');";
			stmnt.executeUpdate(sql);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateOp(LogAttributes la) {
		JsonHandler jh = new JsonHandler();

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
			stmnt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteOp(LogAttributes la) throws SQLException {
	
		JsonHandler jh = new JsonHandler();
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dilo", "postgres", "dilo");
			stmnt = connection.createStatement();
			String sql = "DELETE FROM json WHERE = '"+jh.toJson(la)+"';";
			stmnt.executeUpdate(sql);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}
