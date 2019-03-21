package cool.location.petproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cool.location.petproject.R;
import cool.location.petproject.base.BaseRVAdapter;
import cool.location.petproject.base.IViewHolder;
import cool.location.petproject.bean.DoctorBean;


public class DoctorAdapter extends BaseRVAdapter<DoctorBean, DoctorAdapter.ListenerAdapterHolder> {

    @Override
    protected ListenerAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ListenerAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_listener_adapter, viewGroup, false));
    }

    @Override
    protected void bindItemData(ListenerAdapterHolder viewHolder, DoctorBean doctorBean, int position) {
        viewHolder.bindView(doctorBean, position);
    }

    public class ListenerAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<DoctorBean> {

        @BindView(R.id.tv_title_item_listener)
        TextView mTitle;
        @BindView(R.id.tv_introduce_item_listener)
        TextView mIntroduce;

        public ListenerAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(DoctorBean doctorBean, int position) {
            mTitle.setText(doctorBean.getDoctorName());
            mIntroduce.setText(doctorBean.getGoodAt());
        }
    }
}

