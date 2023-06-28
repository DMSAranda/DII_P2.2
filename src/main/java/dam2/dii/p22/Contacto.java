package dam2.dii.p22;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dam2.dii.p22.gestor.Gestor;
import dam2.dii.p22.model.Contact;

@Path("contacto")
public class Contacto {
	
	
	private static Map<Integer, Contact> agenda2 = Gestor.getListaContactos();    //incializamos la agenda
/*	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getContactByCode(@QueryParam("codigo") int codigo) {
    	
    	String nombre;
    	
    	try{
	    	Contact contacto = Gestor.leerContacto(codigo);
	    	
	    	if (contacto!=null) {
		    	
		    	nombre = contacto.getName();
	    	}
	    	
	    	else {
	    		nombre = "no existe";
	      	}
    	}catch (NumberFormatException e){
    		
    		 nombre = "no se ha especificado codigo";
    	}
    	
        return nombre;
    }
    
    @GET
    @Path("metodo2")
    @Produces(MediaType.TEXT_PLAIN)
    public String getContactByCode2(@QueryParam("codigo") int codigo) {
    	
    	String nombre;
    	
    	try {
	    	Contact contacto = Gestor.leerContacto(codigo);
	    	
	    	if (contacto!=null) {
		    	
		    	nombre = contacto.getName();
	    	}
	    	
	    	else {
	    		nombre = "no existe";
	      	}
    	}catch (NumberFormatException e){
    		
   		 nombre = "no se ha especificado codigo";
    	}
        return nombre;
    }
    
    */
    
    @GET
    @Path("{codigo}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getContactByCode3(@PathParam("codigo") int codigo) {
    	
    	String nombre;
    	
    	try {
	    	Contact contacto = Gestor.leerContacto(codigo);
	    	
	    	if (contacto!=null) {
		    	
		    	nombre = contacto.getName();
	    	}
	    	
	    	else {
	    		nombre = "no existe";
	      	}
    	}catch (NumberFormatException e){
    		
   		 nombre = "no se ha especificado codigo";
    	}
        return nombre;
    }
    
  /*
    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public Contact getContactByJson(@QueryParam("codigo") int codigo) {
    	
    	Contact contacto = Gestor.leerContacto(codigo);
	     if(contacto==null) {
	    	 contacto = new Contact(0,"No existe", "Nulo","","","");
	     }
        return contacto;
    }
    
    
    @GET
    @Path("json2")
    @Produces(MediaType.APPLICATION_JSON)
    public Contact getContactByJson2(@DefaultValue("0") @QueryParam("codigo") int codigo) {
    	
    	Contact contacto = Gestor.leerContacto(codigo);
	     if(contacto==null) {
	    	 contacto = new Contact(codigo,"No existe", "Nulo","","","");
	     }
        return contacto;
    }
*/
	
    //CONTAR
    @GET
    @Path("size")
    @Produces(MediaType.TEXT_PLAIN)
    public int getSumaContactos() {
    	
    	int total=0;
    	total = agenda2.size();
        return total;
    }
    
    //LISTAR
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Contact> getContact() {
    	
        return Gestor.extraerListaContactos();
    }
    
    //INSERTAR
    
    @POST
    @Path("{codigo}")
    @Produces(MediaType.TEXT_PLAIN)
    public String insertContact(@PathParam("codigo") int codigo,
    		@QueryParam("name") String name,
    		@QueryParam("surname") String surname, 
    		@QueryParam("email") String email, 
    		@QueryParam("phone") String phone,
    		@QueryParam("comments") String comments) {
    	
    	boolean result;
    	String mensaje;
    	
    	Contact con = new Contact(codigo, name, surname, email, phone, comments);
    	
    	if (Gestor.compruebaExiste(con)==false) {
    	
	    	result = Gestor.insertarContacto(con);
	    	
	    	if(result == true) {
	    		mensaje = "El contacto se ha insertado correctamente.";
	    	}
	    	else mensaje = "No se ha realizado la inserción.";
    	}
    	
    	else mensaje = "Ya existe un usuario con esos datos";
    	
    	return mensaje;
    }
    
  
    
  /* 
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertContact2(Contact con) {
    	
    	boolean result;
    	String mensaje;
    	  	
    	result = Gestor.insertarContacto(con);
    	
    	if(result == true) {
    		mensaje = "El contacto se ha insertado correctamente.";
    	}
    	else mensaje = "No se ha realizado la inserción.";
    	
    	return mensaje;
    }
  */  
    
    
    //BORRAR
    
    @DELETE
    @Path("{codigo}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteContact(@PathParam("codigo") int codigo) {
    	
    	boolean result;
    	String mensaje;
    	
    	result = Gestor.borrarContacto(codigo);
    	
    	if(result == true) {
    		mensaje = "El contacto se ha eliminado correctamente.";
    	}
    	else mensaje = "No se ha realizado la eliminacion.";
    	
    	return mensaje;
    }
    
    //ACTUALIZAR
    @PUT
    @Path("{codigo}")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateContact(@PathParam("codigo") int codigo,
    		@DefaultValue("")@QueryParam("name") String name,
    		@DefaultValue("")@QueryParam("surname") String surname, 
    		@DefaultValue("")@QueryParam("email") String email, 
    		@DefaultValue("")@QueryParam("phone") String phone,
    		@DefaultValue("")@QueryParam("comments") String comments) {
    	
    	boolean result;
    	String mensaje;
    	
    	Contact con = new Contact(codigo, name, surname, email, phone, comments);
    	
    	
    	if (Gestor.compruebaExiste(con)==false) {
		    	result = Gestor.actualizarContacto(con);
		    	
		    	if(result == true) {
		    		mensaje = "El contacto se ha actualizado correctamente.";
		    	}
		    	else mensaje = "No se ha realizado la actualización.";
    	}
    	
    	else mensaje = "Ya existe un usuario con esos datos";
    	
    	return mensaje;
    }
    
    
    public static void main(String...args) {
    	
    	int total;
    	
    	total = agenda2.size();
    	System.out.println(total);
    	
    }
    
   
    
    
}
