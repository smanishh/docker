package com.example.ms;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringWebHelloWorldApplicationTests {

	@Test
	public void contextLoads() {
	}

	
	@BeforeClass
    public static void init() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
 
        // initialize database
        initDatabase();
    }
	
	@AfterClass
    public static void destroy() throws SQLException, ClassNotFoundException, IOException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.executeUpdate("DROP TABLE employee");
            connection.commit();
        }
    }
	
	/**
     * Database initialization for testing i.e.
     * <ul>
     * <li>Creating Table</li>
     * <li>Inserting record</li>
     * </ul>
     * 
     * @throws SQLException
     */
    private static void initDatabase() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE employee (id INT NOT NULL, name VARCHAR(50) NOT NULL,"
                    + "email VARCHAR(50) NOT NULL, PRIMARY KEY (id))");
            connection.commit();
            statement.executeUpdate(
                    "INSERT INTO employee VALUES (1001,'Vinod Kumar Kashyap', 'vinod@javacodegeeks.com')");
            statement.executeUpdate("INSERT INTO employee VALUES (1002,'Dhwani Kashyap', 'dhwani@javacodegeeks.com')");
            statement.executeUpdate("INSERT INTO employee VALUES (1003,'Asmi Kashyap', 'asmi@javacodegeeks.com')");
            connection.commit();
        }
    }
    
    /**
     * Create a connection
     * 
     * @return connection object
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:mem:employees", "vinod", "vinod");
    }
    
    /**
     * Get total records in table
     * 
     * @return total number of records. In case of exception 0 is returned
     */
    private String getTotalRecords() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
        	String query = "SELECT count(*) as total FROM employee";
        	//query = "SELECT WM_CONCAT(name) as total FROM employee";
        	//query = "SELECT LISTAGG(name, '|') WITHIN GROUP (ORDER BY col_text) as total FROM employee";
        	query = "SELECT GROUP_CONCAT(name ORDER BY name DESC SEPARATOR ',') as total FROM employee";
        	
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                return result.getString("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    @Test
    public void getTotalRecordsTest() {
    	//System.out.println(getTotalRecords());
        //assertThat(3, is(getTotalRecords()));
        assertThat("Vinod Kumar Kashyap,Dhwani Kashyap,Asmi Kashyap", is(getTotalRecords()));
    }
    
    @Test
    public void checkNameExistsTest() {
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
 
            ResultSet result = statement.executeQuery("SELECT name FROM employee");
 
            if (result.first()) {
                assertThat("Vinod Kumar Kashyap", is(result.getString("name")));
            }
 
            if (result.last()) {
                assertThat("Asmi Kashyap", is(result.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

