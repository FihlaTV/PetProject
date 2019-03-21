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
import cool.location.petproject.bean.BaiKeBean;

public class ReaderAdapter extends BaseRVAdapter<BaiKeBean, ReaderAdapter.ReaderAdapterHolder> {

    @Override
    protected ReaderAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ReaderAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book_reader_adapter, viewGroup, false));
    }

    @Override
    protected void bindItemData(ReaderAdapterHolder viewHolder, BaiKeBean baiKeBean, int position) {
        viewHolder.bindView(baiKeBean, position);
    }

    public class ReaderAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<BaiKeBean> {

        @BindView(R.id.tv_title_item_book_reader)
        TextView mTitle;
        @BindView(R.id.tv_writer_item_book_reader)
        TextView mWriter;
        @BindView(R.id.tv_introduce_item_book_reader)
        TextView mIntroduce;

        public ReaderAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(BaiKeBean baiKeBean, int position) {
            mTitle.setText(baiKeBean.getTitle());
            mIntroduce.setText(baiKeBean.getIntroduce());
        }
    }
}

