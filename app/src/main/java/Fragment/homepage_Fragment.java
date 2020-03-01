package Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import andbook.example.protect_me_release.MainActivity;
import andbook.example.protect_me_release.R;

public class homepage_Fragment extends Fragment {

    private WebView mWebView;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hompage, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstatanceState) {
        super.onActivityCreated(savedInstatanceState);

        mWebView = getView().findViewById(R.id.web); // 레이아웃과 연결
        mWebView.setWebViewClient(new WebViewClient()); // 클라이언트 셋팅
        WebSettings mWebSettings = mWebView.getSettings(); // 웹 셋팅
        mWebSettings.setJavaScriptEnabled(true); // 웹 사이트의 자바스크립트 사용 시 true,  미 사용시 false
        // true 사용 시 추후 xss 공격의 여지있음
        mWebView.loadUrl("https://www.naver.com"); // 연결 웹사이트 주소


        // 모바일 사이트 접속 시, Back 키를 가져와 모바일 사이트에서 뒤로가기 가능
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() != KeyEvent.ACTION_DOWN)
                    return true;

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        ((MainActivity) getActivity()).onBackPressed();
                    }
                    return true;
                }
                return false;
            }
        });
    }
}