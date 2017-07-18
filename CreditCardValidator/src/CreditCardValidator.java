import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CreditCardValidator
	{
	static long cardNumber, tempCardNumber;
	static boolean validity;
	static long [ ] rawNumbers = new long [16];
	static long [ ] numbersToAdd = new long [16];
	static int validCCCounter = 0;
	
	public static void main(String[] args) throws IOException
		{
		Scanner file = new Scanner (new File ("creditCardNumbers2.txt"));	
		while(file.hasNext())
			{
			cardNumber = file.nextLong();
			loadArray();
			modifyCCNumber();
			validateCCNumber();
			displayResult();
			}
		
		System.out.println("这张列表里有 " + validCCCounter
				+ " 个可能有效的信用卡。");
		}
	
	public static void loadArray()
		{
		tempCardNumber = cardNumber;
		for (int i = 0; i < 16; i++)
			{
			rawNumbers[i] = tempCardNumber % 10;
			tempCardNumber = tempCardNumber / 10;
			}
		}
	
	public static void modifyCCNumber()
		{
		for (int i = 0; i < 16; i++)
			{
			if (i % 2 == 0)
				{
				numbersToAdd[i] = rawNumbers[i];
				}
			else
				{
				if (rawNumbers[i] * 2 < 10)
					{
					numbersToAdd[i] = rawNumbers[i] * 2;
					}
				else
					{
					long doubledNumber = rawNumbers[i] * 2;
					long onesDigit = doubledNumber % 10;
					long tensDigit = doubledNumber / 10;
					numbersToAdd[i] = (onesDigit + tensDigit);
					}
				}
			}
		}
	
	public static boolean validateCCNumber()
		{
		int total = 0;
		for (long wilma : numbersToAdd)
			{
			total += wilma;
			}
		
		if (total % 10 == 0)
			{
			validity = true;
			}
		return validity;
		}
	
	public static void displayResult()
		{
//		try{Thread.sleep(100);}
//		catch(InterruptedException e){e.printStackTrace();}
		System.out.print("信用卡：" + cardNumber);
		if (validity == true)
			{
			System.out.println(" 可能有效。");
			validCCCounter++;
			}
		else
			{
			System.out.println(" 是无效卡。");
			}
		validity = false;
		}		
	}