package businessLogic;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;


import dataAccess.HibernateDataAccess;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import modelo.bean.RegisterBean;
import modelo.dominio.Evento;
import modelo.dominio.Question;
import modelo.dominio.Usuario;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	HibernateDataAccess hda;

	public BLFacadeImplementation()  {		
		System.out.println("Creating HibernateDataAccess instance");
		hda=new HibernateDataAccess();
	}
	
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
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
		 qry=hda.createAndStoreQuestion(event,question,betMinimum);		
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
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
		
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	} */
    
	@Override
    public boolean register(String username, String password, int numCuenta) {
    	return hda.storeRegister(username, password, numCuenta);
	}

	@Override
	public void initializeBD() {
		hda.initialize();
		
	}

	@Override
	public List<Question> getQuestions(Evento e) {
		List<Question> ld= hda.getQuestions(e);
		return ld;
	}



}

