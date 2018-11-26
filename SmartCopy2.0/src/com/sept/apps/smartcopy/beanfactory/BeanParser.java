package com.sept.apps.smartcopy.beanfactory;

import java.util.Iterator;
import java.util.Map;

import org.dom4j.Element;

import com.sept.apps.smartcopy.bean.InvokeAble;
import com.sept.apps.smartcopy.exception.SmartCopyException;
import com.sept.apps.smartcopy.xmlstep.StepsTag;
import com.sept.apps.smartcopy.xmlstep.steps.AppendTag;
import com.sept.apps.smartcopy.xmlstep.steps.DefineTag;
import com.sept.apps.smartcopy.xmlstep.steps.IfElseTag;
import com.sept.apps.smartcopy.xmlstep.steps.LoopTag;
import com.sept.apps.smartcopy.xmlstep.steps.ToolTag;
import com.sept.project.classz.dynamic.JavaStringCompiler;
import com.sept.project.classz.loader.ClassFactory;
import com.sept.util.StringUtil;

public class BeanParser implements BeanNames {
	private Element element;
	private JavaStringCompiler compiler;
	private String packageName;
	private String className;
	private String name;
	private String id;
	private String description;

	private StringBuffer classStr;

	public BeanParser(Element element) throws SmartCopyException {
		// 业务错误
		if (null == element) {
			throw new SmartCopyException(-4, "配置文件错误");
		}
		// 解析错误
		if (!DOM_BEAN_NAME.equalsIgnoreCase(element.getName().toLowerCase())) {
			throw new SmartCopyException(-3, "配置文件错误");
		}
		this.element = element;
		compiler = new JavaStringCompiler();
		classStr = new StringBuffer();
	}

	/**
	 * 解析节点，
	 * 
	 * @return
	 * @throws Exception
	 */
	public InvokeAble parse() throws Exception {
		this.id = element.attributeValue(BEAN_SHOW_ID);
		this.name = element.attributeValue(BEAN_SHOW_NAME);
		this.className = element.attributeValue(BEAN_CLASS_NAME);
		/**
		 * 如果时class文件
		 */
		if (null != this.className && !className.trim().isEmpty()) {
			for (Iterator<?> it = element.elementIterator(); it.hasNext();) {
				Element element_child = (Element) it.next();
				String elementName = element_child.getName();
				if (DOM_BEAN_DESCRIPTION.equals(elementName)) {
					this.description = element_child.getStringValue();
					break;
				}
			}
			InvokeAble invoke = (InvokeAble) ClassFactory.getClassz(this.className).newInstance();
			invoke.setId(this.id);
			invoke.setName(this.name);
			invoke.setDescription(this.description);
			return invoke;
		}
		// 解析content
		this.className = CLASS_NAME_HEAD + StringUtil.getUUID();
		this.packageName = getPackage();

		StringBuffer importStr = new StringBuffer();
		StringBuffer methodStr = new StringBuffer();
		importStr.append("import " + InvokeAble.class.getName() + ";");

		boolean isSteps = false;
		for (Iterator<?> it = element.elementIterator(); it.hasNext();) {
			Element element_child = (Element) it.next();
			String elementName = element_child.getName();
			if (DOM_BEAN_METHOD_IMPORT.equals(elementName)) {
				importStr.append("import " + element_child.getStringValue() + ";");
			}
			if (DOM_BEAN_METHOD_CONTENT.equals(elementName)) {
				methodStr.append(element_child.getStringValue());
			}
			if (DOM_BEAN_DESCRIPTION.equals(elementName)) {
				this.description = element_child.getStringValue();
			}
			if (DOM_BEAN_STEPS.equals(elementName)) {
				isSteps = true;
				break;
			}
		}
		/**
		 * conten配置方法体
		 */
		if (!isSteps) {
			classStr.setLength(0);
			classStr.append("package " + this.packageName + ";");
			classStr.append(importStr);
			classStr.append("public class " + this.className + " extends InvokeAble {");
			classStr.append("public String entry(String message) throws Exception {");
			classStr.append(methodStr);
			classStr.append("}");
			classStr.append("}");

			Map<String, byte[]> results = compiler.compile(this.className + ".java", classStr.toString());
			Class<?> clazz = compiler.loadClass(this.packageName + "." + this.className, results);
			InvokeAble invoke = (InvokeAble) clazz.newInstance();
			invoke.setId(this.id);
			invoke.setName(this.name);
			invoke.setDescription(this.description);
			return invoke;
		} else {
			/**
			 * steps配置
			 */
			for (Iterator<?> it = element.elementIterator(); it.hasNext();) {
				Element element_child = (Element) it.next();
				String elementName = element_child.getName();
				if (DOM_BEAN_DESCRIPTION.equals(elementName)) {
					this.description = element_child.getStringValue();
				}
				if (DOM_BEAN_STEPS.equals(elementName)) {
					StepsTag invoke = new StepsTag();
					invoke.setId(this.id);
					invoke.setName(this.name);
					invoke.setDescription(this.description);
					// TODO 解析steps成员step
					this.parseSteps(invoke, element_child);
					return invoke;
				}
			}

		}

		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPackage() {
		String packageName = this.getClass().getName();
		packageName = packageName.substring(0, packageName.lastIndexOf("."));
		return packageName;
	}

	/**
	 * 解析steps标签
	 * 
	 * @param invoke
	 * @param steps_element
	 */
	private void parseSteps(InvokeAble invoke, Element steps_element) {
		for (Iterator<?> it = steps_element.elementIterator(); it.hasNext();) {
			Element step_element = (Element) it.next();
			String elementName = step_element.getName();
			/**
			 * define标签
			 */
			if (DOM_BEAN_DEFINE.toLowerCase().equalsIgnoreCase(elementName.toLowerCase())) {
				String name = step_element.attributeValue(BEAN_DEFINE_NAME);
				String value = step_element.attributeValue(BEAN_DEFINE_VALUE);
				String type = step_element.attributeValue(BEAN_DEFINE_TYPE);
				DefineTag dt = new DefineTag(name, value, type);
				invoke.addStep(dt);
			} else if (DOM_BEAN_APPEND.toLowerCase().equalsIgnoreCase(elementName.toLowerCase())) {
				String value = step_element.attributeValue(BEAN_APPEND_VALUE);
				AppendTag at = new AppendTag(value);
				invoke.addStep(at);
			} else if (DOM_BEAN_TOOL.toLowerCase().equalsIgnoreCase(elementName.toLowerCase())) {
				String toolName = step_element.attributeValue(BEAN_TOOL_TOOLNAME);
				String name = step_element.attributeValue(BEAN_TOOL_NAME);
				String value = step_element.attributeValue(BEAN_TOOL_VALUE);
				String regex = step_element.attributeValue(BEAN_TOOL_REGEX);
				String beginIndex = step_element.attributeValue(BEAN_TOOL_BEGININDEX);
				String endIndex = step_element.attributeValue(BEAN_TOOL_ENDINDEX);
				ToolTag tt = new ToolTag(name, value, toolName);
				tt.setBeginIndex(beginIndex);
				tt.setEndIndex(endIndex);
				tt.setRegex(regex);
				invoke.addStep(tt);
			} else if (DOM_BEAN_LOOP.toLowerCase().equalsIgnoreCase(elementName.toLowerCase())) {
				String loopVar = step_element.attributeValue(BEAN_LOOP_LOOPVAR);
				String stepVar = step_element.attributeValue(BEAN_LOOP_STEPVAR);
				String beginIndex = step_element.attributeValue(BEAN_LOOP_BEGININDEX);
				String endIndex = step_element.attributeValue(BEAN_LOOP_ENDINDEX);
				LoopTag lt = new LoopTag(loopVar, stepVar, beginIndex, endIndex);
				this.parseSteps(lt, step_element);
				invoke.addStep(lt);
			} else if (DOM_BEAN_IF.toLowerCase().equalsIgnoreCase(elementName.toLowerCase())) {
				String apara = step_element.attributeValue(BEAN_IF_APARA);
				String bpara = step_element.attributeValue(BEAN_IF_BPARA);
				String compare = step_element.attributeValue(BEAN_IF_COMPARE);
				IfElseTag iet = new IfElseTag(apara, bpara, compare);
				this.parseSteps(iet, step_element);
				invoke.addStep(iet);
			}

		}
	}

}
