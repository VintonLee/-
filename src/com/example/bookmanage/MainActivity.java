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
	//声明控件对象
	private	EditText etuser,etpwd;
	private	Button btlogin;
	
	
	//声明提示对话框
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取编辑框控件对象
		etuser=(EditText) findViewById(R.id.et_user);
		etpwd=(EditText) findViewById(R.id.et_pwd);

		//获取按钮控件对象
		btlogin=(Button) findViewById(R.id.bt_login);

		//为按钮绑定事件监听器
		btlogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String suser=etuser.getText().toString();
				String spwd=etpwd.getText().toString();
				if(suser.equals("")||spwd.equals("")) {
					Toast.makeText(MainActivity.this,"用户名和密码不能为空", Toast.LENGTH_SHORT).show();
				}else if(suser.equals("admin")&&spwd.equals("150001")) {
					//打开对话框，显示“***用户登录成功！"
					
					
					//跳转到ManageActivity
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
