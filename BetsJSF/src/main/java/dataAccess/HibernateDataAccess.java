package dataAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import configuration.UtilDate;
import modelo.dominio.Evento;
import modelo.dominio.Question;
import exceptions.QuestionAlreadyExist;
import modelo.HibernateUtil;
import modelo.bean.RegisterBean;
import modelo.dominio.Usuario;

public class HibernateDataAccess implements DataAccessInterface {
	//protected static EntityManager  db;
//	protected static EntityManagerFactory emf;
	protected static HibernateUtil hu;
	
	//ConfigXML c=ConfigXML.getInstance();
	
	protected static SessionFactory s=HibernateUtil.getSessionFactory();
	

	public HibernateDataAccess()  {	
		
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initialize(){
		
		//db.getTransaction().begin();
		System.out.println("fjisofsa");
		//Calendar
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
		   
		   HibernateDataAccess hda= new HibernateDataAccess();
		   
		   hda.createAndStoreEvento("Atlético-Athletic", UtilDate.newDate(year,month,17));
		   hda.createAndStoreEvento("Eibar-Barcelona", UtilDate.newDate(year,month,17));
		   hda.createAndStoreEvento("Getafe-Celta", UtilDate.newDate(year,month,17));
		   hda.createAndStoreEvento("Getafe-Celta", UtilDate.newDate(year,month,17));
		   hda.createAndStoreEvento("Alavés-Deportivo", UtilDate.newDate(year,month,17));
	    
		   Session s= HibernateUtil.getSessionFactory().getCurrentSession();
		   s.beginTransaction();
		   Query q= s.createQuery("from Evento");
		   List l= q.list();
		   Evento e1=(Evento) l.get(0);
		   Evento e2=(Evento) l.get(1);
		   Evento e3=(Evento) l.get(2);
		   Evento e4=(Evento) l.get(3);
		   hda.createAndStoreQuestion(e1,"Quien va a ganar?", 1.0F);
		   hda.createAndStoreQuestion(e2, "Quien meter� el primer gol?", 4.0F);
		   hda.createAndStoreQuestion(e3, "Quien perder�?", 5.0F);
		   hda.createAndStoreQuestion(e4, "Quien meter� el �ltimo gol?", 2.0F);
		   
	
			
		
			/*Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");*/
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
	

	public boolean existQuestion(Evento event, String question) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("FROM Question WHERE event_eventN = :eventN AND question = :question");
		q.setParameter("eventN", event.getEventNumber());
		q.setParameter("question", question);
		if(q.list().size()!=0) {
			return true;
		}else {
			return false;
		}
	}
	

	@Override
	public void emptyDatabase() {
		Session s=HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("delete from Question");
		Query q2= s.createQuery("delete from Evento");
		q.executeUpdate();
		q2.executeUpdate();
		s.getTransaction().commit();
		
	}
	
	
	public void createAndStoreEvento(String descripcion, Date fecha) {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 Evento e = new Evento();
		 e.setDescription(descripcion);
		 e.setEventDate(fecha);
		 session.persist(e);
		 session.getTransaction().commit();
	} 
	
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
	
	public boolean login(String usern, String pass) {
		boolean ya=false;
		Session s=HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		
		Query q=s.createQuery("from Usuario where nombre= :user and password= :p");
		q.setParameter("user", usern);
		q.setParameter("p", pass);
		
		if(q.list().size()!=0) ya=true;
		
		return ya;
	}







	
}
