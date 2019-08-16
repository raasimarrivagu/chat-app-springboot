package com.ot.ChatApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="keytable")
public class KeyTable {

	@Id
	@GeneratedValue
	@Column(name="keyid")
	@NotNull
	long keyId;
	
	@Column(name="keyname",unique=true)
	@NotNull
	String keyName;
	
	@Column(name="keyvalue")
	@NotNull
	String keyValue;

	
	public long getKeyId() {
		return keyId;
	}
	
	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	
	
}
