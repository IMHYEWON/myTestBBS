package dto;

public class CategoryDto {
	private String categoryCd;
	private String cdTitle;
	private char delYn;
	
	public CategoryDto() {}
	
	public CategoryDto(String categoryCd, String cdTitle, char delYn) {
		super();
		this.categoryCd = categoryCd;
		this.cdTitle = cdTitle;
		this.delYn = delYn;
	}
	
	public String getCategoryCd() {
		return categoryCd;
	}
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
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
		return "CategoryDto [categoryCd=" + categoryCd + ", cdTitle=" + cdTitle + ", delYn=" + delYn + "]";
	}
	
	
	
}
