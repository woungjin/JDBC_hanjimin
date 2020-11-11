package com.myweb.util;

public class PageVO {

	// ȭ�鿡 �׷��� pageNation�� ����ϴ� Ŭ����(pageNum�� ��ü �Խñ� ��, amount�� ������ �ٴѴ�)
	private int startPage; // �Խñ� ȭ�鿡 ������ ù��° ��ȣ
	private int endPage; // �Խñ� ȭ�鿡 ������ ������ ��ȣ
	private boolean prev, next; // ����, ���� ��ư Ȱ��ȭ ����

	private int pageNum = 1; // ��ȸ�ϰ� �ִ� ������ ��ȣ
	private int amount = 10; // ȭ�鿡 ������ �������� ����
	private int total; // ��ü �Խñ� ��

	// ������
	public PageVO(int pageNum, int amount, int total) { // ������ �� �����ڷ� �����ؾ��Ѵ�
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

	// getter setter ���� 
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
