package modelo.dominio;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
//private String tipo;
@OneToMany(targetEntity=Question.class, mappedBy="usuario",
cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
private Set<Question> eventos;
private int numeroCuenta;

public Usuario(){}

/*public Usuario(String tipoUsu){
	 this.tipo=tipoUsu;
	 }

public String getTipo() {
 return tipo; }
public void setTipo(String tipo) {
 this.tipo = tipo; }*/
public String getNombre() {
 return nombre; }
public void setNombre(String nombre) {
 this.nombre = nombre; }
public String getPassword() {
 return password; }
public void setPassword(String password) {
 this.password = password; }
public String toString() { // Usuario
/*return nombre+"/"+password+"/"+tipo;
 }*/
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
