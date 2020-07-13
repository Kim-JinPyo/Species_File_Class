
/**
 * Species를 바이너리 파일에 쓰기
 * @E_mail_Address : kjp527@naver.com
 * @author 201935031 김진표
 * Programmming Homework 7-6-(a)
 * @Last_Changed : 6월 27일
 */

import java.io.*;
import java.util.Scanner;

public class WriteSpeciesFile
{
    public static void main (String [] args)
    {
    	//파일 이름 입력받고 파일 생성
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
        	//파일에 쓰기 위한 스트림 연결
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
        //Species 정보를 최대 5종류를 입력받는다
        Species[] readSpecies = new Species[5];
        
        try
        {
        	//Species.java에 있는 readInput 메서드 이용하여 입력받기
        	 for (int i = 0; i < 5; i++)
             {
        		readSpecies[i] = new Species();
        		readSpecies[i].readInput();
             	outputStream.writeObject(readSpecies[i]);
             	
             	//사용자로부터 더 입력받을 것인지 정함
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