package modelo.bean;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

public class FacadeBean {

	private static BLFacade blf;
	private static FacadeBean singleton= new FacadeBean();
	
	private FacadeBean() {
		try {
			DataAccess a=new DataAccess();
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
