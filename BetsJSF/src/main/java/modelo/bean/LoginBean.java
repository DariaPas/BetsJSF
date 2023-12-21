package modelo.bean;


import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;

public class LoginBean {

	private Date fecha; 
	private String nombre;
	private String password;
	private BLFacade blfacade;

	public LoginBean() {
		this.blfacade= FacadeBean.getBusinessLogic();
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {
		boolean esta=blfacade.login(nombre, password);
		if(esta) {
			return "login";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage("El usuario o la contraseña son incorrectos"));
			return null;
		}
	}
	
	public void onDateSelect(SelectEvent event) {
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Fecha escogida: "+event.getObject()));
	}

}
