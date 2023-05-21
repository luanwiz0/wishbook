package com.team_h.wishbook.controller;

import java.util.Arrays;
import java.util.List;

import com.team_h.wishbook.domain.CartItem;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderInfo {
	private List<CartItem> itemList;
	private List<String> cardTypes = Arrays.asList("aaa", "bbb", "ccc");
	
	private String userTel; // 전화번호
	private String address; // 배송지 주소
	private String cardType; // 카드사
	private String cardNum; // 카드번호
}
