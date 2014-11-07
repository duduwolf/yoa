package com.hlkj.yoa.simpleweb.core; /**
 * Copyright 2010, taop.cn
 */

/**
 * 
 * 
 * @author duduwolf
 */
public class Constants {
	
	/** 默认SimpleWeb在容器里的关键字 */
	public static final String SIMPLEWEB_CODE = "Simpleweb's code";
	
	/** MIME constant for TEXT */
	public static final String MIME_PLAIN = "text/plain";
    /** MIME constant for HTML */
    public static final String MIME_HTML = "text/html; charset=UTF-8";
    /** MIME constant for Javascript */
    public static final String MIME_JS = "text/javascript";
    /** MIME constant form CSS */
    public static final String MIME_CSS = "text/css";
    /** MIME constant for GIF */
    public static final String MIME_GIF = "image/gif";
    /** MIME constant for JPG */
    public static final String MIME_JPG = "image/jpeg";
    /** MIME constant for PNG */
    public static final String MIME_PNG = "image/png";
    /** MIME constant for IMG(JPG|JPEG|GIF|PNG|BMP|etc...) */
    public static final String MIME_IMG = "image/*";
    /** MIME constant for charset, utf-8 */
    public static final String MIME_CHARSET = "UTF-8";
    
    
    /** HTTP etag header */
    public static final String HEADER_ETAG = "ETag";
    /** HTTP etag equivalent of HEADER_IF_MODIFIED */
    public static final String HEADER_IF_NONE = "If-None-Match";
    /** HTTP header for when a file was last modified */
    public static final String HEADER_LAST_MODIFIED = "Last-Modified";
    /** HTTP header to request only modified data */
    public static final String HEADER_IF_MODIFIED = "If-Modified-Since";
    /** The name of the user agent HTTP header */
    public static final String HEADER_USER_AGENT = "User-Agent";
    /** The name of the content-length HTTP header */
    public static final String HEADER_CONTENT_LENGTH = "Content-Length";
    
    
    /** javascript文件后缀名 */
    public static final String EXTENSION_JS = ".js";
    /** css文件后缀名 */
    public static final String EXTENSION_CSS = ".css";
    /** gif文件后缀名 */
    public static final String EXTENSION_GIF = ".gif";
    /** jpg文件后缀名 */
    public static final String EXTENSION_JPG = ".jpg";
    /** png文件后缀名 */
    public static final String EXTENSION_PNG = ".png";
    /** html文件后缀名 */
    public static final String EXTENSION_HTML = ".html";
}
