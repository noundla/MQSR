package com.tgs.mitra.util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class UtilMethod {

	
	public  final  String NAMESPACE = "http://tempuri.org/";
	public final String URL = "http://76.31.161.97:808/MitraQSRAPI/MitraQSRService.svc?wsdl";
	
	//Login
	public final String METHOD_NAME_LOGIN = "Login";
	private String MOCK_SERVICE="http://schemas.datacontract.org/2004/07/MitraQSRAPI";
	                           //http://schemas.datacontract.org/2004/07/MitraQSRAPI
	public static String SOAP_ACTION_LOGIN = "http://tempuri.org/IMitraQSRService/Login";
	
	public UtilMethod() {
		// TODO Auto-generated constructor stub
	}
	public boolean getLoginState(String username,String password)
	{

		  boolean state=false;
			try{
				
				
				/*HttpPost httppost = new HttpPost(URL)       ;   
				
				
				String SOAPRequestXML=" <soapenv:Body><tem:Login><tem:authorization><mit:password>9balaji@</mit:password><mit:userName>Balaji</mit:userName></tem:authorization></tem:Login></soapenv:Body>";
				StringEntity se = new StringEntity(SOAPRequestXML,HTTP.UTF_8);

				se.setContentType("text/xml");  
				httppost.setHeader("Content-Type","application/soap+xml;charset=UTF-8");
				httppost.setEntity(se);  

				HttpClient httpclient = new DefaultHttpClient();
				BasicHttpResponse httpResponse = 
				    (BasicHttpResponse) httpclient.execute(httppost);

				//response.put("HTTPStatus",httpResponse.getStatusLine().toString());
				
				System.out.println("TEST RESPONSE done"+httpResponse.getStatusLine().toString());*/
				
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_LOGIN);

				PropertyInfo propertyPassword = new PropertyInfo();
				propertyPassword.setName("password");
				propertyPassword.setNamespace(MOCK_SERVICE);
				propertyPassword.setValue(password);
				propertyPassword.setType(null);
				

				
				PropertyInfo propertyusername = new PropertyInfo();
				propertyusername.setName("userName");
				propertyusername.setNamespace(MOCK_SERVICE);
				propertyusername.setValue(username);
				propertyusername.setType(null);
				
				
				SoapObject userObject=new SoapObject(NAMESPACE, "authorization");
				userObject.addProperty(propertyPassword);
				userObject.addProperty(propertyusername);
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				 
				 request.addProperty("authorization",userObject);
				
				//request.addProperty(propertyusername);
				//request.addProperty(propertyPassword);
				
				envelope.setOutputSoapObject(request);
				envelope.implicitTypes = true;
				envelope.dotNet = true;

				
				HttpTransportSE transport = new HttpTransportSE(URL);
				//transport.debug=true;
				transport.call(SOAP_ACTION_LOGIN, envelope);
				
				//System.out.println("Request OBJ:"+transport);
	            // System.out.println("Request :"+transport.requestDump);
	            // System.out.println("Response :"+transport.responseDump);
	             SoapObject response = (SoapObject) envelope.bodyIn;
	            // System.out.println("TEST login"+response);
	             SoapObject object=(SoapObject) response.getProperty(0);
	             
	          state=Boolean.valueOf(object.getProperty("ok").toString());
	              
			}

			catch(Exception e){
				e.printStackTrace();

				Log.e(getClass().getName(), e.getMessage());
			}
			return state;

		}
}
