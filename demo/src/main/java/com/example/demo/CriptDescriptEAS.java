package com.example.demo;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CriptDescriptEAS {

	private Cipher cipher;

	public String encrypt(String valor) {
		try {
			setaChipher(Cipher.ENCRYPT_MODE);
			return Base64.encodeBase64String(cipher.doFinal(valor.getBytes()));
		} catch (Exception e) {
			return valor;
		}
	}

	public String decrypt(String valor) {
		try {
			setaChipher(Cipher.DECRYPT_MODE);
			return new String(cipher.doFinal(Base64.decodeBase64(valor)));
		} catch (Exception e) {
			return valor;
		}
	}

	private void setaChipher(int comando) throws Exception {
		String initVector = "ZW5jcnlwdGlvbklu";
		String key = "H4DyVzcJqATH8tJwCFzv8hhO896ThyKL";
		String protocol = "AES/CBC/PKCS5PADDING"; // PKCS5PADDING
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		cipher = Cipher.getInstance(protocol);
		cipher.init(comando, skeySpec, iv);
	}

}