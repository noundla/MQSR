package com.tgs.mitra.util;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.tgs.mitra.bean.Department;
import com.tgs.mitra.bean.MQTicketing;
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
	
	//MQTicketing
	public final String METHOD_NAME_MQTICKETING="MQTicketing";
	public final String SOAP_ACTION_MQTICKETING="http://tempuri.org/IMitraQSRService/MQTicketing";
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
	
	
	
	
	public ArrayList<MQTicketing> getTicketsList(User user,String fieldsToPopulate,String sortOrder,String filter)
	{
		ArrayList<MQTicketing> MQTticketsList=new ArrayList<MQTicketing>();
		try{

			System.out.println("TEST in DPART"+user.getPassword());

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_MQTICKETING);

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

			request.addPropertyIfValue("fieldsToPopulate",fieldsToPopulate);
			request.addPropertyIfValue("sortOrder", sortOrder);
			request.addPropertyIfValue("filter", filter);



			envelope.setOutputSoapObject(request);
			envelope.implicitTypes = true;
			envelope.dotNet = true;


			HttpTransportSE transport = new HttpTransportSE(URL);
			//transport.debug=true;
			transport.call(SOAP_ACTION_MQTICKETING, envelope);

			// System.out.println("TEST Request :"+transport.requestDump);
			// System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;

			SoapObject object=(SoapObject)response.getProperty(0);
			System.out.println("TEST record count:"+object.getProperty("recordCount"));

			SoapObject recordObjecct=(SoapObject)object.getProperty("records");

			//Department date getting
			MQTicketing department=null;
			SoapObject departObject=null;
			for (int i = 0; i < recordObjecct.getPropertyCount(); i++) {

				 department=new MQTicketing();
				departObject=(SoapObject)(recordObjecct).getProperty(i);
				department.setAssignedOwner(departObject.getProperty("assignedOwner").toString());
				department.setCopyToEmail(departObject.getProperty("copyToEmail").toString());
				department.setCreatedDate(departObject.getProperty("createdDate").toString());
				department.setCreatedUser(departObject.getProperty("createdUser").toString());
				department.setDepartment(departObject.getProperty("department").toString());
				department.setDetails(departObject.getProperty("details").toString());
				department.setDueDate(departObject.getProperty("dueDate").toString());
				department.setGuidfield(departObject.getProperty("guidfield").toString());
				department.setLastChange(departObject.getProperty("lastChange").toString());
				department.setLastChangeUser(departObject.getProperty("lastChangeUser").toString());
				department.setPriority(departObject.getProperty("priority").toString());
				department.setReplyId(departObject.getProperty("replyId").toString());
				department.setStoreId(departObject.getProperty("storeId").toString());
				department.setTicketId(departObject.getProperty("ticketId").toString());
				department.setTicketStatus(departObject.getProperty("ticketStatus").toString());
				department.setTitle(departObject.getProperty("title").toString());
				
			 
				
				MQTticketsList.add(department);
			}

			

		}

		catch(Exception e){
			e.printStackTrace();

			Log.e(getClass().getName(), e.getMessage());
		}
		return MQTticketsList;
	}
}
