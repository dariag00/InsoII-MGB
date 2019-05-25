package com.unileon.insoII.mgb.utils;

public class Constants {
	
	public final static int ROLE_ADMIN = 0;
	public final static int ROLE_ACCOUNT_OWNER = 1;
	public final static int ROLE_ACCOUNT_USER = 2;

	public final static int CURRENCY_EURO = 0;
	public final static int CURRENCY_DOLLAR = 1;
	public final static int CURRENCY_POUND = 2;
	
	public final static int TRANSACTION_TYPE_TRANSFER = 0;
	public final static int TRANSACTION_TYPE_INSTANT_TRANSFER = 1;
	
	public final static int OPERATION_TYPE_WITHDRAWAL = 0;
	public final static int OPERATION_TYPE_PAYMENT = 1;
	
	public final static int CARD_STATUS_ACTIVE = 0;
	public final static int CARD_STATUS_INACTIVE = 1;
	public final static int CARD_STATUS_SUSPENDED = 2;
	
	public final static int TRANSFER_OK = 0;
	public final static int TRANSFER_IBAN_NOT_FOUND = -1;
	
}
