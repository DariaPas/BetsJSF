package principal;

import modelo.HibernateUtil;
import modelo.dominio.QuestionLogin;
import org.hibernate.Session;
import java.util.*;

public class CrearPregunta {

	public CrearPregunta(){}
	
	private void createAndStoreQuestionLogin(Long id, String descripcion, Date fecha) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		QuestionLogin e = new QuestionLogin();
		e.setId(id);
		e.setDescripcion(descripcion);
		e.setFecha(fecha);
		session.save(e);
		session.getTransaction().commit();
	}
	
	private List listaPreguntas() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from QuestionLogin").list();
		session.getTransaction().commit();
		return result;
	}
	public static void main(String[] args) {
		CrearPregunta e = new CrearPregunta();
		System.out.println("Creación de preguntas:");
		e.createAndStoreQuestionLogin(1L,"Pepe ha hecho login correctamente", new Date());
		e.createAndStoreQuestionLogin(2L,"Nerea ha intentado hacer login", new Date());
		e.createAndStoreQuestionLogin(3L,"Kepa ha hecho login correctamente", new Date());
		System.out.println("Listado de eventos:");
		
		List eventos = e.listaPreguntas();
		for (int i = 0; i < eventos.size(); i++) {
		QuestionLogin ev = (QuestionLogin) eventos.get(i);
		System.out.println("Id: " + ev.getId() + " Descripcion: "
		+ ev.getDescripcion() + " Fecha: " + ev.getFecha());
	}
	}
}
