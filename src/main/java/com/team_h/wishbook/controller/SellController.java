package com.team_h.wishbook.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.team_h.wishbook.domain.CartItem;
import com.team_h.wishbook.domain.Order;

import jakarta.servlet.http.HttpServletRequest;

@Controller
// @SessionAttributes("memberId")  // 세션에 저장된 사용자 아이디 정보
@SessionAttributes("orderInfo")
public class SellController {
	
	// @AutoWired
	// private SellService sellService;
	
	// 시험용 임시 데이터
	private List<CartItem> itemList = new ArrayList<>(Arrays.asList(new CartItem(1, 1, "aaa", 1, 100, "aaa"), 
		new CartItem(2, 2, "bbb", 2, 100, "bbb"),  new CartItem(3, 3, "ccc", 3, 100, "ccc")));
	private int nextCartId = 4;
	private List<Order> orderList = new ArrayList<>();
	private int nextOrderId = 1;
	
	// --------------------------  장바구니  -------------------------------------
	
	// 장바구니 정보 불러오기
	@RequestMapping("/cart/list")
	public ModelAndView getCartList() {
		ModelAndView mav = new ModelAndView("cart");
		
		// 서비스 클래스로 넘겨줄 때 세션에 있는 아이디 정보 넘겨주기
		// List<CartItem> itemList = sellService.getCartItemList(memberId);
		
		// 리스트로 가져온 목록을 mav 객체에 넣기
		mav.addObject("itemList", itemList);
		
		// cart 관련 view 페이지들을 폴더에 넣어서 관리할 경우
		// mav.setViewName("/cart/cart");
		
		return mav;
	}
	
	
	// 장바구니 물품 추가(상품 상세정보 페이지에서 장바구니에 담기 버튼 클릭)
	@RequestMapping("/cart/add")
	public String addCartItems(@RequestParam("bookId") int itemId, @RequestParam("quantity") int quantity, @RequestParam("price") int price) {
		// 사용자 id(세션)/물품 id/개수를 받아와서 서비스 레이어로 전달
		// 서비스 레이어에서는 해당 정보를 db에 저장
		itemList.add(new CartItem(nextCartId, itemId, "title", quantity, quantity * price, "image"));
		nextCartId++;
		
		// 이후 상품 상세정보 페이지로 redirection
		// 테스트를 위해 임시로 장바구니 목록으로 이동
		return "redirect:/cart/list";
	}
	
	
	// 장바구니 상품 정보 수정
	@RequestMapping("/cart/update")
	public String updateCartItems(@RequestParam("cartItemId") int cartItemId, @RequestParam("quantity") int quantity) {
		// 들어온 정보를 토대로 정보 수정 - 서비스 레이어에 정보 넘겨줌
		// 여기에서는 임시로 컨트롤러에서 정보 수정하는 것으로
		CartItem item = itemList.get(cartItemId);
		item.setQuantity(quantity);
		itemList.set(cartItemId, item);
		
		// 이후 목록 페이지로 redirection
		return "redirect:/cart/list";
	}
	
	// 장바구니 상품 삭제
	@RequestMapping("/cart/delete")
	public String deleteCartItems(@RequestParam("cartItemId") int cartItemId) {
		// 아이디 정보 서비스 레이어에 넘겨줌
		itemList.remove(cartItemId);
		
		return "redirect:/cart/list";
	}
	
	// ---------------------------------  주문  --------------------------------
	
	
	// 결제 폼 초기화
	@ModelAttribute("orderInfo")
	public OrderInfo formBacking(HttpServletRequest request) {
		if (request.getMethod().equalsIgnoreCase("GET")) { // GET request일 경우
			OrderInfo orderInfo = new OrderInfo(); // command 객체 생성
			
			// 사용자 전화번호&주소 불러와서 배송지 주소 정보에 넣기
			// String userTel = sellService.getUserTel(memberId);
			// String address = sellService.getAddress(memberId);
			// orderInfo.setAddress(address);
			
			// 결제할 상품 목록 - 장바구니에서 가져오기
			// List<CartItem> itemList = sellService.getCartItemList(memberId);
			orderInfo.setItemList(itemList);
			orderInfo.setCardType("aaa");

			// 사용자 포인트 정보? 필요하면 추가하고 얼마나 쓸지 물어보는 거 form에다 추가
			
			return orderInfo;
		}
		else {
			return new OrderInfo();
		}
	}
	
	// 결제 페이지로 이동
	@GetMapping("/order/payment")
	public String payment() {
		return "/payment";
	}
	
	
	// 결제 진행
	@PostMapping("/order/add")
	public String addOrder(@ModelAttribute("orderInfo") OrderInfo orderInfo) {
		// 하나씩 돌면서 결제목록에 추가하기
		// sellService.addOrder(orderInfo.getItemList);
		
		for(CartItem item : orderInfo.getItemList()) {
			Order order = new Order();
			order.setOrderId(nextOrderId);
			// order.setUserId(memberId);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
			String strDate = dateFormat.format(Calendar.getInstance().getTime());
			order.setOrderDate(strDate); // 나중에 변경할수도... 일단은 이렇게만
			
			order.setUserTel(orderInfo.getUserTel());
			order.setAddress(orderInfo.getAddress());
			order.setCardType(orderInfo.getCardType());
			order.setCardNum(orderInfo.getCardNum());
			
			order.setBookId(item.getBookId());
			order.setTitle(item.getTitle());
			order.setQuantity(item.getQuantity());
			order.setPrice(item.getPrice());
			order.setImageUrl(item.getImageUrl());
			
			// 추가 주문사항 같은 거 필요하면 넣기
			
			orderList.add(order);
		}
		
		// 서비스 메소드에서 회원 등급에 따른 포인트 적립하는 메소드 추가
		
		// return "/main.jsp";
		return "redirect:/order/list";
	}
	
	// ------------------- 주문 확인 -----------------------------
	
	// 결제목록 확인
	@RequestMapping("/order/list")
	public ModelAndView getUserOrderList() {
		ModelAndView mav = new ModelAndView("userOrderList");
		// 서비스 클래스로 넘겨줄 때 세션에 있는 아이디 정보 넘겨주기
		// List<Order> orderList = sellService.getUserOrderList(memberId);
				
		// 리스트로 가져온 목록을 mav 객체에 넣기
		mav.addObject("orderList", orderList);
		
		return mav;
	}
	
	// 전체 회원 결제목록 확인
	@RequestMapping("/order/allList")
	public ModelAndView getAllOrderList() {
		ModelAndView mav = new ModelAndView("전체 회원 결제목록 확인 뷰 이름");
	
		// List<Order> orderList = sellService.getAllOrderList();
		// mav.addObject("orderList", orderList);
		
		return mav;
	}
	
	// 중고거래 판매목록 확인 - 이건 중고거래 컨트롤러에서 하는 게 더 낫지 않나
}
