package com.sept.apps.smartcopy;


import com.sept.apps.smartcopy.bean.InvokeAble;
import com.sept.apps.smartcopy.beanfactory.BeanFactory;

public class Demo {
	public static void main(String[] args) throws Exception {
		BeanFactory bd = new BeanFactory();
		bd.reload();
		InvokeAble inv1 = bd.getBean("DemoRealize");
		InvokeAble inv2 = bd.getBean("DemoRealize2");
		String message = "aaa,abb,baa,aff,bvv,aee,bgg";
		System.out.println(inv1.entry(message));
		System.out.println(inv2.entry(message));

//		File file = new File("classz/DemoRealize.class");
//		// Path path = Paths.get(new URI("file:///" + "./classz/DemoRealize.class"));
//		Path path = Paths.get(file.toURI());
	}
	
	
	private void test(String str) {
		
	}

}
