package com.harium.etyl.util.checksum;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checksum {

	public String getFileCheckSum(String filepath, ChecksumAlgorithm algorithm) throws NoSuchAlgorithmException, IOException{

		MessageDigest md = MessageDigest.getInstance(algorithm.getCode());

		FileInputStream fis = new FileInputStream(filepath);

		byte[] dataBytes = new byte[1024];

		int nread = 0; 

		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		};

		fis.close();

		byte[] mdbytes = md.digest();

		//convert the byte to hex format
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	public String getCheckSum(String str, ChecksumAlgorithm algorithm){

		String hashedPass = "";

		try {

			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(str.getBytes(Charset.forName("UTF8")), 0, str.length());
			hashedPass = new BigInteger(1, messageDigest.digest()).toString(16);
			if (hashedPass.length() < 32) {
				hashedPass = "0" + hashedPass;
			}

		} catch (Exception e) {
			System.out.println("failed to create md5");
			System.out.println(e.getMessage());
		}

		return hashedPass;
	}

}
