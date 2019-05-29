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
	
	public final static int OPERATION_TYPE_WITHDRAWAL = 1;
	public final static int OPERATION_TYPE_PAYMENT = 0;
	
	public final static int CARD_STATUS_ACTIVE = 0;
	public final static int CARD_STATUS_INACTIVE = 1;
	public final static int CARD_STATUS_SUSPENDED = 2;
	
	public final static int TRANSFER_OK = 0;
	public final static int TRANSFER_IBAN_NOT_FOUND = -1;
	public final static int TRANSFER_IBAN_NOT_ENOUGH_FUNDS = -2;
	
	public final static int CARD_OK=0;
	public final static int CARD_ERROR1=0;

	public static final int OPERATION_OK = 0;
	public static final int OPERATION_IBAN_NOT_ENOUGH_FUNDS = 1;
	public static final int OPERATION_ERROR = 1;
	public static final int TRANSFER_TYPE_ERROR = -1;
	public final static int CREATE_ACCOUNT_OK = 0;
	public final static int CREATE_ACCOUNT_INCORRECT_ACCOUNT_ID = -1;
	public final static int CREATE_ACCOUNT_INCORRECT_PASSWORD = -2;
	public final static int CREATE_ACCOUNT_PASSWORDS_NO_MATCH = -3;
	public final static int CREATE_ACCOUNT_UNKNOWN_ERROR = -99;
	
	public final static int CHANGE_PIN_OK = 0;
	public final static int CHANGE_PIN_INVALID_PIN = -1;
	public final static int CHANGE_PIN_DO_NOT_MATCH= -2;
	
}
