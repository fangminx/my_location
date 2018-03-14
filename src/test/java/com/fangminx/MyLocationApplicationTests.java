package com.fangminx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyLocationApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void smain() {
		String s = "Chang Chun ";
		String re = s.replace(" ","");
		String res = s.replace(" ","").replace(" ","");
		System.out.print(re+"\n"+res);
	}
}
