package com.capgemini.SalesOrder;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.capgemini.SalesOrder.zgwsample_srv.v0.ZGWSAMPLE_SRVRequestHandler;
import com.capgemini.SalesOrder.zgwsample_srv.v0.entitytypes.Product;
import com.capgemini.SalesOrder.zgwsample_srv.v0.entitytypes.SalesOrderLineItem;
import com.capgemini.SalesOrder.zgwsample_srv.v0.helpers.IZGWSAMPLE_SRVRequestHandlerListener;
import com.capgemini.SalesOrder.zgwsample_srv.v0.helpers.ZGWSAMPLE_SRVRequestID;
import com.sap.gwpa.proxy.RequestStatus;
import com.sap.gwpa.proxy.RequestStatus.StatusType;
import com.sap.mobile.lib.request.IResponse;
import com.sap.mobile.lib.supportability.ISDMLogger;
import com.sap.mobile.lib.supportability.SDMLogger;

/**
 * Details screen.
 */
public class Page5DetailsActivity extends ListActivity implements IZGWSAMPLE_SRVRequestHandlerListener 
{
	public static final String TAG = "SalesOrder";
	private ISDMLogger logger;
	
	protected static SalesOrderLineItem parentEntry;
	
	// result of the Detail Request
	private Product entry; 

	// handler for callbacks to the UI thread
	final Handler mHandler = new Handler();
	
	// connectivity error message
	private String emessage = "";
	
	private Page5DetailsAdapter adapter; 
	
	// create runnable for posting
	final Runnable mUpdateResults = new Runnable()
	{
		public void run()
		{
			updateResultsInUi();
		}
	};

	// Refresh UI from background thread
	protected void updateResultsInUi()
	{
		if (entry == null)
		{
			// error occurred
			View loadingView = findViewById(com.capgemini.SalesOrder.R.id.loading_view);
			loadingView.setVisibility(View.GONE);
			Toast.makeText(getApplicationContext(), emessage, Toast.LENGTH_LONG).show();
			finish();
			return;
		}

		adapter = new Page5DetailsAdapter(this, entry);

		setListAdapter(adapter);

		View loadingView = findViewById(com.capgemini.SalesOrder.R.id.loading_view);
		loadingView.setVisibility(View.GONE);
	}

	
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		String value = adapter.getPropertyValue(position);
		Page5DetailsAdapter.SapSemantics sapSemantics = adapter.getSapSemantics(position);
		if (sapSemantics == null || value == null)
		{
			return;
		}
		
		switch (sapSemantics)
		{	
			case map: 	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						Uri.parse("geo:0,0?q=" + value));
						startActivity(intent);
						break;
						
			case tel:   Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + value));
					    startActivity(callIntent);
					    break;
					    
			case email: Intent emailIntent = new Intent(Intent.ACTION_SEND);
						emailIntent.setType("plain/text");
						emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {value});
						startActivity(Intent.createChooser(emailIntent, ""));
				        break;
				        
			case url:   
						if (!value.startsWith("http") && !value.startsWith("HTTP"))
						{
							value = "http://" + value;
						}
						Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(value));
					    startActivity(browserIntent);
				        break;
		}
		
		super.onListItemClick(l, v, position, id);
	}
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(com.capgemini.SalesOrder.R.layout.details);
		setTitle(parentEntry.getProductId());
		// initialize the Logger
		logger = new SDMLogger();
		// register to listen to notifications from the Request Handler
		ZGWSAMPLE_SRVRequestHandler.getInstance(getApplicationContext()).register(this, ZGWSAMPLE_SRVRequestID.LOAD_PRODUCT_FOR_SALESORDERLINEITEM);
		
		// make the request
		// the response should be in "requestCompleted"
		ZGWSAMPLE_SRVRequestHandler.getInstance(getApplicationContext()).loadProductForSalesOrderLineItem(parentEntry);
	}
	
	public void requestCompleted(ZGWSAMPLE_SRVRequestID requestID, List<?> entries, RequestStatus requestStatus) 
	{
		// first check the request's status
		StatusType type = requestStatus.getType();

		if (type == StatusType.OK) 
		{
			if (requestID.equals(ZGWSAMPLE_SRVRequestID.LOAD_PRODUCT_FOR_SALESORDERLINEITEM))
			{
				// cast to the right type
				this.entry = (Product) entries.get(0);
				// post in the UI
				mHandler.post(mUpdateResults);
			}
		}
		else
		{
			// do some error handling
			logger.e(TAG, "The request has returned with an error");
			entry = null;
			emessage = requestStatus.getMessage();
			mHandler.post(mUpdateResults);
		}
	}

	public void authenticationNeeded(String message) 
	{
		logger.e(TAG, "Authentication is needed");
		entry = null;
		emessage = message;
		mHandler.post(mUpdateResults);
		// navigate back to login page
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}
	
	public void batchCompleted(String batchID, IResponse response,
			RequestStatus requestStatus)
	{
		// here you can handle the response of the batch request.
	} 	
}
