package com.fsoft.project.security;

import java.security.MessageDigest;

import com.fsoft.project.utils.LogUtils;

public class Sha1Security {

	public static String SHA1(String input) {
		StringBuffer sb = null;
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest(input.getBytes());
			sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i]&0xff)+0x100,16).substring(1));
			}
		} catch (Exception e) {
			LogUtils.getLogger(Sha1Security.class.getName()).error(e.getMessage());
		}
		return sb.toString();
	}

}
