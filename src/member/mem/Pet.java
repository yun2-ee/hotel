package member.mem;

import java.util.Date;

public class Pet {
	
	private double pno;
	private String id;
	private String pname;
	private Date pbirth;
	private double weight;
	private String neuter;
	private String medical;
	private String memo;
	private String img;
	
	
	


	public Pet(double pno, String id, String pname, Date pbirth, double weight, String neuter, String medical, String memo,
			String img) {
		super();
		this.pno = pno;
		this.id = id;
		this.pname = pname;
		this.pbirth = pbirth;
		this.weight = weight;
		this.neuter = neuter;
		this.medical = medical;
		this.memo = memo;
		this.img = img;
	}





	public double getPno() {
		return pno;
	}





	public void setPno(double pno) {
		this.pno = pno;
	}





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getPname() {
		return pname;
	}





	public void setPname(String pname) {
		this.pname = pname;
	}





	public Date getPbirth() {
		return pbirth;
	}





	public void setPbirth(Date pbirth) {
		this.pbirth = pbirth;
	}





	public double getWeight() {
		return weight;
	}





	public void setWeight(double weight) {
		this.weight = weight;
	}





	public String getNeuter() {
		return neuter;
	}





	public void setNeuter(String neuter) {
		this.neuter = neuter;
	}





	public String getMedical() {
		return medical;
	}





	public void setMedical(String medical) {
		this.medical = medical;
	}





	public String getMemo() {
		return memo;
	}





	public void setMemo(String memo) {
		this.memo = memo;
	}





	public String getImg() {
		return img;
	}





	public void setImg(String img) {
		this.img = img;
	}





	@Override
	public String toString() {
		return "Pet [pno=" + pno + ", id=" + id + ", pname=" + pname + ", pbirth=" + pbirth + ", weight=" + weight
				+ ", neuter=" + neuter + ", medical=" + medical + ", memo=" + memo + ", img=" + img + "]";
	}
	
	

}
