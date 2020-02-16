package com.revature.banking.services;

import org.jasypt.util.text.StrongTextEncryptor;

public abstract class EncryptedService {
	private final StrongTextEncryptor encryptor = new StrongTextEncryptor();

	protected EncryptedService() {
		super();
	}

	protected EncryptedService(final String dbKey) {
		super();
		this.setKey(dbKey);
	}

	public void setKey(final String dbKey) {
		encryptor.setPassword(dbKey);
	}

	protected String decrypt(final String encrypted) {
		return encryptor.decrypt(encrypted);
	}

	protected String encrypt(final String message) {
		return encryptor.encrypt(message);
	}
}
