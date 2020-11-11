package com.myweb.util;

public class PageVO {

	// 화면에 그려질 pageNation을 계산하는 클래스(pageNum와 전체 게시글 수, amount를 가지고 다닌다)
	private int startPage; // 게시글 화면에 보여질 첫번째 번호
	private int endPage; // 게시글 화면에 보여줄 마지막 번호
	private boolean prev, next; // 다음, 이전 버튼 활성화 여부

	private int pageNum = 1; // 조회하고 있는 페이지 번호
	private int amount = 10; // 화면에 보여질 데이터의 개수
	private int total; // 전체 게시글 수

	// 생성자
	public PageVO(int pageNum, int amount, int total) { // 무조건 이 생성자로 생성해야한다
		this.pageNum = pageNum;
		this.amount = amount;
		this.total = total;

		this.endPage = (int) Math.ceil(this.pageNum / (double) 10) * 10;

		this.startPage = this.endPage - 10 + 1;

		int realEnd = (int) Math.ceil(this.total / (double) this.amount);

		if (endPage > realEnd) {
			this.endPage = realEnd;

		}

		this.prev = this.startPage > 1;

		this.next = realEnd > endPage;

	}

	// getter setter 생성 
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
