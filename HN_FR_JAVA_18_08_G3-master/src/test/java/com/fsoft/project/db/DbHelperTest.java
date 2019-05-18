/**
 * 
 */
package com.fsoft.project.db;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;

import com.opensymphony.xwork2.interceptor.annotations.After;

/**
 * @author hungcoutinho
 *
 */
public class DbHelperTest {

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetConnection() {
		Connection conn = DbHelper.getConnection();
		assertTrue(conn != null);
	}
}
