import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


public class ProcessTag {

	
	public void FindTag(String S)
	{
		StringBuilder sb_tag=new StringBuilder();
		StringTokenizer st_text = new StringTokenizer(S," <>");
		while (st_text.hasMoreTokens()) {  
	       	 S=st_text.nextToken().trim();
	       	 S=S.toLowerCase();
	       	 sb_tag.append(S);
	       	 sb_tag.append(",");
		}
		//sb_tag.append("\n");
		String S1=sb_tag.toString();
		S1=S1.substring(0,S1.length()-1);
		S1=S1+"\n";
		System.out.println(S1);
		WriteTag(S1);
	}
	public static void WriteTag(String ent)
	{
		String[] post;
		BufferedWriter bw = null;
		String[] KeyVal;
		//System.out.println("Inside Write Index");
	    try {
	       // APPEND MODE SET HERE
	       bw = new BufferedWriter(new FileWriter("label.txt", true));
	   } catch (IOException ioe) {
	   ioe.printStackTrace();
	    }
		//StringBuilder sb_write=new StringBuilder();
		
		//post=value.split("%");
		try {
			bw.write(ent);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
