package dto;

public class DetailCodeDto {

	private String categoryCd;
	private String detailcd;
	private String cdTitle;
	private char delYn;
	
	
	public DetailCodeDto() {
	}


	public DetailCodeDto(String categoryCd, String detailcd, String cdTitle, char delYn) {
		super();
		this.categoryCd = categoryCd;
		this.detailcd = detailcd;
		this.cdTitle = cdTitle;
		this.delYn = delYn;
	}


	public String getCategoryCd() {
		return categoryCd;
	}


	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}


	public String getDetailcd() {
		return detailcd;
	}


	public void setDetailcd(String detailcd) {
		this.detailcd = detailcd;
	}


	public String getCdTitle() {
		return cdTitle;
	}


	public void setCdTitle(String cdTitle) {
		this.cdTitle = cdTitle;
	}


	public char getDelYn() {
		return delYn;
	}


	public void setDelYn(char delYn) {
		this.delYn = delYn;
	}


	@Override
	public String toString() {
		return "DetailCodeDto [categoryCd=" + categoryCd + ", detailcd=" + detailcd + ", cdTitle=" + cdTitle
				+ ", delYn=" + delYn + "]";
	}
	
	
	
	
}
