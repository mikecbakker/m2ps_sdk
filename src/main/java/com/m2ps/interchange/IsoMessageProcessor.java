
package com.m2ps.interchange;

import java.io.IOException;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOResponseListener;
import org.jpos.iso.ISOSource;
import org.jpos.iso.MUX;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.Log;
import org.jpos.util.NameRegistrar.NotFoundException;

/**
 * 
 * This class provides basic message processor (interchange) functionality. It processes request
 * message received as well as response messages.
 * 
 */
public class IsoMessageProcessor extends Log
	implements
		ISORequestListener,
		ISOResponseListener,
		Configurable
{
	protected String destinationQmux;
	protected MUX destQmux;
	
	/*-----------------------------------------------------------------------------------------------------------------*/
	/**
	 * Constructs a new <code>IsoSrcRequestListener</code> instance.
	 */
	public IsoMessageProcessor()
	{
	}
	
	/**
	 * Retrieves and loads the configuration as defined in the QBean.
	 * 
	 * @param cfg
	 * @throws ConfigurationException
	 * @see org.jpos.core.Configurable#setConfiguration(org.jpos.core.Configuration)
	 */
	@Override
	public void setConfiguration(Configuration cfg) throws ConfigurationException
	{
		/* Param 'dest_qmux' - Mandatory */
		destinationQmux = cfg.get("dest_qmux");
		if (destinationQmux == null)
		{
			throw new ConfigurationException(
				"Incorrect or missing paramter 'dest_qmux'.  This should be set to the value of the destination qmux.");
		}
		
		try
		{
			destQmux = QMUX.getMUX(destinationQmux);
			info("Set 'dest_qmux': " + destinationQmux);
		}
		catch (NotFoundException e)
		{
			throw new ConfigurationException(e);
		}
	}
	
	/*-----------------------------------------------------------------------------------------------------------------*/
	/**
	 * This method is called when a new ISOMsg is received by this ISORequestListener.
	 * 
	 * @param isoSource
	 *           The Source of the message
	 * @param requestMessage
	 *           The request message received.
	 * @return true if processed by this ISORequestListener
	 * @see org.jpos.iso.ISORequestListener#process(org.jpos.iso.ISOSource, org.jpos.iso.ISOMsg)
	 */
	@Override
	public boolean process(ISOSource isoSource, ISOMsg requestMessage)
	{
		try
		{
			
			ISOMsg translatedRequest = requestMessage;
			
			// Perform the mapping translation
			performRequestTranslation(requestMessage, translatedRequest);
			
			if (translatedRequest == null)
			{
				return false;
			}
			
			// Send the translated request
			info("[IsoMessageProcessor] Sending translated request");
			destQmux.request(translatedRequest, 10000, this, translatedRequest);
		}
		catch (ISOException e)
		{
			warn(e);
		}
		catch (IOException e)
		{
			warn(e);
		}
		// TODO why false here?
		return false;
	}
	
	/**
	 * Process a response message from the sink.
	 * 
	 * @param response
	 * @param handBack
	 * @see org.jpos.iso.ISOResponseListener#responseReceived(org.jpos.iso.ISOMsg, java.lang.Object)
	 */
	@Override
	public void responseReceived(ISOMsg response, Object handBack)
	{
		ISOMsg request = (ISOMsg)handBack;
		ISOSource isoSource = request.getSource();
		ISOMsg translatedResponse = response;
		
		try
		{
			// Perform the mapping translation
			performResponseTranslation(response, translatedResponse);
			
			if (translatedResponse == null)
			{
				return;
			}
			
			// Send the translated response.
			info("[IsoMessageProcessor] Sending translated response");
			isoSource.send(translatedResponse);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		catch (ISOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * This function performs translation of a request message from one zone to another.
	 * 
	 * @param request
	 *           This is the input or source message. i.e. The translation function will use this as
	 *           the reference when translating.
	 * @param translatedRequest
	 *           This is the output of the translation operation. The translatedRequest will be the
	 *           final output of the request translation.
	 */
	public void performRequestTranslation(ISOMsg request, ISOMsg translatedRequest)
		throws ISOException,
			IOException
	{
		// Default does nothing.
	}
	
	/**
	 * 
	 * This function performs translation of a response message from one zone to another.
	 * 
	 * @param response
	 *           This is the input or source message. i.e. The translation function will use this as
	 *           the reference when translating.
	 * @param translatedResponse
	 *           This is the output of the translation operation. The translatedResponse will be the
	 *           final output of the response translation.
	 */
	public void performResponseTranslation(ISOMsg response, ISOMsg translatedResponse)
		throws ISOException
	{
		// Default does nothing.
	}
	
	/**
	 * 
	 * This function is called when a response is not received timeously for a given request. The
	 * default implementation does nothing
	 * 
	 * @param handBack
	 *           The object that is associated with this timeout.
	 * @see org.jpos.iso.ISOResponseListener#expired(java.lang.Object)
	 */
	@Override
	public void expired(Object handBack)
	{
		// Do nothing. Don't care.
	}
}
