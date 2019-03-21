package cool.location.petproject.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cool.location.petproject.R;
import cool.location.petproject.base.BaseActivity;
import cool.location.petproject.bean.CurrentUser;
import cool.location.petproject.bean.Note;
import cool.location.petproject.utils.CurrentUserHelper;
import cool.location.petproject.utils.ToastHelper;

public class SendNoteActivity extends BaseActivity {

    @BindView(R.id.edtFeedbackActivityEmail) EditText edtTitle;
    @BindView(R.id.edtFeedbackActivityFeedback) EditText edtValue;
    @BindView(R.id.textView) TextView textView;
    @BindView(R.id.llFeedbackActivityCommit) LinearLayout llFeedbackActivityCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_note);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.llFeedbackActivityCommit)
    public void sendNote() {
        Note note = new Note();
        note.setNoteTitle(edtTitle.getText().toString());
        note.setNoteContent(edtValue.getText().toString());
        note.setCreatTime(System.currentTimeMillis());
        CurrentUser currentUser = CurrentUserHelper.getInstance().getCurrentUser();
        if (currentUser != null) {
            note.setSendUserName(currentUser.getUsername());
        }
        note.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    ToastHelper.showShortMessage("发布成功");
                    finish();
                } else {
                    ToastHelper.showShortMessage("发布失败");
                }
            }
        });
    }
}
