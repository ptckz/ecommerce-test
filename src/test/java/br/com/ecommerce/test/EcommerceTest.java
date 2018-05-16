package br.com.ecommerce.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import br.com.ecommerce.models.Product;

public class EcommerceTest extends ApplicationBootTest {
	
    @InjectMocks
    ProductController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;
    
    @Before
    public void initTests() {
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
	                .andReturn();
	}
	
	@Test
	public void testCreateNewCart() throws Exception {
		MvcResult result = mvc.perform(get("/cart/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
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
