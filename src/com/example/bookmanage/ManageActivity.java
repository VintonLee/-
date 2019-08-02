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
	//�����ؼ�����
	private EditText etname,etprice;
	private RadioGroup rgbanci;
	private CheckBox cbclass[];
	private Spinner spcbs;
	private Button btinsert,btdelete,btupdate,btquery;
	private ListView lvshow;
	//�����洢���ݵı���
	private String sbkname,sbkbanci,sbkclass,sbkcbs;
	private double dbkprice;
	
	//����������������ñ���
	DBHelper dbhelper;
	//�������ݿ�������ñ���
	SQLiteDatabase sdb;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		//��ȡ��ʼ������
		init();
		//��ȡ���������
		dbhelper=new DBHelper(ManageActivity.this,
				"BookStore.db", null, 2);
		//�������ݿ����
		sdb=dbhelper.getWritableDatabase();
		Toast.makeText(ManageActivity.this,"���ݿⴴ���ɹ�",0).show();
	}

	private void init() {
		cbclass=new CheckBox[6];
		//��ȡ�༭��ؼ�����
		etname=(EditText) findViewById(R.id.et_bkname);
		etprice=(EditText) findViewById(R.id.et_bkprice);
		//��ȡ��ѡ��ť��ؼ�����
		rgbanci=(RadioGroup) findViewById(R.id.rg_bkbanci);
		//��ȡ��ѡ��ؼ�����
		cbclass[0]=(CheckBox) findViewById(R.id.cb_1);
		cbclass[1]=(CheckBox) findViewById(R.id.cb_2);
		cbclass[2]=(CheckBox) findViewById(R.id.cb_3);
		cbclass[3]=(CheckBox) findViewById(R.id.cb_4);
		cbclass[4]=(CheckBox) findViewById(R.id.cb_5);
		cbclass[5]=(CheckBox) findViewById(R.id.cb_6);
		//��ȡ�����б�ؼ�����
		spcbs=(Spinner) findViewById(R.id.sp_bkcbs);
		//��ȡ��ť�ؼ�����
		btinsert=(Button) findViewById(R.id.bt_insert);
		btupdate=(Button) findViewById(R.id.bt_update);
		btdelete=(Button) findViewById(R.id.bt_delete);
		btquery=(Button) findViewById(R.id.bt_query);
		//��ȡ�б���ͼ�ؼ�����
		lvshow=(ListView) findViewById(R.id.lv_show);
		
		//Ϊ��ť���¼�������
		btinsert.setOnClickListener(this);
		btupdate.setOnClickListener(this);
		btdelete.setOnClickListener(this);
		btquery.setOnClickListener(this);
		
				
	}
	
	@Override
	public void onClick(View v) {
		
		switch(v.getId()) {
		case R.id.bt_insert:
			//���ݵĻ�ȡ
			getdata();
			//������ӵ����ݱ�
			//1)ִ��SQL���
			/*
			sdb.execSQL("insert into Book(bookname,bookprice,bookbanci,"
					+ "bookleibie,bookcbs) values('"+sbkname+"',"+dbkprice+
					",'"+sbkbanci+"','"+sbkclass+"','"+sbkcbs+"')");
					*/
			//2)���ò���ķ���
			//����һ��ContentValues����
			ContentValues values=new ContentValues();
			//��values��д������
			values.put("bookname", sbkname);
			values.put("bookprice", dbkprice);
			values.put("bookbanci",sbkbanci);
			values.put("bookleibie", sbkclass);
			values.put("bookcbs", sbkcbs);
			sdb.insert("Book", null, values);
			break;
		case R.id.bt_update:
			//1�����޸ĵ����ݴ��뵽һ��values����
			ContentValues value=new ContentValues();
			value.put("bookname", "JSP");
			value.put("bookprice",80);
			//2)��ȡ�������ݵķ���
			sdb.update("Book", value, "bookcbs=?",new String[] {"��ѧ������"});
	
			break;
		case R.id.bt_delete:
			sdb.delete("Book","bookbanci='1��'",null);
		//	sdb.delete("Book","bookbanci=?", new String[] {"1��"});
			sdb.execSQL("delete from Book where bookbanci='1��'");
			break;
		case R.id.bt_query:
			Cursor c=sdb.query("Book", null, "bookcbs=?",
						new String[] {"��ѧ������"}, null, null, null);
		//	c=sdb.rawQuery("select * from Book where bookcbs=?",
		//			new String[] {"��ѧ������"});
			
			//����һ������������
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
			//����
			lvshow.setAdapter(adapter);
			
			
			
			break;
			
		}
	}
	//�����ȡ���ݵķ���
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
