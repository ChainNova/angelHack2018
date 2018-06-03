package com.angehack.sharedparking.common;

import android.util.SparseArray;
import android.view.View;

/**
 * A CommonUtil ViewHolder</br>
 *
 * @author xinglefly
 */

/**
 * use: extends BaseAdapter
 *
 * @Override public View getView(int position, View convertView, ViewGroupparent) {
 *
 *           if (convertView == null) { convertView =LayoutInflater.from(context) .inflate(R.layout.banana_phone,parent, false); }
 *
 *           ImageView bananaView = ViewHolder.get(convertView, R.id.banana);
 *
 *           BananaPhone bananaPhone = getItem(position);
 *           bananaView.setImageResource(bananaPhone.getBanana());
 *
 *           return convertView; }
 */


public class ViewHolder {
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
