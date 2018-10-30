
package com.m2ps.sdk;

import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;

/**
 * TODO
 *
 */
public class ContextAction extends Context
{
	
	/* Interchange methods */
	public void putMessageToCore(ISOMsg msgToCore)
	{
		put(ContextAction.Fields.MESSAGE_TO_CORE, msgToCore);
	}
	
	public ISOMsg getMessageToCore()
	{
		return (ISOMsg)get(ContextAction.Fields.MESSAGE_TO_CORE);
	}
	
	public ISOMsg getMessageFromCore()
	{
		return (ISOMsg) get(ContextAction.Fields.MESSAGE_FROM_CORE);
	}
	
	public void putMessageFromCore(ISOMsg msgToCore)
	{
		put(ContextAction.Fields.MESSAGE_FROM_CORE, msgToCore);
	}
	
	public void putMessageToRemote(ISOMsg msgToRemote)
	{
		put(ContextAction.Fields.MESSAGE_TO_REMOTE, msgToRemote);
	}
	
	public void putMessageFromRemote(ISOMsg msgFromRemote)
	{
		put(ContextAction.Fields.MESSAGE_FROM_REMOTE, msgFromRemote);
	}
	
	public ISOMsg getMessageFromRemote()
	{
		return (ISOMsg) get(ContextAction.Fields.MESSAGE_FROM_REMOTE);
	}
	
	
	/* Core methods */
	public ISOMsg getMessageCore()
	{
		return(ISOMsg) get(ContextAction.Fields.MESSAGE_CORE);
	}
	
	public void putMessageCore(ISOMsg msgCore)
	{
		put(ContextAction.Fields.MESSAGE_CORE, msgCore);
	}
	
	/**
	 * Field name constant for the context.
	 *
	 */
	public static final class Fields
	{
		/* Interchange fields */
		public static final String MESSAGE_FROM_REMOTE = "MESSAGE FROM REMOTE";
		public static final String MESSAGE_FROM_CORE = "MESSAGE FROM CORE";
		public static final String MESSAGE_TO_REMOTE = "MESSAGE TO REMOTE";
		public static final String MESSAGE_TO_CORE = "MESSAGE TO CORE";
		public static final String ISO_SOURCE = "ISO SOURCE";
		
		/* Core fields */
		public static final String MESSAGE_CORE = "MESSAGE CORE";
	}
}

