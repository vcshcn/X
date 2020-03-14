package net.xway.base.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class CipherUtils {
	
	private static String DES_KEY = "PLATFORM"; // DES KEY

	
	/**
	 * MD5 encryption
	 * @param msg
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException 
	 */
	public static String HashWithMD5(String msg) throws NoSuchAlgorithmException, UnsupportedEncodingException  {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(msg.getBytes("ISO8859-1")); 
		byte[] md = digest.digest();
		
		return EncodeWithBase64(md);
	}

	/**
	 * DES����
	 * @param ����
	 * @return ����
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String EncryptWithDES(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		KeyGenerator generator = KeyGenerator.getInstance("DES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(DES_KEY.getBytes());
		generator.init(random);
		Key key = generator.generateKey();
		
		byte[] b = text.getBytes("ISO8859-1");
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] secure = cipher.doFinal(b);
		
		return EncodeWithBase64(secure);
	}

	/**
	 * DES����
	 * @param ����
	 * @return ����
	 * @throws IOException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String DecryptWithDES(String text) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		KeyGenerator generator = KeyGenerator.getInstance("DES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(DES_KEY.getBytes());
		generator.init(random);
		Key key = generator.generateKey();

		byte[] secure = DecodeWithBase64(text);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] b = cipher.doFinal(secure);
		
		return new String(b, "ISO8859-1");
	}

	/**
	 * Base64编码
	 * @param buf
	 * @return
	 */
	public static String EncodeWithBase64(byte[] buf) {
		if (buf == null) {
			return null;
		}
		
		return Base64.getEncoder().encodeToString(buf);
	}
	
	/**
	 * Base64解码
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static byte[] DecodeWithBase64(String s) throws IOException {
		if (s == null) {
			return null;
		}
		
		return Base64.getDecoder().decode(s);
	}

}
