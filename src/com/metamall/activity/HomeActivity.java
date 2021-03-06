package com.metamall.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.metamall.Dialog.CustomProgressDialog;
import com.metamall.R;
import com.metamall.activity.Home.ConvenientBanner.Transformer;
import com.metamall.Search.SearchActivity;
import com.metamall.activity.Home.CBViewHolderCreator;
import com.metamall.activity.Home.ConvenientBanner;
import com.metamall.activity.Home.LocalImageHolderView;
import com.metamall.activity.SonActivity.*;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class HomeActivity extends Activity implements AdapterView.OnItemClickListener {
    private EditText ethome_top_et_search;
    private Button btntimingDepreciate;
    private Button btnTopHot;
    private Button btnBargainSale;
    private Button btnPreaching;
    private Button ibWeekGoods;
    private Button ibLastCrazy;
    private Button ibGoodsEveryDay;
    private Button ibForSale;
    private CustomProgressDialog progressDialog = null;
    
    View Include1=(View) findViewById(R.id.home_include_1);
    View Include2=(View) findViewById(R.id.home_include_2);
    View Include3=(View) findViewById(R.id.home_include_3);
    View Include4=(View) findViewById(R.id.home_include_4);
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initViews();
        init();
        //图片资源
        String url = "http://s16.sinaimg.cn/orignal/89429f6dhb99b4903ebcf&690";
        //得到可用的图片
        Bitmap bitmap = getHttpBitmap(url);
        imageView1 = (ImageView) Include1.findViewById(R.id.pd_viewPager);
        //显示
        imageView1.setImageBitmap(bitmap);

    }




    private void initView() {
        ethome_top_et_search = (EditText) findViewById(R.id.home_top_et_search);
        ethome_top_et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(HomeActivity.this, SearchActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });


    }

    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private List<String> networkImages;
    private String[] images = {"http://liaoning.sinaimg.cn/2014/1111/U10435P1195DT20141111221439.jpg",
            "http://img6.faloo.com/Picture/0x0/0/93/93801.jpg",
            "http://www.peixunwang.com.cn/file/upload/image/2015/05/03/1430615089872781.jpg",
            "http://img.9eye.cn/artist/4611.jpg",
            "http://www.lznews.cn/uploadfile/2014/0506/20140506082640356.jpeg"

    };

    private ListView listView;
    private ArrayAdapter transformerArrayAdapter;
    private ArrayList<String> transformerList = new ArrayList<String>();


    private void initViews() {
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
        listView = (ListView) findViewById(R.id.listView);
        transformerArrayAdapter = new ArrayAdapter(this, R.layout.adapter_transformer, transformerList);
        listView.setAdapter(transformerArrayAdapter);
        listView.setOnItemClickListener(this);
        btntimingDepreciate=(Button) findViewById(R.id.erHuo_home);
        btntimingDepreciate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(HomeActivity.this, TimingDepreciateActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        btnTopHot=(Button) findViewById(R.id.goodSeal_home);
        btnTopHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(HomeActivity.this, TopHotActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        btnBargainSale=(Button) findViewById(R.id.teJia_home);
        btnBargainSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(HomeActivity.this, BargainSaleActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        btnPreaching=(Button) findViewById(R.id.question_home);
        btnPreaching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent();
                i.setClass(HomeActivity.this, PreachingActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        ibWeekGoods=(Button) findViewById(R.id.forMore_weekDated);
        ibWeekGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(HomeActivity.this, WeekGoodsActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        ibGoodsEveryDay=(Button) findViewById(R.id.forMore_goods_everyday);
        ibGoodsEveryDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent();
                i.setClass(HomeActivity.this,GoodsEveryDayActivity.class);
                finish();
            }
        });
        ibLastCrazy=(Button) findViewById(R.id.forMore_last_crazy);
        ibLastCrazy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(HomeActivity.this,LastCrazyActivity.class);
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        ibForSale=(Button) findViewById(R.id.forMore_for_sell);
        ibForSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(HomeActivity.this,ForSealActivity.class);
                finish();
            }
        });
    }

    private void init() {
        initImageLoader();
        loadTestDatas();
        //本地图片例子
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置翻页的效果，不需要翻页效果可用不设
                .setPageTransformer(Transformer.DefaultTransformer);

//        convenientBanner.setManualPageable(false);设置不能手动影响

        //网络加载例子
//        networkImages=Arrays.asList(images);
//        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
//            @Override
//            public NetworkImageHolderView createHolder() {
//                return new NetworkImageHolderView();
//            }
//        },networkImages);
    }

    //初始化网络图片缓存库
    private void initImageLoader() {

        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_default_adimage)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    /*
    加入测试Views
    * */
    private void loadTestDatas() {
        //本地图片集合
        for (int position = 0; position < 7; position++)
            localImages.add(getResId("ic_test_" + position, R.drawable.class));


        //各种翻页效果
        transformerList.add(Transformer.DefaultTransformer.getClassName());
        transformerList.add(Transformer.AccordionTransformer.getClassName());
        transformerList.add(Transformer.BackgroundToForegroundTransformer.getClassName());
        transformerList.add(Transformer.CubeInTransformer.getClassName());
        transformerList.add(Transformer.CubeOutTransformer.getClassName());
        transformerList.add(Transformer.DepthPageTransformer.getClassName());
        transformerList.add(Transformer.FlipHorizontalTransformer.getClassName());
        transformerList.add(Transformer.FlipVerticalTransformer.getClassName());
        transformerList.add(Transformer.ForegroundToBackgroundTransformer.getClassName());
        transformerList.add(Transformer.RotateDownTransformer.getClassName());
        transformerList.add(Transformer.RotateUpTransformer.getClassName());
        transformerList.add(Transformer.StackTransformer.getClassName());
        transformerList.add(Transformer.ZoomInTransformer.getClassName());
        transformerList.add(Transformer.ZoomOutTranformer.getClassName());

        transformerArrayAdapter.notifyDataSetChanged();
    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 开始自动翻页
    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    // 停止自动翻页
    @Override
    protected void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    //点击切换效果
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String name = transformerList.get(position);
        Transformer transformer = Transformer.valueOf(name);
        convenientBanner.setPageTransformer(transformer);
    }

    /**
     * 获取网落图片资源
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return bitmap;

    }
    private void startProgressDialog(){
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
            progressDialog.setMessage("正在加载中...");
        }

        progressDialog.show();
    }

    private void stopProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}










