import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 * This Class is used to link to other console interfaces to perform operations. 
 * @author Ernesto Thermidor
 * @version 1.0
 *
 */
public class InvoiceInterface {
	
	/*StringBuffer invoiceString = null;
	/**
	 * 
	 * @return invoiceString 
	 */
	/*public StringBuffer display()
	{
		return invoiceString;
	}*/
	
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		InvoiceJDBC.getConnection(); //Debug
		//InvoiceJDBC.disConnect();    //Debug
		InvoiceManagement newInvoice = new InvoiceManagement();
		
		System.out.println("Number of Invoices Created: " + newInvoice.createInvoices());
		
		//ArrayList<Invoice> data = new ArrayList<Invoice>();
		//data = newInvoice.viewInvoice(1, 1,"","");
		
		/*for(Invoice i : data)
		{
			System.out.println("-----------------------------------------");
			System.out.println(i.toString());
			System.out.println("-----------------------------------------");
		}*/
		
		/*System.out.println("Do you want to Update This Invoice (Enter 1 to Update or 0 to Quit): ");
		int input = scan.nextInt();
		if(input==1)
		{
			boolean looper = true;
			while(looper)
			{
				System.out.println("What do you want to update: " +
									"\nPrice(1); CustomerInfo(2); Quantity(3); Paid Date(4); Status(5); Quit(0)" +
										"\nEnter: ");
				int input1 = scan.nextInt();
				switch (input1) 
				{
					case 1:
						System.out.println("Current Price: " + data.get(0).getPrice());
						System.out.println("Enter New Price: ");
						double input2 = scan.nextDouble();
						data.get(0).setPrice(input2);
						break;
					case 2:
						scan.nextLine();
						System.out.println("Current Customer Info:\n " + data.get(0).getCustomerInfo());
						System.out.println("Enter New Customer Info: ");
						String input6 = scan.nextLine();
						data.get(0).setCustomerInfo(input6);
						break;
					case 3:
						System.out.println("Current Quantity: "+ data.get(0).getQuantity());
						System.out.println("Enter New Quantity: ");
						int input3 = scan.nextInt();
						data.get(0).setQuantity(input3);
						break;
					case 4:
						scan.nextLine();
						System.out.println("Current Paid Date: " + data.get(0).getPaidDate());
						System.out.println("Enter New Data (yyyy-MM-dd): ");
						String input4 = scan.nextLine();
						data.get(0).setPaidDate(input4);
						break;
					case 5:
						scan.nextLine();
						System.out.println("Current Status: " + data.get(0).getStatus());
						System.out.println("Enter New Status: " );
						String input5 = scan.nextLine();
						data.get(0).setStatus(input5);
						break;
					
					default:
						looper =false;
						break;
				}
				
			}
				//System.out.println(data.get(0).getPrice()); //Debug
		}
		newInvoice.updateInvoice(data.get(0));*/
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date =  new Date();
		String newDate = dateFormat.format(date);
		
		ArrayList<Invoice> delInvoice = new ArrayList<Invoice>();
		
		System.out.println("Enter the Start Date for Deletion: (yyyy-MM-dd)");
		String inputDelStart = scan.nextLine();
		if(inputDelStart.isEmpty())
			inputDelStart = "0000-00-00";
		System.out.println("Enter the End Date for Deletion: (yyyy-MM-dd)");
		String inputDelEnd = scan.nextLine();
		if(inputDelEnd.isEmpty())
			inputDelEnd = newDate;
		
		delInvoice = newInvoice.viewInvoice(5, -1, inputDelStart, inputDelEnd);
		
		if(delInvoice.size() > 0)
		{	
			for(Invoice i : delInvoice)
			{
				System.out.println("-----------------------------------------");
				System.out.println(i.toString());
				System.out.println("-----------------------------------------");
			}
			System.out.println("Do you want to delete above Invoices: (0 for yes Or 1 for Cancel)");
			int inputConf = scan.nextInt();
			if(inputConf == 0)
			{	
				boolean successResult = newInvoice.deleteInvoice(inputDelStart, inputDelEnd);
				
				if(successResult)
					System.out.println("The Invoices have been successfully deleted");
				else
					System.out.println("There was an error invoices not deleted");
			}
		}
		else
			System.out.println("No Invoices Found");
		
		
		
		InvoiceJDBC.disConnect();
		scan.close();
		
	}
}
