package com.example.demo;

import java.io.UnsupportedEncodingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class DemoApplication {

	/*
	 * public static void main(String[] args) throws UnsupportedEncodingException {
	 * 
	 * System.out.println("Test");
	 * 
	 * CriptDescriptEAS crypto = new CriptDescriptEAS();
	 * 
	 * String result = crypto.encrypt(
	 * "{\"fi\":7,\"chn\":\"3\",\"trn\":424,\"data\":{\"CONTA_CORRENTE\":329,\"COOPERATIVA\":1,\"VALOR\":\"senháDoÙsuáãário\"}}"
	 * ); System.out.println("ENCRYPT >>> senhaDoUsuArio");
	 * System.out.println(result);
	 * 
	 * result = crypto.decrypt(result); System.out.println("DECRYPT");
	 * System.out.println(result);
	 * 
	 * String cryptoTest =
	 * "NoOzoFnl860SXUWKa6SSmrtFCUzFALI7OnebC03cm4DObK4/nvpIZXpdAJFl6KXf1AWR/C15TcGfwKNBH6Ppvg==";
	 * 
	 * String resultCrypto = crypto.decrypt(cryptoTest);
	 * System.out.println("DECRYPT --- MEU TESTE");
	 * System.out.println(resultCrypto);
	 * 
	 * byte[] bytes = resultCrypto.getBytes("UTF-8"); String s2 = new String(bytes,
	 * "UTF-8"); // Charset with which bytes were encoded
	 * 
	 * System.out.println(s2);
	 * 
	 * SpringApplication.run(DemoApplication.class, args);
	 * 
	 * }
	 */

	private static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAOkQX84LuZCKoWrZW19Ohk4ZwFlAXoBVPDsA7JDz4RpDmsmzMnzkTSF2irt0/2rg5Yk8Si26XilFezwogF3WwxFgLuRuqGygu1aFgP1W43vDUJFpE9RMqGCEMc42oF6fWCBW47DQbrU7JCAppeqZVz6aZIsc0zcio+r9dil2QJW/AgMBAAECgYAQ20JSoL53Gc+67qWRKxjDpVEoI2OyXHfSvKpfgYQSZjlXxUItjkWV2gEGtduTW1i+qEGlNQmCaqgTHcrK/rF0+Ow0iy9g7SFyRFqM0O4z5DSnke7zARWTpRQ4cfp+DxGG5TOfqOJSSzLKxRfJIcWkEHlb4SViNkrNnCbCY7Q1oQJBAPnl3uN11W0p+kcw2nFhPn3yJ61xTCSUyfyALqk+ERjvEpn9bAbFgrNDTMd8pF3DO8MHW7VSrIH3L07s0tpww+MCQQDuwUX0aQZuarkKa7m3VJyguLosfAb3cALV58PxdwpBFf2aoU6yp4N2GtUeMEiGZGJuFCWMKN3RcYuQDkcjH+V1AkAt85IHu3wyZZyrCJWycZI/MI8ROpsowt9deeiaoFoefp+qB0qc+CavdfmhWQ8UWrkbhLfdYMVt5JkjZzLijgfHAkAwM4ba/CUXP6aR6wO4dnWUoRa9CmEhrVR1OPA/HIhOcZEcmbpYqScKPgqOqLLLpxKUJK8b59g4g5Loh2lnNvZNAkAr8MFFNTV7lAC36mTYecdO0J/A8XkIPQkYa7z6x+qDpUyfTHf+ELkMKdqSi/0Tsn+xdBWeGGgLMSQbbY8TYErJ";
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDpEF/OC7mQiqFq2VtfToZOGcBZQF6AVTw7AOyQ8+EaQ5rJszJ85E0hdoq7dP9q4OWJPEotul4pRXs8KIBd1sMRYC7kbqhsoLtWhYD9VuN7w1CRaRPUTKhghDHONqBen1ggVuOw0G61OyQgKaXqmVc+mmSLHNM3IqPq/XYpdkCVvwIDAQAB";
	// private static final Logger LOGGER = LoggerFactory.getLogger(RsaUtil.class);

	public static PublicKey getPublicKey(String base64PublicKey) {
		PublicKey publicKey = null;

		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
			// LOGGER.error("Erro ao definir chave publica (RSA)
			// ".concat(exception.getMessage()));
			exception.printStackTrace();
		}
		return null;
	}

	public static PrivateKey getPrivateKey(String base64PrivateKey) {
		PrivateKey privateKey = null;
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			privateKey = keyFactory.generatePrivate(keySpec);
			return privateKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
			// LOGGER.error("Erro ao definir chave privada (RSA)
			// ".concat(exception.getMessage()));
			exception.printStackTrace();
		}
		return null;
	}

	public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException,
			InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		return cipher.doFinal(data.getBytes());
	}

	public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return new String(cipher.doFinal(data));
	}

	public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException,
			InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
	}

	public static void main(String[] args)
			throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
		try {
			String encryptedString = Base64.getEncoder().encodeToString(encrypt("EduÁrd", publicKey));
			System.out.println(encryptedString);
			// String decryptedString = decrypt(encryptedString, privateKey);
			String decryptedString = decrypt(
					"xlEJBxC/g1nP75EuuQL6AhhGl6uecP9Tu1OMZfWnwNqtloIdn+R5TEPaNtFs+7M2l/k9FJBGbY/5y8YIBEs8SouvdcEQIPS+P+qnHg7Yk3x0z9Jij1o42QCVpOGafbNFWBRno55p0ol/p1/BcRRj56g9+W1cPt072AMybtbDULw=",
					privateKey
			);
			System.out.println(decryptedString);
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
		}
	}

}
