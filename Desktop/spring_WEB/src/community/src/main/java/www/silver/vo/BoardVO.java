package www.silver.vo;

import java.util.List;

public class BoardVO {
	private Long postNum; // �� ��ȣ
	private String writer; // �ۼ���
	private String title; // �� ����
	private String content; // �� ����
	private java.sql.Timestamp createdAt; // �� �ۼ��Ͻ�
	private int price; // ����
	private String saleStatus; // �Ǹ� ����
	private List<String> filename; // ÷������ �̸�
	
	
	public Long getPostNum() {
		return postNum;
	}
	public void setPostNum(Long postNum) {
		this.postNum = postNum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public java.sql.Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.sql.Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	public List<String> getFilename() {
		return filename;
	}
	public void setFilename(List<String> filename) {
		this.filename = filename;
	}
	
	

}
