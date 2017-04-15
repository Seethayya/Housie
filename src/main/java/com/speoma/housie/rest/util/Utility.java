package com.speoma.housie.rest.util;
/**
 * 
 */

import org.apache.commons.lang3.StringUtils;

import com.speoma.housie.rest.model.Customer;

/**
 * @author Seethayya
 *
 */
public class Utility {

	/**
	 * @param customers
	 * @param response
	 * @param customer
	 * @return
	 */
	public static String getFullName(Customer customer) {
		StringBuffer name = new StringBuffer(StringUtils.defaultString(customer.getFirstName()));
		name.append(" ");
		if (StringUtils.isNotBlank(customer.getMiddleName())) {
			name.append(customer.getMiddleName()).append(" ");
		}
		name.append(StringUtils.defaultString(customer.getLastName()));
		return name.toString().toUpperCase();
	}
}
