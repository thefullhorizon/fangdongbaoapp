package com.hx.fdb.ui.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hx.fdb.R;

/**
 * 自定义dialog listview
 * 可以定制显示动画
 * Created by yanxin on 17/4/13.
 */
public class ListDialog extends AlertDialog {

    private ListView listView;
    private TextView titleTxt;
    private DataAdapter dataAdapter;
    private OnItemClickListener onItemClickListener;

    protected ListDialog(@NonNull Context context) {
        super(context,R.style.MyDialog);
    }

    protected ListDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public static ListDialog create(Context context) {
        ListDialog dialog = new ListDialog(context);
        DialogCollection.getInstance().put(context.getClass(),dialog);
        dialog.setView(LayoutInflater.from(context).inflate(R.layout.dialog_list,null));
        return dialog;
    }

    public static ListDialog create(Context context, View view) {
        ListDialog dialog = new ListDialog(context);
        DialogCollection.getInstance().put(context.getClass(),dialog);
        dialog.setView(view);
        return dialog;
    }

    public static ListDialog create(Context context, int theme, View view) {
        ListDialog dialog = new ListDialog(context,theme);
        DialogCollection.getInstance().put(context.getClass(),dialog);
        dialog.setView(view);
        return dialog;
    }

    @Override
    public void setView(View view) {
        super.setView(view);
        this.titleTxt = (TextView) view.findViewById(R.id.titleTxt);
        this.listView = (ListView) view.findViewById(R.id.list);
        this.listView.setAdapter(new Adapter());
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    public void setAdapter(BaseAdapter adapter) {
        this.listView.setAdapter(adapter);
    }

    public void setTitle(CharSequence charSequence) {
        this.titleTxt.setText(charSequence);
    }

    public void setDataAdapter(DataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataAdapter!=null?dataAdapter.getCount():0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_list_item,null);
            }
            ((TextView)convertView.findViewById(R.id.itemTxt)).setText(dataAdapter.getItem(position));
            return convertView;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public static abstract class DataAdapter {
        public abstract int getCount();
        public abstract String getItem(int position);
    }

}
