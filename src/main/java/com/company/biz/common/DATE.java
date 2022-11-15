package com.company.biz.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DATE {
	public static void main(String args[]){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(df.format(cal.getTime()));
		cal.add(Calendar.DATE, +7);
		System.out.println(df.format(cal.getTime()));
	}
}
