package cool.location.petproject.activity;

import android.os.Bundle;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cool.location.petproject.R;
import cool.location.petproject.base.BaseActivity;
import cool.location.petproject.bean.CurrentUser;
import cool.location.petproject.utils.CurrentUserHelper;


public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //第一：默认初始化
        Bmob.initialize(this, "21d1459c12029098e125ce14730eb285");
        if (BmobUser.isLogin()) {
            CurrentUser currentUser = BmobUser.getCurrentUser(CurrentUser.class);
            CurrentUserHelper.getInstance().updateCurrentUser(currentUser);
            toActivity(MainActivity.class);
        } else {
            toActivity(LoginActivity.class);
            finish();
        }
    }
}
