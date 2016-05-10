package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.*;
import com.metamall.R;
import com.metamall.Service.ImageService;
import com.metamall.model.CommentData;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/12.
 */
public class UserCommentActivity extends Activity {
    /**
     *组件
     */
    private ImageButton ibback;
    private Button btpush;
    private ImageView ivgoods;
    private RatingBar rbcomment;
    private EditText etcomment;
    /**
     * 数据
     */
    private Float starsNum;
    private static final String IMG_URL= "http://img3.imgtn.bdimg.com/it/u=3011800411,1331506788&fm=21&gp=0.jpg";   //商品图片网址
    CommentData cdata=new CommentData();

    @Override
    protected void onCreate(Bundle savedStanceState){
        super.onCreate(savedStanceState);
        setContentView(R.layout.activity_user_comment);

        ivgoods=(ImageView)findViewById(R.id.user_comment_image);
        Bitmap bitmap1 = getBitmapFromUrl(IMG_URL);
        ivgoods.setImageBitmap(bitmap1);
        ivgoods.invalidate();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_logo_image);
        BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ivgoods.setImageBitmap(UserCommentActivity.getRoundedCornerBitmap(bitmap));

        initView();

    }
    private void initView(){
        ibback=(ImageButton) findViewById(R.id.ib_back_user_comment);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rbcomment=(RatingBar) findViewById(R.id.user_comment_rating_bar);
        rbcomment.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                starsNum=rbcomment.getRating();
            }
        });
        etcomment=(EditText) findViewById(R.id.user_comment_et);
        etcomment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btpush=(Button) findViewById(R.id.comment_user_push);
        btpush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdata.setSubstance(etcomment.getText().toString());
                cdata.setStarsNum(starsNum);
                Date date = new Date();
                DateFormat dformat = new SimpleDateFormat("yyyy/MM/dd hh24:mm:ss");
                cdata.setUtc(dformat.format(date));
                Intent i=new Intent();
                i.setClass(UserCommentActivity.this,MyActivity.class);
                Toast.makeText(getApplicationContext(),"评论成功",Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //得到画布
        Canvas canvas = new Canvas(output);


        //将画布的四角圆化
        final int color = Color.RED;
        final Paint paint = new Paint();
        //得到与图像相同大小的区域  由构造的四个值决定区域的位置以及大小
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        //值越大角度越明显
        final float roundPx = 50;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        //drawRoundRect的第2,3个参数一样则画的是正圆的一角，如果数值不同则是椭圆的一角
        canvas.drawRoundRect(rectF, roundPx,roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
    /**
     * 到Url去下載圖片回傳BITMAP回來
     * @param imgUrl
     * @return
     */
    private Bitmap getBitmapFromUrl(String imgUrl) {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(IMG_URL);
            InputStream is = url.openConnection().getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);
            bis.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
//    public static void main(String[] args) throws Exception {
//        URL url=new URL("http://www.bjtime.cn");//取得资源对象
//        URLConnection uc=url.openConnection();//生成连接对象
//        uc.connect(); //发出连接
//        long ld=uc.getDate(); //取得网站日期时间
//        Date date=new Date(ld); //转换为标准时间对象
//        //分别取得时间中的小时，分钟和秒，并输出
//        System.out.print(date.getHours()+"时"+date.getMinutes()+"分"+date.getSeconds()+"秒");
//    }
//    Calendar c = Calendar.getInstance();
//
//    Integer year= c.get(Calendar.YEAR);
//    Integer month= c.get(Calendar.MONTH);
//    Integer day= c.get(Calendar.DAY_OF_MONTH);
//    Integer hour= c.get(Calendar.HOUR_OF_DAY);
//    Integer minute = c.get(Calendar.MINUTE);

}
