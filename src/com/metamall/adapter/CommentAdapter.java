package com.metamall.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

import android.util.SparseIntArray;
import android.widget.RatingBar;

import com.metamall.R;
import com.metamall.model.CommentData;

import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public class CommentAdapter extends UniversalAdapter<CommentData>{
    private SparseIntArray nums;




    public CommentAdapter(Context context, List<CommentData> datas,
                           int itemLayoutId) {
        super(context, datas, itemLayoutId);
        nums = new SparseIntArray();

    }

    @Override
    public void updateItem(final ViewHolder holder, final CommentData cdata) {
        holder.setTextViewText(R.id.user_comment_name_data, cdata.getnickname()+cdata.getutc());
        holder.setTextViewText(R.id.goods_comment_style,cdata.getspecification());
        holder.setTextViewText(R.id.user_goods_comment_textview,cdata.getsubstance());
        RatingBar rbRating=holder.getView(R.id.room_ratingbar_comment);
        rbRating.setRating(cdata.getstarsNum());



    }
    public SparseIntArray getCommentNum() {
        return nums;
    }
    //public void commentSparseArray() {
        //创建一个SparseArray对象
      //  SparseArray<String> sparseArray = new SparseArray<String>();

        //向sparseArray存入元素value，key为index
//        sparseArray.put(index, value);

        //sparseArray.indexOfKey(index);
        //查找value所在的位置，如果不存在，则返回-1
        //sparseArray.indexOfValue(value);
        //更新某个key的值
//        sparseArray.setValueAt(index, value);
        //获取index所对应的值，没有则返回null
  //      sparseArray.get(index);
        //获取index所对应的值，没有则返回自定义的默认值"default-value"
        //sparseArray.get(index,"default-value");
        //删除index对应的元素
    //    sparseArray.delete(index);
        //移除，本质也是调用delete(int)方法
        //sparseArray.remove(index);
        //清空所有数据
        //sparseArray.clear();
  //  }
   // public void testSparseBooleanArray()
    //{

//		SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        //这种创建方式可以设置容器的大小
      //  SparseBooleanArray sparseBooleanArray = new SparseBooleanArray(5);


        //存入数据，同样有两种方法
//        sparseBooleanArray.put(int, boolean);

//        sparseBooleanArray.append(int, boolean);

        //根据key获取对应的boolean值，没有则返回false
//        sparseBooleanArray.get(key);
        //跟上面类似,valueIfKeyNotFound是自定义的假设不存在则返回的默认值
  //      sparseBooleanArray.get(key, valueIfKeyNotFound);

        //获取第5个位置的键值
    //    sparseBooleanArray.keyAt(5);
        //获取第5个元素的值
      //  sparseBooleanArray.valueAt(5);

        //删除某个key的元素
//        sparseBooleanArray.delete(key);
        //清除所有
  //      sparseBooleanArray.clear();

   // }
    /**
     * 计算平均评分
     */
    public float overallMerit() {
        float Merit = 0;
        float averageMerit=0;
        for (int i = 0; i < datas.size(); i++) {
            Merit += (nums.get(i)) * (datas.get(i).getstarsNum());
            averageMerit=Merit/datas.size();

        }

        return averageMerit;

    }
    /**
     * 得到喜欢人数
     */
    public int ExcitedNum(){
        Integer count=0;
        Integer excitedNum=0;


        for(int i=0;i<datas.size();i++){
            if(datas.get(i).getstarsNum()==5||datas.get(i).getstarsNum()==4){
                nums.put(excitedNum,datas.hashCode());                                          //todo  datas.?????
                count++;
            }
            excitedNum=count;

        }
        return excitedNum;
    }

    /**
     * 得到一般人数
     */
    public int NormalNum(){
        Integer count=0;
        Integer normalNum=0;
        for(int i=0;i<datas.size();i++){
            if(datas.get(i).getstarsNum()==3||datas.get(i).getstarsNum()==2){
                nums.put(normalNum,datas.hashCode());                                           //todo
                count++;
            }
            normalNum=count;
        }
        return normalNum;


    }

    /**
     * 得到不喜欢人数
     */
    public int DissatisfiedNum(){
        Integer count=0;
        Integer dissatisfiedNum=0;
        for(int i=0;i<datas.size();i++){
            if(datas.get(i).getstarsNum()==1){
                nums.put(dissatisfiedNum,datas.hashCode());                                  //todo
                count++;
            }
            dissatisfiedNum=count;
        }
        return dissatisfiedNum;
    }
    /**
     * 保存数据
     */




}
