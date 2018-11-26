package com.sept.apps.smartcopy.exception;

import com.sept.exception.ExceptionNames;
import com.sept.exception.SeptException;

public class SmartCopyException extends SeptException {
	private static final long serialVersionUID = 8605483565352355562L;
	public static final String exceptionType = "系统";
	static {
		try {
			ExceptionNames.addExceptionName(-2, "XML文件读取错误");
			ExceptionNames.addExceptionName(-3, "XML文件解析错误");
			ExceptionNames.addExceptionName(-4, "业务逻辑错误");


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SmartCopyException() {
		this(ExceptionNames.defaultCode, "程序出现系统异常!");
	}

	public SmartCopyException(Throwable cause) {
		this(ExceptionNames.defaultCode, cause == null ? "程序出现系统异常!" : cause.toString(), cause);
	}

	public SmartCopyException(String errtext) {
		this(ExceptionNames.defaultCode, errtext);
	}

	public SmartCopyException(String errtext, Throwable cause) {
		this(ExceptionNames.defaultCode, errtext, cause);
	}

	public SmartCopyException(int errcode, String errtext) {
		super(errcode + "_" + errtext);
	}

	public SmartCopyException(int errcode, String errtext, Throwable cause) {
		super(errcode + "_" + errtext, cause);
	}
}
