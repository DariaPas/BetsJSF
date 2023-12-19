package modelo.dominio;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Usuario {
	@Id
	private String nombre;
	private String password;
	@OneToMany(targetEntity=Question.class, mappedBy="usuario",
	cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private Set<Question> eventos;
	private int numeroCuenta;
	
	public Usuario(){
		
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
	
	public String toString() { 
		return nombre+"/"+password+"/";
	}
	
	public Set<Question> getEventos() {
		return eventos;
	}
	
	public void setEventos(Set<Question> eventos) {
		this.eventos = eventos;
	}
	
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}


} 
