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

import ec.edu.epn.laboratorioBJ.entities.Grado;
import ec.edu.epn.laboratoriosBJ.beans.GradoDAO;
import ec.edu.epn.seguridad.VO.SesionUsuario;


@ManagedBean(name = "gradoController")
@SessionScoped
public class GradoController implements Serializable {

	// ****************************************************************/
		// ********************* VARIABLES MANEJO SESION **********************/
		// ****************************************************************/

		/**
		* 
		*/
	private static final long serialVersionUID = 1L;
	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
	HttpSession session = request.getSession();
	SesionUsuario su = (SesionUsuario) session.getAttribute("sesionUsuario");

	/****************************************************************************/

	/** SERIVICIOS **/
	@EJB(lookup = "java:global/ServiciosSeguridadEPN/GradoDAOImplement!ec.edu.epn.laboratoriosBJ.beans.GradoDAO")
	private GradoDAO gradoI;

	// Variables de clase
	private List<Grado> grados = new ArrayList<>();

	// Método init
	@PostConstruct	
	public void init() {

		try {			
			grados = (gradoI.getAll(Grado.class));
		} catch (Exception e) {

		}

	}

	public List<Grado> getGrados() {
		return grados;
	}

	public void setGrados(List<Grado> grados) {
		this.grados = grados;
	}

}
