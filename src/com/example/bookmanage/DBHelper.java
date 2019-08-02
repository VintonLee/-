package com.example.bookmanage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//�Զ�������࣬ȥ�̳�һ��������
public class DBHelper extends SQLiteOpenHelper{
	//����һ���ַ�����������ʾһ�������SQL���
	public static final String CREATE_TABLE="create table Book("
			+ "_id integer primary key autoincrement,"
			+ "bookname text,"
			+ "bookprice real,"
			+ "bookbanci text,"
			+ "bookleibie text,"
			+ "bookcbs varchar(30))";
	//���嵱ǰ��Ĺ��췽��
	public DBHelper(
			Context context, 
			String name, 
			CursorFactory factory, 
			int version) {
		super(context, name, factory, version);
		
		// TODO Auto-generated constructor stub
	}
	//��д���󷽷�
	@Override
	//onCreate()�����ݿ��һ�α�������ʱ����ã����ݱ�Ĵ�����������ʼ������
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//����Ϊһ�����ݿ�����ڸ÷�����ʹ�øö���ʵ�ֶ����ݿ�Ļ�������
		//��������ִ��
		db.execSQL(CREATE_TABLE);
	}
	//���ݿ����ʱ�����ã����ݱ�����ӣ�ɾ����
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
