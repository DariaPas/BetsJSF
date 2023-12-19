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
	 * This method creates a user  with a username, password and accountNumber
	 * @param username
	 * @param password
	 * @param acountNumber
	 * @return true if it has been created correctly, else false
	 */
	boolean storeRegister(String username, String password, Integer numCuenta);
	
	/**
	 * This method creates and stores an event on the database with the description and the date
	 * @param description of the new event
	 * @param date of the new event
	 */
	void createAndStoreEvento(String descripcion, Date fecha);

}