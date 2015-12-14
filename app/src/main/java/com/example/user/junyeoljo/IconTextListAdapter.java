package com.example.user.junyeoljo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015-12-13.
 */
public class IconTextListAdapter extends BaseAdapter {

    private Context mContext;

    private List<IconTextItem> mItems = new ArrayList<IconTextItem>();

    public IconTextListAdapter(Context context) {
        mContext = context;
    }

    public void addItem(IconTextItem it) {
        mItems.add(it);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        this.notifyDataSetChanged();
    }

    public void setListItems(List<IconTextItem> lit) {
        mItems = lit;
    }

    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public Object getItemDay(int position) {
        return mItems.get(position).getData(0);
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isSelectable(int position) {
        try {
            return mItems.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final IconTextView itemView;
        final int pos = position;

        if (convertView == null) {
            itemView = new IconTextView(mContext, mItems.get(position));
        } else {
            itemView = (IconTextView) convertView;

            itemView.setText(0, mItems.get(position).getData(0));
            itemView.setText(1, mItems.get(position).getData(1));
        }

        return itemView;
    }

}
