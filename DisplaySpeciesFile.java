
/**
 * Species 객체가 저장되어 있는 파일을 읽기
 * @E_mail_Address : kjp527@naver.com
 * @author 201935031 김진표
 * Programmming Homework 7-6-(b)
 * @Last_Changed : 6월 27일
 */

import java.io.*;
import java.util.Scanner;

public class DisplaySpeciesFile
{
	public static void main (String [] args) throws IOException
    {
    	//파일 이름 입력받고 파일 생성
    	Scanner key = new Scanner(System.in);
    	System.out.println("Input File Name : ");
    	String fileName = key.nextLine();
    	File binaryFile = new File(fileName);
        
        ObjectInputStream inputStream = null;
        try
        {
        	//생성된 파일에 스트림 연결
            inputStream = new ObjectInputStream (
                    new FileInputStream (binaryFile));
        }
        catch (IOException e)
        {
            System.out.println ("Error opening input file " +
                    fileName + ".");
            System.exit (0);
        }
        //결과를 텍스트 파일에 나타냄
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
        	//바이너리 파일에 있는 클래스를 모두 읽어들여서 텍스트 파일에 적음
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
        catch (EOFException e) //파일에 더이상 읽을 객체가 없음
        {
            inputStream.close();
            outputStream.close();
        }
        
        System.out.println ("End of program.");
    }
}