import java.util.Scanner;
import java.util.Base64;

public class Base64Tool
{
	
	public String encrypt(String txt)
	{
		//Encrypt the text and return the result.
		byte[] encrypted = Base64.getEncoder().encode(txt.getBytes());
		return new String(encrypted);
	}
	
	public String decrypt(String txt)
	{
		//Decrypt the text and return the result.
		byte[] text = txt.getBytes();
		byte[] decode = Base64.getDecoder().decode(text);
		return new String(decode);
	}
}
