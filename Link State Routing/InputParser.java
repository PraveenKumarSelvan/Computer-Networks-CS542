package computernetworks;

import java.io.BufferedReader;
import java.io.File;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;

public class InputParser {
	static int [][]a;
	static boolean loadsuccessfull=false;
	static ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
	
	
	public static void newfile(String nfile)// nfile reads the file name and path from user 
	{	
		loadsuccessfull=true;
		String line;
		int cols=-1;
		try
		{
			File file =new File(nfile);
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line=br.readLine())!=null)
			{ 
				ArrayList<Integer> tmp = new ArrayList<>();
				for(String ele: line.split(" "))
				{									//gets input topology seperated by comma's
					tmp.add(Integer.parseInt(ele));//reach each line and stores it in 'tmp' array list
				}
				if(cols == -1)
					cols = tmp.size();
				else
					if(cols != tmp.size())//condition to check if input matrix is valid
					{
						System.out.print("\nInvalid number of columns in one of the input rows");
						break;
					}
				matrix.add(tmp);//adding input matrix to 2d array
				
				}
			if(matrix.size()==0)//checks for invalid input
			{
				System.out.print("no input");//if file is empty prints error message
				loadsuccessfull=false;
			}
			else
			{
				if(matrix.size() !=cols)//checks for invalid input file 
				{
					System.out.print("\nRows and Cols do not match\n");//if input rows and cols do not match
					loadsuccessfull=false;
				}
				else
					System.out.print("\nCorrect input");
			}
			br.close();
			convertArrayListToArray();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 

	}


	private static void convertArrayListToArray() {
		a = new int[matrix.size()][matrix.size()];
		int i=0,j=0;
		
		for(ArrayList<Integer> tmp: matrix)
		{
			for(Integer x: tmp)
			{
				a[i][j++] = x; 			//Copies arraylist value(Input topology) into a 2d-array a[][]
			}
			i++;
			j=0;
		}
	}
	
	public static void printmatrix() //prints the current input matrix 
	{
		System.out.print("\nInput topology matrix is\n");
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a.length;j++)
			{
				System.out.print("   "+a[i][j]);
			}
			System.out.print("\n");
		}
	}
}
