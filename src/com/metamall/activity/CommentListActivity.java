package com.metamall.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.metamall.R;
import com.metamall.adapter.CommentAdapter;
import com.metamall.model.CommentData;


import java.util.ArrayList;



public class CommentListActivity extends Activity {
    CommentData cdata;
    /**
     * 评论列表
     */
    private ArrayList<CommentData> comments;
    private CommentAdapter adapter;
    private ListView listView;



    /**
     * 组件
     */
    private ImageButton ibback;
    private TextView tvOverallMerit;
    private TextView tvAllComment;
    private RelativeLayout relativeLayoutAll;
    private TextView tvExcited;
    private RelativeLayout relativeLayoutExcited;
    private TextView tvNormal;
    private RelativeLayout relativeLayoutNormal;
    private TextView tvDissatisfied;
    private RelativeLayout relativeLayoutDissatisfied;
    private RatingBar rbStars;

    /**
     * 数据
     */

    SharedPreferences sharedPre=getSharedPreferences("config", MODE_PRIVATE);
    String UserName=sharedPre.getString("User", "");
    private Float AverageMerit=adapter.overallMerit();
    private Integer mExitedNum=adapter.ExcitedNum();
    private Integer mNormal=adapter.NormalNum();
    private Integer mDissatisfied=adapter.DissatisfiedNum();
    private Integer rbMin;





    @Override
    protected void onCreate(Bundle savedStanceState){
        super.onCreate(savedStanceState);
        setContentView(R.layout.activity_comment_list);
        initData();
        initView();
        getCommentProducts();
    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.comment_btn_back);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvOverallMerit=(TextView) findViewById(R.id.User_comment_score_list);
        tvOverallMerit.setText("综合评价\t（"+AverageMerit+"）");

        tvAllComment=(TextView) findViewById(R.id.comment_all_num);
        tvAllComment.setText(mDissatisfied+mNormal+mExitedNum);
        tvExcited=(TextView) findViewById(R.id.comment_excited_num);
        tvExcited.setText(mExitedNum);
        tvNormal=(TextView) findViewById(R.id.comment_normal_num);
        tvNormal.setText(mNormal);
        tvDissatisfied=(TextView) findViewById(R.id.comment_dissatisfied_num);
        tvDissatisfied.setText(mDissatisfied);

        rbStars=(RatingBar) findViewById(R.id.comment_rating_bar);
        rbStars.setRating(this.OverAllStar());
        listView=(ListView)findViewById(R.id.comment_list);
        listView.setAdapter(adapter);

        relativeLayoutAll=(RelativeLayout) findViewById(R.id.comment_all);
        relativeLayoutAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getCommentNum().get();
            }
        });
        relativeLayoutExcited=(RelativeLayout) findViewById(R.id.comment_excited);
        relativeLayoutExcited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getCommentNum().get();
            }
        });
        relativeLayoutNormal=(RelativeLayout) findViewById(R.id.comment_normal);
        relativeLayoutNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getCommentNum().get();
            }
        });
        relativeLayoutDissatisfied=(RelativeLayout) findViewById(R.id.comment_dissatisfied);
        relativeLayoutDissatisfied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getCommentNum().get();
            }
        });

    }
    /**
     * 初始化数据
     */
    private void initData() {
        comments = new ArrayList<CommentData>();
        adapter = new CommentAdapter(this, comments,
                R.layout.comment_list);
    }
    /**
     * 从服务器获取购物车商品数据
     */
    private void getCommentProducts() {
        ArrayList<CommentData> list = new ArrayList<CommentData>();
        for (int i = 0; i < comments.size(); i++) {
            cdata.setnickname(UserName);
            cdata.setspecification("规格|淡蓝修身");
            cdata.getutc();
            cdata.getsubstance();
            cdata.getstarsNum();
            list.add(cdata);
        }
        comments.addAll(list);
        for (int i = 0; i < comments.size(); i++) {
            adapter.getCommentNum().put(i, 1);
        }

    }
    /**
     * 比较与0.5/1/1.5/2/2.5/3/3.5/4/4.5/5绝对值（abs(int)）的差值那个最大
     */

    public int OverAllStar(){
        // TODO Auto-generated method stub
        double[] arr = new double[]{java.lang.Math.abs(AverageMerit-1), java.lang.Math.abs(AverageMerit-2)
                ,java.lang.Math.abs(AverageMerit-3),java.lang.Math.abs(AverageMerit-4)
                ,java.lang.Math.abs(AverageMerit-5)};
        Double amin = getMax_Min(arr);
        if(amin==java.lang.Math.abs(AverageMerit-1)){
            rbMin=1;
        }else if(amin==java.lang.Math.abs(AverageMerit-2)){
            rbMin=2;
        }else if(amin==java.lang.Math.abs(AverageMerit-3)){
            rbMin=3;
        }else if(amin==java.lang.Math.abs(AverageMerit-4)){
            rbMin=4;
        }else {
            rbMin=5;
        }
        return rbMin;


    }
    public static Double getMax_Min(double[] arr){
        double min = arr[0];
        for(int x = 1;x<arr.length;x++){

            if(min>arr[x])
                min = arr[x];
        }
        return min;
    }
}
