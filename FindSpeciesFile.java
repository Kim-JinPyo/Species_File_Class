
/**
 * Species가 저장되어 있는 바이너리 파일에서 원하는 species 검색하기
 * @E_mail_Address : kjp527@naver.com
 * @author 201935031 김진표
 * Programmming Homework 7-6-(c)
 * @Last_Changed : 6월 27일
 */

import java.io.*;
import java.util.Scanner;

public class FindSpeciesFile {

	public static void main(String[] args) throws FileNotFoundException, IOException  {
		PrintWriter outputStream = 
				new PrintWriter(new FileOutputStream(new File("HW6_c_output.txt"))); //결과 값 저장을 위한 스트림
		while (true) {
			// 파일 이름 입력받고 파일 생성
			Scanner key = new Scanner(System.in);
			System.out.println("Input File Name : ");
			String fileName = key.nextLine();
			File binaryFile = new File(fileName);

			//파일이 존재하지 않을 경우
			if (!binaryFile.exists()) {
				System.out.println("The file not existed");
				System.exit(0);
			}

			while (true) {
				// Species 이름 입력받음
				System.out.println("Species Name : ");
				String speciesName = key.nextLine();
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(binaryFile));
				Species readSpecies = null;

				try {
				// 파일을 조사하고 원하는 값을 발견할 시 출력
				while ((readSpecies = (Species) inputStream.readObject()) != null) {
					if (readSpecies.getName().equalsIgnoreCase(speciesName)) {
						System.out.println("Found. Input years : ");
						int y = key.nextInt();
						key.nextLine();
						int years = readSpecies.projectedPopulation(y);

						// 결과를 텍스트파일에 적음
						outputStream.println("Name : " + readSpecies.getName());
						outputStream.println("Population : " + readSpecies.getPopulation());
						outputStream.println("Growth Rate : " + readSpecies.getGrowthRate());
						outputStream.println(
								"The " + readSpecies.getName() + " species population after " + y + " years" + " is " + years + "\n");
						System.out.println("Write completed");
						inputStream.close();
						break;
					}
				}
				}
				catch(ClassNotFoundException e)
				{
					System.out.println("ClassNotFound Error!");
					System.exit(0);
				}
				catch(EOFException e) //파일 안에 읽어들일 객체가 없음
				{
					System.out.println("Not Found.");
					inputStream.close();
				}
				//다른 species를 찾을 것인지 묻는 과정
				System.out.println("Find another species?(y or Y)");
				char a;
				a = key.nextLine().charAt(0);

				if (a == 'y' || a == 'Y')
					continue;
				else
					break;

			}
			//다른 파일을 읽을 것인지 묻는 과정
			System.out.println("Find another File?(y or Y)");
			char b;
			b = key.nextLine().charAt(0);

			if (b == 'y' || b == 'Y')
				continue;
			else
				break;
		}
		outputStream.close();
		System.out.println("Program quit");

	}

}
