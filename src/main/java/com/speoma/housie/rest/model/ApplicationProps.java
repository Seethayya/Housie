package com.speoma.housie.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "APPLICATION_PROPS")
public class ApplicationProps extends BaseDto {

	private String key;
	private String value;
	/**
	 * @return the key
	 */
	@Column(name="KEY")
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the value
	 */
	@Column(name="VALUE")
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}