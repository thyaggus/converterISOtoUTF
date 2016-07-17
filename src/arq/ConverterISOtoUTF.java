package arq;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConverterISOtoUTF {
	
	private static int numberOfFiles;
	
	private static boolean isISO8859(String address) {
        String regex = "[a-zá-úãõâ-ûä-üA-ZÁ-ÚÂ-ÛÄ-ÜÃÕ0-9\\-_@\\s\\.!$]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
	
	private static String nameUTF(String nomeOriginal) throws UnsupportedEncodingException{
		return new String(nomeOriginal.getBytes("ISO-8859-1"), "UTF-8");
	}
	
	private static String rename(String name, String address) throws UnsupportedEncodingException{
		File target  = new File(address +"/"+ name);
		String changedName = nameUTF(name);
		System.out.println("#####: " + changedName);
		
		File destiny = new File(address +"/"+ changedName);
		
		if (!target.renameTo(destiny)) {
			throw new RuntimeException("Error renameing file: " + target.getName());
		}
		
		return changedName;
	}
	
	private static void listFiles(String endereco) {
		File dir  = new File(endereco);
	    if (dir.isDirectory()) {
	        for (String s: dir.list()){
	        	try {
	        		File file = new File(endereco + "/" + s);
	        		if (file.isDirectory()) {
	        			listFiles(endereco + "/" + s);
	        			if (!isISO8859(s)){
	        				s = nameUTF(s);
//	        				rename(s, endereco);
//	        				if (!isISO8859(rename(s, endereco))){
	        				if (!isISO8859(s)){
	        					System.out.println("#> Rename error: " + s);
	        				}
	        			}
	        		} else {
	        			if (!isISO8859(s)){
	        				s = nameUTF(s);
//	        				rename(s, endereco);
//	        				if (!isISO8859(rename(s, endereco))){
	        				if (!isISO8859(s)){
	        					System.out.println("#> Rename error: " + s);
	        				}
	        			}
	        		}
	        		numberOfFiles ++;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
	        }  
	    } else {  
	        throw new RuntimeException();  
	    }  
	}
	
    private static void imprimirCaracteres(String s) {
    	System.out.println(s);
    	for (int i = 0; i < s.length(); i++) {
    		int c = s.charAt(i);
    		System.out.println(c + " - " + s.charAt(i));
    	}
    }
    
	public static void main (String [] args) {
		//listarArquivos("/Users/tiago/Desktop/Musicas/Sertanejo/Rio Negro & Solimoes");
		numberOfFiles = 0;
//		listFiles("/Users/tiago/Desktop/Mus");
		System.out.println("Test mach: " + isISO8859("Rio Negro E Solimões - Arrastão"));
		System.out.println("Test mach: " + isISO8859("Rio Negro E Solimões - Arrastão"));
		imprimirCaracteres("Rio Negro E Solimões - Arrastão");
		imprimirCaracteres("Rio Negro E Solimões - Arrastão");
		System.out.println("--------->>>>>>>>>>><<<<<<<<<<<<--------------");
		System.out.println("total of changed files: " + numberOfFiles);
	}
}