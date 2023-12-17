package modelo.dominio;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
public class Question {
	 @Id
	 private Long id;
	 private String descripcion;
	 private Date fecha;
	 @ManyToOne(targetEntity=Usuario.class, cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	 @Fetch(value = FetchMode.JOIN)
	 private Usuario usuario;
	 public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	private boolean login;
	
	 public Question() {}
	
	public Long getId() {
		return id;
	 }
	public void setId(Long id) {
		this.id = id;
	 }
	public String getDescripcion() {
		return descripcion;
	 }
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	 }
	public Date getFecha() {
		return fecha;
	 }
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	 }

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	
} 
