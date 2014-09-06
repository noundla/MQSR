package com.tgs.mitra.util;

import java.sql.Date;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.tgs.mitra.bean.Department;
import com.tgs.mitra.bean.MQTicketing;
import com.tgs.mitra.bean.User;

public class UtilMethod {


	public  final  String NAMESPACE = "http://tempuri.org/";
	public final String URL = "http://73.166.145.211:808/MitraQSRAPI/MitraQSRService.svc?wsdl";

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

	//ReplyTicket
	public final String METHOD_NAME_REPLYTICKET="ReplyTicket";
	public final String SOAP_ACTION_REPLYTICKET="http://tempuri.org/IMitraQSRService/ReplyTicket";


	//CreateTicket
	public final String METHOD_NAME_CREATETICKET="CreateTicket";
	public final String SOAP_ACTION_CREATETICKET="http://tempuri.org/IMitraQSRService/CreateTicket";

	//DepartmentQuestions
	public final String METHOD_NAME_DEPARTMENTQUESTIONS="DepartmentQuestions";
	public final String SOAP_ACTION_DEPARTMENTQUESTIONS="http://tempuri.org/IMitraQSRService/DepartmentQuestions";

	//MyTickets
	public final String METHOD_NAME_MYTICKETS="MyTickets";
	public final String SOAP_ACTION_MYTICKETS="http://tempuri.org/IMitraQSRService/MyTickets";

	//Userallowedstores
	public final String METHOD_NAME_USERALLOWEDSTORES="Userallowedstores";
	public final String SOAP_ACTION_USERALLOWEDSTORES="http://tempuri.org/IMitraQSRService/Userallowedstores";

	//ReplyTickets
	public final String METHOD_NAME_REPLYTICKETS="ReplyTickets";
	public final String SOAP_ACTION_REPLYTICKETS="http://tempuri.org/IMitraQSRService/ReplyTickets";


	//HomeScreen
	public final String METHOD_NAME_HOMESCREEN="HomeScreen";
	public final String SOAP_ACTION_HOMESCREEN="http://tempuri.org/IMitraQSRService/HomeScreen";
	
	//popupReply
	public final String METHOD_NAME_POPUPREPLY="popupReply";
	public final String SOAP_ACTION_POPUPREPLY="http://tempuri.org/IMitraQSRService/popupReply";
	
	//Home Ticket type
		public final String METHOD_NAME_HOMESCREENINFO="HomeScreenInfo";
		public final String SOAP_ACTION_HOMESCREENINFO="http://tempuri.org/IMitraQSRService/HomeScreenInfo";
		
		public static int createdTicketId=0;


	/**
	 * Crate object for Methods class.
	 */
	public UtilMethod() {
		createdTicketId=0;
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

		}
		return departments;
	}

	/**
	 * 
	 * @param user
	 * @param fieldsToPopulate
	 * @param sortOrder
	 * @param filter
	 * @return
	 */
	public ArrayList<MQTicketing> getTicketsList(User user,String fieldsToPopulate,String sortOrder,String filter)
	{
		ArrayList<MQTicketing> MQTticketsList=new ArrayList<MQTicketing>();
		try{

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
			MQTicketing ticketing=null;
			SoapObject departObject=null;
			for (int i = 0; i < recordObjecct.getPropertyCount(); i++) {

				ticketing=new MQTicketing();
				departObject=(SoapObject)(recordObjecct).getProperty(i);
				ticketing.setAssignedOwner(departObject.getProperty("assignedOwner"));
				ticketing.setCopyToEmail(departObject.getProperty("copyToEmail"));
				ticketing.setCreatedDate(departObject.getProperty("createdDate"));
				ticketing.setCreatedUser(departObject.getProperty("createdUser"));
				ticketing.setDepartment(departObject.getProperty("department"));
				ticketing.setDetails(departObject.getProperty("details"));
				ticketing.setDueDate(departObject.getProperty("dueDate"));
				ticketing.setGuidfield(departObject.getProperty("guidfield"));
				ticketing.setLastChange(departObject.getProperty("lastChange"));
				ticketing.setLastChangeUser(departObject.getProperty("lastChangeUser"));
				ticketing.setPriority(departObject.getProperty("priority"));
				ticketing.setReplyId(departObject.getProperty("replyId"));
				ticketing.setStoreId(departObject.getProperty("storeId"));
				ticketing.setTicketId(departObject.getProperty("ticketId"));
				ticketing.setTicketStatus(departObject.getProperty("ticketStatus"));
				ticketing.setTitle(departObject.getProperty("title"));

				MQTticketsList.add(ticketing);
			}

		}

		catch(Exception e){
			e.printStackTrace();

		}
		return MQTticketsList;
	}
	/**
	 * Create Ticket
	 * @param curentUser
	 * @param createTicket
	 * @return
	 */
	public boolean createTicket(User curentUser,MQTicketing createTicket )
	{

		boolean result=false;
		try{
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_CREATETICKET);

			PropertyInfo propertyPassword = new PropertyInfo();
			propertyPassword.setName("password");
			propertyPassword.setNamespace(MOCK_SERVICE);
			propertyPassword.setValue(curentUser.getPassword());
			propertyPassword.setType(null);


			PropertyInfo propertyusername = new PropertyInfo();
			propertyusername.setName("userName");
			propertyusername.setNamespace(MOCK_SERVICE);
			propertyusername.setValue(curentUser.getUser());
			propertyusername.setType(null);


			SoapObject userObject=new SoapObject(NAMESPACE, "authorization");
			userObject.addProperty(propertyPassword);
			userObject.addProperty(propertyusername);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

			request.addProperty("authorization",userObject);

			String MOBSERVICE="http://schemas.datacontract.org/2004/07/MitraBO";


			SoapObject newTicket=new SoapObject(NAMESPACE, "newTicket");

			PropertyInfo assignedOwner = new PropertyInfo();
			assignedOwner.setName("assignedOwner");
			assignedOwner.setNamespace(MOBSERVICE);
			assignedOwner.setValue(createTicket.getAssignedOwner());
			assignedOwner.setType(null);
			newTicket.addProperty(assignedOwner);

			//ticket.addPropertyIfValue("assignedOwner",replayTicket.getAssignedOwner());

			PropertyInfo copyToEmail = new PropertyInfo();
			copyToEmail.setName("copyToEmail");
			copyToEmail.setNamespace(MOBSERVICE);
			copyToEmail.setValue(createTicket.getCopyToEmail());
			copyToEmail.setType(null);

			newTicket.addProperty(copyToEmail);

			//ticket.addPropertyIfValue("copyToEmail", replayTicket.getCopyToEmail());

			PropertyInfo createdDate = new PropertyInfo();
			createdDate.setName("createdDate");
			createdDate.setNamespace(MOBSERVICE);
			createdDate.setValue(createTicket.getCreatedDate());
			createdDate.setType(null);

			newTicket.addProperty(createdDate);
			//ticket.addPropertyIfValue("createdDate", replayTicket.getCreatedDate());

			PropertyInfo createdUser = new PropertyInfo();
			createdUser.setName("createdUser");
			createdUser.setNamespace(MOBSERVICE);
			createdUser.setValue(createTicket.getCreatedUser());
			createdUser.setType(null);

			newTicket.addProperty(createdUser);
			//ticket.addPropertyIfValue("createdUser", replayTicket.getCreatedUser());


			PropertyInfo department = new PropertyInfo();
			department.setName("department");
			department.setNamespace(MOBSERVICE);
			department.setValue(createTicket.getDepartment());
			department.setType(null);

			newTicket.addProperty(department);
			//ticket.addPropertyIfValue("department", replayTicket.getDepartment());
			PropertyInfo details = new PropertyInfo();
			details.setName("details");
			details.setNamespace(MOBSERVICE);
			details.setValue( createTicket.getDetails());
			details.setType(null);
			newTicket.addProperty(details);
			//ticket.addPropertyIfValue("details", replayTicket.getDetails());

			PropertyInfo dueDate = new PropertyInfo();
			dueDate.setName("dueDate");
			dueDate.setNamespace(MOBSERVICE);
			dueDate.setValue( createTicket.getDueDate());
			dueDate.setType(null);
			newTicket.addProperty(dueDate);
			//ticket.addPropertyIfValue("dueDate", replayTicket.getDueDate());
			PropertyInfo guidfield = new PropertyInfo();
			guidfield.setName("guidfield");
			guidfield.setNamespace(MOBSERVICE);
			guidfield.setValue(createTicket.getGuidfield());
			guidfield.setType(null);
			newTicket.addProperty(guidfield);

			//ticket.addPropertyIfValue("guidfield", replayTicket.getGuidfield());
			PropertyInfo lastChange = new PropertyInfo();
			lastChange.setName("lastChange");
			lastChange.setNamespace(MOBSERVICE);
			lastChange.setValue(createTicket.getLastChange());
			lastChange.setType(null);
			newTicket.addProperty(lastChange);
			//ticket.addPropertyIfValue("lastChange", replayTicket.getLastChange());

			PropertyInfo lastChangeUser = new PropertyInfo();
			lastChangeUser.setName("lastChangeUser");
			lastChangeUser.setNamespace(MOBSERVICE);
			lastChangeUser.setValue(createTicket.getLastChangeUser());
			lastChangeUser.setType(null);
			newTicket.addProperty(lastChangeUser);
			//ticket.addPropertyIfValue("lastChangeUser", replayTicket.getLastChangeUser());

			PropertyInfo priority = new PropertyInfo();
			priority.setName("priority");
			priority.setNamespace(MOBSERVICE);
			priority.setValue(createTicket.getPriority());
			priority.setType(null);
			newTicket.addProperty(priority);
			//ticket.addPropertyIfValue("priority", replayTicket.getPriority());

			PropertyInfo replyId = new PropertyInfo();
			replyId.setName("replyId");
			replyId.setNamespace(MOBSERVICE);
			replyId.setValue(createTicket.getReplyId());
			replyId.setType(null);
			newTicket.addProperty(replyId);
			//ticket.addPropertyIfValue("replyId", replayTicket.getReplyId());

			PropertyInfo storeId = new PropertyInfo();
			storeId.setName("storeId");
			storeId.setNamespace(MOBSERVICE);
			storeId.setValue(createTicket.getStoreId());
			storeId.setType(null);
			newTicket.addProperty(storeId);
			//ticket.addPropertyIfValue("storeId", replayTicket.getStoreId());

			PropertyInfo ticketId = new PropertyInfo();
			ticketId.setName("ticketId");
			ticketId.setNamespace(MOBSERVICE);
			ticketId.setValue(createTicket.getTicketId());
			ticketId.setType(null);
			newTicket.addProperty(ticketId);
			//ticket.addPropertyIfValue("ticketId", replayTicket.getTicketId());

			PropertyInfo ticketStatus = new PropertyInfo();
			ticketStatus.setName("ticketStatus");
			ticketStatus.setNamespace(MOBSERVICE);
			ticketStatus.setValue(createTicket.getTicketStatus());
			ticketStatus.setType(null);
			newTicket.addProperty(ticketStatus);
			//ticket.addPropertyIfValue("ticketStatus", replayTicket.getTicketStatus());

			PropertyInfo title = new PropertyInfo();
			title.setName("title");
			title.setNamespace(MOBSERVICE);
			title.setValue(createTicket.getTitle());
			title.setType(null);
			newTicket.addProperty(title);
			//ticket.addPropertyIfValue("title", replayTicket.getTitle());

			request.addProperty("newTicket",newTicket);

			envelope.setOutputSoapObject(request);
			envelope.implicitTypes = true;
			envelope.dotNet = true;


			HttpTransportSE transport = new HttpTransportSE(URL);
			transport.debug=true;
			transport.call(SOAP_ACTION_CREATETICKET, envelope);

			//System.out.println("TEST Request :"+transport.requestDump);
			System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;
			//System.out.println("TEST RESULT :"+response);

			SoapObject object=(SoapObject) response.getProperty(0);

			System.out.println("TEST web service "+object.getProperty("ticketId").toString());
			result=Boolean.valueOf(object.getProperty("ok").toString());
			


		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{

		}
		return result;


	}

	/**
	 * 
	 * @param user
	 * @param depatId
	 * @param storeId
	 * @return
	 */
	public ArrayList<MQDetQsn> getDeptQuestions(User user,String depatId,String storeId)
	{

		ArrayList<MQDetQsn> MQDetQsnList=new ArrayList<MQDetQsn>();
		try{

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_DEPARTMENTQUESTIONS);

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

			request.addPropertyIfValue("DeptId",depatId);
			request.addPropertyIfValue("storeId", storeId);



			envelope.setOutputSoapObject(request);
			envelope.implicitTypes = true;
			envelope.dotNet = true;


			HttpTransportSE transport = new HttpTransportSE(URL);
			// transport.debug=true;
			transport.call(SOAP_ACTION_DEPARTMENTQUESTIONS, envelope);

			//System.out.println("TEST Request :"+transport.requestDump);
			//System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;

			SoapObject object=(SoapObject)response.getProperty(0);

			SoapObject depobj=null;
			MQDetQsn detQsn=null;
			for (int i = 0; i < object.getPropertyCount(); i++) {
				depobj=(SoapObject)(object).getProperty(i);
				detQsn=new MQDetQsn();
				detQsn.setDepartmentId(depobj.getProperty("DepartmentId").toString());
				detQsn.setQuestionId(depobj.getProperty("QuestionId").toString());
				detQsn.setQuestionTitle(depobj.getProperty("QuestionTitle").toString());
				MQDetQsnList.add(detQsn); 
			}

		}

		catch(Exception e){
			e.printStackTrace();


		}
		return MQDetQsnList;


	}
	/**
	 * 
	 * @param curentUser Current user information 
	 * @param replayTicket Replay for ticket.
	 * @return
	 */
	public boolean replayTicket(User curentUser,MQTicketing replayTicket )
	{
		boolean result=false;
		createdTicketId=0;
		try{
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_REPLYTICKET);

			PropertyInfo propertyPassword = new PropertyInfo();
			propertyPassword.setName("password");
			propertyPassword.setNamespace(MOCK_SERVICE);
			propertyPassword.setValue(curentUser.getPassword());
			propertyPassword.setType(null);


			PropertyInfo propertyusername = new PropertyInfo();
			propertyusername.setName("userName");
			propertyusername.setNamespace(MOCK_SERVICE);
			propertyusername.setValue(curentUser.getUser());
			propertyusername.setType(null);


			SoapObject userObject=new SoapObject(NAMESPACE, "authorization");
			userObject.addProperty(propertyPassword);
			userObject.addProperty(propertyusername);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

			request.addProperty("authorization",userObject);

			String MOBSERVICE="http://schemas.datacontract.org/2004/07/MitraBO";


			SoapObject ticket=new SoapObject(NAMESPACE, "Ticket");

			PropertyInfo assignedOwner = new PropertyInfo();
			assignedOwner.setName("assignedOwner");
			assignedOwner.setNamespace(MOBSERVICE);
			assignedOwner.setValue(replayTicket.getAssignedOwner());
			assignedOwner.setType(null);
			ticket.addProperty(assignedOwner);

			//ticket.addPropertyIfValue("assignedOwner",replayTicket.getAssignedOwner());

			PropertyInfo copyToEmail = new PropertyInfo();
			copyToEmail.setName("copyToEmail");
			copyToEmail.setNamespace(MOBSERVICE);
			copyToEmail.setValue(replayTicket.getCopyToEmail());
			copyToEmail.setType(null);

			ticket.addProperty(copyToEmail);

			//ticket.addPropertyIfValue("copyToEmail", replayTicket.getCopyToEmail());

			PropertyInfo createdDate = new PropertyInfo();
			createdDate.setName("createdDate");
			createdDate.setNamespace(MOBSERVICE);
			createdDate.setValue(replayTicket.getCreatedDate());
			createdDate.setType(null);

			ticket.addProperty(createdDate);
			//ticket.addPropertyIfValue("createdDate", replayTicket.getCreatedDate());

			PropertyInfo createdUser = new PropertyInfo();
			createdUser.setName("createdUser");
			createdUser.setNamespace(MOBSERVICE);
			createdUser.setValue(replayTicket.getCreatedUser());
			createdUser.setType(null);

			ticket.addProperty(createdUser);
			//ticket.addPropertyIfValue("createdUser", replayTicket.getCreatedUser());


			PropertyInfo department = new PropertyInfo();
			department.setName("department");
			department.setNamespace(MOBSERVICE);
			department.setValue(replayTicket.getDepartment());
			department.setType(null);

			ticket.addProperty(department);
			//ticket.addPropertyIfValue("department", replayTicket.getDepartment());
			PropertyInfo details = new PropertyInfo();
			details.setName("details");
			details.setNamespace(MOBSERVICE);
			details.setValue( replayTicket.getDetails());
			details.setType(null);
			ticket.addProperty(details);
			//ticket.addPropertyIfValue("details", replayTicket.getDetails());

			PropertyInfo dueDate = new PropertyInfo();
			dueDate.setName("dueDate");
			dueDate.setNamespace(MOBSERVICE);
			dueDate.setValue( replayTicket.getDueDate());
			dueDate.setType(null);
			ticket.addProperty(dueDate);
			//ticket.addPropertyIfValue("dueDate", replayTicket.getDueDate());
			PropertyInfo guidfield = new PropertyInfo();
			guidfield.setName("guidfield");
			guidfield.setNamespace(MOBSERVICE);
			guidfield.setValue(replayTicket.getGuidfield());
			guidfield.setType(null);
			ticket.addProperty(guidfield);

			//ticket.addPropertyIfValue("guidfield", replayTicket.getGuidfield());
			PropertyInfo lastChange = new PropertyInfo();
			lastChange.setName("lastChange");
			lastChange.setNamespace(MOBSERVICE);
			lastChange.setValue(replayTicket.getLastChange());
			lastChange.setType(Date.class);
			ticket.addProperty(lastChange);
			//ticket.addPropertyIfValue("lastChange", replayTicket.getLastChange());

			PropertyInfo lastChangeUser = new PropertyInfo();
			lastChangeUser.setName("lastChangeUser");
			lastChangeUser.setNamespace(MOBSERVICE);
			lastChangeUser.setValue(replayTicket.getLastChangeUser());
			lastChangeUser.setType(null);
			ticket.addProperty(lastChangeUser);
			//ticket.addPropertyIfValue("lastChangeUser", replayTicket.getLastChangeUser());

			PropertyInfo priority = new PropertyInfo();
			priority.setName("priority");
			priority.setNamespace(MOBSERVICE);
			priority.setValue(replayTicket.getPriority());
			priority.setType(null);
			ticket.addProperty(priority);
			//ticket.addPropertyIfValue("priority", replayTicket.getPriority());

			PropertyInfo replyId = new PropertyInfo();
			replyId.setName("replyId");
			replyId.setNamespace(MOBSERVICE);
			replyId.setValue(replayTicket.getReplyId());
			replyId.setType(null);
			ticket.addProperty(replyId);
			//ticket.addPropertyIfValue("replyId", replayTicket.getReplyId());

			PropertyInfo storeId = new PropertyInfo();
			storeId.setName("storeId");
			storeId.setNamespace(MOBSERVICE);
			storeId.setValue(replayTicket.getStoreId());
			storeId.setType(null);
			ticket.addProperty(storeId);
			//ticket.addPropertyIfValue("storeId", replayTicket.getStoreId());

			PropertyInfo ticketId = new PropertyInfo();
			ticketId.setName("ticketId");
			ticketId.setNamespace(MOBSERVICE);
			ticketId.setValue(replayTicket.getTicketId());
			ticketId.setType(null);
			ticket.addProperty(ticketId);
			//ticket.addPropertyIfValue("ticketId", replayTicket.getTicketId());

			PropertyInfo ticketStatus = new PropertyInfo();
			ticketStatus.setName("ticketStatus");
			ticketStatus.setNamespace(MOBSERVICE);
			ticketStatus.setValue(replayTicket.getTicketStatus());
			ticketStatus.setType(null);
			ticket.addProperty(ticketStatus);
			//ticket.addPropertyIfValue("ticketStatus", replayTicket.getTicketStatus());

			PropertyInfo title = new PropertyInfo();
			title.setName("title");
			title.setNamespace(MOBSERVICE);
			title.setValue(replayTicket.getTitle());
			title.setType(null);
			ticket.addProperty(title);
			//ticket.addPropertyIfValue("title", replayTicket.getTitle());

			request.addProperty("Ticket",ticket);

			envelope.setOutputSoapObject(request);
			envelope.implicitTypes = true;
			envelope.dotNet = true;


			HttpTransportSE transport = new HttpTransportSE(URL);
			transport.debug=true;
			transport.call(SOAP_ACTION_REPLYTICKET, envelope);

			//System.out.println("TEST Request :"+transport.requestDump);
			System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;

			SoapObject object=(SoapObject) response.getProperty(0);

			result=Boolean.valueOf(object.getProperty("ok").toString());

			if(result)
			{
				createdTicketId=Integer.parseInt(object.getProperty("ticketId").toString());
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		 
		return result;

	}


	/**
	 * 
	 * @param user
	 * @param status
	 * @return
	 */
	public ArrayList<MQTickets> getMyTeckets(User user,String status)
	{

		ArrayList<MQTickets> mQTicketsList=new ArrayList<MQTickets>();
		try{

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_MYTICKETS);

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

			request.addPropertyIfValue("Status",status);

			envelope.setOutputSoapObject(request);
			envelope.implicitTypes = true;
			envelope.dotNet = true;


			HttpTransportSE transport = new HttpTransportSE(URL);
			// transport.debug=true;
			transport.call(SOAP_ACTION_MYTICKETS, envelope);

			//  System.out.println("TEST Request :"+transport.requestDump);
			// System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;

			SoapObject object=(SoapObject)response.getProperty(0);

			SoapObject depobj=null;
			MQTickets myticket=null;
			for (int i = 0; i < object.getPropertyCount(); i++) {
				depobj=(SoapObject)(object).getProperty(i);
				myticket=new MQTickets();
				myticket.setDepartmentId(depobj.getProperty("DepartmentId").toString());
				myticket.setDepartmentName(depobj.getProperty("DepartmentName").toString());
				myticket.setLastModified(depobj.getProperty("LastModified").toString());
				myticket.setLastModifiedBy(depobj.getProperty("LastModifiedBy").toString());
				myticket.setStatus(depobj.getProperty("Status").toString());
				myticket.setTicketDescription(depobj.getProperty("TicketDescription").toString());
				myticket.setTicketId(depobj.getProperty("TicketId").toString());
				myticket.setTicketTitle(depobj.getProperty("TicketTitle").toString());

				mQTicketsList.add(myticket); 
			}

		}

		catch(Exception e){
			e.printStackTrace();


		}
		return mQTicketsList;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public ArrayList<String> getUserallowedstoresList(User user)
	{

		ArrayList<String> stroList=new ArrayList<String>();

		try{


			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_USERALLOWEDSTORES);

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
			transport.call(SOAP_ACTION_USERALLOWEDSTORES, envelope);

			// System.out.println("Request :"+transport.requestDump);
			// System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;
			SoapObject object=(SoapObject) response.getProperty(0);
			
			

			for (int i = 0; i < object.getPropertyCount(); i++) {

				stroList.add(object.getProperty(i).toString());
			}

		}

		catch(Exception e){
			e.printStackTrace();

		}
		return stroList;

	}


	/**
	 * Replay user created and assinged tickets.
	 * @param user
	 * @return
	 */
	public ArrayList<MQTickets> getReplyTicketsList(User user)
	{

		ArrayList<MQTickets> mQTicketsList=new ArrayList<MQTickets>();

		try{


			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_REPLYTICKETS);

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
			transport.call(SOAP_ACTION_REPLYTICKETS, envelope);

			//System.out.println("Request :"+transport.requestDump);
			//System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;
			SoapObject object=(SoapObject) response.getProperty(0);

			SoapObject depobj=null;
			MQTickets myticket=null;
			for (int i = 0; i < object.getPropertyCount(); i++) {
				depobj=(SoapObject)(object).getProperty(i);
				myticket=new MQTickets();
				myticket.setDepartmentId(depobj.getProperty("DepartmentId").toString());
				myticket.setDepartmentName(depobj.getProperty("DepartmentName").toString());
				myticket.setLastModified(depobj.getProperty("LastModified").toString());
				myticket.setLastModifiedBy(depobj.getProperty("LastModifiedBy").toString());
				myticket.setStatus(depobj.getProperty("Status").toString());
				//anyType{}
				if(!depobj.getProperty("TicketDescription").toString().equalsIgnoreCase("anyType{}"))
				{
				myticket.setTicketDescription(depobj.getProperty("TicketDescription").toString());
				}
				myticket.setTicketId(depobj.getProperty("TicketId").toString());
				myticket.setTicketTitle(depobj.getProperty("TicketTitle").toString());
				//hasReply
				myticket.setHasReplay(Boolean.valueOf(depobj.getProperty("hasReply").toString()));
				//new field Replyscount
				myticket.setReplayCount(Integer.parseInt(depobj.getProperty("Replyscount").toString()));
				mQTicketsList.add(myticket); 
			}


		}

		catch(Exception e){
			e.printStackTrace();

		}
		return mQTicketsList;

	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public ArrayList<HomeScreenInfo> getHomeScreenInfoList(User user)
	{

		ArrayList<HomeScreenInfo> homeinfoList=new ArrayList<HomeScreenInfo>();

		try{


			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_HOMESCREEN);

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
			transport.call(SOAP_ACTION_HOMESCREEN, envelope);

			// System.out.println("Request :"+transport.requestDump);
			// System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;
			SoapObject object=(SoapObject) response.getProperty(0);


			SoapObject homeScreen=null;
			HomeScreenInfo mHomeScreenInfo=null;
			for (int i = 0; i < object.getPropertyCount(); i++) {
				homeScreen=(SoapObject)(object).getProperty(i);
				mHomeScreenInfo=new HomeScreenInfo();
				 
				mHomeScreenInfo.setDescription(homeScreen.getProperty("description").toString());
				mHomeScreenInfo.setCount(homeScreen.getProperty("icount").toString());
				mHomeScreenInfo.setImage(homeScreen.getProperty("imageURL").toString());

				homeinfoList.add(mHomeScreenInfo); 
			}

		}

		catch(Exception e){
			e.printStackTrace();

		}
		return homeinfoList;

	}
	
	
	/**
	 * 
	 * @param user
	 * @param ticketId
	 * @return
	 */
	public ArrayList<MQReply> getTicketPopup(User user,String ticketId)
	{

		ArrayList<MQReply> mMQReplyList=new ArrayList<MQReply>();
		try{

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_POPUPREPLY);

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

			request.addPropertyIfValue("ticketId",ticketId);

			envelope.setOutputSoapObject(request);
			envelope.implicitTypes = true;
			envelope.dotNet = true;


			HttpTransportSE transport = new HttpTransportSE(URL);
			// transport.debug=true;
			transport.call(SOAP_ACTION_POPUPREPLY, envelope);

			//  System.out.println("TEST Request :"+transport.requestDump);
			// System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;

			SoapObject object=(SoapObject)response.getProperty(0);
			
			System.out.println("TEST obj :"+object.getPropertyCount()+object);
			
			SoapObject depobj=null;
			MQReply replayObj=null;
			
			for (int i = 0; i < object.getPropertyCount(); i++) {
				depobj=(SoapObject)(object).getProperty(i);
				replayObj=new MQReply();
				
				System.out.println("TEST :::"+depobj.getProperty("ReplyMessages").toString());
				replayObj.setReplayMessage(depobj.getProperty("ReplyMessages").toString());
				
				mMQReplyList.add(replayObj);
			}
			

		}

		catch(Exception e){
			e.printStackTrace();


		}
		return mMQReplyList;
	}
	
	/**
	 * 
	 * @param user
	 * @param ticketsType
	 * @return
	 */
	public ArrayList<MQTickets> getHomeTicketsInfo(User user,String ticketsType)
	{

		ArrayList<MQTickets> mQTicketsList=new ArrayList<MQTickets>();
		try{

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_HOMESCREENINFO);

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

			request.addPropertyIfValue("TicketsType",ticketsType);

			envelope.setOutputSoapObject(request);
			envelope.implicitTypes = true;
			envelope.dotNet = true;


			HttpTransportSE transport = new HttpTransportSE(URL);
			 transport.debug=true;
			transport.call(SOAP_ACTION_HOMESCREENINFO, envelope);

			System.out.println("TEST Request :"+transport.requestDump);
			System.out.println("Response :"+transport.responseDump);
			SoapObject response = (SoapObject) envelope.bodyIn;

			SoapObject object=(SoapObject)response.getProperty(0);
			
			System.out.println("TEST obj :"+object.getPropertyCount());
			
			SoapObject depobj=null;
			MQTickets myticket=null;
			for (int i = 0; i < object.getPropertyCount(); i++) {
				depobj=(SoapObject)(object).getProperty(i);
				myticket=new MQTickets();
				//myticket.setDepartmentId(depobj.getProperty("DepartmentId").toString());
				myticket.setDepartmentName(depobj.getProperty("DepartmentName").toString());
				myticket.setLastModified(depobj.getProperty("LastModified").toString());
				myticket.setLastModifiedBy(depobj.getProperty("LastModifiedBy").toString());
				myticket.setStatus(depobj.getProperty("Status").toString());
				myticket.setTicketDescription(depobj.getProperty("TicketDescription").toString());
				myticket.setTicketId(depobj.getProperty("TicketId").toString());
				myticket.setTicketTitle(depobj.getProperty("TicketTitle").toString());
				//hasReply
				//myticket.setHasReplay(Boolean.valueOf(depobj.getProperty("hasReply").toString()));
				mQTicketsList.add(myticket); 
			}
			

		}

		catch(Exception e){
			e.printStackTrace();


		}
		return mQTicketsList;
	}

}
