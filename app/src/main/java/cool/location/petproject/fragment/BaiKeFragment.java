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
import cool.location.petproject.adapter.ReaderAdapter;
import cool.location.petproject.bean.BaiKeBean;
import cool.location.petproject.utils.ActivityUtil;


public class BaiKeFragment extends Fragment implements View.OnTouchListener {

    @BindView(R.id.rlv_book_reader) RecyclerView mRecyclerView;
    Unbinder unbinder;
    private List<BaiKeBean> mBaiKeBeanList = new ArrayList<>();
    private ReaderAdapter mReaderAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
        getBookList();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    public void getBookList() {
        BmobQuery<BaiKeBean> query = new BmobQuery<>();
        query.setLimit(50).order("createdAt")
                .findObjects(new FindListener<BaiKeBean>() {
                    @Override
                    public void done(List<BaiKeBean> baiKeBeanList, BmobException e) {
                        if (e == null) {
                            LogUtils.d("BaiKeFragment BmobQuery success:" + baiKeBeanList);
                            mBaiKeBeanList = baiKeBeanList;
                            if (mReaderAdapter == null) {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                mReaderAdapter = new ReaderAdapter();
                                mReaderAdapter.setOnItemClickListener(mBookClickListener);
                                mReaderAdapter.setDataSilently(mBaiKeBeanList);
                                mRecyclerView.setAdapter(mReaderAdapter);
                            } else {
                                mReaderAdapter.setData(mBaiKeBeanList);
                            }

                        } else {
                            LogUtils.d("BaiKeFragment BmobQuery failed : " + e);
                        }
                    }
                });
    }

    private AdapterView.OnItemClickListener mBookClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            if (mReaderAdapter == null) { return; }
            BaiKeBean baiKeBean = mReaderAdapter.getItem(position);
            ActivityUtil.startBookReaderActivity(BaiKeFragment.this, baiKeBean);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
