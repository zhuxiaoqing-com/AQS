package com.mdb.util;

import java.security.SecureRandom;

public final class Helper {
	private Helper() {
	}

	private static final SecureRandom random = new SecureRandom();

	public static byte[] makeRandValues(int bytes) {
		byte[] v = new byte[bytes];
		random.nextBytes(v);
		return v;
	}

	private static final String HEX_CHARS = "0123456789abcdef";

	public static String toHexString(byte[] b) {
		return toHexString(b, 0, b.length);
	}

	public static String toHexString(byte[] b, int off, int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = off; i < (off + len); i++) {
			sb.append(HEX_CHARS.charAt(b[i] >> 4 & 0xf));
			sb.append(HEX_CHARS.charAt(b[i] & 0xf));
		}
		return sb.toString();
	}

	public static byte[] fromHexString(String s) {
		int l = s.length() / 2;
		byte[] bs = new byte[l];
		for (int i = 0; i < l; i++)
			bs[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
		return bs;
	}

	public static String toFileNameString(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0, l = s.length(); i < l; i++) {
			char c = s.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
				sb.append(c);
			else if (((c >> 8) & 0xff) != 0) {
				sb.append('_');
				sb.append(HEX_CHARS.charAt(c >> 12 & 0xf));
				sb.append(HEX_CHARS.charAt(c >> 8 & 0xf));
				sb.append(HEX_CHARS.charAt(c >> 4 & 0xf));
				sb.append(HEX_CHARS.charAt(c & 0xf));
			} else {
				sb.append('-');
				sb.append(HEX_CHARS.charAt(c >> 4 & 0xf));
				sb.append(HEX_CHARS.charAt(c & 0xf));
			}
		}
		return sb.toString();
	}

	public static String fromFileNameString(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0, l = s.length(); i < l;) {
			char c = s.charAt(i++);
			if (c == '_')
				sb.append((char) Integer.parseInt(s.substring(i, i += 4), 16));
			else if (c == '-')
				sb.append((char) Integer.parseInt(s.substring(i, i += 2), 16));
			else
				sb.append(c);
		}
		return sb.toString();
	}
}
