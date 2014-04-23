/*this is a third sync test*/

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
	public boolean createInvoices()
	{
		boolean success = false;
		return success;
	}
	
	/**
	 * This Class creates an ArrayList of Invoices to be displayed. 
	 * @return newwInvoice Returns an ArrayList of Invoices. 
	 */
	public ArrayList<Invoice> viewInvoice()
	{
		ArrayList<Invoice> newInvoice = null;
		return newInvoice;
	}
	
	/**
	 * This Class updates existing invoice. 
	 * @param newInvoiceID
	 * @return newInvoice Returns an single invoice.
	 */
	public Invoice updateInvoice(int newInvoiceID)
	{
		Invoice newInvoice = null;
		return newInvoice ;
	}
	
	/**
	 * This Class deletes multiple or single invoice using invoiceID
	 * @param invoiceID
	 * @return newInvoice Returns an ArrayList of invoices. 
	 */
	public ArrayList<Invoice> deleteInvoice(ArrayList<Integer> invoiceID)
	{
		ArrayList<Invoice> newInvoice = null;
		return newInvoice;
	}

}
