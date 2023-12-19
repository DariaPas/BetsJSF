package businessLogic;


import java.util.Date;
import java.util.List;

import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import modelo.dominio.Evento;
import modelo.dominio.Question;

import javax.jws.WebMethod;
import javax.servlet.annotation.WebServlet;

/**
 * Interface that specifies the business logic.
 */
@WebServlet
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Evento event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public List<Evento> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public List<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	/**
	 * This method creates a user  with a username, password and accountNumber
	 * @param username
	 * @param password
	 * @param acountNumber
	 * @return true if it has been created correctly, else false
	 */
	@WebMethod boolean register(String u, String p, int numC);

	/**
	 * This method retrieves the questions of a given event 
	 * @param event in which the questions are retrieved
	 * @return the list of questions of that event
	 */
	@WebMethod List<Question> getQuestions(Evento e);
	
	/**
	 * This method retrieves the user from the registered users wit the username and the password
	 * @param username
	 * @param password
	 * @return true if the user exists, else false
	 */
	@WebMethod boolean login(String u, String p);



	
}
