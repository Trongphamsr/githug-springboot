package com.trungtamjava.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.trungtamjava.model.OrderDTO;
import com.trungtamjava.model.OrderItemDTO;
import com.trungtamjava.model.ProductDTO;
import com.trungtamjava.service.ProductService;

@Controller
public class OrderController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value=("/them-gio-hang/{productId}"), method=RequestMethod.GET)
	public String addToCart(HttpServletRequest request, @PathVariable(name="productId")int productId,
			HttpSession session){
		ProductDTO productDTO = productService.getProductById(productId);
		if(session.getAttribute("cart")==null) {
			OrderDTO orderDTO = new OrderDTO();
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			orderItemDTO.setNumber(1);
			orderItemDTO.setProductDTO(productDTO);
			List<OrderItemDTO> orderItemDTOs = new ArrayList<OrderItemDTO>();
			orderItemDTOs.add(orderItemDTO);
			orderDTO.setItemDTOs(orderItemDTOs);
			session.setAttribute("cart", orderDTO);
			request.setAttribute("order", orderDTO);
		}
		else {
			OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
			List<OrderItemDTO> orderItemDTOs = orderDTO.getItemDTOs();
			boolean flag = false;
			for(OrderItemDTO orderItemDTO : orderItemDTOs) {
				if(orderItemDTO.getProductDTO().getId()== productDTO.getId()) {
					orderItemDTO.setNumber(orderItemDTO.getNumber()+1);
					flag = true;
				}
			}
			if(!flag) {
				// neu trong gio hang chua co san pham nay
				OrderItemDTO orderItemDTO = new OrderItemDTO();
				orderItemDTO.setNumber(1);
				orderItemDTO.setProductDTO(productDTO);
				orderItemDTOs.add(orderItemDTO);
			}
			session.setAttribute( "cart", orderDTO);
			request.setAttribute("order", orderDTO);
		}
		return "cart";
	}
	
	@RequestMapping(value=("/xem-gio-hang"), method=RequestMethod.GET)
	public String viewCart(HttpServletRequest request, HttpSession session){
		if(session.getAttribute("cart")!=null) {
			OrderDTO orderDTO =(OrderDTO) session.getAttribute("cart");
			request.setAttribute("order", orderDTO);
		}
	return "cart";
	}
	
	
	// xoa gio hang
	
	@RequestMapping(value=("/xoa-gio-hang/{productId}"), method=RequestMethod.GET)
	public String deleteCart(HttpServletRequest request, HttpSession session,
			@PathVariable(name="productId") int productId){
		
		if(session.getAttribute("cart")!=null) {
			OrderDTO orderDTO =(OrderDTO) session.getAttribute("cart");
			List<OrderItemDTO> orderItemDTOs = orderDTO.getItemDTOs();
			Iterator<OrderItemDTO> iterator = orderItemDTOs.iterator();
			while(iterator.hasNext()) {
				if(iterator.next().getProductDTO().getId()==productId) {
					iterator.remove();
				}
			}
			if(orderItemDTOs.isEmpty()) {
				session.removeAttribute("cart");
			}
		}
	return "cart";
	}
}
