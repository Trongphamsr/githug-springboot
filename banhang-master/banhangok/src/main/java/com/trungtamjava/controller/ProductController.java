package com.trungtamjava.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.trungtamjava.model.ProductDTO;
import com.trungtamjava.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;

	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value=("/danh-sach-san-pham"), method=RequestMethod.GET)
	public String getAllProduct(HttpServletRequest request){
		List<ProductDTO> products = productService.getAllProducts();
		request.setAttribute("products", products);
		request.setAttribute("msg", messageSource.getMessage("product.name", null, null));
		return"listProducts";
		
	}
	// dg dan den trang them khach hang dung get
			@RequestMapping(value=("/them-san-pham"), method=RequestMethod.GET)
			public String addProduct(HttpServletRequest request){
				request.setAttribute("product", new ProductDTO());
				return "addProduct";
				
			}
			
			// them ng dung
			@RequestMapping(value="/them-san-pham", method=RequestMethod.POST)
			// @Valid canh User
			public String addProduct(HttpServletRequest request, 
					@ModelAttribute("product")  ProductDTO productDTO){
				
				MultipartFile file = productDTO.getFile();
				try {
					File newfile= new File("D:/upload/" + file.getOriginalFilename());
					FileOutputStream fileOutputStream;
					
						fileOutputStream = new FileOutputStream(newfile);
						fileOutputStream.write(file.getBytes());
						fileOutputStream.close();
						productDTO.setImageURL(file.getOriginalFilename());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				productService.addProduct(productDTO);
				return "redirect:/danh-sach-san-pham";
			}
		
		@RequestMapping(value=("/chi-tiet-san-pham/{productId}"), method=RequestMethod.GET)
		public String viewProduct(HttpServletRequest request, @PathVariable(name="productId") int productId){
			request.setAttribute("product", productService.getProductById(productId));
			return "viewProduct";	
		}
		
		// phuong thuc xoa
		
			@RequestMapping(value=("/xoa-san-pham/{productId}"), method=RequestMethod.GET)
			public String deleteProduct(HttpServletRequest request, @PathVariable(name="productId") int productId){
				productService.deleteProduct(productId);
				return "redirect:/danh-sach-san-pham";
				
			}
			
			// lay danh sach hien thi ra de sua khach hang, bang get
			
			@RequestMapping(value=("/sua-san-pham/{productId}"), method=RequestMethod.GET)
			public String editProduct(HttpServletRequest request, @PathVariable(name="productId") int productId){
				request.setAttribute("product", productService.getProductById(productId));
				return "editProduct";
			}
			
			// sau khi lay dk thong tin hien thi ra roi ta update lai vao csdl, bang pt post
			
			
			@RequestMapping(value="/sua-san-pham", method=RequestMethod.POST)
			// @Valid canh User
			public String editProduct(HttpServletRequest request, 
					@ModelAttribute("product")  ProductDTO product){
				productService.updateProduct(product);
				return "redirect:/danh-sach-san-pham";
			}
}
