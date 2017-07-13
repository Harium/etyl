package com.harium.etyl.util.checksum;

public enum ChecksumAlgorithm {
	
	MD2("MD2"),
	MD5("MD5"),	
	SHA1("SHA1");
	
	private final String code;

	ChecksumAlgorithm(String code){
		this.code = code;
	}
	
	public final String getCode(){ 
		return code; 
	}
	
}
