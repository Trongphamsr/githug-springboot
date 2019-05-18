/**
 * 
 */
package com.fsoft.project.action;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.fsoft.project.base.BaseAction;
import com.fsoft.project.dao.impl.CategoryDaoImpl;
import com.fsoft.project.dao.impl.ImageDetailDaoImpl;
import com.fsoft.project.dao.impl.ManuFacturerDaoImpl;
import com.fsoft.project.dao.impl.ProductDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ImageDetail;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.CategoryService;
import com.fsoft.project.service.ImageDetailService;
import com.fsoft.project.service.ManuFacturerService;
import com.fsoft.project.service.ProductService;
import com.fsoft.project.service.impl.CategoryServiceImpl;
import com.fsoft.project.service.impl.ImageDetailServiceImpl;
import com.fsoft.project.service.impl.ManuFacturerServiceImpl;
import com.fsoft.project.service.impl.ProductServiceImpl;
import com.fsoft.project.utils.constants.Constants;
import com.fsoft.project.utils.constants.WebConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

/**
 * @author hungcoutinho
 *
 */
public class ProductAction extends BaseAction implements Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5903240637301411070L;
	private int productId;
	private ProductService productService;
	private ImageDetailService imageDetailService;
	private ManuFacturerService manuFacturerService;
	private CategoryService categoryService;
	private Product product;
	private List<ImageDetail> listImageDetail;
	private List<Product> listRelateProduct;
	
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	private String productName;
	private String imagePath;
	private int manuFacturerId;
	private int categoryId;
	private String color;
	private int price;
	private int result;
	private String msg;
	private int id;
	private List<Product> listProduct;
	private boolean noData;
	private String description;
	private String manuFacturerName;
	private String categoryName;
	
	@Override
	public void prepare() throws Exception {
		productService = new ProductServiceImpl(new ProductDaoImpl());
		imageDetailService = new ImageDetailServiceImpl(new ImageDetailDaoImpl());
		manuFacturerService = new ManuFacturerServiceImpl(new ManuFacturerDaoImpl());
		categoryService = new CategoryServiceImpl(new CategoryDaoImpl());
	}

	public String execute() throws SQLException {
		if(productId == 0) productId = (int) getSession().get(Constants.PRODUCT);
		product = productService.getProductById(productId);
		listImageDetail = imageDetailService.getListImageDetailByProductId(productId);
		listRelateProduct = productService.getListProductRelated(product);
		getSession().put(Constants.PRODUCT, productId);
		getSession().put(Constants.PAGE_INDEX, Constants.PRODUCT_DETAIL);
		return Action.SUCCESS;
	}
	
	public String productDetail() throws SQLException {
		Object object = getSession().get(Constants.PRODUCT);
		if(object != null) {
			int productId = (int) object;
			product = productService.getProductById(productId);
			listImageDetail = imageDetailService.getListImageDetailByProductId(productId);
			listRelateProduct = productService.getListProductRelated(product);
			return Action.SUCCESS;
		}
		return Action.SUCCESS;
	}
	public String addProduct() throws SQLException {
		Product product = new Product();
		ManuFacturer manuFacturer = new ManuFacturer();
		Category category = new Category();
		System.out.println(categoryService.getCategoryId(categoryName));
		System.out.println(manuFacturerService.getManufacturerId(manuFacturerName));
		category.setId(categoryService.getCategoryId(categoryName));
		manuFacturer.setId(manuFacturerService.getManufacturerId(manuFacturerName));
		product.setProductName(productName);
		product.setManuFacturer(manuFacturer);
		product.setCategory(category);
		product.setColor(color);
		product.setPrice(price);
		product.setDescription(description);
		product.setImagePath(myFileFileName);
		result = productService.addProduct(product,myFile);
		if (result > 0) {
			msg = "success";
		} else {
			msg = "false";
		}
		return WebConstants.ADD_PRODUCT;
	}

	public String allProduct() throws SQLException {
		listProduct = productService.allProduct();
		if (listProduct.size() == 0) {
			noData = false;
		} else {
			noData = true;
		}
		return WebConstants.ALL_PRODUCT;
	}

	public String fetchProduct() throws SQLException {
		product =  productService.fetchProduct(id);
		return WebConstants.UPDATE_PRODUCT;
	}


	public String updateProduct() throws Exception {
		ManuFacturer manuFacturer = new ManuFacturer();
		Category category = new Category();
		String idStr = ServletActionContext.getRequest().getParameter("id");
		Product product = new Product();
		if (idStr != null) {
			product = productService.getProductById(id);
			product.setImagePath(myFileFileName);
		}
		manuFacturer.setId(manuFacturerId);
		category.setId(categoryId);
		product.setProductName(productName);
		product.setManuFacturer(manuFacturer);
		product.setCategory(category);
		product.setColor(color);
		product.setPrice(price);
		product.setDescription(description);
		product.setId(id);
		result=productService.updateProduct(product,myFile);
		System.out.println("result: "+result);
		if (result > 0) {
			msg = "success";
		} else {
			msg = "false";
		}
		return WebConstants.UPDATE_PRODUCT;
	}


	public String deleteProduct() throws SQLException, Exception {
		result = productService.deleteProduct(id);
		if (result > 0) {
			msg = "Record deleted successfully";
		} else {
			msg = "Some error";
		}
		return WebConstants.DELETE_PRODUCT;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ImageDetail> getListImageDetail() {
		return listImageDetail;
	}

	public void setListImageDetail(List<ImageDetail> listImageDetail) {
		this.listImageDetail = listImageDetail;
	}

	public List<Product> getListRelateProduct() {
		return listRelateProduct;
	}

	public void setListRelateProduct(List<Product> listRelateProduct) {
		this.listRelateProduct = listRelateProduct;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getManuFacturerId() {
		return manuFacturerId;
	}

	public void setManuFacturerId(int manuFacturerId) {
		this.manuFacturerId = manuFacturerId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public boolean isNoData() {
		return noData;
	}

	public void setNoData(boolean noData) {
		this.noData = noData;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManuFacturerName() {
		return manuFacturerName;
	}

	public void setManuFacturerName(String manuFacturerName) {
		this.manuFacturerName = manuFacturerName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
