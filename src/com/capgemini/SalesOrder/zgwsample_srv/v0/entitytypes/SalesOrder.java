package com.capgemini.SalesOrder.zgwsample_srv.v0.entitytypes;
/*

 Auto-Generated by SAP NetWeaver Gateway Productivity Accelerator, Version 1.1.1

*/
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sap.gwpa.proxy.BaseEntityType;
import com.sap.gwpa.proxy.ODataQuery;
import com.sap.gwpa.proxy.TypeConverter;
import com.sap.mobile.lib.parser.IODataEntry;
import com.sap.mobile.lib.parser.IODataProperty;
import com.sap.mobile.lib.parser.IODataSchema;
import com.sap.mobile.lib.parser.IODataServiceDocument;
import com.sap.mobile.lib.parser.IParser;
import com.sap.mobile.lib.parser.ODataEntry;
import com.sap.mobile.lib.parser.ParserException;

/**
 * SalesOrder Entity Type 
 *
 * <br>key (SoId)
 */
public class SalesOrder extends BaseEntityType 
{
	// SalesOrder properties
    private String DeliveryStatus;
    private String BillingStatus;
    private String LifecycleStatus;
    private BigDecimal TaxAmountExt;
    private BigDecimal TaxAmount;
    private BigDecimal NetAmountExt;
    private BigDecimal NetAmount;
    private BigDecimal GrossAmountExt;
    private BigDecimal GrossAmount;
    private String CurrencyCode;
    private String BuyerName;
    private String BuyerId;
    private String Note;
    private String SoId;
    private String CreatedBy;
    private Boolean ChangedByBp;
    private Date CreatedAt;
    private String ChangedBy;
    private Date ChangedAt;
    private Boolean CreatedByBp;

	// reference to the parser
	private IParser parser;
	// reference to the schema
	private IODataSchema schema;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	// SalesOrder navigation properties
	private  BusinessPartner businesspartner = null;
	private  List<SalesOrderLineItem> lineitems = null;

	private static Map<String, String> salesorderLabels;

	private static Map<String, String> salesorderTypes;	
	
	/**
	 * Constructor
	 * @param entry
	 * @throws MalformedURLException
	 */
	public SalesOrder(final IODataEntry entry, IParser parser, IODataSchema schema) throws MalformedURLException
	{
		super(entry);
		this.DeliveryStatus =  TypeConverter.getAsString(entry.getPropertyValue("DeliveryStatus"));
		this.BillingStatus =  TypeConverter.getAsString(entry.getPropertyValue("BillingStatus"));
		this.LifecycleStatus =  TypeConverter.getAsString(entry.getPropertyValue("LifecycleStatus"));
		this.TaxAmountExt =  TypeConverter.getAsFloat(entry.getPropertyValue("TaxAmountExt"));
		this.TaxAmount =  TypeConverter.getAsFloat(entry.getPropertyValue("TaxAmount"));
		this.NetAmountExt =  TypeConverter.getAsFloat(entry.getPropertyValue("NetAmountExt"));
		this.NetAmount =  TypeConverter.getAsFloat(entry.getPropertyValue("NetAmount"));
		this.GrossAmountExt =  TypeConverter.getAsFloat(entry.getPropertyValue("GrossAmountExt"));
		this.GrossAmount =  TypeConverter.getAsFloat(entry.getPropertyValue("GrossAmount"));
		this.CurrencyCode =  TypeConverter.getAsString(entry.getPropertyValue("CurrencyCode"));
		this.BuyerName =  TypeConverter.getAsString(entry.getPropertyValue("BuyerName"));
		this.BuyerId =  TypeConverter.getAsString(entry.getPropertyValue("BuyerId"));
		this.Note =  TypeConverter.getAsString(entry.getPropertyValue("Note"));
		this.SoId =  TypeConverter.getAsString(entry.getPropertyValue("SoId"));
		this.CreatedBy =  TypeConverter.getAsString(entry.getPropertyValue("CreatedBy"));
		this.ChangedByBp =  TypeConverter.getAsBoolean(entry.getPropertyValue("ChangedByBp"));
		this.CreatedAt =  TypeConverter.getAsDate(entry.getPropertyValue("CreatedAt"));
		this.ChangedBy =  TypeConverter.getAsString(entry.getPropertyValue("ChangedBy"));
		this.ChangedAt =  TypeConverter.getAsDate(entry.getPropertyValue("ChangedAt"));
		this.CreatedByBp =  TypeConverter.getAsBoolean(entry.getPropertyValue("CreatedByBp"));
	    this.parser = parser;
        this.schema = schema;
 	}
	
	/**
	 * SalesOrder Constructor</br>
	 * Dummy values may apply
	 *
	 */
	public SalesOrder( String SoId) 
	{
		super(new ODataEntry());
		
        this.setSoId(SoId);
	}

	// SalesOrder navigation properties

	/**
	 * BusinessPartner Query
	 * @return - ODataQuery
	 * @throws - MalformedURLException
	 */
	public ODataQuery BusinessPartnerQuery() throws MalformedURLException
	{
		String url = getEntry().getId() + "/" + "BusinessPartner";
		ODataQuery query = new ODataQuery(url);
		
		return query;
	}

	/**
	 * Get BusinessPartner  
	 * @return - BusinessPartner
	 * @throws - IllegalArgumentException, ParserException, MalformedURLException
	 */
	public BusinessPartner BusinessPartner(String data) throws IllegalArgumentException, ParserException, MalformedURLException
	{
		BusinessPartner result = null;
			
		List<IODataEntry> parsedSDMODataEntries = getParsedSDMODataEntries(data, parser, schema, "SalesOrder","BusinessPartner");	

	    result = new BusinessPartner (parsedSDMODataEntries.get(0), parser, schema);
        return result;
	}

	/**
	 * LineItems Query
	 * @return - ODataQuery
	 * @throws - MalformedURLException
	 */
	public ODataQuery LineItemsQuery() throws MalformedURLException
	{
		String url = getEntry().getId() + "/" + "LineItems";
		ODataQuery query = new ODataQuery(url);
		
		return query;
	}

	/**
	 * Get LineItems  
	 * @return - List&ltSalesOrderLineItem&gt
	 * @throws - IllegalArgumentException, ParserException, MalformedURLException
	 */
	public List<SalesOrderLineItem> LineItems(String data) throws IllegalArgumentException, ParserException, MalformedURLException
	{
		List<SalesOrderLineItem> result = null;
			
		List<IODataEntry> parsedSDMODataEntries = getParsedSDMODataEntries(data, parser, schema, "SalesOrder","LineItems");	


		result = new LinkedList<SalesOrderLineItem>(); 
	
		for (IODataEntry entry : parsedSDMODataEntries)
		{
			result.add(new SalesOrderLineItem(entry, parser, schema));
		}
        return result;
	}


	/**
	 * BusinessPartner Load method
	 *
	 * loads the navigation property entries into the entityType
	 * @throws ParserException 
	 * @throws IllegalArgumentException
	 * @throws MalformedURLException
	 *
	 */
	public void loadBusinessPartner(String data) throws IllegalArgumentException, ParserException, MalformedURLException
	{
		BusinessPartner semanticData = BusinessPartner(data);
		setBusinessPartner(semanticData);
	}

	/**
	 * LineItems Load method
	 *
	 * loads the navigation property entries into the entityType
	 * @throws ParserException 
	 * @throws IllegalArgumentException
	 * @throws MalformedURLException
	 *
	 */
	public void loadLineItems(String data) throws IllegalArgumentException, ParserException, MalformedURLException
	{
		List<SalesOrderLineItem> semanticData = LineItems(data);
		setLineItems(semanticData);
	}

	// SalesOrder properties getters and setters
		
	/**
	 * @return - String PO Ordering
	 */
	public String getDeliveryStatus()
	{
		return this.DeliveryStatus;
	}
	
	/**
	 * @param DeliveryStatus - PO Ordering
	 */
	public void setDeliveryStatus(String DeliveryStatus)
	{
		this.DeliveryStatus = DeliveryStatus;
		
		getEntry().putPropertyValue("DeliveryStatus", DeliveryStatus);
	}
		
	/**
	 * @return - String PO Confirmation
	 */
	public String getBillingStatus()
	{
		return this.BillingStatus;
	}
	
	/**
	 * @param BillingStatus - PO Confirmation
	 */
	public void setBillingStatus(String BillingStatus)
	{
		this.BillingStatus = BillingStatus;
		
		getEntry().putPropertyValue("BillingStatus", BillingStatus);
	}
		
	/**
	 * @return - String PO Lifecycle
	 */
	public String getLifecycleStatus()
	{
		return this.LifecycleStatus;
	}
	
	/**
	 * @param LifecycleStatus - PO Lifecycle
	 */
	public void setLifecycleStatus(String LifecycleStatus)
	{
		this.LifecycleStatus = LifecycleStatus;
		
		getEntry().putPropertyValue("LifecycleStatus", LifecycleStatus);
	}
		
	/**
	 * @return - BigDecimal Amount
	 */
	public BigDecimal getTaxAmountExt()
	{
		return this.TaxAmountExt;
	}
	
	/**
	 * @param TaxAmountExt - Amount
	 */
	public void setTaxAmountExt(BigDecimal TaxAmountExt)
	{
		this.TaxAmountExt = TaxAmountExt;
		
		getEntry().putPropertyValue("TaxAmountExt",  String.valueOf(this.TaxAmountExt.floatValue()));
	}
		
	/**
	 * @return - BigDecimal Tax Amt.
	 */
	public BigDecimal getTaxAmount()
	{
		return this.TaxAmount;
	}
	
	/**
	 * @param TaxAmount - Tax Amt.
	 */
	public void setTaxAmount(BigDecimal TaxAmount)
	{
		this.TaxAmount = TaxAmount;
		
		getEntry().putPropertyValue("TaxAmount",  String.valueOf(this.TaxAmount.floatValue()));
	}
		
	/**
	 * @return - BigDecimal Amount
	 */
	public BigDecimal getNetAmountExt()
	{
		return this.NetAmountExt;
	}
	
	/**
	 * @param NetAmountExt - Amount
	 */
	public void setNetAmountExt(BigDecimal NetAmountExt)
	{
		this.NetAmountExt = NetAmountExt;
		
		getEntry().putPropertyValue("NetAmountExt",  String.valueOf(this.NetAmountExt.floatValue()));
	}
		
	/**
	 * @return - BigDecimal Net Amt.
	 */
	public BigDecimal getNetAmount()
	{
		return this.NetAmount;
	}
	
	/**
	 * @param NetAmount - Net Amt.
	 */
	public void setNetAmount(BigDecimal NetAmount)
	{
		this.NetAmount = NetAmount;
		
		getEntry().putPropertyValue("NetAmount",  String.valueOf(this.NetAmount.floatValue()));
	}
		
	/**
	 * @return - BigDecimal Amount
	 */
	public BigDecimal getGrossAmountExt()
	{
		return this.GrossAmountExt;
	}
	
	/**
	 * @param GrossAmountExt - Amount
	 */
	public void setGrossAmountExt(BigDecimal GrossAmountExt)
	{
		this.GrossAmountExt = GrossAmountExt;
		
		getEntry().putPropertyValue("GrossAmountExt",  String.valueOf(this.GrossAmountExt.floatValue()));
	}
		
	/**
	 * @return - BigDecimal Gross Amt.
	 */
	public BigDecimal getGrossAmount()
	{
		return this.GrossAmount;
	}
	
	/**
	 * @param GrossAmount - Gross Amt.
	 */
	public void setGrossAmount(BigDecimal GrossAmount)
	{
		this.GrossAmount = GrossAmount;
		
		getEntry().putPropertyValue("GrossAmount",  String.valueOf(this.GrossAmount.floatValue()));
	}
		
	/**
	 * @return - String Currency
	 */
	public String getCurrencyCode()
	{
		return this.CurrencyCode;
	}
	
	/**
	 * @param CurrencyCode - Currency
	 */
	public void setCurrencyCode(String CurrencyCode)
	{
		this.CurrencyCode = CurrencyCode;
		
		getEntry().putPropertyValue("CurrencyCode", CurrencyCode);
	}
		
	/**
	 * @return - String Company Name
	 */
	public String getBuyerName()
	{
		return this.BuyerName;
	}
	
	/**
	 * @param BuyerName - Company Name
	 */
	public void setBuyerName(String BuyerName)
	{
		this.BuyerName = BuyerName;
		
		getEntry().putPropertyValue("BuyerName", BuyerName);
	}
		
	/**
	 * @return - String Bus. Part. ID
	 */
	public String getBuyerId()
	{
		return this.BuyerId;
	}
	
	/**
	 * @param BuyerId - Bus. Part. ID
	 */
	public void setBuyerId(String BuyerId)
	{
		this.BuyerId = BuyerId;
		
		getEntry().putPropertyValue("BuyerId", BuyerId);
	}
		
	/**
	 * @return - String Description
	 */
	public String getNote()
	{
		return this.Note;
	}
	
	/**
	 * @param Note - Description
	 */
	public void setNote(String Note)
	{
		this.Note = Note;
		
		getEntry().putPropertyValue("Note", Note);
	}
		
	/**
	 * @return - String Sa. Ord. ID
	 */
	public String getSoId()
	{
		return this.SoId;
	}
	
	/**
	 * @param SoId - Sa. Ord. ID
	 */
	public void setSoId(String SoId)
	{
		this.SoId = SoId;
		
		getEntry().putPropertyValue("SoId", SoId);
	}
		
	/**
	 * @return - String Employee ID
	 */
	public String getCreatedBy()
	{
		return this.CreatedBy;
	}
	
	/**
	 * @param CreatedBy - Employee ID
	 */
	public void setCreatedBy(String CreatedBy)
	{
		this.CreatedBy = CreatedBy;
		
		getEntry().putPropertyValue("CreatedBy", CreatedBy);
	}
		
	/**
	 * @return - Boolean Value
	 */
	public Boolean getChangedByBp()
	{
		return this.ChangedByBp;
	}
	
	/**
	 * @param ChangedByBp - Value
	 */
	public void setChangedByBp(Boolean ChangedByBp)
	{
		this.ChangedByBp = ChangedByBp;
		
	}
		
	/**
	 * @return - Date Time Stamp
	 */
	public Date getCreatedAt()
	{
		return this.CreatedAt;
	}
	
	/**
	 * @param CreatedAt - Time Stamp
	 */
	public void setCreatedAt(Date CreatedAt)
	{
		this.CreatedAt = CreatedAt;
		
		getEntry().putPropertyValue("CreatedAt", dateFormat.format(CreatedAt));
	}
		
	/**
	 * @return - String Employee ID
	 */
	public String getChangedBy()
	{
		return this.ChangedBy;
	}
	
	/**
	 * @param ChangedBy - Employee ID
	 */
	public void setChangedBy(String ChangedBy)
	{
		this.ChangedBy = ChangedBy;
		
		getEntry().putPropertyValue("ChangedBy", ChangedBy);
	}
		
	/**
	 * @return - Date Time Stamp
	 */
	public Date getChangedAt()
	{
		return this.ChangedAt;
	}
	
	/**
	 * @param ChangedAt - Time Stamp
	 */
	public void setChangedAt(Date ChangedAt)
	{
		this.ChangedAt = ChangedAt;
		
		getEntry().putPropertyValue("ChangedAt", dateFormat.format(ChangedAt));
	}
		
	/**
	 * @return - Boolean Value
	 */
	public Boolean getCreatedByBp()
	{
		return this.CreatedByBp;
	}
	
	/**
	 * @param CreatedByBp - Value
	 */
	public void setCreatedByBp(Boolean CreatedByBp)
	{
		this.CreatedByBp = CreatedByBp;
		
	}
	
	/**
	 * @return - representation of the Entity Type object in OData4SAP format
	 */
	public String getStringPayload()  
	{
		String xml = null;
		
		xml = getEntry().toXMLString();
			
		return xml;
	}
	
	/**
	 * @return - self ODataQuery object
	 * @throws MalformedURLException 
	 */
	public ODataQuery getEntityQuery() throws MalformedURLException  
	{
		return new ODataQuery(getEntry().getSelfLink().getUrl());
	}
	
	/**
	 * @return - the date format.
	 */
	public DateFormat getDateFormat()
	{
		return this.dateFormat;
	}

	// SalesOrder navigation properties setters and getters
	
	/**
	 * Returns navigation property
	 * Returns null if was not expanded
	 * @throws MalformedURLException
	 */
	public BusinessPartner getBusinessPartner() throws MalformedURLException
	{
		if (null == businesspartner)
		{
			//load InlineEntries for BusinessPartner
			List<IODataEntry> inlineEntries  = getInlineEntries(getEntry(),"BusinessPartner");

			if (null != inlineEntries) 
			{
				businesspartner = new BusinessPartner(inlineEntries.get(0), parser, schema);
			}
		}
		return businesspartner;
	}

	/**
	 * Sets navigation property
	 * @param businesspartner - the navigation property to set.
	 */
	public void setBusinessPartner(BusinessPartner businesspartner) 
	{
		this.businesspartner = businesspartner;
	}
	
	/**
	 * Returns navigation property
	 * Returns null if was not expanded
	 * @throws MalformedURLException
	 */
	public List<SalesOrderLineItem> getLineItems() throws MalformedURLException
	{
		if (null == lineitems)
		{
			//load InlineEntries for LineItems
			List<IODataEntry> inlineEntries  = getInlineEntries(getEntry(),"LineItems");

			if (null != inlineEntries) 
			{
				lineitems = new LinkedList<SalesOrderLineItem>();
				for (IODataEntry isdmoDataEntry : inlineEntries) 
				{
					lineitems.add(new SalesOrderLineItem(isdmoDataEntry, parser, schema));
				}
			}
		}
		return lineitems;
	}

	/**
	 * Sets navigation property
	 * @param lineitems - the navigation property to set.
	 */
	public void setLineItems(List<SalesOrderLineItem> lineitems) 
	{
		this.lineitems = lineitems;
	}
	

	/**
    * Static method that loads all of the entity type property labels. 
    * This method is called when the service class is initialized.
    * @param service Service document object containing all of the entity type properties.
   	*/	
    public static void loadLabels(IODataServiceDocument service)
    {
    	List<IODataProperty> properties = getSchemaPropertiesFromCollection(service, "SalesOrderCollection" );
        
    	salesorderLabels = new HashMap<String, String>();
    	salesorderTypes = new HashMap<String, String>();
    	
    	if (properties != null)
    	{
	        for (IODataProperty property : properties) 
	        {
	        	salesorderLabels.put(property.getName(), property.getLabel());
	        	salesorderTypes.put(property.getName(), property.getType());
			}
    	}
    }
    
    
    /**
    * Static method that returns the type for a given property name.
    * @param propertyName Property name.
    * @return Property label.
   	*/
    public static String getTypeForProperty(String propertyName)
    {
        return getLabelFromDictionary(salesorderTypes, propertyName);
    }
    
    
    /**
    * Static method that returns the label for a given property name.
    * @param propertyName Property name.
    * @return Property label.
   	*/
    public static String getLabelForProperty(String propertyName)
    {
        return getLabelFromDictionary(salesorderLabels, propertyName);
    }
}