package net.nortlam.testing;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.types.ObjectId;

/**
 *
 * @author Mauricio "Maltron" Leal <maltron@gmail.com> */
@Path("/v1")
public class Resource implements Serializable {

    private static final Logger LOG = Logger.getLogger(Resource.class.getName());
    
    @EJB
    private Service service;
    
    @GET @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmail(@PathParam("email") String email) throws NotFoundException {
        Person person = service.findByEmail(email);
        return Response.ok(person).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String person) {
        LOG.log(Level.INFO, ">>> create():{0}", person != null ? person.toString() : "NULL");
        
//        ObjectId objectId = service.create(person);
        
        return Response.ok().build();
    }


}
