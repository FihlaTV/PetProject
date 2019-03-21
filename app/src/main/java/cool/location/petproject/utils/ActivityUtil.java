package cool.location.petproject.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import cool.location.petproject.R;
import cool.location.petproject.activity.PlayVideoActivity;
import cool.location.petproject.activity.ReaderBaiKeActivity;
import cool.location.petproject.bean.BaiKeBean;
import cool.location.petproject.bean.VideoBean;
import cool.location.petproject.constants.AppConstant;


public class ActivityUtil {

    public static boolean isFinishing(Activity activity) {
        return (activity == null || activity.isFinishing());
    }

    public static void startActivity(Activity activity, Class targetClass) {
        Intent intent = new Intent(activity, targetClass);
        activity.startActivity(intent);
    }

    public static void startBookReaderActivity(Fragment fragment, BaiKeBean baiKeBean) {
        if (fragment == null || fragment.getActivity() == null) { return; }
        Intent intent = new Intent(fragment.getActivity(), ReaderBaiKeActivity.class);
        intent.putExtra(AppConstant.IntentKey.EXTRA_DATA, baiKeBean);
        fragment.startActivity(intent);
        fragment.getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.slide_in_from_middle_to_middle);
    }


    public static void startPlayVideoActivity(Fragment fragment, VideoBean videoBean) {
        if (fragment == null || fragment.getActivity() == null) { return; }
        Intent intent = new Intent(fragment.getActivity(), PlayVideoActivity.class);
        intent.putExtra(AppConstant.IntentKey.EXTRA_DATA, videoBean);
        fragment.startActivity(intent);
        fragment.getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.slide_in_from_middle_to_middle);
    }
}
