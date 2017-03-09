package com.pricing.MasterDiners;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Encoder;
public class Encoding {
	public static String EncodingBase64(byte[] Data) throws UnsupportedEncodingException
	{
		return java.net.URLEncoder.encode(new BASE64Encoder().encode(Data),"UTF-8");
	}
}
