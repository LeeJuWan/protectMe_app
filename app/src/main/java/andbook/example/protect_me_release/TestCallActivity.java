package andbook.example.protect_me_release;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class TestCallActivity extends AppCompatActivity {

    private final String Phone_regex = "^\\d{3}\\d{3,4}\\d{4}$";

    private Button testCall_btn;
    private EditText testCall_value;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcall);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 상태바 제거

        Initialize();

        testCall_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(testCall_value.getText().toString().getBytes().length > 0 && !"".equals(testCall_value.getText().toString().trim())){
                    if(Pattern.matches(Phone_regex,testCall_value.getText().toString().trim())){
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+testCall_value.getText().toString().trim()));
                        startActivity(intent);
                    }
                    else{
                        new AlertDialog.Builder(context, R.style.MyCustomDialogStyle)
                                .setCancelable(false)
                                .setTitle("입력값 오류")
                                .setIcon(R.drawable.icon96)
                                .setMessage("테스트하고자 하는 번호를 올바르게 입력해주세요")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                }
                else{
                    new AlertDialog.Builder(context, R.style.MyCustomDialogStyle)
                            .setCancelable(false)
                            .setTitle("입력값 오류")
                            .setIcon(R.drawable.icon96)
                            .setMessage("테스트하고자 하는 번호를 올바르게 입력해주세요")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        });
    }

    private void  Initialize(){
        testCall_btn = findViewById(R.id.Test_call_action);
        testCall_value = findViewById(R.id.Test_call_edit);
        context = this;
    }
}
