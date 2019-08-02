package com.example.bookmanage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//自定义帮助类，去继承一个帮助类
public class DBHelper extends SQLiteOpenHelper{
	//声明一个字符串常量，表示一条建表的SQL语句
	public static final String CREATE_TABLE="create table Book("
			+ "_id integer primary key autoincrement,"
			+ "bookname text,"
			+ "bookprice real,"
			+ "bookbanci text,"
			+ "bookleibie text,"
			+ "bookcbs varchar(30))";
	//定义当前类的构造方法
	public DBHelper(
			Context context, 
			String name, 
			CursorFactory factory, 
			int version) {
		super(context, name, factory, version);
		
		// TODO Auto-generated constructor stub
	}
	//重写抽象方法
	@Override
	//onCreate()是数据库第一次被创建的时候调用，数据表的创建及基本初始化工作
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//参数为一个数据库对象，在该方法中使用该对象实现对数据库的基本操作
		//建表语句的执行
		db.execSQL(CREATE_TABLE);
	}
	//数据库更新时被调用，数据表的增加，删除等
	@Override
	public void onUpgrade(
			SQLiteDatabase db, 
			int oldVersion, 
			int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists Book");
		onCreate(db);

		
	}

}
