package ec.edu.epn.laboratoriosBJ.controller;

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
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;


import ec.edu.epn.LaboratoriosBJ.beans.PosgiroDAO;
import ec.edu.epn.laboratorioBJ.entities.Posgiro;
import ec.edu.epn.seguridad.VO.SesionUsuario;




@ManagedBean(name = "PosgiroController")
@SessionScoped
public class PosgiroController implements Serializable{
	
	/** VARIABLES DE SESION ***/
	private static final long serialVersionUID = 1L;
	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
	HttpSession session = request.getSession();
	SesionUsuario su = (SesionUsuario) session.getAttribute("sesionUsuario");
	
	/****************************************************************************/

	/** SERIVICIOS DAOS **/
	@EJB(lookup = "java:global/ServiciosSeguridadEPN/PosgiroDAOImplement!ec.edu.epn.LaboratoriosBJ.beans.PosgiroDAO")
	private PosgiroDAO posgiroI;
	
	
	/**VARIABLES DE LA CLASE**/
	private List<Posgiro> listarPosgiros =new ArrayList<>();
	private Posgiro nuevoPosgiro;
	
	/**METODO INIT**/
	@PostConstruct
	public void init(){		
		try {
			listarPosgiros= posgiroI.getAll(Posgiro.class);
			nuevoPosgiro=new Posgiro();
		} catch (Exception e) {
					
		}
	}

	
	/**METODO CREAR NUEVO USUARIO**/
	public void agregarPosgiro(ActionEvent event) {
		try {
			
			posgiroI.save(nuevoPosgiro);
			listarPosgiros = posgiroI.getAll(Posgiro.class);

			FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Posgiro almacenado exitosamente"));
			
			nuevoPosgiro = new Posgiro();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ha ocurrido un error"));
		}

	}
	
	
    /**GET AND SET POSGIRO**/

	public List<Posgiro> getListarPosgiros() {
		return listarPosgiros;
	}

	public void setListarPosgiros(List<Posgiro> listarPosgiros) {
		this.listarPosgiros = listarPosgiros;
	}
	
	public Posgiro getNuevoPosgiro() {
		return nuevoPosgiro;
	}

	public void setNuevoPosgiro(Posgiro nuevoPosgiro) {
		this.nuevoPosgiro = nuevoPosgiro;
	}

	
	

}
