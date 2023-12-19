package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;


import dataAccess.HibernateDataAccess;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import modelo.dominio.Evento;
import modelo.dominio.Question;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	HibernateDataAccess hda;

	/**
	 * Constructor withut a parameter
	 */
	public BLFacadeImplementation()  {		
		System.out.println("Creating HibernateDataAccess instance");
		hda=new HibernateDataAccess();
	}
	
	/**
	 * Constructor with an HibernateDataAccess parameter
	 * @param da
	 */
    public BLFacadeImplementation(HibernateDataAccess da)  {
		System.out.println("Creating BLFacadeImplementation instance with HibernateDataAccess parameter");
		da=new HibernateDataAccess();
		da.emptyDatabase();
		da.initialize();
		hda = da;
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Evento event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	   Question qry=null;
	   if(event!=null) {
		
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
		 qry=hda.createAndStoreQuestion(event,question,betMinimum);	
	   }
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public List<Evento> getEvents(Date date)  {
		List<Evento> events=hda.getEvents(date);
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public List<Date> getEventsMonth(Date date) {
		List<Date>  dates=hda.getEventsMonth(date);
		return dates;
	}
	
	/**
	 * This method creates a user  with a username, password and accountNumber
	 * @param username
	 * @param password
	 * @param acountNumber
	 * @return true if it has been created correctly, else false
	 */
	@WebMethod
    public boolean register(String username, String password, int numCuenta) {
    	return hda.storeRegister(username, password, numCuenta);
	}
	
	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD() {
		hda.initialize();
		
	}

	/**
	 * This method retrieves the questions of a given event 
	 * @param event in which the questions are retrieved
	 * @return the list of questions of that event
	 */
	@WebMethod
	public List<Question> getQuestions(Evento e) {
		List<Question> ld= hda.getQuestions(e);
		return ld;
	}

	/**
	 * This method retrieves the user from the registered users wit the username and the password
	 * @param username
	 * @param password
	 * @return true if the user exists, else false
	 */
	@WebMethod
	public boolean login(String u, String p) {
		return hda.login(u, p);
	}



}

