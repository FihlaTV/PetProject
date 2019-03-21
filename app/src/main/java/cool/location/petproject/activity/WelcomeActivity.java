package cool.location.petproject.activity;

import android.os.Bundle;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cool.location.petproject.R;
import cool.location.petproject.base.BaseActivity;


public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //第一：默认初始化
        Bmob.initialize(this, "21d1459c12029098e125ce14730eb285");
        if (BmobUser.isLogin()) {
            toActivity(MainActivity.class);
        } else {
            toActivity(LoginActivity.class);
            finish();
        }
    }
}
