package dataAccess;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import exceptions.QuestionAlreadyExist;
import modelo.bean.RegisterBean;
import modelo.dominio.Evento;
import modelo.dominio.Question;

public interface DataAccessInterface {

		
	/**
	 * This method removes all the elements of the database
	 */
	void emptyDatabase();
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	void initialize();

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	List<Evento> getEvents(Date date);

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	List<Date> getEventsMonth(Date date);

	
	/**
	 * This method checks if the question has been previously added to the event 
	 * 
	 * @param event the event
	 * @param question the question to check  
	 * @return true if the event contains this the questions, false in other case
	 */
	boolean existQuestion(Evento event, String question);

	/**
	 * 
	 * @param username
	 * @param password
	 * @param numCuenta
	 * @return
	 */
	boolean storeRegister(String username, String password, Integer numCuenta);
	
	
	void createAndStoreEvento(String descripcion, Date fecha);

}