package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import modelo.dominio.Usuario;

public class RegisterBean {
	
	private static List<Usuario> tipos=new ArrayList<Usuario>();
	private Date fecha;
	private int numeroCuenta;
	private BLFacade blfacade;
	private String nombre;
	private String password;

	public RegisterBean() {
		this.blfacade=FacadeBean.getBusinessLogic();
	 }

	public RegisterBean(String n, String p, Integer numC) {
		this.nombre=n;
		this.password=p;
		this.numeroCuenta=numC;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	public List<Usuario> getTipos() {
		return tipos;
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
	
	public void onDateSelect(SelectEvent event) {
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Fecha escogida: "+event.getObject()));
		}
	
	public String doRegister() {
		if(nombre.length()>10) {
			 FacesContext.getCurrentInstance().addMessage(null,
			 new FacesMessage("Error: La longitud del nombre no puede ser mayor de 10."));
			 return null;
		}
		if(password.length()>10) {
			 FacesContext.getCurrentInstance().addMessage(null,
			 new FacesMessage("Error: La longitud de la contraseña no puede ser nayor de 10."));
			 return null;
		}
		int cont=0;
		int aux=numeroCuenta;
		while(aux>0) {
			aux=aux/10;
			cont++;
		}
		if(cont!=4) {
			FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage("Error: La longitud de el numero de cuenta tiene que ser 4."));
			return null;
		}else {this.blfacade.register(nombre, password, numeroCuenta);	  
			return "login2";
		}	
	}
	
}