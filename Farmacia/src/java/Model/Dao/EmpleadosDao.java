package Model.Dao;

import Model.Entities.Empleados;
import Model.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpleadosDao {
    
    public List<Empleados> ListaEmpleados(){
        List<Empleados> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "FROM Empleados";
        try{
            lista = session.createQuery(hql).list();
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }
        
        return lista;
    }
    
    public void agregar(Empleados empleado) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(empleado);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }
    
    public void modificar(Empleados empleado) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(empleado);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }
    
    public void eliminar(Empleados empleado) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(empleado);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }
    
    
    public Empleados login(Empleados user) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT u FROM Empleados u WHERE u.usuario = :usuario AND u.clave = :clave";
        Query q = sesion.createQuery(hql);
        q.setParameter("usuario", user.getUsuario());
        q.setParameter("clave", user.getClave());
        return (Empleados) q.uniqueResult();
    }
    
}
