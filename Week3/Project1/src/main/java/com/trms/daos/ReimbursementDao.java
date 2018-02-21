package com.trms.daos;

import java.io.File;
import java.util.List;

import com.trms.beans.Reimbursement;

public interface ReimbursementDao {
	public int insertReimbursement(Reimbursement r);
	public int insertAttachment(File f, int r_id);
	public int getReimburseByEmpId(int empId);
	public List<Reimbursement> getPersonalReimb(int empId);
	public List<Reimbursement> getReimburse(int empId);
	public int getNumberAttachments(int rId);
	public int getEmpIdByReimburse(int rId);
	public int updateApproved(int rId, int response);
	public int setApproveId(int rId, int empId);
	public int setApproveLvl(int rId, int newLvl);
}