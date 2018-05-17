package br.com.ecommerce.test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerce.controllers.ProductController;
import br.com.ecommerce.models.Cart;
import br.com.ecommerce.models.Product;
import br.com.ecommerce.services.CartService;
import br.com.ecommerce.services.ProductService;

public class EcommerceTest extends ApplicationBootTest {
	
    @InjectMocks
    ProductController controller;
    
	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;
    
    @Before
    public void initTests() throws SQLException {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
	
	@Test
	public void testCreateNewProduct() throws Exception {
		 Product p1 = mockProduct("testFindByIdProduct");
		 byte[] p1Json = toJson(p1);
		 
	     MvcResult result = mvc.perform(post("/product/")
	                .content(p1Json)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.name", is(p1.getName())))
	                .andReturn();
	}
	
	@Test
	public void testCreateNewCart() throws Exception {
		MvcResult result = mvc.perform(get("/cart/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
	}
	
	@Test
	public void testAddProductCart() throws Exception {
		Product p1 = mockProduct("testAddProductCart");
		
		Product newProduct = productService.createNewProduct(p1);
		long idP = newProduct.getId();
		
		Cart newCart = cartService.createNewCart();
		long idC = newCart.getId();
		
		MvcResult result = mvc.perform(get("/cart/"+idC+"/"+idP+"/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
	}
	
	@Test
	public void testRemoveProductCart() throws Exception {
		Product p1 = mockProduct("testRemoveProductCart");
		
		Product newProduct = productService.createNewProduct(p1);
		long idP = newProduct.getId();
		
		Cart newCart = cartService.createNewCart();
		long idC = newCart.getId();
		
		cartService.addProductCart(newCart, newProduct, 2);
		
		MvcResult result = mvc.perform(delete("/cart/"+idC+"/"+idP)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
	}
	
	@Test
	public void testUpdateQuantityProductCart() throws Exception {
		Product p1 = mockProduct("testRemoveProductCart");
		
		Product newProduct = productService.createNewProduct(p1);
		long idP = newProduct.getId();
		
		Cart newCart = cartService.createNewCart();
		long idC = newCart.getId();
		
		cartService.addProductCart(newCart, newProduct, 2);
		
		MvcResult result = mvc.perform(put("/cart/"+idC+"/"+idP+"/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
	}
	
	@Test
	public void testAmountCart() throws Exception {
		
		Product newProduct1 = productService.findById(1);
		
		Cart newCart = cartService.createNewCart();
		long idC = newCart.getId();
		
		cartService.addProductCart(newCart, newProduct1, 1);
		
		MvcResult result = mvc.perform(get("/cart/amount/"+idC)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(newCart.getAmount())))
                .andReturn();
		
	}
	
	private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }
	
    private Product mockProduct(String prefix) {
    	Product r = new Product();
    	r.setName(prefix + "_name"); 
    	r.setValue(2500);

        return r;
    }
    
}
