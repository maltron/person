package net.nortlam.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAcceptableMapper implements ExceptionMapper<NotAcceptableException> {

    @Override
    public Response toResponse(NotAcceptableException ex) {
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}
