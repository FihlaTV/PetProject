package cool.location.petproject.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cool.location.petproject.R;
import cool.location.petproject.base.BaseActivity;
import cool.location.petproject.base.TabEntity;
import cool.location.petproject.fragment.BaiKeFragment;
import cool.location.petproject.fragment.DoctorFragment;
import cool.location.petproject.fragment.MeFragment;
import cool.location.petproject.fragment.NoteFragment;
import cool.location.petproject.utils.ToastHelper;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout_main_activity) CommonTabLayout mCommonTabLayout;
    @BindView(R.id.ll_main_activity) LinearLayout activityMain;

    private NoteFragment mNoteFragment;
    private BaiKeFragment mBaiKeFragment;
    private DoctorFragment mDoctorFragment;
    private MeFragment mMeFragment;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String[] mTitles = {"宠物百科", "宠物贴吧", "宠物医疗", "我的"};
    private int[] mIconUnselectIds = {R.drawable.icon_message_unpress, R.drawable.icon_job_unpress, R.drawable.icon_discover_unpress, R.drawable.icon_me_unpress};
    private int[] mIconSelectIds = {R.drawable.icon_message_press, R.drawable.icon_job_press, R.drawable.icon_discover_press, R.drawable.icon_me_press};
    private long firstBack = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //第一：默认初始化
        Bmob.initialize(this, "21d1459c12029098e125ce14730eb285");
        initTab();
        initFragment();
        getPermission();
    }

    public void getPermission() {
        PermissionUtils.permission(PermissionConstants.PHONE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {

                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            PermissionUtils.launchAppDetailsSettings();
                        }
                    }
                }).request();
    }

    private void initTab() {
        for (int i = 0; i < mIconSelectIds.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mCommonTabLayout.setTabData(mTabEntities);
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;

        if (mBaiKeFragment == null) {
            mBaiKeFragment = new BaiKeFragment();
            transaction.add(R.id.ll_main_activity, mBaiKeFragment);
        }

        if (mNoteFragment == null) {
            mNoteFragment = new NoteFragment();
            transaction.add(R.id.ll_main_activity, mNoteFragment);
        }

        if (mDoctorFragment == null) {
            mDoctorFragment = new DoctorFragment();
            transaction.add(R.id.ll_main_activity, mDoctorFragment);
        }
        if (mMeFragment == null) {
            mMeFragment = new MeFragment();
            transaction.add(R.id.ll_main_activity, mMeFragment);
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        mCommonTabLayout.setCurrentTab(currentTabPosition);
    }

    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.show(mBaiKeFragment);
                transaction.hide(mNoteFragment);
                transaction.hide(mDoctorFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.show(mNoteFragment);
                transaction.hide(mBaiKeFragment);
                transaction.hide(mDoctorFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.show(mDoctorFragment);
                transaction.hide(mNoteFragment);
                transaction.hide(mBaiKeFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                transaction.show(mMeFragment);
                transaction.hide(mNoteFragment);
                transaction.hide(mBaiKeFragment);
                transaction.hide(mDoctorFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstBack < 2000) {
            super.onBackPressed();
        } else {
            firstBack = System.currentTimeMillis();
            ToastHelper.showShortMessage(R.string.back_btn_exit_pop);
        }
    }

}