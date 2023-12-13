package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import modelo.dominio.Usuario;

public class LoginBean {
	
	private Usuario tipo;
	private static List<Usuario> tipos=new ArrayList<Usuario>();

	private Date fecha; 
	
	private String nombre;
	private String password;

	public LoginBean() {
		tipos.add(new Usuario("estudiante"));
		//tipos.add(new TipoUsuario(2,"profesor"));
	 }
	public Usuario getTipo() {
		return tipo;
	 }
	public void setTipo(Usuario tipo) {
		this.tipo = tipo;
		System.out.println("El tipo del usuario:"+tipo.getTipo());
	 }
	public List<Usuario> getTipos() {
		return tipos;
	 }
	public void setTipos(List<Usuario> tipos) {
		this.tipos = tipos;
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

public String comprobar() {
			if (nombre.length()!=password.length()){
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Error: La longitud del nombre y de la contraseña son diferentes."));
		 return null;
		 }
			if(nombre.equals("pirata")){
		 return "login";
		 }
			else {
		 return "login";
		 }
 }
public void onDateSelect(SelectEvent event) {
	 FacesContext.getCurrentInstance().addMessage(null,
	 new FacesMessage("Fecha escogida: "+event.getObject()));
	}
public static Usuario getObject(String tipo) {
	 for (Usuario t: tipos){
	 if (tipo.equals(t.getTipo()))
	 return t;}
	 return null;
	}
public void listener(AjaxBehaviorEvent event) {
	 FacesContext.getCurrentInstance().addMessage(null,
	 new FacesMessage("El tipo del usuario:"+tipo.getTipo())); 
}
public void onEventSelect(SelectEvent event) {
this.tipo=(Usuario)event.getObject();
FacesContext.getCurrentInstance().addMessage("miForm:mensajes",
 new FacesMessage("El tipo del usuario (tabla):"+tipo.getTipo()));}
}