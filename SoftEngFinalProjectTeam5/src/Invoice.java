import java.util.Date;
/**
 * This Class holds the Invoice DataTypes. 
 * @author Kahliik Burrell
 * 
 * @version 1.0
 *
 */

public class Invoice {

	int invoiceNum, quantity;
	Date DateOfOrder, paidDate;
	double price;
	String companyCode, status;

	/**
	* 	This method is the Invoice object default constructor
	*	it instantiates the instance variables for an Invoice Object
	* 	and sets them to their default values
	*/
	public Invoice(){
	} 

	/**
	*	@param oNum represents the invoice number	
	*	@param oDate represents the date of the order
	*	@param total represents the total price of the order
	*	@param quantity shows the amount of items purchased within an order
	*	@param pDate represents the date the order was completely paid for	
	*	@param cCode indicates the company code used to pay for the order
	*	@param stat represents the current status of the order
	*  
	*
	*	This method is the Invoice object overloaded constructor. 
	*	It allows for the creation of a user-created invoices
	*/

	public Invoice(int oNum, Date oDate, double total, int quantity, Date pDate,  String cCode, String stat)
	{
	setInvoiceNum(oNum);
	
	setPrice(total);
	setCompanyCode(cCode);
	setStatus(stat);
	}

	/**
	* Sets the invoice number for an invoice
	*
	* @param oNum set the order number to the invoice number
	* 
	*/
	public void setInvoiceNum(int oNum){
	invoiceNum = oNum;
	}

	

	/**
	* Sets the price that was paid for an order
	*
	* @param total
	* 
	*/
	public void setPrice(double total){
	price = total;
	}

	/**
	* Sets the company code used to pay for an order
	*
	* @param cCode
	* 
	*/
	public void setCompanyCode(String cCode){
	companyCode = cCode;
	}



	/**
	* Sets the status for an invoice
	*
	* @param stat
	* 
	*/
	public void setStatus(String stat){
	status = stat;
	}


	/**
	* Accesses the invoice number of an invoice
	*
	* @return the invoice number assigned to an invoice
	*/
	public int getInvoiceNum(){
	return invoiceNum;
	}


	/**
	* Accesses the total price of an order for a given invoice
	*
	* @return the total price of an order for a given invoice
	*/
	public double getPrice(){
	return price;
	}


	/**
	* Accesses the company code used to pay for the order
	*
	* @return the company code used to pay for the order
	*/
	public String getCompanyCode(){
	return companyCode;
	}


	/**
	* Accesses the current status of an invoice
	*
	* @return the current status of an invoice
	*/
	public String getStatus(){
	return status;
	}




}
