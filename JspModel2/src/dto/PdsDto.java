package dto;

import java.io.Serializable;

public class PdsDto implements Serializable {
	
	/* 자료실 */

	private int seq;
	private String id;
	private String title;
	private String content;
	private String filename;		//abc.txt
	private String newfilename; 	//333424t.xt
	private int readcount;
	private int downcount;
	private String regdate;
	
	
	public PdsDto() {
		
	}
	
	

	// 사용자에게 입력받는 (외부)에서 받는 생성자
	public PdsDto(String id, String title, String content, String filename, String newfilename) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
	}




	public PdsDto(int seq, String id, String title, String content, String filename, String newfilename, int readcount,
			int downcount, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
		this.readcount = readcount;
		this.downcount = downcount;
		this.regdate = regdate;
	}
	
	
	public int getSeq() {
		return seq;
	}



	public void setSeq(int seq) {
		this.seq = seq;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public String getNewfilename() {
		return newfilename;
	}



	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}



	public int getReadcount() {
		return readcount;
	}



	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}



	public int getDowncount() {
		return downcount;
	}



	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}



	public String getRegdate() {
		return regdate;
	}



	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}




	@Override
	public String toString() {
		return "PdsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", filename="
				+ filename + ", newfilename=" + newfilename + ", readcount=" + readcount + ", downcount=" + downcount
				+ ", regdate=" + regdate + "]";
	}


	// 파일명 중복되는 경우 덮어씌워짐ㅁ
	// 시스템에서 중복검사 해줌
	// 중복인 경우 파일명 ㅂ변경해서 올려줌
	// SEQ 사용 ? -> New Date()
	// 업로드할떄는 newfilename으로 바꾸고
	// 다운로드받을때는 사용자가 적은 원본 filename다운로드
	
	
}

