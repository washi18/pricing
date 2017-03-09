package com.pricing.MasterDiners;

import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Signature {
	private static final String HMAC_SHA1_ALGORITHM="HmacSHA1";
	public static String calculateRFC2104HMAC(String data,String key) throws SignatureException
	{
		String result;
		try
		{
			SecretKeySpec signingkey=new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
			Mac mac=Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingkey);
			byte[] rawHmac=mac.doFinal(data.getBytes());
			result=Encoding.EncodingBase64(rawHmac);
		}catch(Exception e)
		{
			throw new SignatureException("Failed to generate HMAC: "+e.getMessage());
		}
		return result;
	}
}
 