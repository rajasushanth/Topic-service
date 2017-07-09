package com.starkinc.stopic.util;

import org.springframework.security.crypto.encrypt.TextEncryptor;

/**
 * @author RajaSushanth
 *
 */
public class EncryptorUtil {

	private static TextEncryptor textEncryptor;

	public EncryptorUtil(TextEncryptor textEncryptor) {
		EncryptorUtil.textEncryptor = textEncryptor;
	}

	public static TextEncryptor getTextEncryptor() {
		return textEncryptor;
	}

}
