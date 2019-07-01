
import java.util.Scanner;
public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input=new Scanner(System.in);
	//method to output the game rules before the game starts.	
		rules();

//Variable declaration.
int grid[][]=new int [5][5];	//2D array that works as the grid being shown but only does calculations from the inside of the program.
int []row1=new int[25];			//an array that stores the number of times the program asks the user for an input of the coordinates for the rows.
int []row2=new int[25];			//an array that stores the number of times the program asks the user for an input of the coordinates for the columns.
int [] rw=new int[8];			//stores the value of which row the mine is generated randomly in.
int [] cl=new int[8];			//stores the value of which column the mine is generated randomly in.
int show[][]=new int [5][5];	//2D array that shows only how many mines are around the users chosen block but doesnt do the calculations.
int count=0;					//holds and displays a values which a player can tell how many mines are around a chosen block while playing.
int signal=0;					//sets a value of signal greater than one and this is when the code breaks and shows that he/she lost the game.

//method to draw a grid.
	for (int row = 0; row < 5; row++){
	for (int col = 0; col < 5; col++){
		grid[row][col]= 0;
		show[row][col]= 0;
}
	}

	//method to set mines on 8 random blocks.
	mineset(cl,rw,show,grid);
	
	//this loop lets the locations of the mines as 2 but doesnt output it to the user(just for inside calculations).
	for(int u=0;u<8;u++){
	grid[rw[u]][cl[u]]=2;
	}
	//This method is to output the grid that need to be shown to the user and which is calculated using the grid 2d array.
	grid1(show);
	System.out.println(" ");
		
	//this big do loop basically gets input from the user to choose the coordinates and ditermining how many mines are around that block.
	do{
	for(int ask=0;ask<25;ask++){
		count=0;
		
		//output that the user won when the ask variable is over 17 because there are 25 blocks in total and 8 of them are mines so onnly 17 safe blocks.
		if(ask>=17){
			System.out.println("YOU WON NICE JOB!");
			break;
		}
		
		//Asking the user to choose a mine.
		do{
		System.out.print("Choose a row Coordinate from 0 to 4:");
		row1[ask]=input.nextInt();
		}while(row1[ask]<0 || row1[ask]>4);{
		}
		
		do{
		System.out.print("Choose a column Coordinate from 0 to 4:");
		row2[ask]=input.nextInt();
		}while(row2[ask]<0 || row2[ask]>4);{
		} 
		System.out.println(" ");

		//top left block check
		if(row1[ask]!=0 && row2[ask]!=0){
		if(grid[(row1[ask])-1][(row2[ask])-1]==2){
			count++;	
		}
		}
		//top right block check
		if(row1[ask]!=0 && row2[ask]!=4){
		if(grid[(row1[ask])-1][(row2[ask])+1]==2){
			count++;
		}
		}
		//Bottom Left block check
		if(row1[ask]!=4 && row2[ask]!=0){
		if(grid[(row1[ask])+1][(row2[ask])-1]==2){
			count++;
		}
		}
		//Bottom right block check
		if(row1[ask]!=4 && row2[ask]!=4){
		if(grid[(row1[ask])+1][(row2[ask])+1]==2){
			count++;
		}
		}
		//top block check
		if(row1[ask]!=0){
		if(grid[(row1[ask])-1][row2[ask]]==2){
			count++;
		}
		}
		//Bottom block check
		if(row1[ask]!=4){
		if(grid[(row1[ask])+1][row2[ask]]==2){
			count++;
		}
		}
		//right block check
		if(row2[ask]!=4){
		if(grid[row1[ask]][(row2[ask])+1]==2){
			count++;
		}
		}
		//left block check
		if(row2[ask]!=0){
		if(grid[row1[ask]][(row2[ask])-1]==2){
			count++;
		}
		}
			
		//if the user chooses a block that is a 2 in the grid array which is the calculation array, the code breaks and goes to the lose screen where it says that the user lost and outputs the grid with only the mines showing.
		if(grid[row1[ask]][row2[ask]]==2){
			signal++;
			break;
		}else{
	countnum(show,ask,row1,count,row2);
		}
	}
	}while(signal<1);{
		System.out.println("GAMEOVER! YOU TAPPED ON A MINE!");
		//print the final grid which shows the user only the mine locations after he/she loses.
		for (int row = 0; row < 5; row++){
			for (int col = 0; col < 5; col++){
				System.out.print(grid[row][col] + " ");
			}
	System.out.println(" ");
		}
	}
	input.close();
	}
	
	/*method to output the rules of the games.
	 * pre:no parameters.
	 *post:no items being displayed.
	 */
	public static void rules(){
	System.out.println("MINESWEEPER!");
	System.out.println("Rules:");
	System.out.println("Pick a random block and it will tell you how many mines are contained in the 8 blocks that surround the picked block.");
	System.out.println("Try to avoid the mines and pick all the safe 8 blocks. Thats when you finish the game.");
	System.out.println("Good Luck!");
	System.out.println("---------------------------------------------------------------------------------------------------------------------");
}
	/* method to outut the grid on which the user chooses his/her block.(grid which does not show the mines, only zeros)
	 * pre:one int parameter(int[]show)
	 * post:no variable being returned.
	 */
	public static void grid1(int [][] show){
		
		//Drawing the grid.
		for (int row = 0; row < 5; row++){
			for (int col = 0; col < 5; col++){
				System.out.print(show[row][col] + " ");
			}
	System.out.println(" ");
		}
	}
	
	/*method to set random 8 locations for the mines.
	 * pre:4 int parameters(int[]cl,int[]rw,int[][]show,int[][]grid).
	 *post: nothing being returned.
	 */
	public static void mineset(int[]cl,int[]rw,int[][]show,int[][]grid){
		for(int u=0;u<8;u++){
			cl[u]=(int)(Math.random()*5);
			rw[u]=(int)(Math.random()*5);
			show[rw[u]][cl[u]]=grid[rw[u]][cl[u]];
}	
	}
	
	/*method to output the number of mines around a block.
	 * pre:5 int parameters(int[][]show,int ask,int[]row1,int count,int[]row2).
	 *post:nothing being returned.
	 */
	public static void countnum(int[][]show,int ask,int[]row1,int count,int[]row2){
		//printing the grid with the numbers and the users choice of block.
		for (int row = 0; row < 5; row++){
			for (int col = 0; col < 5; col++){			
				show[row1[ask]][row2[ask]]=count;
				System.out.print(show[row][col] + " ");
			}
				System.out.println(" ");
		
		}
	}
}
