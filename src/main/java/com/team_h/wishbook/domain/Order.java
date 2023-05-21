package com.team_h.wishbook.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Order {
	private int orderId;
	
	private String userId; // 구매자 id
	private String orderDate; // 구매 날짜
	private String userTel; // 전화번호
	private String address; // 배송지 주소
	private String cardType; // 카드사
	private String cardNum; // 카드번호
	
	private int bookId;
	private String title;
	private int quantity;
	private int price; // 수량까지 계산한 총 금액(배달비 포함)
	private String imageUrl;
	
	// lineItem은 아예 삭제하고 한 주문에는 하나의 아이템만 들어가도록 수정
}