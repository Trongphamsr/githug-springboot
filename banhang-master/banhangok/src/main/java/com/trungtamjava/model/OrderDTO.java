package com.trungtamjava.model;

import java.util.List;

public class OrderDTO {

	private List<OrderItemDTO> itemDTOs;
	public List<OrderItemDTO> getItemDTOs(){
		return itemDTOs;
	}
	public void setItemDTOs(List<OrderItemDTO> itemDTOs){
		this.itemDTOs = itemDTOs;
	}
}
