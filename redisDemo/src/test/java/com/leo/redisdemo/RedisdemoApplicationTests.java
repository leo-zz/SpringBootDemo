package com.leo.redisdemo;

import com.leo.redisdemo.util.RedisHashUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisdemoApplicationTests {

	@Autowired
	RedisHashUtil<String> redisHashUtil;

	@Test
	public void firstRedisTest() {
		System.out.println("***********************测试向Redis插入数据");
		redisHashUtil.put("Mi8","XiaoMi",-1);
		redisHashUtil.put("V10","Honor",-1);
		redisHashUtil.put("Pro2","Smartisan",-1);
		redisHashUtil.put("NEX","VIVO",-1);

		System.out.println("**********************测试从Redis读取数据，以及查询数据总数");
		long count = redisHashUtil.count();
		List<String> all = redisHashUtil.getAll();
		System.out.println("遍历hash中的value,共计"+redisHashUtil.count()+"个品牌。分别为：");
		for (String s:all) {
			System.out.print(s+"   ");
		}
		System.out.println("");

		System.out.println("***********************测试Redis中是否存在指定数据");
		if(redisHashUtil.isKeyExists("Pro2")){
			String pro2 = redisHashUtil.get("Pro2");
			System.out.println("型号为Pro2的手机存在，其品牌为："+pro2);
		}

		System.out.println("***********************测试从Redis中删除数据");
		redisHashUtil.remove("Pro2");
		if(!redisHashUtil.isKeyExists("Pro2")){
			System.out.println("型号为Pro2的手机被移除了");
		}

		System.out.print("剩余型号还有：");
		redisHashUtil.getKeys().forEach(key-> System.out.print(key+"  "));
		System.out.println("");

		System.out.println("***********************测试清空Redis中的hash数据");
		redisHashUtil.clear();
		System.out.println("所有手机型号都被清空，剩余："+redisHashUtil.count()+"个");
	}

}
