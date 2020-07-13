
/**
 * Species ��ü�� ����Ǿ� �ִ� ������ �б�
 * @E_mail_Address : kjp527@naver.com
 * @author 201935031 ����ǥ
 * Programmming Homework 7-6-(b)
 * @Last_Changed : 6�� 27��
 */

import java.io.*;
import java.util.Scanner;

public class DisplaySpeciesFile
{
	public static void main (String [] args) throws IOException
    {
    	//���� �̸� �Է¹ް� ���� ����
    	Scanner key = new Scanner(System.in);
    	System.out.println("Input File Name : ");
    	String fileName = key.nextLine();
    	File binaryFile = new File(fileName);
        
        ObjectInputStream inputStream = null;
        try
        {
        	//������ ���Ͽ� ��Ʈ�� ����
            inputStream = new ObjectInputStream (
                    new FileInputStream (binaryFile));
        }
        catch (IOException e)
        {
            System.out.println ("Error opening input file " +
                    fileName + ".");
            System.exit (0);
        }
        //����� �ؽ�Ʈ ���Ͽ� ��Ÿ��
        PrintWriter outputStream = null;
        try {
        outputStream = new PrintWriter(new FileOutputStream(new File("HW6_display.txt")));}
        catch (IOException e)
        {
            System.out.println ("Error opening output file " +
                    fileName + ".");
            System.exit (0);
        }
        
        Species readSpecies = null;
        try
        {
        	//���̳ʸ� ���Ͽ� �ִ� Ŭ������ ��� �о�鿩�� �ؽ�Ʈ ���Ͽ� ����
        	while (inputStream != null)
        	{
        		readSpecies = (Species)inputStream.readObject();
        		outputStream.print("Name : " + readSpecies.getName());
        		outputStream.print(" Population : " + readSpecies.getPopulation());
        		outputStream.println(" GrothRate : " + readSpecies.getGrowthRate());
        	}
            inputStream.close();
            outputStream.close();
        }
        catch (ClassNotFoundException e)
        {
        	System.out.println("Error the class not Found");
        	inputStream.close();
        	outputStream.close();
        	System.exit(0);
        }
        catch (EOFException e) //���Ͽ� ���̻� ���� ��ü�� ����
        {
            inputStream.close();
            outputStream.close();
        }
        
        System.out.println ("End of program.");
    }
}