package bean;

import java.sql.Date;

public class Reimbursement {
	
	private String username;
	private String empcomment;
	private Date submitDate;
	private Date startDate;
	private int urgent;
	private int amount;
	private String supervisor;
	private String supcomment;
	private String dept;
	private String deptcomment;
	private String benco;
	private String bencocomment;
	private int id;
	private int rejected;
	private int event;
	private int superapp;
	private int deptapp;
	private int bencoapp;
	private int finalapp;

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmpcomment() {
		return empcomment;
	}
	public void setEmpcomment(String empcomment) {
		this.empcomment = empcomment;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getUrgent() {
		return urgent;
	}
	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getSupcomment() {
		return supcomment;
	}
	public void setSupcomment(String supcomment) {
		this.supcomment = supcomment;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDeptcomment() {
		return deptcomment;
	}
	public void setDeptcomment(String deptcomment) {
		this.deptcomment = deptcomment;
	}
	public String getBenco() {
		return benco;
	}
	public void setBenco(String benco) {
		this.benco = benco;
	}
	public String getBencocomment() {
		return bencocomment;
	}
	public void setBencocomment(String bencocomment) {
		this.bencocomment = bencocomment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRejected() {
		return rejected;
	}
	public void setRejected(int rejected) {
		this.rejected = rejected;
	}
	public int getEvent() {
		return event;
	}
	public void setEvent(int event) {
		this.event = event;
	}
	public int getSuperapp() {
		return superapp;
	}
	public void setSuperapp(int superapp) {
		this.superapp = superapp;
	}
	public int getDeptapp() {
		return deptapp;
	}
	public void setDeptapp(int deptapp) {
		this.deptapp = deptapp;
	}
	public int getBencoapp() {
		return bencoapp;
	}
	public void setBencoapp(int bencoapp) {
		this.bencoapp = bencoapp;
	}
	public int getFinalapp() {
		return finalapp;
	}
	public void setFinalapp(int finalapp) {
		this.finalapp = finalapp;
	}
	
	

	
}
