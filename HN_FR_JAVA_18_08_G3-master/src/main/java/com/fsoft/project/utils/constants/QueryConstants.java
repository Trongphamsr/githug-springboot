/**
 * 
 */
package com.fsoft.project.utils.constants;

/**
 * @author hungcoutinho
 *
 */
public class QueryConstants {

	public static final String SELECT_NEW_PRODUCT = "SELECT TOP(?) P.Id AS 'ProductId',P.ProductName,M.Id AS 'ManuFacturerId',M.Name AS 'ManuFacturerName',M.Description AS 'ManuFacturerDescription', C.Id AS 'CategoryId', C.Name AS 'CategoryName',P.CreateDate,P.Color,P.Price,P.Description AS 'ProductDescription',P.ImagePath FROM PRODUCT P INNER JOIN MANUFACTURER M ON P.ManuFacturerId = M.Id INNER JOIN CATEGORY C ON P.CategoryId = C.Id ORDER BY P.CreateDate DESC";

	public static final String SELECT_PRODUCT = "SELECT TOP(?) P.Id AS 'ProductId',P.ProductName,M.Id AS 'ManuFacturerId',M.Name AS 'ManuFacturerName',M.Description AS 'ManuFacturerDescription', C.Id AS 'CategoryId', C.Name AS 'CategoryName',P.CreateDate,P.Color,P.Price,P.Description AS 'ProductDescription',P.ImagePath FROM PRODUCT P INNER JOIN MANUFACTURER M ON P.ManuFacturerId = M.Id AND P.Id > ? INNER JOIN CATEGORY C ON P.CategoryId = C.Id";

	public static final String SELECT_PRODUCT_BY_ID = "SELECT P.Id AS 'ProductId',P.ProductName,M.Id AS 'ManuFacturerId',M.Name AS 'ManuFacturerName',M.Description AS 'ManuFacturerDescription', C.Id AS 'CategoryId', C.Name AS 'CategoryName',P.CreateDate,P.Color,P.Price,P.Description AS 'ProductDescription',P.ImagePath FROM PRODUCT P INNER JOIN MANUFACTURER M ON P.ManuFacturerId = M.Id AND P.Id = ? INNER JOIN CATEGORY C ON P.CategoryId = C.Id";

	public static final String SEARCH_PRODUCT = "SELECT P.Id AS 'ProductId',P.ProductName,M.Id AS 'ManuFacturerId',M.Name AS 'ManuFacturerName',M.Description AS 'ManuFacturerDescription', C.Id AS 'CategoryId', C.Name AS 'CategoryName',P.CreateDate,P.Color,P.Price,P.Description AS 'ProductDescription',P.ImagePath"
			+ " FROM PRODUCT P INNER JOIN CATEGORY C ON P.CategoryId = C.Id INNER JOIN MANUFACTURER M ON P.ManuFacturerId = M.Id"
			+ " WHERE P.ProductName LIKE ? OR C.Name LIKE ? OR M.Name LIKE ?";

	public static final String SELECT_PRODUCT_RELATED = "{CALL GET_LIST_RELATE_PRODUCT(?,?,?,?)}";

	public static final String SEARCH_PRODUCT_BY_VALUE = "SELECT P.Id AS 'ProductId',P.ProductName,M.Id AS 'ManuFacturerId',M.Name AS 'ManuFacturerName',M.Description AS 'ManuFacturerDescription', C.Id AS 'CategoryId', C.Name AS 'CategoryName',P.CreateDate,P.Color,P.Price,P.Description AS 'ProductDescription',P.ImagePath"
			+ " FROM PRODUCT P INNER JOIN CATEGORY C ON P.CategoryId = C.Id INNER JOIN MANUFACTURER M ON P.ManuFacturerId = M.Id ";

	public static final String SELECT_TOTAL_PRODUCT_SELECT_BY_VALUE = "SELECT COUNT(*) AS 'Number' FROM PRODUCT P INNER JOIN CATEGORY C ON P.CategoryId = C.Id ";

	public static final String SELECT_TOTAL_PRODUCT = "SELECT COUNT(*) AS 'Number' FROM PRODUCT";

	public static final String SELECT_CATEGORY_BY_ID = "SELECT Id,Name FROM CATEGORY WHERE Id = ?";

	public static final String SELECT_CATEGORY = "SELECT Id,Name FROM CATEGORY";

	public static final String SELECT_MANUFACTURER_BY_ID = "SELECT Id,Name,Description FROM MANUFACTURER WHERE Id = ?";

	public static final String GET_MEMBER = "SELECT * FROM MEMBER WHERE Email = ? AND Password = ?";

	public static final String CURRENT_ORDER = " SELECT IDENT_CURRENT ('ORDER_PRODUCT') AS Current_Identity";

	public static final String SELECT_MANUFACTURER = "SELECT Id,Name,Description FROM MANUFACTURER";

	public static final String SELECT_IMAGE_BY_PRODUCT = "SELECT I.Id AS 'ImageId', I.ImagePath AS 'ImageDetailPath',P.Id AS 'ProductId',P.ProductName,M.Id AS 'ManuFacturerId',M.Name AS 'ManuFacturerName',M.[Description] AS 'ManuFacturerDescription',C.Id AS 'CategoryId',C.Name AS 'CategoryName',P.CreateDate,P.Color,P.Price,P.[Description] AS 'ProductDescription',P.ImagePath FROM IMAGEDETAIL I INNER JOIN PRODUCT P ON I.ProductId = P.Id AND I.ProductId = ? INNER JOIN MANUFACTURER M ON P.ManuFacturerId = M.Id INNER JOIN CATEGORY C ON P.CategoryId = C.Id";

	public static final String SELECT_IMAGE_BY_ID = "SELECT I.Id AS 'ImageId', I.ImagePath AS 'ImageDetailPath',P.Id AS 'ProductId',P.ProductName,M.Id AS 'ManuFacturerId',M.Name AS 'ManuFacturerName',M.[Description] AS 'ManuFacturerDescription',C.Id AS 'CategoryId',C.Name AS 'CategoryName',P.CreateDate,P.Color,P.Price,P.[Description] AS 'ProductDescription',P.ImagePath FROM IMAGEDETAIL I INNER JOIN PRODUCT P ON I.ProductId = P.Id AND I.Id = ? INNER JOIN MANUFACTURER M ON P.ManuFacturerId = M.Id INNER JOIN CATEGORY C ON P.CategoryId = C.Id";

	public static final String ADD_LINEITEM = "INSERT INTO LINEITEM(OrderID, ProductID,NumberProduct) VALUES(?,?,?)";

	public static final String ADD_ORDER = "INSERT INTO ORDER_PRODUCT(TotalPrice, MemberId, CreateDate) VALUES(?,?,?)";

	public static final String UPDATE_MEMBER = "UPDATE Member SET FirstName = ?, LastName = ?, Address = ?, Phone = ?, Email =?, Password = ? WHERE Id= ?";

	/**
	 * @author trong register member
	 */
	public static final String REGISTER_MEMBER = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?)";

	/**
	 * category
	 */
	public static final String ADD_CATEGORY = "INSERT INTO CATEGORY VALUES(?)";

	public static final String ALL_CATEGORY = "SELECT Name FROM CATEGORY";

	public static final String UPDATE_CATEGORY = "UPDATE CATEGORY SET Name=? WHERE Name=?";
	public static final String DELETE_CATEGORY = "DELETE FROM CATEGORY WHERE Name=?";

	public static final String ID_CATEGORY = "SELECT * FROM CATEGORY";

	public static final String ID_MANUFACTURER = "SELECT * FROM MANUFACTURER";

	public static final String ADD_MANUFACTURER = "INSERT INTO MANUFACTURER VALUES(?)";

	public static final String ALL_MANUFACTURER = "SELECT Name FROM MANUFACTURER";

	public static final String UPDATE_MANUFACTURER = "UPDATE MANUFACTURER SET Name=? WHERE Name=?";
	
	public static final String DELETE_MANUFACTURER = "DELETE FROM MANUFACTURER WHERE Name=?";
	                                                                                      
	public static final String ADD_PRODUCT = "INSERT INTO PRODUCT(ProductName, ImagePath, ManuFacturerId, CategoryId, CreateDate, Color, Price,Description) VALUES(?,?,?,?,?,?,?,?)";
	
	public static final String UPDATE_PRODUCT = "UPDATE PRODUCT SET  ProductName =?, ImagePath=? , ManuFacturerId=? , CategoryId=?,CreateDate=?,  Color=?, Price=? , Description=? WHERE Id=?";
	
	public static final String DELETE_PRODUCT = "DELETE FROM PRODUCT WHERE Id=?";

	public static final String PRE_SELECT_UPDATE_PRODUCT = "SELECT * FROM PRODUCT WHERE Id=?";

	public static final String ALL_PRODUCT = "SELECT Id,ProductName,ImagePath,ManuFacturerId,CategoryId,CreateDate,Color,Price FROM PRODUCT";

	public static final String GET_ID_DETAIL = "INSERT INTO IMAGEDETAIL VALUES(?,?)";

	public static final String ADD = "INSERT INTO upload VALUES(?)";

	public static final String CHECK_EMAIL_EXISTS = "SELECT* FROM MEMBER WHERE Email=?";
	
	public static final String GET_ID_CATEGORY = "SELECT Id FROM CATEGORY WHERE Name=?";
	
	public static final String GET_ID_MANUFACTURER = "SELECT Id FROM MANUFACTURER WHERE Name=?";
}
