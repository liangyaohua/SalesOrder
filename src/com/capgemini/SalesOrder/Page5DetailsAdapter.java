package com.capgemini.SalesOrder;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.capgemini.SalesOrder.zgwsample_srv.v0.entitytypes.Product;
	
/**
 * Details adapter.
 */
public class Page5DetailsAdapter extends BaseAdapter
{
	public static enum SapSemantics {tel, email, url};
	

	private Context mContext;
	private Product entry;

	private List<String> propertiesValues = new ArrayList<String>();
	private List<String> labels = new ArrayList<String>();
	private List<String> sapSemanticsList = new ArrayList<String>();

	/**
	 * Constructs a new details adapter with the given parameters.
	 * @param c - application context.
	 * @param e - entry.
	 */
	public Page5DetailsAdapter(Context c, Product e)
	{
		
		mContext = c;
		entry = e;
		propertiesValues.add(String.valueOf(entry.getName()));
		labels.add(Product.getLabelForProperty("Product"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getPrice()) + " " + String.valueOf(entry.getCurrencyCode()));
		labels.add(Product.getLabelForProperty("Price"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getCategory()));
		labels.add(Product.getLabelForProperty("Category"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getTypeCode()));
		labels.add(Product.getLabelForProperty("Type Code"));
		sapSemanticsList.add(null);
		propertiesValues.add(String.valueOf(entry.getDescription()));
		labels.add(Product.getLabelForProperty("Description"));
		sapSemanticsList.add(null);
	}

	/**
	 * Returns the amount of entries.
	 * @return - the amount of entries.
	 */
	public int getCount()
	{
		return propertiesValues.size() + 1;
	}

	/**
	 * Returns the item in the given position.
	 * @param position - the position of the desired item.
	 * @return - the item in the given position.
	 */
	public Object getItem(int position)
	{
		return position;
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
	
	private class ViewHolder 
	{
		public ImageView imageView;
		public TextView textView1;
		public TextView textView2;
		public TableRow tableRow1;
		public TableRow tableRow2;
		public TextView textView0;
	}
		
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View rowView = convertView;
		
		if (rowView == null || position == propertiesValues.size()) 
		{			
			LayoutInflater mInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			// Inflate a view template
			rowView = mInflater.inflate(com.capgemini.SalesOrder.R.layout.item_detail_row, parent, false);
			
			ViewHolder holder = new ViewHolder();
			
			holder.textView1 = (TextView) rowView.findViewById(com.capgemini.SalesOrder.R.id.textView1);
			holder.textView1.setTextSize(22);
			holder.textView2 = (TextView) rowView.findViewById(com.capgemini.SalesOrder.R.id.textView2);
			holder.imageView = (ImageView) rowView.findViewById(com.capgemini.SalesOrder.R.id.imageView1);
			holder.tableRow1 = (TableRow) rowView.findViewById(com.capgemini.SalesOrder.R.id.tableRow1);
			holder.tableRow2 = (TableRow) rowView.findViewById(com.capgemini.SalesOrder.R.id.tableRow2);
			
			holder.textView0 = (TextView) rowView.findViewById(com.capgemini.SalesOrder.R.id.textView0);	
			rowView.setTag(holder);
		}
		
		ViewHolder holder = (ViewHolder) rowView.getTag();
		
		// navigation to next page
		if (position == propertiesValues.size())
		{
			holder.tableRow1.setVisibility(View.VISIBLE);
			holder.imageView.setVisibility(View.VISIBLE);
			holder.textView1.setText("Supplier");
			holder.textView1.setTextSize(22);
			holder.textView2.setText(String.valueOf(entry.getSupplierId()) + " " + String.valueOf(entry.getSupplierName()));
			
			holder.imageView.setImageResource(com.capgemini.SalesOrder.R.drawable.navigateto);

			holder.tableRow2.setOnClickListener(new android.view.View.OnClickListener()
			{
				public void onClick(View v)
				{	
					Intent intent = new Intent(mContext, Page6DetailsActivity.class);

					Page6DetailsActivity.parentEntry = entry;

					mContext.startActivity(intent);
				}
			});

			return rowView;
		}

		String value = getPropertyValue(position);
		String label = labels.get(position);

		holder.textView1.setText(label);
		holder.textView2.setText(value);
		holder.imageView.setVisibility(View.INVISIBLE);
		holder.tableRow1.setVisibility(View.VISIBLE);
		holder.textView0.setVisibility(View.GONE);
		
		SapSemantics sapSemantics = getSapSemantics(position);
		if (sapSemantics != null)
		{
			switch (sapSemantics)
			{
				case tel:   holder.imageView.setImageResource(com.capgemini.SalesOrder.R.drawable.tel);
							holder.imageView.setVisibility(View.VISIBLE);
						    break;
						    
				case email: holder.imageView.setImageResource(com.capgemini.SalesOrder.R.drawable.email);
							holder.imageView.setVisibility(View.VISIBLE);
					        break;
					        
				case url:   holder.imageView.setImageResource(com.capgemini.SalesOrder.R.drawable.url);
							holder.imageView.setVisibility(View.VISIBLE);
					        break;
			}
		}

		return rowView;
	}
	
	/**
	 * Returns the SAP semantics in the given position.
	 * @param position
	 * @return - SAP semantics in the given position.
	 */
	public SapSemantics getSapSemantics(int position)
	{
		if (sapSemanticsList == null || sapSemanticsList.isEmpty() || position >= sapSemanticsList.size())
		{
			return null;
		}
		
		String value = this.sapSemanticsList.get(position);
		if (value == null)
		{
			return null;
		}
		
		value = value.toLowerCase();
		
		SapSemantics[] values = SapSemantics.values();
		for (SapSemantics sapSemantics : values) 
		{
			String semanticName = sapSemantics.name();
			if (semanticName.equals(value) || value.contains(semanticName + ";"))
			{
				return sapSemantics;
			}
		}
		return null;
	}
	
	/**
	 * Returns the property value.
	 * @param value
	 * @return - property value.
	 */
	public String getPropertyValue(int position)
	{
		if (propertiesValues == null || propertiesValues.isEmpty() || position >= propertiesValues.size())
		{
			return mContext.getString(com.capgemini.SalesOrder.R.string.no_value);
		}

		String value = propertiesValues.get(position);
		if (value == null || value.length() == 0 || value.equalsIgnoreCase("null"))
		{
			return mContext.getString(com.capgemini.SalesOrder.R.string.no_value);
		}
		
		return value;
	}
}
