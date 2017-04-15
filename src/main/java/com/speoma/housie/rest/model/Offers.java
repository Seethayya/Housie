package com.speoma.housie.rest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ACCOUNT_DETAILS")
public class Offers extends BaseDto {

	private String offerCode;
	private String companyOrSite;
	private Long maxUsers;
	private Long used;
	private Date startDate;
	private Date expiryDate;
	/**
	 * @return the offerCode
	 */
	@Column(name="OFFER_CODE")
	public String getOfferCode() {
		return offerCode;
	}
	/**
	 * @param offerCode the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	
	/**
	 * @return the companyOrSite
	 */
	@Column(name="COMPANY_SITE")
	public String getCompanyOrSite() {
		return companyOrSite;
	}
	
	/**
	 * @param companyOrSite the companyOrSite to set
	 */
	public void setCompanyOrSite(String companyOrSite) {
		this.companyOrSite = companyOrSite;
	}
	
	/**
	 * @return the maxUsers
	 */
	@Column(name="MAX_USERS")
	public Long getMaxUsers() {
		return maxUsers;
	}
	/**
	 * @param maxUsers the maxUsers to set
	 */
	public void setMaxUsers(Long maxUsers) {
		this.maxUsers = maxUsers;
	}
	/**
	 * @return the used
	 */
	@Column(name="USED")
	public Long getUsed() {
		return used;
	}
	/**
	 * @param used the used to set
	 */
	public void setUsed(Long used) {
		this.used = used;
	}
	/**
	 * @return the startDate
	 */
	@Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the expiryDate
	 */
	@Column(name = "EXPIRY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}