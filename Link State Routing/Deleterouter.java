package computernetworks;

import java.util.Scanner;

public class Deleterouter 
{
	public int[][] delrouter(int router,int[][] delroute)//method to delete a router from the input matrix 
	{
		int[][] afterdel = new int[delroute.length-1][delroute.length-1];
		int value=router-1;//the router value is reduced since array begins with index 0
		int p=0;
		for(int i=0;i<delroute.length;i++)
		{
			if ( i == value) 
				continue;


			int q = 0;
			for( int j = 0; j <delroute.length; ++j) //deletes the particular router given by user
			{
				if ( j == value)
					continue;

				afterdel[p][q] = delroute[i][j];
				q++;
			}

			p++;
		}
		return afterdel;//returns the modified topology matrix
	}

	public int[][] addrouter(int[][] delroute)
	{
		Scanner input= new Scanner(System.in);
		int [][]afteradd=new int[delroute.length+1][delroute.length+1];
		for(int i=0;i<delroute.length;i++)
		{
			for( int j = 0; j <delroute.length; ++j)
			{
				afteradd[i][j]=delroute[i][j];//used to hold the previous topology matrix
			}
		}

		System.out.print("\nEnter the link weight from all routers to  new router");
		for( int i = 0; i <delroute.length+1; i++)
		{
			for(int j=delroute.length;j<delroute.length+1;j++) 
			{
				afteradd[i][j]=input.nextInt();		//adding new link weights along the column
			}
		}
		System.out.print("\nEnter new router link weight corresponding to other router ");
		for(int i=delroute.length;i<delroute.length+1;i++)
		{

			for( int j = 0; j <delroute.length+1; ++j)
			{
				afteradd[i][j]=input.nextInt();//adding link weight from new router to other routers
			}
		}

		return afteradd;//returns the modified topology matrix
	}
}





