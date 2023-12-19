package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import modelo.dominio.Evento;
import modelo.dominio.Question;

public class CreateBean {
	private Date fecha; 
	private Evento e;
	private Question q;
	private BLFacade blfacade;
	private List<Evento> eventos=new ArrayList<Evento>();
	private List<Date> eventosM=new ArrayList<Date>();
	private List<Question> preguntas=new ArrayList<Question>();
	
	private float minbet;
	private String quest_text;
	
	
	public CreateBean() {
		blfacade=FacadeBean.getBusinessLogic();
		eventos=blfacade.getEvents(fecha);	
	}
	
	public Evento getE() {
		return e;
	}
	
	public void setE(Evento e) {
		this.e = e;
	}
	
	public List<Date> getEventosM() {
		return eventosM;
	}
	
	public void setEventosM(List<Date> eventosM) {
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
	
	public Evento getEvent() {
		return e;
	}
	
	public void setEvent(Evento e) {
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
	
	public List<Evento> getEventos() {
		return eventos;
	}
	
	public void setEventos(Vector<Evento> eventos) {
		this.eventos = eventos;
	}
	
	public List<Question> getPreguntas() {
		return preguntas;
	}
	
	public void setPreguntas(Vector<Question> preguntas) {
		this.preguntas = preguntas;
	}
	
	public void onDateSelect(SelectEvent event) {
		fecha=(Date) event.getObject();
		eventos=blfacade.getEvents(fecha);
		FacesContext.getCurrentInstance().addMessage(null,
		new FacesMessage("Fecha escogida: "+event.getObject()));
	}
	
	public void listener(AjaxBehaviorEvent event) {
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("El tipo de evento:"+e.getEventNumber()+"/"+e.getDescription())); 
	}
	
	public void onEventSelect(SelectEvent event) {
		this.e=(Evento)event.getObject();
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
		this.e=(Evento)event.getObject();
		preguntas=e.getQuestions();
		FacesContext.getCurrentInstance().addMessage("miForm:mensajes",
		new FacesMessage("Preguntas del evento "+e.getDescription()));
	}
}
