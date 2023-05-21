package com.team_h.wishbook.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItem implements Serializable{
	private static final long serialVersionUID = 1L;		
	
	private int cartId;
	private int bookId;
	private String title;
	private int quantity;
	private int price;
	private String imageUrl; // 사진
	
	// 테스트용 임시 생성자
	public CartItem() {
		
	}
	
	public CartItem(int cartId, int bookId, String title, int quantity, int price, String imageUrl) {
		super();
		this.cartId = cartId;
		this.bookId = bookId;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
		this.imageUrl = imageUrl;
	}
}
