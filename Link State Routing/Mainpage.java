package computernetworks;
/*
 * Link state Routing(Dijkstra;s)
 * @author Selvan,Praveen Kumar
 */
//Class Mainpage from which other methods can be reached 
import java.util.*;
public class Mainpage
{	
	
	/*
	 * creating objects for class dijkistra and Deleterouter
	 */
	static dijkistra djk = new dijkistra();
	static Deleterouter del=new Deleterouter();
	static boolean inputloaded=false;// Boolean value to check if input matrix is loaded
	static boolean inputchanged=false;//Boolean to check if input matrix is modified by Modify Topology Function
	
	public static void main(String[] args)
	{
		int start=-1,end=-1;
		int prevchoice=-1,n=0;
		
		 
		System.out.print("\n \t\t\t CS542 Link State Routing Simulator");


		do
		{	
			Scanner in= new Scanner(System.in);
			System.out.print("\n(1) Create a Network Topology");
			System.out.print("\n(2) Build a Connection Table");
			System.out.print("\n(3) Shortest Path to Destination Router");
			System.out.print("\n(4) Modify a topology");
			System.out.print("\n(5) Print Connection Tables for all Routers");
			System.out.print("\n(6) Exit");

			System.out.print("\nSelect an option: ");
			
			String opt =in.nextLine();
			
			
			System.out.print("\nYour input is \'"+opt+"\'\n");
			/*
			 * Gets an input option from user and checks for error 
			 */
			//Checks for User Input error
			if(opt.length()>1 || !Character.isDigit(opt.charAt(0)) || Integer.parseInt(opt) > 6 || Integer.parseInt(opt) < 1)
			{
				System.out.println("\n -- ERROR: Please Enter a valid number between 1 and 6 ");
			}
			else
			{
				n = Integer.parseInt(opt);
				if(inputloaded || n==1 || n==5)
				{
					switch(n)
					{
					case 1://class to add Input topology matrix
					{	System.out.print("\n Enter the name of the file:");
						String nfile=in.nextLine();
						InputParser.newfile(nfile); //Inputparser.newfile method to get input file from user
						inputloaded = InputParser.loadsuccessfull;//Boolean to check if input is loaded
						InputParser.printmatrix();//print input matrix
						break;
					}
					case 2://To get connection Table for Router
					{
						System.out.print("\nSelect a source router: ");
						start =in.nextInt();
						djk.connectiontable(start);//User Passes router number for which connection table is required
						break;
					}
					case 3://To find the shortest path between two nodes
					{
						System.out.print("\nEnter the souce  router: ");
						start=in.nextInt();
						System.out.print("\nEnter the destination router: ");
						end=in.nextInt();			//User enters source and destn to find shortest path
						djk.findshortest(start,end);//Method call to function findshortest to get shortest path
						djk.PrintShortestPath();
						break;
					}
					case 4://Modify the existing topology
					{
						ModifiyInput();
						if(prevchoice == 2 && inputchanged)
						{
							djk.connectiontable(start);//Prints Modified connection table after changing topology 
						}
						if(prevchoice == 3 && inputchanged)
						{
							djk.findshortest(start,end);//Prints Modified path after changing topology
							djk.PrintShortestPath();
						}
						if(prevchoice==1 | prevchoice==5)//Shows Connection table change after modifying topology
						{
							System.out.print("\n No previous choices");
							System.out.print("\nDisplaying Connection table after modifying");
							for(int i=1;i<InputParser.a.length;i++)
							djk.connectiontable(i);//method call to get connection table with source as parameter
						}
						inputchanged = false;
						
						break;
					}
					case 5://Prints Connection table for all nodes
					{
						for(int i=1;i<=InputParser.a.length;i++)
							djk.connectiontable(i);//passes source value for each node in input matrix
						break;
					}
					case 6:
						System.out.println("\n \t\t\t ------- Exiting Program -------");  
						break;

					default: 
						System.out.println("\n -- ERROR: Please Enter a valid number between 1 and 6 ");
						break;
					}
				}
				else
				{
					System.out.println("\n -- ERROR: Please Load input before procedding with other options");
				}
				if(inputloaded)
					prevchoice = n;//used to check condition if user has previously made 
			}
			
		
		}while(n!=6);
		
		
	}


	private static void ModifiyInput() //Method to implement add or delete node in the input topology
	{
		Scanner uinput=new Scanner(System.in);
		int choice=-1;
		do
		{
			
			System.out.print("\n1.Delete a router ");
			System.out.print("\n2.Add a new router ");
			System.out.print("\n3.Go Back to Main Menu ");
			System.out.print("\nSelect an option: ");

			String option =uinput.nextLine();
			System.out.print("\nYour input is \'"+option+"\'\n");

			if(option.length()>1 || !Character.isDigit(option.charAt(0)) || Integer.parseInt(option) > 3 || Integer.parseInt(option) < 1)
			{
				System.out.println("\n -- ERROR: Please Enter a valid number  1 to 3 ");
			}
			else
			{
				choice = Integer.parseInt(option);
				if(inputloaded)//Checks if input file is previously loaded
				{
					switch(choice)
					{
					case 1://To delete a router
					{
						System.out.print("\nEnter the router to be deleted: ");
						int value=uinput.nextInt();		//gets router to be deleted from user 				
						InputParser.a=del.delrouter(value,InputParser.a);//updates the modified topology returned by the method 
																		//function call to delete router
						System.out.println("\n Matrix after deletion");
						InputParser.printmatrix();
						inputchanged= true;
						break;
					}
					case 2:
					{
						System.out.print("\nAdd a new router");
						InputParser.a=del.addrouter(InputParser.a);// updates the modified topology returned by the method
																	//function call to add router
						System.out.print("\n Matrix after adding a router");
						InputParser.printmatrix();
						inputchanged = true;
						break;
					}
					case 3:
						break;
					default:
					{
						System.out.print("\nselect option 1 or 2 or 3");
						break;
					}

					}
				}
				else
					System.out.println("\n -- ERROR: Please Load input before procedding with other options");
			}
		} while(choice != 3 && choice !=1 && choice !=2 );
		uinput.close();
	}
}
