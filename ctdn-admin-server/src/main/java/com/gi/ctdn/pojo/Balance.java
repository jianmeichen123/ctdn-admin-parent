package com.gi.ctdn.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
public class Balance
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="com_id")
	private Long companyId;
	@Column(name="com_name")
	private String companyName;
	@Column(name="subject_code")
	private String subjectCode;
	@Column(name="subject_name")
	private String subjectName;
	@Column(name = "subject_name_clear")
	private String subjectNameClear;
	@Column(name="currency")
	private String currency;
	@Column(name="Initial_debit_balance")
	private BigDecimal initialDebitBalance;
	@Column(name="Initial_credit_balance")
	private BigDecimal initialCreditBalance;
	@Column(name="current_debit_amount")
	private BigDecimal currentDebitAmount;
	@Column(name="current_credit_amount")
	private BigDecimal currentCreditAmount;
	@Column(name="debit_year_amount")
	private BigDecimal debitYearAmount;
	@Column(name="credit_year_amount")
	private BigDecimal creditYearAmount;
	@Column(name="final_debit_balance")
	private BigDecimal finalDebitBalance;
	@Column(name="final_credit_balance")
	private BigDecimal finalCreditBalance;
	@Column(name="year")
	private int year;
	@Column(name="month")
	private int month;
	@Transient
	private Long contrastCompanyId;
	@Transient
	private String contrastSubjectCode;
	@Transient
	private String contrastSubjectName;
	@Transient
	private String contrastSubjectNameClear;
	@Transient
	private BigDecimal contrastFinalDebitBalance;
	@Transient
	private BigDecimal contrastFinalCreditBalance;
	@Transient
	private  Long companyGroupId;
	@Transient
	private  boolean isExists = false;
	@Transient
	private  Integer checkBalanceType;
	@Transient
	private  String checkContrastSubjectCodeClear;
	@Transient
	private  String checkSubjectCodeClear;
	@Transient
	private  String checkAddCode;
	@Transient
	private  String checkComName;
	@Transient
	private  Integer checkType;


	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getCompanyId()
	{
		return companyId;
	}
	public void setCompanyId(Long companyId)
	{
		this.companyId = companyId;
	}
	public String getCompanyName()
	{
		return companyName;
	}
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	public String getSubjectCode()
	{
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode)
	{
		this.subjectCode = subjectCode;
	}
	public String getSubjectName()
	{
		return subjectName;
	}
	public void setSubjectName(String subjectName)
	{
		this.subjectName = subjectName;
	}
	public String getCurrency()
	{
		return currency;
	}
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	public BigDecimal getInitialDebitBalance()
	{
		return initialDebitBalance;
	}
	public void setInitialDebitBalance(BigDecimal initialDebitBalance)
	{
		this.initialDebitBalance = initialDebitBalance;
	}
	public BigDecimal getInitialCreditBalance()
	{
		return initialCreditBalance;
	}
	public void setInitialCreditBalance(BigDecimal initialCreditBalance)
	{
		this.initialCreditBalance = initialCreditBalance;
	}
	public BigDecimal getCurrentDebitAmount()
	{
		return currentDebitAmount;
	}
	public void setCurrentDebitAmount(BigDecimal currentDebitAmount)
	{
		this.currentDebitAmount = currentDebitAmount;
	}
	public BigDecimal getCurrentCreditAmount()
	{
		return currentCreditAmount;
	}
	public void setCurrentCreditAmount(BigDecimal currentCreditAmount)
	{
		this.currentCreditAmount = currentCreditAmount;
	}
	public BigDecimal getDebitYearAmount()
	{
		return debitYearAmount;
	}
	public void setDebitYearAmount(BigDecimal debitYearAmount)
	{
		this.debitYearAmount = debitYearAmount;
	}
	public BigDecimal getCreditYearAmount()
	{
		return creditYearAmount;
	}
	public void setCreditYearAmount(BigDecimal creditYearAmount)
	{
		this.creditYearAmount = creditYearAmount;
	}
	public BigDecimal getFinalDebitBalance()
	{
		return finalDebitBalance;
	}
	public void setFinalDebitBalance(BigDecimal finalDebitBalance)
	{
		this.finalDebitBalance = finalDebitBalance;
	}
	public BigDecimal getFinalCreditBalance()
	{
		return finalCreditBalance;
	}
	public void setFinalCreditBalance(BigDecimal finalCreditBalance)
	{
		this.finalCreditBalance = finalCreditBalance;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public int getMonth()
	{
		return month;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}


	public Long getContrastCompanyId() {
		return contrastCompanyId;
	}

	public void setContrastCompanyId(Long contrastCompanyId) {
		this.contrastCompanyId = contrastCompanyId;
	}

	public BigDecimal getContrastFinalDebitBalance() {
		return contrastFinalDebitBalance;
	}

	public void setContrastFinalDebitBalance(BigDecimal contrastFinalDebitBalance) {
		this.contrastFinalDebitBalance = contrastFinalDebitBalance;
	}

	public BigDecimal getContrastFinalCreditBalance() {
		return contrastFinalCreditBalance;
	}

	public void setContrastFinalCreditBalance(BigDecimal contrastFinalCreditBalance) {
		this.contrastFinalCreditBalance = contrastFinalCreditBalance;
	}

	public String getContrastSubjectCode() {
		return contrastSubjectCode;
	}

	public void setContrastSubjectCode(String contrastSubjectCode) {
		this.contrastSubjectCode = contrastSubjectCode;
	}

	public String getContrastSubjectName() {
		return contrastSubjectName;
	}

	public void setContrastSubjectName(String contrastSubjectName) {
		this.contrastSubjectName = contrastSubjectName;
	}

	public Long getCompanyGroupId() {
		return companyGroupId;
	}

	public void setCompanyGroupId(Long companyGroupId) {
		this.companyGroupId = companyGroupId;
	}

	public String getSubjectNameClear() {
		return subjectNameClear;
	}

	public void setSubjectNameClear(String subjectNameClear) {
		this.subjectNameClear = subjectNameClear;
	}

	@Override
	public String toString() {
		return "Balance[com_id="+ this.getCompanyId() + ", com_name=" +  this.getCompanyName() + ", subject_code=" + this.getSubjectCode() +", subject_name=" + this.getSubjectName() +", debit=" + this.getFinalDebitBalance() +", credit=" +this.getFinalCreditBalance() +", constdebit=" + this.getContrastFinalDebitBalance() +", constcredit=" +this.getContrastFinalCreditBalance() + ", contrast_subject_name=" + this.getContrastSubjectName() + "]";
	}

	public boolean isExists() {
		return isExists;
	}

	public void setExists(boolean exists) {
		isExists = exists;
	}

	public Integer getCheckBalanceType() {
		return checkBalanceType;
	}

	public void setCheckBalanceType(Integer checkBalanceType) {
		this.checkBalanceType = checkBalanceType;
	}

	public String getCheckContrastSubjectCodeClear() {
		return checkContrastSubjectCodeClear;
	}

	public void setCheckContrastSubjectCodeClear(String checkContrastSubjectCodeClear) {
		this.checkContrastSubjectCodeClear = checkContrastSubjectCodeClear;
	}

	public String getCheckSubjectCodeClear() {
		return checkSubjectCodeClear;
	}

	public void setCheckSubjectCodeClear(String checkSubjectCodeClear) {
		this.checkSubjectCodeClear = checkSubjectCodeClear;
	}

	public String getCheckAddCode() {
		return checkAddCode;
	}

	public void setCheckAddCode(String checkAddCode) {
		this.checkAddCode = checkAddCode;
	}

	public String getCheckComName() {
		return checkComName;
	}

	public void setCheckComName(String checkComName) {
		this.checkComName = checkComName;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public String getContrastSubjectNameClear() {
		return contrastSubjectNameClear;
	}

	public void setContrastSubjectNameClear(String contrastSubjectNameClear) {
		this.contrastSubjectNameClear = contrastSubjectNameClear;
	}
}
