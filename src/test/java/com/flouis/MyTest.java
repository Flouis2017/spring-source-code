package com.flouis;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.flouis.entity.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class MyTest {

	@Test
	public void defaultTest(){
		try {
			// 通过forName获取Class对象
			Class clazz = Class.forName("com.flouis.entity.Person");

			// newInstance()--》调用无参（默认）构造方法实例化对象
			Person person = (Person) clazz.newInstance();
			System.out.println(person.hashCode());
			person.setName("Tom");
			person.setAge(30);
			System.out.println(person);

			System.out.println("===================================================");

			// 通过getConstructor(s)获取构造方法实例化对象
			Constructor[] constructors = clazz.getConstructors();
			for (Constructor c : constructors){
				System.out.println(c);
			}
//			Person person2 = (Person) constructors[1].newInstance("Flouis", 25);
			Person person2 = (Person) clazz.getConstructor(String.class, Integer.class).newInstance("Flouis", 25);
			System.out.println(person2.hashCode());
			System.out.println(person2);

			System.out.println("===================================================");
			Field[] declaredFields = clazz.getDeclaredFields();
			for (Field f : declaredFields){
				System.out.println(f);
			}

			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method m : declaredMethods){
				System.out.println(m);
			}



		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void hutoolTest(){
		Person person = new Person("Flouis", 28);
		Map<String, Object> map = BeanUtil.beanToMap(person);
		System.out.println(MapUtil.getStr(map, "name"));
	}

}
