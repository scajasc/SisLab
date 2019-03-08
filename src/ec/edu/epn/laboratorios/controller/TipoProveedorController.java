package ec.edu.epn.laboratorios.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ec.edu.epn.becas.model.NumericValidator;
import ec.edu.epn.laboratorioBJ.beans.TipoProveedorDAO;
import ec.edu.epn.laboratorioBJ.entities.Tipoproveedor;
import ec.edu.epn.seguridad.VO.SesionUsuario;

@ManagedBean(name = "tipoProveedorController")
@SessionScoped
public class TipoProveedorController implements Serializable {

	/** VARIABLES DE SESION ***/
	private static final long serialVersionUID = 6771930005130933302L;
	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
	HttpSession session = request.getSession();
	SesionUsuario su = (SesionUsuario) session.getAttribute("sesionUsuario");

	/****************************************************************************/

	/** SERIVICIOS **/

	@EJB(lookup = "java:global/ServiciosSeguridadEPN/TipoProveedorDAOImplement!ec.edu.epn.laboratorioBJ.beans.TipoProveedorDAO")

	private TipoProveedorDAO tipoProveedorI;

	// variables de la clase
	private List<Tipoproveedor> tiposProveedor = new ArrayList<>();
	private Tipoproveedor nuevoTipoProveedor;

	// Metodo Init
	@PostConstruct
	public void init() {
		try {
			tiposProveedor = tipoProveedorI.getAll(Tipoproveedor.class);
			nuevoTipoProveedor = new Tipoproveedor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void agregarTipoProveedor(ActionEvent event) {
		try {

			tipoProveedorI.save(nuevoTipoProveedor);
			tiposProveedor = tipoProveedorI.getAll(Tipoproveedor.class);

			FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Tipo de Proveedor almacenado exitosamente"));

			nuevoTipoProveedor = new Tipoproveedor();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ha ocurrido un error"));
		}

	}

	public List<Tipoproveedor> getTiposProveedor() {
		return tiposProveedor;
	}

	public void setTiposProveedor(List<Tipoproveedor> tiposProveedor) {
		this.tiposProveedor = tiposProveedor;
	}
	
	public Tipoproveedor getNuevoTipoProveedor() {
		return nuevoTipoProveedor;
	}

	public void setNuevoTipoProveedor(Tipoproveedor nuevoTipoProveedor) {
		this.nuevoTipoProveedor = nuevoTipoProveedor;
	}

}
