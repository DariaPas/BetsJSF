package modelo.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@SuppressWarnings("serial")
@Entity
public class Question implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 private String descripcion;
	 private Date fecha;
	 private float betM;
	 
	 @OneToOne
	 private Evento e;
	 @ManyToOne(targetEntity=Usuario.class, cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	 @Fetch(value = FetchMode.JOIN)
	 private Usuario usuario;
	 public boolean isLogin() {
		return login;
	}
	 
	 public Question() {
		 super();
	 }
	 @Override
	public String toString() {
		return  id + ", " + descripcion;
	}
	 
	 public Question(String question, float betMinimum, Evento event) {
			this.descripcion=question;
			this.betM=betMinimum;
			this.e=event;
		}

	public float getBetM() {
			return betM;
		}

		public void setBetM(float betM) {
			this.betM = betM;
		}

		public Evento getE() {
			return e;
		}

		public void setE(Evento e) {
			this.e = e;
		}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	private boolean login;
	
	 

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

	

	
} 
