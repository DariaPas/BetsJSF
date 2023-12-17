package modelo.bean;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import dataAccess.HibernateDataAccess;

public class FacadeBean {

	private static BLFacade blf;
	private static FacadeBean singleton= new FacadeBean();
	//private static DataAccess a=new DataAccess();
	
	private FacadeBean() {
		try {
			HibernateDataAccess a=new HibernateDataAccess();
			blf= new BLFacadeImplementation(a);
		}catch(Exception e){
			System.out.println("Está dando error: "+e.getMessage());
		}
	}
	public static FacadeBean getSingleton() {
		return singleton;
	}

	public static void setSingleton(FacadeBean singleton) {
		FacadeBean.singleton = singleton;
	}

	
	
	public static BLFacade getBlf() {
		return blf;
	}

	public static void setBlf(BLFacade blf) {
		FacadeBean.blf=blf;
	}

	public static BLFacade getBusinessLogic() {
		return blf;
	}
	
}
