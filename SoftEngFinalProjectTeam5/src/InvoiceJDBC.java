import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;
/**
 * This class is used to connect to JDBC to retrieve or set Invoices. 
 * @author Team 5
 * @version 1.0
 */

public class InvoiceJDBC {
	private static final String USERNAME="dbuser";
	private static final String PASSWORD="enter516";
	private static final String CONNECT_URL ="jdbc:mysql://192.168.1.18:3306/purchasesystem";
	private static Connection conn = null;
	
	
	
	public static void getConnection()
	{
		if(conn != null) return;
		
		try
		{
			conn = DriverManager.getConnection(CONNECT_URL, USERNAME, PASSWORD);
			System.out.println("Connected: " + conn); //Debug Connection
		} 
		catch (SQLException e) 
		{
			
			System.err.println(e);
		}
		
	}
	
	public static void disConnect()
	{
		try
		{
			if(conn != null)
			{
				conn.close();				//System.out.println("Connected: " + conn); //Debug
			}  
		} 
		catch(SQLException e)
		{
			System.err.println(e);
		}
	}
	
	public static int createInvoiceFromOrderList(String newSQL)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fDate = dateFormat.format(cal.getTime());
		try 
		{
			PreparedStatement s = conn.prepareStatement(newSQL);
			s.setString(1, fDate);
			int rs = s.executeUpdate();
			return rs;
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	/**
	 * Author Ernesto Thermidor
	 * @return newInvoice It returns an ArrayList of Invoices.
	 */
	public ResultSet getInvoiceList(String newSQL, int placeHolder, String newStartDate, String newEndDate)
	{
		Statement stmt;
		ResultSet rs = null;
		try 
		{
			if(placeHolder > 0)
			{
				PreparedStatement stmt1 = conn.prepareStatement(newSQL);
				stmt1.setInt(1, placeHolder);
				rs = stmt1.executeQuery();
			}
			else if(placeHolder < 0)
			{
				PreparedStatement stmt2 = conn.prepareStatement(newSQL);
				stmt2.setString(1, newStartDate);
				stmt2.setString(2, newEndDate);
				rs= stmt2.executeQuery();
			}
			else 
			{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(newSQL);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return rs;
	}
	
	/**
	 * Author Kahliik Burrell
	 * @param Invocie Pass by Reference of Arraylist of Invoices. 
	 * @return success Returns boolean value depending on the success of the connection. 
	 */
	public int setInvoieList(String sql,int newInvoiceNum ,double newPrice, String newCustomerInfo, 
									String newPaidDate, String newStatus, int newQuantity)
	{
		int affected = 0;
		try 
		{
			PreparedStatement upStmt = conn.prepareStatement(sql);
			upStmt.setDouble(1, newPrice);
			upStmt.setString(2, newCustomerInfo);
			upStmt.setInt(3, newQuantity);
			upStmt.setString(4, newPaidDate);
			upStmt.setString(5, newStatus);
			upStmt.setInt(6, newInvoiceNum);
			
			affected = upStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return  affected;
	}
	
	public int deleteInvoiceList(String sql, String StartDate, String EndDate)
	{
		int affected = 0;
		try 
		{
			PreparedStatement deStmt = conn.prepareStatement(sql);
			deStmt.setString(1, StartDate);
			deStmt.setString(2, EndDate);
			affected = deStmt.executeUpdate();
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return affected;
	}
	
	/**
	 * Author Cyril Thomas
	 * @return newInvoice It returns ArrayList of Invoices. 
	 */
	/*public ArrayList<Invoice> getOrderList()
	{
		ArrayList<Invoice> newInvoice = null;;
		return newInvoice;
	}*/

}
