
package com.m2ps.message;

import java.util.Hashtable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 * M2PS Iso Messages
 * 
 */
public class M2PSIsoMsg
{
	public static class Bit
	{
		public static int _002_PAN = 2;
		public static int _003_PROCESING_CODE = 3;
		public static int _004_AMOUNT_TRAN = 4;
		public static int _005_AMOUNT_SETTLE = 5;
		public static int _007_TRANSMISSION_DATE_TIME = 7;
		public static int _009_CONVERSION_RATE_SETTLE = 9;
		public static int _011_SYSTEM_TRACE_AUDIT_NR = 11;
		public static int _012_TIME_LOCAL_TRANSACTION = 12;
		public static int _013_DATE_LOCAL_TRANSACTION = 13;
		public static int _014_DATE_EXPIRATION = 14;
		public static int _015_DATE_SETTLEMENT = 15;
		public static int _016_DATE_CONVERSION = 16;
		public static int _018_MERCHANT_TYPE = 18;
		public static int _022_POS_ENTRY_MODE = 22;
		public static int _023_CARD_SEQUENCE_NUMBER = 23;
		public static int _025_POS_CONDITION_CODE = 25;
		public static int _026_POS_PIN_CAPTURE_CODE = 26;
		public static int _027_AUTHORIZATION_ID_RESPONSE_LENGTH = 27;
		public static int _028_AMOUNT_TRANSACTION_FEE = 28;
		public static int _029_AMOUNT_SETTLE_FEE = 29;
		public static int _030_AMOUNT_TRANSACTION_PROCESSING_FEE = 30;
		public static int _031_AMOUNT_SETTLE_PROCESSING_FEE = 31;
		public static int _032_ACQUIRING_INSTITUTION_ID_CODE = 32;
		public static int _033_FORWARDING_INSTITUTION_ID_CODE = 33;
		public static int _035_TRACK2_DATA = 35;
		public static int _037_RETRIEVAL_REFERENCE_NUMBER = 37;
		public static int _038_AUTHORIZATION_ID_RESPONSE = 38;
		public static int _039_RESPONSE_CODE = 39;
		public static int _040_SERVICE_RESTRICTION_CODE = 40;
		public static int _041_CARD_ACCEPTOR_TERMINAL_ID = 41;
		public static int _042_CARD_ACCEPTOR_ID_CODE = 42;
		public static int _043_CARD_ACCEPTOR_NAME_LOCATION = 43;
		public static int _044_ADDITIONAL_RESPONSE_DATA = 44;
		public static int _045_TRACK1_DATA = 45;
		public static int _048_ADDITIONAL_DATA = 48;
		public static int _049_CURRENCY_CODE_TRANSACTION = 49;
		public static int _050_CURRENCY_CODE_SETTLE = 50;
		public static int _052_PIN_DATA = 52;
		public static int _053_SECURITY_RELATED_CONTROL_INFORMATION = 53;
		public static int _054_ADDITIONAL_AMOUNTS = 54;
		public static int _056_MESSAGE_REASON_CODE = 56;
		public static int _057_AUTHORIZATION_LIFE_CYCLE_CODE = 57;
		public static int _058_AUTHORIZING_AGENT_INSTITUTION = 58;
		public static int _059_ECHO_DATA = 59;
		public static int _066_SETTLEMENT_CODE = 66;
		public static int _067_EXTENDED_PAYMENT_CODE = 67;
		public static int _070_NETWORK_MANAGEMENT_INFORMATION_CODE = 70;
		public static int _090_ORIGINAL_DATA_ELEMENTS = 90;
		public static int _091_FILE_UPDATE_CODE = 91;
		public static int _095_REPLACEMENT_AMOUNTS = 95;
		public static int _097_AMOUNT_NET_SETTLE = 97;
		public static int _098_PAYEE = 98;
		public static int _0100_RECEIVING_INSTITUTION_ID_CODE = 100;
		public static int _101_FILE_NAME = 101;
		public static int _102_ACCOUNT_IDENTIFICATION_1 = 102;
		public static int _103_ACCOUNT_IDENTIFICATION_2 = 103;
		public static int _123_POS_DATA_CODE = 123;
	}
	
	public static class MsgType
	{
		private static Hashtable htRspMsgTypes = new Hashtable();
		public static final String _0200_TRAN_REQ = "0200";
		public static final String _0210_TRAN_REQ_RSP = "0210";
		public static final String _0420_ACQ_REV_ADV = "0420";
		public static final String _0430_ACQ_REV_ADV_RSP = "0430";
		public static final String _0800_NETWRK_MANAGEMENT_REQ = "0800";
		public static final String _0810_NETWRK_MANAGEMENT_REQ_RSP = "0810";
		
		static
		{
			htRspMsgTypes.put(_0200_TRAN_REQ, _0210_TRAN_REQ_RSP);
			htRspMsgTypes.put(_0420_ACQ_REV_ADV, _0430_ACQ_REV_ADV_RSP);
			htRspMsgTypes.put(_0800_NETWRK_MANAGEMENT_REQ, _0810_NETWRK_MANAGEMENT_REQ_RSP);
		}
		
		/**
		 * 
		 * Returns the associated response message type
		 * 
		 * @param msgType
		 *           The request message type
		 * @return the associated response message type
		 */
		public static String getResponseMsgType(String msgType)
		{
			return (String)htRspMsgTypes.get(msgType);
		}
	}
	
	
	public class RspCode
	{
		public static final String _00_APPROVED = "00";
		public static final String _91_ISSUER_SWITCH_INOPERATIVE = "91";
	}
	
	/**
	 * 
	 * Copies the field specified from one ISO message to another.
	 * 
	 * @param to
	 *           The destination ISO message
	 * @param from
	 *           The source ISO message
	 * @param field
	 *           The field that should be copied.
	 */
	public static void copyField(ISOMsg to, ISOMsg from, String field)
	{
		try
		{
			to.set(field, from.getString(field));
		}
		catch (ISOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Copies the field specified from one ISO message to another.
	 * 
	 * @param to
	 *           The destination ISO message
	 * @param from
	 *           The source ISO message
	 * @param field
	 *           The field that should be copied.
	 */
	public static void copyField(ISOMsg to, ISOMsg from, int field)
	{
		try
		{
			to.set(field, from.getString(field));
		}
		catch (ISOException ex)
		{
			ex.printStackTrace();
		}
	}
}
