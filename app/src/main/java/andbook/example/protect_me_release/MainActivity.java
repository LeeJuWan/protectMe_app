package andbook.example.protect_me_release;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import Fragment.homepage_Fragment;
import Fragment.use_Fragment;
import Fragment.main_Fragment;
import Fragment.testSetting_Fragment;
import Other_Utill.BottomNavigationhelper;

/*
 * Create by 이주완 2019.05.20
 * Copyright (c) 2019. 이주완 All rights reserved.
 *
 * 2019.05.20 지속적인 기능 개선 관리필요
 * 개발자 : 이주완  소속: 공주대학교 컴퓨터 공학부
 * ----------------------------------------------
 * 2019.07.15
 * GPS 기능 개선
 * 개선: 기능 활성화 시 위치값을 실시간으로 가져옴
 * ______________________________________________
 * 2019.09.07
 * 개선: 재부팅 후 리시버를 통해 알람 셋팅 진행 , 알람 클릭 시 MainActivity로 연결
 *
 * ----------------------------------------------
 * 2020.03.01
 * File Name 변경, Fragment Back 기능 추가, 소스 코드 리펙토링, 28 Version Up
 * WebView 접속 후, Back 기능 추가, Pie 버전에 따른 HTTP 접속 대응
 * */


public class MainActivity extends AppCompatActivity {

    private long backKeyClickTime = 0;
    private FragmentManager fragmentManager;
    private BottomNavigationView navigationView;
    // FrameLayout의 각 메뉴에 Fragement를 바꿔줌

    private homepage_Fragment homepage;
    private use_Fragment how_to_use;
    private main_Fragment main;
    private testSetting_Fragment testingSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 상태바 제거

        InitView();

        // 하단탭 선택 시 고정될 수 있도록 설정
        BottomNavigationhelper.disableShiftMode(navigationView);

        // 가장먼저 나올 화면 설정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,main).commitAllowingStateLoss();

        // 하단탭 메뉴 선택 시 호출될 메뉴 목록
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               FragmentTransaction transaction1 = fragmentManager.beginTransaction();
               switch (item.getItemId()){
                   case R.id.navigation_home:{
                       transaction1.replace(R.id.frame_layout,main).addToBackStack(null).commit();
                       break;
                   }
                   case R.id.navigation_how_to_use:{
                       transaction1.replace(R.id.frame_layout,how_to_use).addToBackStack(null).commit();
                       break;
                   }
                   case R.id.navigation_homepage:{
                       transaction1.replace(R.id.frame_layout,homepage).addToBackStack(null).commit();
                       break;
                   }
                   case R.id.navigation_test_setting:{
                       transaction1.replace(R.id.frame_layout,testingSetting).addToBackStack(null).commit();
                   }
               }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else { // 더이상 스택에 프래그먼트가 없을 시 액티비티에서 앱 종료 여부 결정
            if (System.currentTimeMillis() > backKeyClickTime + 2000) { // 1회 누를 시 Toast
                backKeyClickTime = System.currentTimeMillis();
                Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (System.currentTimeMillis() <= backKeyClickTime + 2000) { // 연속 2회 누를 시 activty shutdown
                ActivityCompat.finishAffinity(this);
            }
        }
    }

    private void InitView(){
        fragmentManager = getSupportFragmentManager();
        // FrameLayout의 각 메뉴에 Fragement를 바꿔줌
        homepage = new homepage_Fragment();
        how_to_use =new use_Fragment();
        main = new main_Fragment();
        testingSetting = new testSetting_Fragment();
        navigationView = findViewById(R.id.bottom_navigation_view);
    }
}
