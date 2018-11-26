package com.sept.apps.smartcopy.beanfactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sept.apps.smartcopy.bean.InvokeAble;
import com.sept.apps.smartcopy.bean.ScFactory;
import com.sept.apps.smartcopy.exception.SmartCopyException;

public class BeanFactory implements ScFactory {
	private final HashMap<String, String[]> hmNames = new HashMap<>();
	private final HashMap<String, Element> hmElement = new HashMap<>();
	private final HashMap<String, InvokeAble> hmObject = new HashMap<>();
	private Properties properties;

	public BeanFactory() throws Exception {
		properties = new Properties();
		properties.load(new FileInputStream(new File("./conf/beans.properties")));
		reload();
	}

	@Override
	public InvokeAble getBean(String name) throws Exception {
		InvokeAble object = null;
		if (hmElement.containsKey(name) && null != hmElement.get(name)) {
			if (hmObject.containsKey(name)) {
				object = (InvokeAble) hmObject.get(name);
			} else {
				rParseElement(name.substring(0, name.indexOf(".")), hmElement.get(name));
				object = hmObject.get(name);
			}
		} else {
			throw new SmartCopyException(-2, "bean配置[" + name + "]未找到，请尝试重启或刷新配置！");
		}

		return object;
	}

	@Override
	public HashMap<String, String[]> getNames() {
		return this.hmNames;
	}

	@Override
	public boolean reload() throws Exception {
		SAXReader reader = new SAXReader();
		for (Object key : properties.keySet()) {
			Document document = reader.read(properties.getProperty(key.toString()));
			Element root = document.getRootElement();
			for (Iterator<?> it = root.elementIterator(); it.hasNext();) {
				rParseElement(key.toString(), (Element) it.next());
			}
		}

		return false;
	}

	private void rParseElement(String key, Element element) throws Exception {
		BeanParser bp = new BeanParser(element);
		InvokeAble object = bp.parse();
		hmNames.put(key + "." + object.getId(), new String[] { object.getName(), object.getDescription() });
		hmElement.put(key + "." + bp.getId(), element);
		hmObject.put(key + "." + bp.getId(), object);
	}
}
