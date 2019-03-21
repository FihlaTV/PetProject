package cool.location.petproject.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cool.location.petproject.R;
import cool.location.petproject.adapter.DoctorAdapter;
import cool.location.petproject.bean.DoctorBean;


public class DoctorFragment extends Fragment implements View.OnTouchListener {

    @BindView(R.id.rlv_listener_fragment) RecyclerView mRecyclerView;
    private DoctorAdapter mDoctorAdapter;
    private List<DoctorBean> mDoctorBeanList = new ArrayList<>();
    Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
        getListenerList();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    public void getListenerList() {
        BmobQuery<DoctorBean> query = new BmobQuery<>();
        query.setLimit(50).order("createdAt")
                .findObjects(new FindListener<DoctorBean>() {
                    @Override
                    public void done(List<DoctorBean> doctorBeanList, BmobException e) {
                        if (e == null) {
                            LogUtils.d("DoctorFragment BmobQuery success:" + mDoctorAdapter);
                            mDoctorBeanList = doctorBeanList;
                            if (mDoctorAdapter == null) {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                mDoctorAdapter = new DoctorAdapter();
                                mDoctorAdapter.setOnItemClickListener(mListenerClickListener);
                                mDoctorAdapter.setDataSilently(mDoctorBeanList);
                                mRecyclerView.setAdapter(mDoctorAdapter);
                            } else {
                                mDoctorAdapter.setData(mDoctorBeanList);
                            }

                        } else {
                            LogUtils.d("DoctorFragment BmobQuery failed : " + e);
                        }
                    }
                });
    }

    private AdapterView.OnItemClickListener mListenerClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            if (mDoctorAdapter == null) { return; }
            DoctorBean doctorBean = mDoctorAdapter.getItem(position);
//            ActivityUtil.startPlayListenerActivity(DoctorFragment.this, doctorBean);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
