/*this is a third sync test*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This Class is to manage and perform operation on the single or multiple invoices. 
 * @author Cyril Thomas
 * @version 1.0
 */
public class InvoiceManagement {
	
	/**
	 * This methods creates Invoices
	 * @return success Returns true or false depending on the execution of the SQL
	 */
	public InvoiceManagement()
	{
		InvoiceJDBC.getConnection();
	}
	
	public int createInvoices()
	{
		
		
		String createSQL;
		createSQL ="INSERT INTO InvoiceTable( invoiceNum, DateOfOrder, Price, CustomerInfo, Quantity, CompanyCode )"
					+ 	" SELECT OrderID, OrderDate, OrderPrice, CustomerInfo, ItemQuantity, PaymentInfo"
					+	" FROM OrdersTable"
					+	" WHERE OrdersTable.PaymentInfo LIKE 'C%'"
					+	" AND OrdersTable.OrderDate >= ?"
					+	" AND NOT EXISTS" 
					+ 	" (SELECT InvoiceNum FROM InvoiceTable WHERE InvoiceNum = OrderID)";
		
		int rows =InvoiceJDBC.createInvoiceFromOrderList(createSQL);
		return rows;
	}
	
	/**
	 * This Class creates an ArrayList of Invoices to be displayed. o
	 * @return newwInvoice Returns an ArrayList of Invoices. 
	 */
	public ArrayList<Invoice> viewInvoice(int select,int placeHolder, String newStartDate, String newEndDate)
	{
		ArrayList<Invoice> newInvoice = new ArrayList<Invoice>();
		
		InvoiceJDBC vInvoice = new InvoiceJDBC();
		ResultSet vI = null;
		try
		{
			switch (select) 
			{
				case 1:
					String sql0 = "SELECT * FROM InvoiceTable WHERE invoiceNUM = ?";
					vI=vInvoice.getInvoiceList(sql0, placeHolder,"","");
				break;
				case 2:
					String sql1 = "SELECT * FROM  InvoiceTable ORDER BY invoiceNum DESC";
					vI = vInvoice.getInvoiceList(sql1,0,"","");
				break;
				case 3:
					String sql2 = "SELECT * FROM InvoiceTable WHERE STATUS ='Pending' ORDER BY 'invoiceNUM' ASC";
					vI = vInvoice.getInvoiceList(sql2,0,"","");
				break;
				case 4:
					String sql3 = "SELECT * FROM InvoiceTable WHERE STATUS ='Paid' ORDER BY 'invoiceNUM' ASC";
					vI= vInvoice.getInvoiceList(sql3,0,"","");
				break;
				case 5:
					String sql4 = "SELECT * FROM `InvoiceTable` WHERE `InvoiceTable`.`paidDate` >= ? AND `InvoiceTable`.`paidDate` <= ?" +
								  " AND EXISTS (SELECT `Status` FROM `InvoiceTable` WHERE `Status` = 'Paid'" + 
								  " OR `Status` = 'Cancelled')";
					vI= vInvoice.getInvoiceList(sql4, -1, newStartDate, newEndDate);
				break;
				default:
				break;
			}
			
			while(vI.next())
			{
				Invoice invoiceData = new Invoice();
				invoiceData.setInvoiceNum(vI.getInt("invoiceNum"));
				invoiceData.setDateOfOrder(vI.getString("DateOfOrder"));
				invoiceData.setPrice(vI.getDouble("Price"));
				invoiceData.setCustomerInfo(vI.getString("CustomerInfo"));
				invoiceData.setQuantity(vI.getInt("Quantity"));
				invoiceData.setPaidDate(vI.getString("paidDate"));
				invoiceData.setStatus(vI.getString("Status"));
				invoiceData.setCompanyCode(vI.getString("CompanyCode"));
				
				newInvoice.add(invoiceData); 
			}
			
		}
		catch(SQLException e)
		{
			System.err.println(e);
		}
		
		return newInvoice;
	}
	
	/**
	 * This Class updates existing invoice. 
	 * @param newInvoiceID
	 * @return newInvoice Returns an single invoice.
	 */
	public boolean updateInvoice(Invoice newInvoice)
	{
		ArrayList<Invoice> updateInvoice=viewInvoice(1, newInvoice.getInvoiceNum(),"","");
		String sql =  "UPDATE InvoiceTable SET " +
					 " InvoiceTable.Price = ?, InvoiceTable.CustomerInfo = ?, InvoiceTable.Quantity = ?, InvoiceTable.paidDate = ?, InvoiceTable.Status = ? " +
					 " WHERE InvoiceTable.invoiceNum = ? "	;
		double newPrice;
		String newCustomerInfo, newPaidDate, newStatus;
		int newQuantity;
		int newInvoiceNum = newInvoice.getInvoiceNum();
//------------------------------------------------------------------------------------------------------------------		
		if(updateInvoice.get(0).getPrice() != newInvoice.getPrice())
			 newPrice = newInvoice.getPrice();
		else
			newPrice = updateInvoice.get(0).getPrice();
		
		if(!(updateInvoice.get(0).getCustomerInfo().equals(newInvoice.getCustomerInfo())))
			newCustomerInfo = newInvoice.getCustomerInfo();
		else
			newCustomerInfo = updateInvoice.get(0).getCustomerInfo();
		
		if((newInvoice.getPaidDate() != null))
			newPaidDate = newInvoice.getPaidDate();
		else
			newPaidDate = updateInvoice.get(0).getPaidDate();
		
		if(!(updateInvoice.get(0).getStatus().equals(newInvoice.getStatus())))
			newStatus = newInvoice.getStatus();
		else
			newStatus = updateInvoice.get(0).getStatus();
			
		if(updateInvoice.get(0).getQuantity() != newInvoice.getQuantity())
			newQuantity = newInvoice.getQuantity();
		else
			newQuantity = updateInvoice.get(0).getQuantity();
//------------------------------------------------------------------------------------------------------------------					
		InvoiceJDBC newUpdate = new InvoiceJDBC();
		int success = newUpdate.setInvoieList(sql, newInvoiceNum, newPrice, newCustomerInfo, newPaidDate, newStatus, newQuantity);
		
		if(success == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * This Class deletes multiple or single invoice using invoiceID
	 * @param invoiceID
	 * @return newInvoice Returns an ArrayList of invoices. 
	 */
	public boolean deleteInvoice(String StartDate, String EndDate)
	{
		String sql1 = "DELETE FROM `InvoiceTable` WHERE paidDate >= ? AND paidDate <= ? " +
					  " AND EXISTS (SELECT * FROM (SELECT STATUS FROM InvoiceTable WHERE STATUS = 'Paid'" + 
					  " OR STATUS = 'Cancelled') AS X)";
		
		InvoiceJDBC newDelete = new InvoiceJDBC();
		int success = newDelete.deleteInvoiceList(sql1, StartDate, EndDate);
		System.out.println("Success: " + success);
		if(success > 0)
			return true;
		else
			return false;
	}

}
