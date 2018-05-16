package br.com.ecommerce.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.ecommerce.ApplicationBoot;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationBoot.class)
public abstract class ApplicationBootTest {

	
}
