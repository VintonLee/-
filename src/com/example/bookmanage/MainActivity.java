package com.example.bookmanage;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	//�����ؼ�����
	private	EditText etuser,etpwd;
	private	Button btlogin;
	
	
	//������ʾ�Ի���
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ȡ�༭��ؼ�����
		etuser=(EditText) findViewById(R.id.et_user);
		etpwd=(EditText) findViewById(R.id.et_pwd);

		//��ȡ��ť�ؼ�����
		btlogin=(Button) findViewById(R.id.bt_login);

		//Ϊ��ť���¼�������
		btlogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String suser=etuser.getText().toString();
				String spwd=etpwd.getText().toString();
				if(suser.equals("")||spwd.equals("")) {
					Toast.makeText(MainActivity.this,"�û��������벻��Ϊ��", Toast.LENGTH_SHORT).show();
				}else if(suser.equals("admin")&&spwd.equals("150001")) {
					//�򿪶Ի�����ʾ��***�û���¼�ɹ���"
					
					
					//��ת��ManageActivity
					Intent intent=new Intent(MainActivity.this,ManageActivity.class);
					startActivity(intent);
					
				}else {
					
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
