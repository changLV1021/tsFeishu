package com.hundsun.ts2dingding.utils;

public class StringUtils {
	 /**
	  * 首字母大写
	  * @param name
	  * @return
	  */
    public static String captureFirst(String name) {
    	return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

	/**
	 * 替换字符串并让它的下一个字母为大写
	 * @param srcStr
	 * @param org
	 * @param ob
	 * @return
	 */
	public static String replaceUnderlineAndfirstToUpper(String srcStr,String org,String ob) {
		String newString = "";
		int first=0;
		while(srcStr.indexOf(org)!=-1) {
			first=srcStr.indexOf(org);
			if(first!=srcStr.length()) {
				newString=newString+srcStr.substring(0,first)+ob;
				srcStr=srcStr.substring(first+org.length(),srcStr.length());
				srcStr=captureFirst(srcStr);
			}
		}
		newString=newString+srcStr;
		return newString;
	}

	/**
	 * 去除html代码中含有的标签
	 * @param htmlStr
	 * @return
	 */
	public static String delHtmlTags(String htmlStr) {
		//定义script的正则表达式，去除js可以防止注入
		String scriptRegex="<script[^>]*?>[\\s\\S]*?<\\/script>";
		//定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
		String styleRegex="<style[^>]*?>[\\s\\S]*?<\\/style>";
		//定义HTML标签的正则表达式，去除标签，只提取文字内容
		String htmlRegex="<[^>]+>";
		//定义空格,回车,换行符,制表符
		String spaceRegex = "\\s*|\t|\r|\n";

		// 过滤script标签
		htmlStr = htmlStr.replaceAll(scriptRegex, "");
		// 过滤style标签
		htmlStr = htmlStr.replaceAll(styleRegex, "");
		// 过滤html标签
		htmlStr = htmlStr.replaceAll(htmlRegex, "");
		// 过滤空格等
		htmlStr = htmlStr.replaceAll(spaceRegex, "");
		htmlStr = htmlStr.replaceAll("&nbsp;", "");
		return htmlStr.trim(); // 返回文本字符串
	}
	/**
	 * 获取HTML代码里的内容
	 * @param htmlStr
	 * @return
	 */
	public static String getTextFromHtml(String htmlStr){
		//去除html标签
		htmlStr = delHtmlTags(htmlStr);
		//去除空格" "
		htmlStr = htmlStr.replaceAll(" ","");
		return htmlStr;
	}
}
