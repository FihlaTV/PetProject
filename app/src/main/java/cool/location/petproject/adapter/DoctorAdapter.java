package cool.location.petproject.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cool.location.petproject.R;
import cool.location.petproject.base.BaseRVAdapter;
import cool.location.petproject.base.CCApplication;
import cool.location.petproject.base.IViewHolder;
import cool.location.petproject.bean.DoctorBean;
import de.hdodenhof.circleimageview.CircleImageView;


public class DoctorAdapter extends BaseRVAdapter<DoctorBean, DoctorAdapter.ListenerAdapterHolder> {

    private Activity mActivity;

    public void setData(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    protected ListenerAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ListenerAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listener_adapter, viewGroup, false), mActivity);
    }

    @Override
    protected void bindItemData(ListenerAdapterHolder viewHolder, DoctorBean doctorBean, int position) {
        viewHolder.bindView(doctorBean, position);
    }

    public class ListenerAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<DoctorBean> {

        @BindView(R.id.civ_avatar) CircleImageView mAvatar;
        @BindView(R.id.tv_doctor_name_adapter) TextView mDoctorName;
        @BindView(R.id.tv_doctor_work_from_adapter) TextView DoctorWorkFrom;
        @BindView(R.id.tv_doctor_good_at_adapter) TextView DoctorGoodAt;
        @BindView(R.id.tv_phone_number_adapter) TextView PhoneNumber;
        @BindView(R.id.civ_call_phone) CircleImageView mCallPhone;

        private Activity mActivity;

        public ListenerAdapterHolder(View itemView, Activity activity) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mActivity = activity;
        }

        @Override
        public void bindView(final DoctorBean doctorBean, int position) {
            mDoctorName.setText(doctorBean.getDoctorName());
            DoctorWorkFrom.setText("工作年限：" + doctorBean.getWorkFrom());
            DoctorGoodAt.setText("擅长领域：" + doctorBean.getGoodAt());
            PhoneNumber.setText("手机号：" + doctorBean.getPhoneNumber());
            try {
                Glide.with(CCApplication.getInstance())
                        .load(doctorBean.getAvatar())
                        .placeholder(R.drawable.icon_normal)
                        .fitCenter()
                        .dontAnimate()
                        .into(mAvatar);
            } catch (Exception e) {
            }
            mCallPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + doctorBean.getPhoneNumber()));
                    if (ActivityCompat.checkSelfPermission(CCApplication.getInstance(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED || mActivity == null) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mActivity.startActivity(intent);
                }
            });
        }
    }
}

