package com.capgemini.SalesOrder;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capgemini.SalesOrder.zgwsample_srv.v0.entitytypes.SalesOrderLineItem;

/**
 * List adapter.
 */
public class Page3ListAdapter extends BaseAdapter
{	
	private List<SalesOrderLineItem> entries;
	private List<SalesOrderLineItem> filteredEntries;

	private Context mContext;

	/**
	 * Constructs a new list adapter with the given context.
	 * @param c - application context.
	 */
	public Page3ListAdapter(Context c)
	{
		mContext = c;
	}

	/**
	 * Returns the list of entries.
	 * @return - list of entries.
	 */
	public List<SalesOrderLineItem> getEntries()
	{
		return entries;
	}

	/**
	 * Sets the given entries.
	 * @param entries
	 */
	public void setEntries(List<SalesOrderLineItem> entries)
	{
		this.entries = entries;
		this.filteredEntries = entries;
	}

	/**
	 * Returns the number of entries.
	 * @return - the number of entries.
	 */
	public int getCount()
	{
		return filteredEntries.size();
	}

	/**
	 * Returns the item in the given position.
	 * @param position - the position of the desired item.
	 * @return - the item in the given position.
	 */
	public Object getItem(int position)
	{
		return filteredEntries.get(position);
	}

	/**
	 * Returns the id of the item in the given position.
	 * @param position - the position of the item.
	 * @return - the id of the item in the given position.
	 */
	public long getItemId(int position)
	{
		return position;
	}
	
	private static class ViewHolder 
	{
		public LinearLayout ll;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View rowView = convertView;
		
		if (rowView == null) 
		{
			LayoutInflater mInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// Inflate a view template
			rowView = mInflater.inflate(com.capgemini.SalesOrder.R.layout.item_entry, parent, false);
			
			ViewHolder holder = new ViewHolder();
			holder.ll = (LinearLayout) rowView.findViewById(com.capgemini.SalesOrder.R.id.linearLayout1);
			holder.ll.setPadding(10, 10, 10, 10);
			holder.ll.setOrientation(LinearLayout.VERTICAL);
			
			rowView.setTag(holder);
		}

		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.ll.removeAllViews();
		
		SalesOrderLineItem entry = filteredEntries.get(position);
		
		TextView ProductIdTextView = new TextView(mContext);
		//set the first property to bigger font
		ProductIdTextView.setTextSize(22); 
		ProductIdTextView.setText(getPropertyValue(String.valueOf(entry.getProductId())));
		holder.ll.addView(ProductIdTextView);	
		
		TextView QuantityTextView = new TextView(mContext);
		QuantityTextView.setText("Quantity: " + getPropertyValue(String.valueOf(entry.getQuantity())));
		holder.ll.addView(QuantityTextView);	
		
		TextView AmountTextView = new TextView(mContext);
		AmountTextView.setText("Amount: " + getPropertyValue(String.valueOf(entry.getGrossAmount())) + " " + getPropertyValue(String.valueOf(entry.getCurrencyCode())) + ", includes Tax: " + getPropertyValue(String.valueOf(entry.getTaxAmount())) + " " + getPropertyValue(String.valueOf(entry.getCurrencyCode())));
		holder.ll.addView(AmountTextView);
		
		TextView PosTextView = new TextView(mContext);
		PosTextView.setText("PO Item Pos: " + getPropertyValue(String.valueOf(entry.getSoItemPos())));
		holder.ll.addView(PosTextView);
		
		TextView DateTextView = new TextView(mContext);
		DateTextView.setText(getPropertyValue("Delivery data: " + String.valueOf(entry.getDeliveryDate())));
		holder.ll.addView(DateTextView);	
		
		return rowView;
	}
	
	/**
	 * Returns the property value.
	 * @param value
	 * @return - property value.
	 */
	public String getPropertyValue(String value)
	{
		if (value.equalsIgnoreCase("null"))
		{
			return mContext.getString(com.capgemini.SalesOrder.R.string.no_value);
		}

		return value;
	}

	/**
	 * Filters the items by the given constraint.
	 * @param constraint
	 */
	public void filter(CharSequence constraint)
	{
		if (constraint != null)
		{
			constraint = constraint.toString().toLowerCase();
			this.filteredEntries = new ArrayList<SalesOrderLineItem>();
			for (SalesOrderLineItem entry : entries)
			{
				ArrayList<String> values = new ArrayList<String>();

				values.add(String.valueOf(entry.getProductId()).toLowerCase());

				boolean found = false;
				for (String s : values)
				{
					if (s != null && s.contains(constraint))
					{
						found = true;
						break;
					}
				}
				
				if(found)
					filteredEntries.add(entry);
			}
		} 
		else
		{
			this.filteredEntries = entries;
		}
		
		notifyDataSetChanged();
	}
}