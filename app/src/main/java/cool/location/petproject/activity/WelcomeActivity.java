package cool.location.petproject.activity;

import android.os.Bundle;

import cn.bmob.v3.Bmob;
import cool.location.petproject.R;
import cool.location.petproject.base.BaseActivity;


public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //第一：默认初始化
        Bmob.initialize(this, "50fce2799c4f7c973de087d7b2cf6f37");
//        toActivity(MainActivity.class);
//        if (BmobUser.isLogin()) {
        toActivity(MainActivity.class);
//        } else {
//            toActivity(LoginActivity.class);
//        }
        finish();
    }
}
