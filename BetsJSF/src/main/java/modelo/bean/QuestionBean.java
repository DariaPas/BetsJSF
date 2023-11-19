package modelo.bean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import domain.Question;
import domain.Event;

public class QuestionBean {
	
	private Date fecha; 
	private Event e;
	private Question q;
	
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
	public void onDateSelect(SelectEvent event) {
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Fecha escogida: "+event.getObject()));
		}
	
}