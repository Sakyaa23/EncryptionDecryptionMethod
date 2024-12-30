package com.ibm;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import net.iharder.Base64;

public class EncryptDecryptMethod {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
   // private static String myEncryptionKey = "Automation for ECM FileNet LightsOn LO";
    private static String myEncryptionKey = "Encryption Key for FileNetReconCE BatchJob Automation";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
   // private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;

    public EncryptDecryptMethod() throws Exception {
        
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }


    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBytes(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
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
    	EncryptDecryptMethod td= new EncryptDecryptMethod();
    	//String target="LP8YRE7z%W3Z-y7qWDHY";
      String target="Sch3ma#19";
      // String target="msh!NhjX2fcDrN2F1Djt86-Uj";
        String encrypted=td.encrypt(target);
        String decrypted=td.decrypt(encrypted);

        System.out.println("String To Encrypt:"+ target);
        System.out.println("Encrypted String:" + encrypted);
        System.out.println("Decrypted String:" + decrypted);

    }

}