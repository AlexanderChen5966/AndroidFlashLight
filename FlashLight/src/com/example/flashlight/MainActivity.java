/*
 * 練習一
 * 這是第一個專案的練習
 * 手電筒
 * 
 * 使用手電筒之前要先在Manifest檔案打開相機的權限，如下要加入Manifest檔案當中
 * <uses-permission android:name="android.permission.CAMERA" />
 * 
 * 手電筒其實就是利用相機的閃光燈，所以不用懷疑
 * */

package com.example.flashlight;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button button;
	Camera camera = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		initHandler();
	}

	private void initView() {
		// TODO Auto-generated method stub
		button = (Button)findViewById(R.id.button);
	}

	private void initHandler() {
		// TODO Auto-generated method stub
		button.setOnClickListener(new View.OnClickListener() {
			
//			Camera camera = Camera.open();
//			Parameters parameters = camera.getParameters();
			
//Case1: 將開關直接寫死在函式中，重複使用性差，懶惰時可以用		
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				 if(button.getText().toString().equals("Off")){
//					   
//					 parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
//					 camera.setParameters(parameters);
//					 camera.startPreview();
//					 
//					 button.setTextColor(0xFF00FFFF);
//					 button.setText("On");
//					 
//					 }else{
//					 parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
//					         camera.setParameters(parameters);
//					         
//					         button.setText("Off");
//					         button.setTextColor(0xFF000000);
//					 }
//					 
//			}
//		});
			
//Case2:將開關利用方法獨立出去，如果有要做燈光閃爍時可以重複用方法即可
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//button上的字串是否等於Off 
				 if(button.getText().toString().equals("Off")){
					  //將手電筒開關程式抽離
					  //以便之後要增加行為時可以使用
					  on();
					 
				}else{

					  off(); 
					 }
					 
			}
		});
	}

	protected void on() {
		// TODO Auto-generated method stub
		//取得Camera的參數
		Parameters parameters = camera.getParameters();
		//設定持續亮燈
		parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
		//將參數回存回去Camera
		camera.setParameters(parameters); 
		//設定按鈕按下後文字與顏色改變
		button.setTextColor(0xFF00FFFF);
		button.setText("On");
		
	}
	
	protected void off() {
		// TODO Auto-generated method stub
		//取得Camera的參數
		Parameters parameters = camera.getParameters();
		//設定關閉亮燈
		parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
		//將參數回存回去Camera
		camera.setParameters(parameters);
		button.setText("Off");
        button.setTextColor(0xFF000000);
		
	}

	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		camera = Camera.open();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		off();
		camera.release(); //Release Camera
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
	/*參數列表
	*FLASH_MODE_ON：只亮一次，亮完後關閉亮燈
	FLASH_MODE_OFF：關閉亮燈
	FLASH_MODE_TORCH：持續亮燈
	
	API網址: http://developer.android.com/reference/android/hardware/Camera.Parameters.html
	*/