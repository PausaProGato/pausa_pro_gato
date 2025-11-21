package br.com.pausaprogato;

import br.com.pausaprogato.beans.QualidadeSono;
import br.com.pausaprogato.bo.RegistroDiarioBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;

@Path("/qualidadesono")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QualidadeSonoResource {

    @POST
    public Response inserir(QualidadeSono obj) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        String msg = bo.inserirQualidadeSono(obj);
        return Response.status(Response.Status.CREATED)
                .entity("{\"message\": \"" + msg + "\"}")
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, QualidadeSono obj) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        obj.setId(id);
        String msg = bo.atualizarQualidadeSono(obj);
        return Response.ok("{\"message\": \"" + msg + "\"}").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        String msg = bo.deletarQualidadeSono(id);
        return Response.ok("{\"message\": \"" + msg + "\"}").build();
    }


    @GET
    public Response listarTodos() throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        return Response.ok(bo.selecionarQualidadeSono()).build();
    }
}

