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
import cool.location.petproject.bean.Book;

public class ReaderAdapter extends BaseRVAdapter<Book, ReaderAdapter.ReaderAdapterHolder> {

    @Override
    protected ReaderAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ReaderAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book_reader_adapter, viewGroup, false));

    }

    @Override
    protected void bindItemData(ReaderAdapterHolder viewHolder, Book book, int position) {
        viewHolder.bindView(book, position);
    }

    public class ReaderAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<Book> {

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
        public void bindView(Book book, int position) {
            mTitle.setText(book.getTitle());
            mWriter.setText(book.getWriter());
            mIntroduce.setText(book.getIntroduce());
        }
    }
}

