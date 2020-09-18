import java.io.*;  
import java.util.Scanner;  
import java.util.ArrayList;
import java.util.Random;

public class ConsoleRoulette {
	
	public static void main(String[] args) { 
		ArrayList<String> players = loadPlayers();
		
		System.out.println("Welcome to Console Roulette");
		System.out.println("Players");
		System.out.println("---------------------------");
		
		for(int i = 0; i < players.size(); i++){
			System.out.println(i + 1 + " - " + players.get(i));
		}
		System.out.println("---------------------------");
		
		Scanner in = new Scanner(System.in);
	    Random rand = new Random();
	    
	    int rouletteNumber;
	    
	    ArrayList<String> nameBetAmount = new ArrayList<String>();
	    
	    System.out.println("Enter Name Bet Amount e.g Tiki_Monkey 2 1.0, 's' to start Spinning.");
    	String line = in.nextLine();
    	
    	while (!line.equalsIgnoreCase("s")){
    		if(isValid(line)){
    			nameBetAmount.add(line);
    		}else{
    			System.out.println("Invalid input");
    		}
    		line = in.nextLine();
    	}
	    
	    try {
	        while (true) {
	        			    	
		    	if(!nameBetAmount.isEmpty()){
			    	System.out.println("Bets");
					System.out.println("---------------------------");
					
					for(int i = 0; i < nameBetAmount.size(); i++){
						System.out.println(i+1 + " - " + nameBetAmount.get(i));
					}
					
					System.out.println("---------------------------");
		    	}

		        rouletteNumber = rand.nextInt(37);
		        System.out.println("Number: " + rouletteNumber);
		        System.out.printf("%1s %10s %10s %10s", "Player", "Bet", "Outcome", "Winnings");
		        System.out.println();
		        System.out.println("---");
		        
		        String[] nameBetAmountArray = new String[3];
		        
		        for(int i = 0; i < nameBetAmount.size(); i++){      	
		        	nameBetAmount.set(i, evaluateBet(nameBetAmount.get(i), rouletteNumber));
		        	
		        	nameBetAmountArray = nameBetAmount.get(i).split(" ");
		        	
		        	System.out.printf("%1s %10s %10s %10s", nameBetAmountArray[0], nameBetAmountArray[1], nameBetAmountArray[3], nameBetAmountArray[4]);
		        	System.out.println();
				}
	            Thread.sleep(5 * 1000);
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }	
	}
	
	public static String evaluateBet(String nameBetAmount, int rouletteNumber){
			
		int bet = 0;
    	double winning = 0.0;
    	String outCome = "";
    	String[] nameBetAmountArray = nameBetAmount.split(" ");
    	
    	if(rouletteNumber == 0){
			return nameBetAmountArray[0] + " " + nameBetAmountArray[1] + " " + nameBetAmountArray[2] + " " + " LOSE 0.0";
		}
		
    	if(isInteger(nameBetAmountArray[1])){
    		bet = Integer.parseInt(nameBetAmountArray[1]);
			
    		if (bet == rouletteNumber){
				winning = 36.0*Double.parseDouble(nameBetAmountArray[2]);
				outCome = "WIN";
			}else{
				winning = 0.0;
				outCome = "LOSE";
			}
    	}else{
    		
    		if(rouletteNumber % 2 == 0){
    			if(nameBetAmountArray[1].equalsIgnoreCase("EVEN")){
            		winning = 2.0*Double.parseDouble(nameBetAmountArray[2]);
            		outCome = "WIN";
            	}else{
            		outCome = "LOSE";
            	}
    		}else{
    			if(nameBetAmountArray[1].equalsIgnoreCase("ODD")){
            		winning = 2.0*Double.parseDouble(nameBetAmountArray[2]);
        			outCome = "WIN";
            	}else{
            		outCome = "LOSE";
            	}
    		}
    	
    	}
    	
    	return nameBetAmountArray[0] + " " + nameBetAmountArray[1] + " " + nameBetAmountArray[2] + " " + outCome + " " + winning;
	}
	
	public static ArrayList<String> loadPlayers(){
		ArrayList<String> playerNames = new ArrayList<String>();
		
		try{    
			FileInputStream fis = new FileInputStream("Console-Roulette.txt");       
			Scanner sc = new Scanner(fis);
		
			while(sc.hasNextLine()){  
				playerNames.add(sc.nextLine());
			}  
			sc.close(); 
		}catch(IOException e){  
			e.printStackTrace();  
		}

		return playerNames;
	}
	
	public static boolean isInteger(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double i = Integer.parseInt(strNum);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isValid(String s){
		if(s.isEmpty())
			return false;
		
		String[] nameBetAmountArray = s.split(" ");
		if(nameBetAmountArray.length != 3)
			return false;
		
		String bet = nameBetAmountArray[1];
		
		if(isInteger(bet)){
			if((Integer.parseInt(bet) >= 1 || Integer.parseInt(bet) <= 36))
				return true;
		}else{
			if(nameBetAmountArray[1].equalsIgnoreCase("EVEN") || nameBetAmountArray[1].equalsIgnoreCase("ODD")){
				return true;
			}
		}
		
		return false;
	}
}