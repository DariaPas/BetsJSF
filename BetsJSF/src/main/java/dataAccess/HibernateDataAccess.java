package dataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import configuration.UtilDate;
import modelo.dominio.Evento;
import modelo.dominio.Question;
import modelo.HibernateUtil;
import modelo.dominio.Usuario;

public class HibernateDataAccess implements DataAccessInterface {
	protected static HibernateUtil hu;
	protected static SessionFactory s=HibernateUtil.getSessionFactory();
	

	public HibernateDataAccess()  {	
		
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation)
	 */	
	public void initialize(){
		try {

		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
		   
		   HibernateDataAccess hda= new HibernateDataAccess();
		   
		   hda.createAndStoreEvento("AtlÃ©tico-Athletic", UtilDate.newDate(year,month,17));
		   hda.createAndStoreEvento("Eibar-Barcelona", UtilDate.newDate(year,month,17));
		   hda.createAndStoreEvento("Getafe-Celta", UtilDate.newDate(year,month,17));
		   hda.createAndStoreEvento("Getafe-Celta", UtilDate.newDate(year,month,17));
		   hda.createAndStoreEvento("AlavÃ©s-Deportivo", UtilDate.newDate(year,month,17));
	    
		   Session s= HibernateUtil.getSessionFactory().getCurrentSession();
		   s.beginTransaction();
		   Query q= s.createQuery("from Evento");
		   List l= q.list();
		   Evento e1=(Evento) l.get(0);
		   Evento e2=(Evento) l.get(1);
		   Evento e3=(Evento) l.get(2);
		   Evento e4=(Evento) l.get(3);
		   hda.createAndStoreQuestion(e1,"Quien va a ganar?", 1.0F);
		   hda.createAndStoreQuestion(e2, "Quien meterá el primer gol?", 4.0F);
		   hda.createAndStoreQuestion(e3, "Quien perderá?", 5.0F);
		   hda.createAndStoreQuestion(e4, "Quien meterá el último gol?", 2.0F);	
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Evento> getEvents(Date date) {
		System.out.println(">> HibernateDataAccess: getEvents");
		List<Evento> le = new ArrayList<Evento>();	
		Session s=HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		
			Query q=s.createQuery("from Evento where eventDate= :fecha");
			q.setParameter("fecha", date);
			List<Evento> eventos=q.list();
				for(Evento e: eventos) {
					System.out.println(e.toString());
					le.add(e);
				}
				s.getTransaction().commit();
			return le;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date){
		List<Date> ld=new ArrayList<Date>();
		Session s=HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
		
		Query q= s.createQuery("SELECT DISTINCT ev.eventDate FROM Evento ev WHERE ev.eventDate BETWEEN :1 and :2");
		q.setParameter("1", firstDayMonthDate);
		q.setParameter("2", lastDayMonthDate);
		
		List<Date> dates=q.list();
		for (Date d:dates) {
			System.out.println(d.toString());
			ld.add(d);
		}
		return ld;
	}
	/**
	 * This method retrieves the questions of a given event 
	 * @param event in which the questions are retrieved
	 * @return the list of questions of that event
	 */
	public List<Question> getQuestions(Evento event){
		List<Question> lq = new ArrayList<Question>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q=session.createQuery("select ques from Question ques where ques.e=?1");
		q.setParameter("1", event);
		List<Question> eventList= q.list();
		for(Question ques: eventList) {
			System.out.println(ques.toString());
			lq.add(ques);
		}
		return lq;
	}
	
	/**
	 * This method empties the database
	 */
	public void emptyDatabase() {
		Session s=HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("delete from Question");
		Query q2= s.createQuery("delete from Evento");
		Query q3= s.createQuery("delete from Usuario");
		q.executeUpdate();
		q2.executeUpdate();
		q3.executeUpdate();
		s.getTransaction().commit();
		
	}
	
	/**
	 * This method creates and stores an event on the database with the description and the date
	 * @param description of the new event
	 * @param date of the new event
	 */
	public void createAndStoreEvento(String descripcion, Date fecha) {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 Evento e = new Evento();
		 e.setDescription(descripcion);
		 e.setEventDate(fecha);
		 session.persist(e);
		 session.getTransaction().commit();
	} 
	
	/**
	 * This method creates and stores a question on the database with the event, the question and minBet
	 * @param event in which we want to create the question
	 * @param question we want to create
	 * @param minBet of the question
	 * @return the created Question
	 */
	public Question createAndStoreQuestion(Evento event, String question, float bet) {
		 Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		 s.beginTransaction();
		 Query q=s.createQuery("from Evento where eventNumber= :eventoN");
		 q.setParameter("eventoN", event.getEventNumber());
		 Evento e= (Evento) q.list().get(0);
		 s.getTransaction().begin();
		 Question que= e.addQuestion(question, bet);
		 s.persist(e);
		 s.getTransaction().commit();
		 return que;
	}
	
	/**
	 * This method creates a user  with a username, password and accountNumber
	 * @param username
	 * @param password
	 * @param acountNumber
	 * @return true if it has been created correctly, else false
	 */
	public boolean storeRegister(String username, String password, Integer numCuenta) {
		boolean res=false;
		Session s= HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		
		Query q=s.createQuery("from Usuario where nombre= :user");
		q.setParameter("user", username);
		
		if(q.list().size()==0) {
			Usuario u= new Usuario();
			u.setNombre(username);
			u.setPassword(password);
			u.setNumeroCuenta(numCuenta);
			s.persist(u);
			res=true;
		}
		s.beginTransaction().commit();
		return res;
	}
	
	/**
	 * This method retrieves the user from the registered users wit the username and the password
	 * @param username
	 * @param password
	 * @return true if the user exists, else false
	 */
	public boolean login(String usern, String pass) {
		boolean ya=false;
		Session s=HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		
		Query q=s.createQuery("from Usuario where nombre= :user and password= :p");
		q.setParameter("user", usern);
		q.setParameter("p", pass);
		
		if(q.list().size()>0) ya=true;
		
		s.getTransaction().commit();
		
		return ya;
	}







	
}
