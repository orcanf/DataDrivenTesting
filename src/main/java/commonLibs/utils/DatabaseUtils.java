package commonLibs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseUtils {
	
	private Connection connection;
	
	public void openConnection(String server, int portNumber, String databaseName, String username, String password) throws SQLException {
		
		String connectionString = String.format("jdbc:mysql://%s%d/%s", server, portNumber, databaseName);
		
		connection = DriverManager.getConnection(connectionString, username, password);
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
	
	public Object[][] executeSQLSelectQuery(String sqlQuery) throws SQLException{
		
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		
		resultSet.last();
		
		int rowCount = resultSet.getRow();
		
		int cellCount = resultSet.getMetaData().getColumnCount();
		
		Object [][] data = new Object[rowCount][cellCount];
		
		for(int row = 1; row <= rowCount; row++) {
			for(int cell = 1; cell <= cellCount; cell++) {
				data[row -1][cell -1] = resultSet.getString(cell);
				
			}
		}
		
		return data;
	} 
	

}

