/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//import javax.inject.Named;
//import javax.enterprise.context.Dependent;
import Model.Dao.ClientesDao;
import Model.Entities.Clientes;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Miguel
 */
//@Named(value = "clientesController")
//@Dependent
@ManagedBean
@ViewScoped
public class ClientesController implements Serializable {

    public ClientesController() {
    }
    
    private List<Clientes> listaClientes;
    private Clientes cliente;

    public List<Clientes> getListaClientes() {
        
        ClientesDao clientesDao = new ClientesDao();
        this.listaClientes = clientesDao.listarClientes();
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    public void limpiarCliente(){
        cliente = new Clientes();
        cliente.setFechaIngreso(new Date());
    }
    
    public void agregarCliente(){
        ClientesDao cd = new ClientesDao();
        cd.agregar(cliente);
    }
    
    public void modificarCliente(){
        ClientesDao cd = new ClientesDao();
        cd.modificar(cliente);
        limpiarCliente();
    }
    
    public void eliminarCliente(){
         ClientesDao cd = new ClientesDao();
         cd.eliminar(cliente);
         limpiarCliente();
    }
}