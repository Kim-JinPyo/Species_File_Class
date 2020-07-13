
/**
 * Species�� ����Ǿ� �ִ� ���̳ʸ� ���Ͽ��� ���ϴ� species �˻��ϱ�
 * @E_mail_Address : kjp527@naver.com
 * @author 201935031 ����ǥ
 * Programmming Homework 7-6-(c)
 * @Last_Changed : 6�� 27��
 */

import java.io.*;
import java.util.Scanner;

public class FindSpeciesFile {

	public static void main(String[] args) throws FileNotFoundException, IOException  {
		PrintWriter outputStream = 
				new PrintWriter(new FileOutputStream(new File("HW6_c_output.txt"))); //��� �� ������ ���� ��Ʈ��
		while (true) {
			// ���� �̸� �Է¹ް� ���� ����
			Scanner key = new Scanner(System.in);
			System.out.println("Input File Name : ");
			String fileName = key.nextLine();
			File binaryFile = new File(fileName);

			//������ �������� ���� ���
			if (!binaryFile.exists()) {
				System.out.println("The file not existed");
				System.exit(0);
			}

			while (true) {
				// Species �̸� �Է¹���
				System.out.println("Species Name : ");
				String speciesName = key.nextLine();
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(binaryFile));
				Species readSpecies = null;

				try {
				// ������ �����ϰ� ���ϴ� ���� �߰��� �� ���
				while ((readSpecies = (Species) inputStream.readObject()) != null) {
					if (readSpecies.getName().equalsIgnoreCase(speciesName)) {
						System.out.println("Found. Input years : ");
						int y = key.nextInt();
						key.nextLine();
						int years = readSpecies.projectedPopulation(y);

						// ����� �ؽ�Ʈ���Ͽ� ����
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
				catch(EOFException e) //���� �ȿ� �о���� ��ü�� ����
				{
					System.out.println("Not Found.");
					inputStream.close();
				}
				//�ٸ� species�� ã�� ������ ���� ����
				System.out.println("Find another species?(y or Y)");
				char a;
				a = key.nextLine().charAt(0);

				if (a == 'y' || a == 'Y')
					continue;
				else
					break;

			}
			//�ٸ� ������ ���� ������ ���� ����
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
