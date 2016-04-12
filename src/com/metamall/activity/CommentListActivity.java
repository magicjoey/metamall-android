package com.metamall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import com.metamall.R;
import com.metamall.model.CommentData;
import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/4/11.
 */
public class CommentListActivity extends Activity {
    CommentData cdata;

    /**
     * 组件
     */
    private ImageButton ibback;
    private TextView tv_overall_merit;
    private TextView tv_all_comment;
    private TextView tv_excited;
    private TextView tv_normal;
    private TextView tv_dissatisfied;
    private RatingBar rb_stars;

    @Override
    protected void onCreate(Bundle savedStanceState){
        super.onCreate(savedStanceState);
        setContentView(R.layout.activity_comment_list);
        initView();
    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.comment_btn_back);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_overall_merit=(TextView) findViewById(R.id.User_comment_score);
        tv_all_comment=(TextView) findViewById(R.id.comment_all_num);
        tv_excited=(TextView) findViewById(R.id.comment_excited_num);
        tv_normal=(TextView) findViewById(R.id.comment_normal_num);
        tv_dissatisfied=(TextView) findViewById(R.id.comment_dissatisfied_num);
        rb_stars=(RatingBar) findViewById(R.id.comment_rating_bar);

    }

}
