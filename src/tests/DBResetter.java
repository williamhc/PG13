package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DBResetter
{
	private static final String LIVE_NAME = "PG13.script";
	private static final String BACKUP_NAME = "OriginalPG13.script";
	
	public static void ResetHSQLDB()
	{
		//DELETE LIVE FILE
		try
		{ 
			String orig ="./database/" + BACKUP_NAME;
			String dest = "./database/" + LIVE_NAME;
			InputStream in = new FileInputStream(orig);
			OutputStream out = new FileOutputStream(dest);
			byte[] buf = new byte[100000];
			int len;
			while ((len = in.read(buf)) > 0) {
			   out.write(buf, 0, len);
			}
			in.close();
			out.close(); 
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//COPY NEW FILE WITH LIVE_NAME 
	}
}
