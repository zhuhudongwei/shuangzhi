package com.wechat.utils;


public class BaseException extends RuntimeException
{
	
	public static final int CODE_SYSERROR = -1;
    public static final int CODE_USERERROR = -2;
    
	private boolean	debug = true;
	private String	trace;
	private int	code		= CODE_SYSERROR;
	private String errorMessage;
	
	
	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public BaseException()
	{
		super();
		init();
	}
	
	public BaseException(String s)
	{
		super(s);
		this.errorMessage = s;
		init();
	}
	
	public BaseException(int code, String s)
	{
		super(s);
		this.code = code;
		this.errorMessage = s;
		init();
	}
	
	public BaseException(String message, Throwable throwable) {
        super(message, throwable);
        init();
    }
	
	public int getCode()
	{
		return this.code;
	}
	
	public String getMessage()
	{
		return (debug ? trace : "") + super.getMessage();
	}
	
	public String getDetailMessage()
	{
		return super.getMessage();
	}
	
	private void init()
	{
		StackTraceElement traces[] = getStackTrace();
		String className = traces[0].getClassName();
		int n = className.lastIndexOf('.');
		if(n > 0)
			className = className.substring(n + 1);
		trace = className + "." + traces[0].getMethodName() + "[line: " + traces[0].getLineNumber() + "]: ";
	}
	
	@Override
	public String getLocalizedMessage()
	{
		return super.getLocalizedMessage();
	}
	
}
