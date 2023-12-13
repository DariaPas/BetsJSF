package modelo.bean;

import java.util.Date;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import domain.Event;

public class CreateBean {
	private Date fecha; 
	private Event e;
	private Question q;
	private BLFacade blfacade;
	private Vector<Event> eventos=new Vector<Event>();
	private Vector<Date> eventosM=new Vector<Date>();
	private Vector<Question> preguntas=new Vector<Question>();
	
	private float minbet;
	private String quest_text;
	
	
	public CreateBean() {
		blfacade=FacadeBean.getBusinessLogic();
		eventos=blfacade.getEvents(fecha);	
		//eventosM=blfacade.getEventsMonth(fecha);
	}
	public Event getE() {
		return e;
	}
	public void setE(Event e) {
		this.e = e;
	}
	public Vector<Date> getEventosM() {
		return eventosM;
	}
	public void setEventosM(Vector<Date> eventosM) {
		this.eventosM = eventosM;
	}
	public Question getQ() {
		return q;
	}
	public void setQ(Question q) {
		this.q = q;
	}
	public BLFacade getBlfacade() {
		return blfacade;
	}
	public void setBlfacade(BLFacade blfacade) {
		this.blfacade = blfacade;
	}
	public Event getEvent() {
		return e;
	}
	public void setEvent(Event e) {
		this.e=e;
	}
	
	public Question getQuestion() {
		return q;
	}
	public void setQuestion(Question q) {
		this.q=q;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	
	}
	public String getQuest_text() {
        return quest_text;
    }

    public void setQuest_text(String quest_text) {
        this.quest_text = quest_text;
    }
    public float getMinbet() {
		return minbet;
	}
	public void setMinbet(float minbet) {
		this.minbet = minbet;
	}
	public Vector<Event> getEventos() {
		return eventos;
	}
	public void setEventos(Vector<Event> eventos) {
		this.eventos = eventos;
	}
	public Vector<Question> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(Vector<Question> preguntas) {
		this.preguntas = preguntas;
	}
	public void onDateSelect(SelectEvent event) {
		fecha=(Date) event.getObject();
		eventos=blfacade.getEvents(fecha);
		//eventosM=blfacade.getEventsMonth(fecha);
		//preguntas=null;
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Fecha escogida: "+event.getObject()));
		}
	public void listener(AjaxBehaviorEvent event) {
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("El tipo de evento:"+e.getEventNumber()+"/"+e.getDescription())); 
	}
	public void onEventSelect(SelectEvent event) {
		this.e=(Event)event.getObject();
		preguntas=e.getQuestions();
		FacesContext.getCurrentInstance().addMessage("miForm:mensajes",
		new FacesMessage("Preguntas del evento "+e.getDescription()));
	}
	public void CreateQuestion() throws EventFinished, QuestionAlreadyExist {
		
		if(e == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("Elige evento")); 

		}
		else if (quest_text.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("Tienes que insertar la pregunta")); 
		}
		else if (minbet < 0.0) {
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage("Bet tiene que ser mayor de 0.0")); 
		
		}
		
		blfacade.createQuestion(e, quest_text, minbet);
	}
	public void onQuestionSelect(SelectEvent event){
		FacesContext.getCurrentInstance().addMessage("miForm:mensajes",
				new FacesMessage("Preguntas del evento "+e.getDescription()));
	}
	public void onEventSelectQ(SelectEvent event) {
		this.e=(Event)event.getObject();
		preguntas=e.getQuestions();
		FacesContext.getCurrentInstance().addMessage("miForm:mensajes",
		new FacesMessage("Preguntas del evento "+e.getDescription()));
	}
}
