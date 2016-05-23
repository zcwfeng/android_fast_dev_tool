package com.zcwfeng.fastdev.secure.skb.rsa;

import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


public class RsaTools {
//	 public static final String KEY_ALGORITHM = "RSA";  

	private static final String ALGORITHM = "RSA";

//	private static PublicKey getPublicKeyFromX509(String algorithm,
//			String bysKey) throws NoSuchAlgorithmException, Exception {
//		byte[] decodedKey = Base64.decode(bysKey);
//		X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);
//
//		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
//		return keyFactory.generatePublic(x509);
//	}
//
//	public static String encrypt(String content, String key) {
//		try {
//			PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, key);
//
//			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//			cipher.init(Cipher.ENCRYPT_MODE, pubkey);
//
//			byte plaintext[] = content.getBytes("UTF-8");
//			byte[] output = cipher.doFinal(plaintext);
//
//			String s = new String(Base64.encode(output));
//
//			return s;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	public static String sign(String content, String privateKey) {
		String charset = "utf-8";
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decode(privateKey,Base64.DEFAULT));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(charset));

			byte[] signed = signature.sign();

			return Base64.encode(signed,Base64.DEFAULT).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean doCheck(String content, String sign, String publicKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decode(publicKey,Base64.DEFAULT);
			PublicKey pubKey = keyFactory
					.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes("utf-8"));

			boolean bverify = signature.verify(Base64.decode(sign,Base64.DEFAULT));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}



	/**
	 *
	 * skb 支持None/CBC
	* 解密
	* @param content 密文
	* @param private_key 商户私钥
	* @param input_charset 编码格式
	* @return 解密后的字符串
	*/
	public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        // 解密时把 Cipher.getInstance("RSA")
        // 改成 Cipher.getInstance("RSA/ECB/PKCS1Padding") 解决乱码问题
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, prikey);
        
        InputStream ins = new ByteArrayInputStream(Base64.decode(content,Base64.DEFAULT));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

		String descryptPass = new String(writer.toByteArray(), input_charset);
		Log.e("zcw",descryptPass);
		return descryptPass;
    }
	
	/**
	* 得到私钥
	* @param key 密钥字符串（经过base64编码）
	* @throws Exception
	*/
	public static PrivateKey getPrivateKey(String key) throws Exception {

		byte[] keyBytes;
		
		keyBytes = Base64.decode(key,Base64.DEFAULT);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		return privateKey;
	}
}