package file_client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.Scanner;

public class file_share_client 
{
	private static Scanner sc;
	private static FileInputStream fis;
	private static Socket s;

	public static void main(String[] args) 
	{
		sc = new Scanner(System.in);
		System.out.println("\nEnter path of file:");
		String path = sc.nextLine();
		System.out.println("Enter name and extension of file i.e. abc.jpg");
		String name = sc.nextLine();
		System.out.println("Enter receiver's IP: ");
		String ip = sc.nextLine();
		File f1 = new File(path+"\\"+name);
		try 
		{
			fis = new FileInputStream(f1);
			s = new Socket(ip, 1111);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			int index,count = 0;
			dos.writeInt((int)f1.length());
			while((index = fis.read()) != -1)
			{
				dos.writeByte(index);
				count++;
				System.out.println("Sent "+(count/1024)+" KiloBytes of "+(f1.length()/1024)+" KBs");
			}
			System.out.println("\nFile Sent....");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}