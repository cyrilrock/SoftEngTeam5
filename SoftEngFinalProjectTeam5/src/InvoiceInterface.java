import java.util.ArrayList;
import java.util.Scanner;


/**
 * This Class is used to link to other console interfaces to perform operations. 
 * @author Ernesto Thermidor
 * @version 1.0
 *
 */
public class InvoiceInterface 
{
	
	
	/**
	 * 
	 * @return invoiceString 
	 */
	public void Invoicedisplay()
	{
		boolean loopcounter = true;
		boolean check = true;
		boolean success = false;
		InvoiceManagement displayInvoice = new InvoiceManagement();
		ArrayList<Invoice> data = new ArrayList<Invoice>();
		Scanner scan = new Scanner(System.in);
		int year, month, day;
		String lookDate = "2001-01-01";
		
	
		do
		{
			try
			{
				System.out.println("\t\t\t\t Welcome to Invoice Management");
				System.out.println();
				System.out.println("(1) View Invoice.");
				System.out.println("(2) Create Invoice.");
				System.out.println("(3) Delete Invoice.");
				System.out.println("(0) To Back.");
				System.out.print("Enter (1,2,3,0): ");
				int input1 = scan.nextInt();
				switch (input1) 
				{
					case 1:
					{	
						do
						{
							System.out.println("\n(1) View Invoices by Specfic ID");
							System.out.println("(2) View All Invoices From New to Old");
							System.out.println("(3) View Pending Invoices");
							System.out.println("(4) View Paid Invoices");
							System.out.println("(0) To Back");
							System.out.print("Enter(1,2,3,0): ");
							int input2 = scan.nextInt();
							
							switch (input2) 
							{
								case 1:
									System.out.print("\nPlease Enter the Invoice ID: ");
									int invoiceID = scan.nextInt();
									data = displayInvoice.viewInvoice(1, invoiceID, "", "");
									if(data.size()<1)
										System.out.println("\nNo Invoice Found By that ID!!!");
									else
										for(Invoice i : data)
										{
											System.out.println("-----------------------------------------");
											System.out.println(i.toString());
											System.out.println("-----------------------------------------");
										}
									System.out.println("\nDo you want to Update the Above Invoice(Enter (1) to Update (0) to Go Back)");
									System.out.print("Enter: ");
									int subInput1 = scan.nextInt();
									if (subInput1 == 1) 
									{
										boolean looper = true;
										
										while(looper)
										{
											System.out.println("\nWhat do you want to Update: ");
											System.out.println("[Price(1); CustomerInfo(2); Quantity(3); Paid Date(4); Status(5)]; To Go Back(0)");
											System.out.print("Enter:");
											int subInput2 = scan.nextInt();
											
											switch(subInput2)
											{
												case 1:
													System.out.println("\nCurrent Price: " + data.get(0).getPrice());
													System.out.print("\nEnter New Price: ");
													double subSubInput1 = scan.nextDouble();
													data.get(0).setPrice(subSubInput1);
													break;
												case 2:
													scan.nextLine();
													System.out.println("\nCurrent Customer Info:\n " + data.get(0).getCustomerInfo());
													System.out.print("\nEnter New Customer Info: ");
													String subSubInput2 = scan.nextLine();
													data.get(0).setCustomerInfo(subSubInput2);
													break;
												case 3:
													System.out.println("\nCurrent Quantity: "+ data.get(0).getQuantity());
													System.out.print("\nEnter New Quantity: ");
													int subSubInput3 = scan.nextInt();
													data.get(0).setQuantity(subSubInput3);
													break;
												case 4:
													scan.nextLine();
													System.out.println("\nCurrent Paid Date: " + data.get(0).getPaidDate());
													while(true)
													{
														System.out.print("\nPlease Enter Year in 4 digits(yyyy): ");
														year = scan.nextInt();
														System.out.print("\nPlese Enter Month in 2 digits(MM): ");
														month = scan.nextInt();
														System.out.print("\nPlease Enter Dayin 2 digits(dd): ");
														day = scan.nextInt();
														if(year >= 1900 && month >= 01 && month <= 12 && day >00 && day <= 31)
														{
															lookDate =  String.valueOf(year) +"-"+String.valueOf(month)+"-"+String.valueOf(day);
															break;
														}
														else
														{	
															System.out.println("Wrong Date Input Please ReEnter!!!");
															System.out.print("\nDo you want to ReEnter the date If Yes Enter 1 or 0 to Quit");
															int cont1 = scan.nextInt();
															if(cont1 != 1)
																break;
														}
													}
													data.get(0).setPaidDate(lookDate);
													break;
												case 5:
													scan.nextLine();
													System.out.println("\nCurrent Status: " + data.get(0).getStatus());
													System.out.print("\nEnter New Status (1) Paid (2) Pending (3) Cancelled OR Leave Blank to Go Back: " );
													int subSubInput5 = scan.nextInt();
													if(subSubInput5 == 1)
													{
														data.get(0).setStatus("Paid");
													}
													else if(subSubInput5 == 2)
													{
														data.get(0).setStatus("Pending");
													}
													else if(subSubInput5 == 3)
													{
														data.get(0).setStatus("Cancelled");
													}
													break;
												
												default:
													looper =false;
													break;
											}
										}
										System.out.println("\nNew Updated Information");
										System.out.println("-----------------------------------------");
										System.out.println(data.get(0).toString());
										System.out.println("-----------------------------------------");
										System.out.print("Please Confirm Enter (1) to Save Changes or (0) to Discard: ");
										int subInput2 = scan.nextInt();
										if(subInput2 == 1)
											 success = displayInvoice.updateInvoice(data.get(0));
										if(success)
											System.out.println("Update SuccessFul");
										else
											System.out.println("There was a problem with Updating the Invoices");
									}
									break;
								
								case 2:
									data = displayInvoice.viewInvoice(2, 0, "", "");
									if(data.size()<1)
										System.out.println("\nNo Invoices In Database");
									else
										for(Invoice i : data)
										{
											System.out.println("-----------------------------------------");
											System.out.println(i.toString());
											System.out.println("-----------------------------------------");
										}
									break;
								case 3:
									data = displayInvoice.viewInvoice(3, 0, "", "");
									if(data.size()<1)
										System.out.println("\nNo Pending Invoices Found");
									else
										for(Invoice i : data)
										{
											System.out.println("-----------------------------------------");
											System.out.println(i.toString());
											System.out.println("-----------------------------------------");
										}
									break;
								case 4:
									data = displayInvoice.viewInvoice(4, 0, "", "");
									if(data.size()<1)
										System.out.println("\nNo Paid Invoices Found");
									else
										for(Invoice i : data)
										{
											System.out.println("-----------------------------------------");
											System.out.println(i.toString());
											System.out.println("-----------------------------------------");
										}
									break;
								default:
									check=false;
									break;
							}
						}while(check);
						break;
					}
					case 2:
					{	
						//scan.next();
						System.out.println("\nPlease Enter From Which Date to Create the Invoices From");
						while(true)
						{
							System.out.print("\nPlease Enter Year in 4 digits(yyyy): ");
							year = scan.nextInt();
							System.out.print("\nPlese Enter Month in 2 digits(MM): ");
							month = scan.nextInt();
							System.out.print("\nPlease Enter Dayin 2 digits(dd): ");
							day = scan.nextInt();
							
							if(year >= 1900 && month >= 01 && month <= 12 && day >00 && day <= 31)
							{
								lookDate =  String.valueOf(year) +"-"+String.valueOf(month)+"-"+String.valueOf(day);
								break;
							}
							else
							{	
								System.out.println("Wrong Date Input Please ReEnter!!!");
								System.out.print("\nDo you want to ReEnter the date If Yes Enter 1 or 0 to Quit");
								int cont1 = scan.nextInt();
								if(cont1 != 1)
									break;
							}
						}
						
						System.out.println("\nNumber of Invoices Created: " + displayInvoice.createInvoices(lookDate));
						
						break;
					}
			
					case 3:
					{
						System.out.println("Enter(1) to Set the Start Date Or \nEnter(2) to Set the End Date");
						break;
					}
					default:
						loopcounter = false;
						break;
				}
			}
			catch(Exception e)
			{
				scan.nextLine();
				System.err.println(e);
			}
			
		}while(loopcounter);
		
		InvoiceJDBC.disConnect();
		scan.close();
	}
	
}	

	
