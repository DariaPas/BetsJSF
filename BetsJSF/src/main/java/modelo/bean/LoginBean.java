package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import modelo.dominio.Usuario;

public class LoginBean {
	
	private Usuario tipo;
	//private static List<Usuario> tipos=new ArrayList<Usuario>();

	private Date fecha; 
	
	private String nombre;
	private String password;
	private BLFacade blfacade;

	public LoginBean() {
		this.blfacade= FacadeBean.getBusinessLogic();
	 }
	/*public Usuario getTipo() {
		return tipo;
	 }
	public void setTipo(Usuario tipo) {
		this.tipo = tipo;
		System.out.println("El tipo del usuario:"+tipo.getTipo());
	 }*/
	/*public List<Usuario> getTipos() {
		return tipos;
	 }
	public void setTipos(List<Usuario> tipos) {
		this.tipos = tipos;
	 }*/

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

public void login() {
		blfacade.login(nombre, password);
 }
public void onDateSelect(SelectEvent event) {
	 FacesContext.getCurrentInstance().addMessage(null,
	 new FacesMessage("Fecha escogida: "+event.getObject()));
}

}