package net.xway.base.database.datasource;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.dbcp2.BasicDataSource;

import net.xway.base.utils.CipherUtils;


public class SimpleDbcpDataSourceProxy extends BasicDataSource {
	
	private Logger logger = LogManager.getLogManager().getLogger(SimpleDbcpDataSourceProxy.class.getName());
	private boolean encrypt = true;
	
	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	public void setPassword(String password) {
		if (encrypt == true && password != null) {
			try {
				password = CipherUtils.DecryptWithDES(password) ;
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException| BadPaddingException | IOException e) {
				throw new java.lang.RuntimeException(e);
			}
		}
		super.setPassword(password);		
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return logger;
	}


}
