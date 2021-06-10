//Noah Krill
//6/10/21
//This project is to take a simple text file and print the hash in sha256, sha1, and MD5

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.File;
import java.security.MessageDigest;
public class test
{
private static String getHash(final String hashtype, final File fileName)
{
	int bc=0;
	StringBuilder stringBuild = new StringBuilder();
	try
	{
		//getting the stream of the file content
	FileInputStream stream = new FileInputStream(fileName);

	// spliting the data into chuncks ina byte array
	byte[] byteArray = new byte[1024];
	MessageDigest digest = MessageDigest.getInstance(hashtype);
	while ((bc = stream.read(byteArray))!=-1)
	{
		digest.update(byteArray,0,bc);
	}
	stream.close();

	byte[] bytes = digest.digest();

	//converting to hexadecimal
	for(int i=0; i< bytes.length; ++i)
	{
		stringBuild.append(Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1));
	}

	}
	catch(Exception e)
	{
		System.out.println("Something went wrong");
	}
	return stringBuild.toString();

}
//This is the driver code
public static void main(String[] args)
	{
		System.out.println("Name of file");
		Scanner scan = new Scanner(System.in);
		File file = new File(scan.nextLine());
		String hash = getHash("Sha-256", file);
		System.out.println("The hash for Sha-256 is " + hash);
		hash = getHash("Sha-1", file);
		System.out.println("The hash for Sha-1 is " + hash);
		hash = getHash("MD5", file);
		System.out.println("The hash for MD5 is " + hash);
	}
}