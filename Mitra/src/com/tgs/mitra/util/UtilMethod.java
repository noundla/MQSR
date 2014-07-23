package com.tgs.mitra.util;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.tgs.mitra.bean.Department;
import com.tgs.mitra.bean.User;

import android.util.Log;

public class UtilMethod {


	public  final  String NAMESPACE = "http://tempuri.org/";
	public final String URL = "http://76.31.161.97:808/MitraQSRAPI/MitraQSRService.svc?wsdl";

	//Login
	public final String METHOD_NAME_LOGIN = "Login";
	private String MOCK_SERVICE="http://schemas.datacontract.org/2004/07/MitraQSRAPI";
	public static String SOAP_ACTION_LOGIN = "http://tempuri.org/IMitraQSRService/Login";

	//Department
	public final String METHOD_NAME_MQDEPARTMENT="MQDepartment";
	public final String SOAP_ACTION_MQDEPARTMENT="http://tempuri.org/IMitraQSRService/MQDepartment";
	public UtilMethod() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean getLoginState(User user)
	{

		boolean state=false;
		try{


			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_LOGIN);

			PropertyInfo propertyPassword = new PropertyInfo();
			propertyPassword.setName("password");
			propertyPassword.setNamespace(MOCK_SERVICE);
			propertyPassword.setValue(user.getPassword());
			propertyPassword.setType(null);


			PropertyInfo propertyusername = new PropertyInfo();
			propertyusername.setName("userName");
			propertyusername.setNamespace(MOCK_SERVICE);
			propertyusername.setValue(user.getUser());
			propertyusername.setType(null);


			SoapObject userObject=new SoapObject(NAMESPACE, "authorization");
			userObject.addProperty(propertyPassword);
			userObject.addProperty(propertyusername);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

			request.addProperty("authorization",userObject);

			envelope.setOutputSoapObject(request);
			envelope.implicitTypes = true;
			envelope.dotNet = true;


			HttpTransportSE transport = new HttpTransportSE(URL);
			//transport.debug=true;
			transport.call(SOAP_ACTION_LOGIN, envelope);

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

	/**
	 * 
	 * @param user
	 * @return
	 */
	public ArrayList<Department> getDepartmentList(User user)
	{
		ArrayList<Department> departments=new ArrayList<Department>();
		try{

			System.out.println("TEST in DPART"+user.getPassword());

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_MQDEPARTMENT);

			PropertyInfo propertyPassword = new PropertyInfo();
			propertyPassword.setName("password");
			propertyPassword.setNamespace(MOCK_SERVICE);
			propertyPassword.setValue(user.getPassword());
			propertyPassword.setType(null);


			PropertyInfo propertyusername = new PropertyInfo();
			propertyusername.setName("userName");
			propertyusername.setNamespace(MOCK_SERVICE);
			propertyusername.setValue(user.getUser());
			propertyusername.setType(null);


			SoapObject userObject=new SoapObject(NAMESPACE, "authorization");
			userObject.addProperty(propertyPassword);
			userObject.addProperty(propertyusername);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

			request.addProperty("authorization",userObject);

			request.addPropertyIfValue("fieldsToPopulate","");
			request.addPropertyIfValue("sortOrder", "");
			request.addPropertyIfValue("filter", "");



			envelope.setOutputSoapObject(request);
			envelope.implicitTypes = true;
			envelope.dotNet = true;


			HttpTransportSE transport = new HttpTransportSE(URL);
			//transport.debug=true;
			transport.call(SOAP_ACTION_MQDEPARTMENT, envelope);

			// System.out.println("TEST Request :"+transport.requestDump);
			// System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;

			SoapObject object=(SoapObject)response.getProperty(0);
			System.out.println("TEST record count:"+object.getProperty("recordCount"));

			SoapObject recordObjecct=(SoapObject)object.getProperty("records");

			//Department date getting
			Department department=null;
			SoapObject departObject=null;
			for (int i = 0; i < recordObjecct.getPropertyCount(); i++) {

				department=new Department();
				departObject=(SoapObject)(recordObjecct).getProperty(i);

				department.setAllowTicketing(Boolean.valueOf(departObject.getProperty("allowTicketing").toString()));
				department.setDepartment(departObject.getProperty("department").toString());
				department.setGuidfield(departObject.getProperty("guidfield").toString());
				department.setLastChange(departObject.getProperty("lastChange").toString());
				department.setLastChangeUser(departObject.getProperty("lastChangeUser").toString());

				departments.add(department);
			}


		}

		catch(Exception e){
			e.printStackTrace();

			Log.e(getClass().getName(), e.getMessage());
		}
		return departments;
	}
}
