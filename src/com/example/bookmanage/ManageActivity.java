package com.example.bookmanage;

import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class ManageActivity extends Activity implements OnClickListener {
	//声明控件对象
	private EditText etname,etprice;
	private RadioGroup rgbanci;
	private CheckBox cbclass[];
	private Spinner spcbs;
	private Button btinsert,btdelete,btupdate,btquery;
	private ListView lvshow;
	//声明存储数据的变量
	private String sbkname,sbkbanci,sbkclass,sbkcbs;
	private double dbkprice;
	
	//声明帮助类对象引用变量
	DBHelper dbhelper;
	//声明数据库对象引用变量
	SQLiteDatabase sdb;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		//调取初始化方法
		init();
		//获取帮助类对象
		dbhelper=new DBHelper(ManageActivity.this,
				"BookStore.db", null, 2);
		//创建数据库对象
		sdb=dbhelper.getWritableDatabase();
		Toast.makeText(ManageActivity.this,"数据库创建成功",0).show();
	}

	private void init() {
		cbclass=new CheckBox[6];
		//获取编辑框控件对象
		etname=(EditText) findViewById(R.id.et_bkname);
		etprice=(EditText) findViewById(R.id.et_bkprice);
		//获取单选按钮组控件对象
		rgbanci=(RadioGroup) findViewById(R.id.rg_bkbanci);
		//获取复选框控件对象
		cbclass[0]=(CheckBox) findViewById(R.id.cb_1);
		cbclass[1]=(CheckBox) findViewById(R.id.cb_2);
		cbclass[2]=(CheckBox) findViewById(R.id.cb_3);
		cbclass[3]=(CheckBox) findViewById(R.id.cb_4);
		cbclass[4]=(CheckBox) findViewById(R.id.cb_5);
		cbclass[5]=(CheckBox) findViewById(R.id.cb_6);
		//获取下拉列表控件对象
		spcbs=(Spinner) findViewById(R.id.sp_bkcbs);
		//获取按钮控件对象
		btinsert=(Button) findViewById(R.id.bt_insert);
		btupdate=(Button) findViewById(R.id.bt_update);
		btdelete=(Button) findViewById(R.id.bt_delete);
		btquery=(Button) findViewById(R.id.bt_query);
		//获取列表视图控件对象
		lvshow=(ListView) findViewById(R.id.lv_show);
		
		//为按钮绑定事件监听器
		btinsert.setOnClickListener(this);
		btupdate.setOnClickListener(this);
		btdelete.setOnClickListener(this);
		btquery.setOnClickListener(this);
		
				
	}
	
	@Override
	public void onClick(View v) {
		
		switch(v.getId()) {
		case R.id.bt_insert:
			//数据的获取
			getdata();
			//数据添加到数据表
			//1)执行SQL语句
			/*
			sdb.execSQL("insert into Book(bookname,bookprice,bookbanci,"
					+ "bookleibie,bookcbs) values('"+sbkname+"',"+dbkprice+
					",'"+sbkbanci+"','"+sbkclass+"','"+sbkcbs+"')");
					*/
			//2)调用插入的方法
			//创建一个ContentValues对象
			ContentValues values=new ContentValues();
			//向values中写入数据
			values.put("bookname", sbkname);
			values.put("bookprice", dbkprice);
			values.put("bookbanci",sbkbanci);
			values.put("bookleibie", sbkclass);
			values.put("bookcbs", sbkcbs);
			sdb.insert("Book", null, values);
			break;
		case R.id.bt_update:
			//1）把修改的数据存入到一个values对象
			ContentValues value=new ContentValues();
			value.put("bookname", "JSP");
			value.put("bookprice",80);
			//2)调取更新数据的方法
			sdb.update("Book", value, "bookcbs=?",new String[] {"科学出版社"});
	
			break;
		case R.id.bt_delete:
			sdb.delete("Book","bookbanci='1版'",null);
		//	sdb.delete("Book","bookbanci=?", new String[] {"1版"});
			sdb.execSQL("delete from Book where bookbanci='1版'");
			break;
		case R.id.bt_query:
			Cursor c=sdb.query("Book", null, "bookcbs=?",
						new String[] {"科学出版社"}, null, null, null);
		//	c=sdb.rawQuery("select * from Book where bookcbs=?",
		//			new String[] {"科学出版社"});
			
			//创建一个适配器对象
			String[] from= {"bookname","bookprice",
					"bookbanci","bookleibie","bookcbs"};
			int[] to= {R.id.tv_name,R.id.tv_name,R.id.tv_banci,
					R.id.tv_class,R.id.tv_cbs};
			SimpleCursorAdapter adapter=
					new SimpleCursorAdapter(
							ManageActivity.this, 
							R.layout.list_item, 
							c, 
							from, 
							to);
			//关联
			lvshow.setAdapter(adapter);
			
			
			
			break;
			
		}
	}
	//定义获取数据的方法
	private void getdata() {
		sbkname="";
		sbkbanci="";
		sbkclass="";
		sbkcbs="";
		sbkname=etname.getText().toString();
		dbkprice=Double.parseDouble(etprice.getText().toString());
		for(int i=0;i<rgbanci.getChildCount();i++) {
			RadioButton rb=(RadioButton) rgbanci.getChildAt(i);
			if(rb.isChecked()) {
				sbkbanci=rb.getText().toString();
				break;
			}
		}
		for(int i=0;i<cbclass.length;i++) {
			if(cbclass[i].isChecked()) {
				sbkclass+=cbclass[i].getText().toString()+"  ";
			}
		}
		sbkcbs=spcbs.getSelectedItem().toString();
		
	}
	
}
