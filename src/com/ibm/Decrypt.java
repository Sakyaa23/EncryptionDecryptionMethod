package com.ibm;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import net.iharder.Base64;

public class Decrypt {

	private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
   // private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;
    private static String myEncryptionKey = "Automation for ECM FileNet LightsOn LO";
    public Decrypt() throws Exception {
       // myEncryptionKey = "ThisIsSpartaThisIsSparta";
        
       // myEncryptionKey = "sakya";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }
	public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decode(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
	 public static void main(String args []) throws Exception
	    {
	    	Decrypt td= new Decrypt();

	     //   String target="imparator";
	      //  String encrypted=td.encrypt(target);
	        //String decrypted=td.decrypt("FdBNaYWfjpWN9eYghMpbRA==");
String decrypted=td.decrypt("q01xn+NeNUw0k7uTtmvzlg==");
	      //  System.out.println("String To Encrypt: "+ target);
	      //  System.out.println("Encrypted String:" + encrypted);
	        System.out.println("Decrypted String:" + decrypted);

	    }
}
