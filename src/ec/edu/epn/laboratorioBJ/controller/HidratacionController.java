package ec.edu.epn.laboratorioBJ.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ec.edu.epn.laboratorioBJ.entities.Hidratacion;
import ec.edu.epn.laboratoriosBJ.beans.HidratacionDAO;
import ec.edu.epn.seguridad.VO.SesionUsuario;
import ec.edu.epn.seguridad.servicio.ServicioSeguridad;

@ManagedBean(name ="hidratacionController")
@SessionScoped


public class HidratacionController implements Serializable{
	
	/** VARIABLES DE SESION ***/
	private static final long serialVersionUID = 6771930005130933302L;
	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
	HttpSession session = request.getSession();
	SesionUsuario su = (SesionUsuario) session.getAttribute("sesionUsuario");

	/******************************************************************/

	/** SERIVICIOS **/
	@EJB(lookup = "java:global/ServiciosSeguridadEPN/HidratacionDAOImplement!ec.edu.epn.laboratoriosBJ.beans.HidratacionDAO")
	private HidratacionDAO hidratacionI;// I (interface)
	
	private Hidratacion hidratacion;
	private List<Hidratacion> listaHidratacion;
	
	
	@PostConstruct
	public void init() {
		try {

			setHidratacion(new Hidratacion());
			listaHidratacion = new ArrayList<Hidratacion>();
			
			listaHidratacion=hidratacionI.getAll(Hidratacion.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public List<Hidratacion> getListaHidratacion() {
		return listaHidratacion;
	}


	public void setListaHidratacion(List<Hidratacion> listaHidratacion) {
		this.listaHidratacion = listaHidratacion;
	}


	public Hidratacion getHidratacion() {
		return hidratacion;
	}


	public void setHidratacion(Hidratacion hidratacion) {
		this.hidratacion = hidratacion;
	}
	
	

}
