
/**
 * Species�� ���̳ʸ� ���Ͽ� ����
 * @E_mail_Address : kjp527@naver.com
 * @author 201935031 ����ǥ
 * Programmming Homework 7-6-(a)
 * @Last_Changed : 6�� 27��
 */

import java.io.*;
import java.util.Scanner;

public class WriteSpeciesFile
{
    public static void main (String [] args)
    {
    	//���� �̸� �Է¹ް� ���� ����
    	Scanner key = new Scanner(System.in);
    	System.out.println("Input File Name : ");
        String fileName = key.nextLine();
        File binaryFile = new File(fileName);
        
        if (binaryFile.exists())
        {
        	System.out.println("The File already exists");
        	System.out.println("Overwrite? (y or Y)");    	
        	char a;
        	a = key.nextLine().charAt(0);
        	
        	if (a != 'y' && a != 'Y')
        		System.exit(0);
        }
        
        ObjectOutputStream outputStream = null;
        try
        {
        	//���Ͽ� ���� ���� ��Ʈ�� ����
            outputStream = new ObjectOutputStream (
                    new FileOutputStream (binaryFile));
        }
        catch (IOException e)
        {
            System.out.println ("Error opening output file " +
                    fileName + ".");
            System.exit (0);
        }
        
        char a = 0;
        //Species ������ �ִ� 5������ �Է¹޴´�
        Species[] readSpecies = new Species[5];
        
        try
        {
        	//Species.java�� �ִ� readInput �޼��� �̿��Ͽ� �Է¹ޱ�
        	 for (int i = 0; i < 5; i++)
             {
        		readSpecies[i] = new Species();
        		readSpecies[i].readInput();
             	outputStream.writeObject(readSpecies[i]);
             	
             	//����ڷκ��� �� �Է¹��� ������ ����
             	if (i < 4)
             	{
             	System.out.println("If you want to write more data?"
             			+ "(y or Y, maximum 5 Species)");
             	a = key.nextLine().charAt(0);
             	
             	if (a == 'y' || a == 'Y')
             		continue;
             	else
             		break;
             	}
             }
            outputStream.close ();
        }
        catch (IOException e)
        {
            System.out.println ("Error writing to file " +
                    fileName + ".");
            System.exit (0);
        }
        System.out.println ("Records sent to file " +
                fileName + ".");
    }
}