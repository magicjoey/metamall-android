package com.metamall.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.widget.RatingBar;
import android.widget.TextView;
import com.metamall.R;
import com.metamall.model.CommentData;
import com.metamall.model.ProductData;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public class CommentAdapter extends UniversalAdapter<CommentData>{
    private SparseIntArray nums;
    private int index = 1;
    private String value = "value";

    public CommentAdapter(Context context, List<CommentData> datas,
                           int itemLayoutId) {
        super(context, datas, itemLayoutId);
        nums = new SparseIntArray();

    }

    @Override
    public void updateItem(final ViewHolder holder, final CommentData data) {
        holder.setTextViewText(R.id.user_comment_name_data, data.getnickname()+data.getutc());
        holder.setTextViewText(R.id.goods_comment_style,data.getspecification());
        holder.setTextViewText(R.id.user_goods_comment_textview,data.getsubstance());
        RatingBar rbRating=holder.getView(R.id.room_ratingbar_comment);
        rbRating.setRating(data.getstarsNum());



    }
    public SparseIntArray getCommentNum() {
        return nums;
    }
    public void commentSparseArray() {
        //创建一个SparseArray对象
        SparseArray<String> sparseArray = new SparseArray<String>();

        //向sparseArray存入元素value，key为index
        sparseArray.put(index, value);

        //sparseArray.indexOfKey(index);
        //查找value所在的位置，如果不存在，则返回-1
        //sparseArray.indexOfValue(value);
        //更新某个key的值
        sparseArray.setValueAt(index, value);
        //获取index所对应的值，没有则返回null
        sparseArray.get(index);
        //获取index所对应的值，没有则返回自定义的默认值"default-value"
        //sparseArray.get(index,"default-value");
        //删除index对应的元素
        sparseArray.delete(index);
        //移除，本质也是调用delete(int)方法
        //sparseArray.remove(index);
        //清空所有数据
        //sparseArray.clear();
    }
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


}
