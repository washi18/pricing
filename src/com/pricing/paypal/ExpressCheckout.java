package com.pricing.paypal;

import java.io.IOException;

import com.paypal.merchant.DoExpressCheckout;
import com.paypal.merchant.GetExpressCheckout;
import com.paypal.merchant.SetExpressCheckout;
import com.pricing.dao.CConfiguracionPaypalDAO;
import com.pricing.model.CConfiguracionPaypal;

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;

public class ExpressCheckout {
	SetExpressCheckout setExpressCheckout;
	GetExpressCheckout getExpressCheckout;
	DoExpressCheckout doExpressCheckout;
	CConfiguracionPaypal datosConfigPaypal;
	CConfiguracionPaypalDAO configPaypalDAO;
	public ExpressCheckout(){
		datosConfigPaypal=new CConfiguracionPaypal();
		configPaypalDAO=new CConfiguracionPaypalDAO();
		configPaypalDAO.asignarConfigPaypal(configPaypalDAO.recuperarConfigPaypalBD());
		if(configPaypalDAO.getDatosConfigPaypal()!=null)
			datosConfigPaypal=configPaypalDAO.getDatosConfigPaypal();
	}
	public String[] setExpressCheckoutTest(String Monto,String Descripcion) throws IOException {
		System.out.println("El monto para paypal es: "+Monto);
		String[] result=new String[2];
		System.out.println("Entre a setExpressCheckout :)");
		setExpressCheckout=new SetExpressCheckout();
		SetExpressCheckoutResponseType setExpressCheckoutResponse = setExpressCheckout
				.setExpressCheckout(Monto,Descripcion,datosConfigPaypal.getCaccountId(),datosConfigPaypal.getCsellerUserName());
		if(setExpressCheckoutResponse.getAck().getValue().equals("Success"))
		{
//			result[0]="https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
//					+ setExpressCheckoutResponse.getToken();
			result[0]="https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
					+ setExpressCheckoutResponse.getToken();
			result[1]=setExpressCheckoutResponse.getToken();
		}
		return result;
	}
	public GetExpressCheckoutDetailsResponseType getExpressCheckoutTest(String Token) throws IOException {
		getExpressCheckout=new GetExpressCheckout();
		GetExpressCheckoutDetailsResponseType getExpressCheckoutDetailsResponse = getExpressCheckout
				.getExpressCheckout(Token);
		if(getExpressCheckoutDetailsResponse.getAck().getValue().toString().equals("Success"))
		{
			System.out.println("La informacion se obtuvo correctamente :)");
		}
		return getExpressCheckoutDetailsResponse;
	}
	public String doExpressCheckoutTest(String payerId,String Token, BasicAmountType amount) throws IOException {
		doExpressCheckout=new DoExpressCheckout();
		DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentResponse = doExpressCheckout
				.doExpressCheckout(payerId,Token,amount,datosConfigPaypal.getCaccountId());
			if(doExpressCheckoutPaymentResponse.getAck().getValue().equals("Success"))
			{
				System.out.println("Se obtuvo la transaccion :)");
			}
		return doExpressCheckoutPaymentResponse
				.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo()
				.get(0).getTransactionID();
	}
}
