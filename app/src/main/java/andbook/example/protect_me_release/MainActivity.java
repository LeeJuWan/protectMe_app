package andbook.example.protect_me_release;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

import FragmentFile.Homepage_Fragment;
import FragmentFile.How_to_use_Fragment;
import FragmentFile.Main_Fragment;
import FragmentFile.TestOrSetting_Fragment;
import Source.BottomNavigationhelper;

/*
 * Create by 이주완 2019.05.20
 * Copyright (c) 2019. 이주완 All rights reserved.
 *
 * 2019.05.20 지속적인 기능 개선 관리필요
 * 개발자 : 이주완  소속: 공주대학교 컴퓨터 공학부
 * 베타 Version
 * ----------------------------------------------
 * 2019.07.15
 * GPS 기능 개선
 * 기존: 마지막 위치값을 가져옴
 * 개선: 기능 활성화 시 위치값을 실시간으로 가져옴
 * */

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    //FrameLayout의 각 메뉴에 Fragement를 바꿔줌

    private Homepage_Fragment homepage = new Homepage_Fragment();
    private How_to_use_Fragment how_to_use =new How_to_use_Fragment();
    private Main_Fragment main = new Main_Fragment();
    private TestOrSetting_Fragment testingSetting = new TestOrSetting_Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //상태바 제거


        BottomNavigationView navigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_view);

        //하단탭 선택 시 고정될 수 있도록 설정
        BottomNavigationhelper.disableShiftMode(navigationView);

        //가장먼저 나올 화면 설정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,main).commitAllowingStateLoss();

        //하단탭 메뉴 선택 시 호출될 메뉴 목록
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               FragmentTransaction transaction1 = fragmentManager.beginTransaction();
               switch (item.getItemId()){
                   case R.id.navigation_home:{
                       transaction1.replace(R.id.frame_layout,main).commitAllowingStateLoss();
                       break;
                   }
                   case R.id.navigation_how_to_use:{
                       transaction1.replace(R.id.frame_layout,how_to_use).commitAllowingStateLoss();
                       break;
                   }
                   case R.id.navigation_homepage:{
                       transaction1.replace(R.id.frame_layout,homepage).commitAllowingStateLoss();
                       break;
                   }
                   case R.id.navigation_test_setting:{
                       transaction1.replace(R.id.frame_layout,testingSetting).commitAllowingStateLoss();
                   }
               }
                return true;
            }
        });
    }
}
