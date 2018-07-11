package org.gluu.oxtrust.api.oxtrust;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.gluu.oxtrust.api.configuration.oxtrust.OxTrustConfig;
import org.gluu.oxtrust.api.errors.ApiError;
import org.gluu.oxtrust.service.config.oxtrust.OxTrustConfigurationService;
import org.gluu.oxtrust.util.OxTrustApiConstants;
import org.xdi.config.oxtrust.AppConfiguration;
import org.xdi.service.security.Secure;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.HttpHeaders.LOCATION;
import static javax.ws.rs.core.Response.Status.TEMPORARY_REDIRECT;

@Path(OxTrustApiConstants.BASE_API_URL + "/configurations/oxtrust")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Secure("#{apiPermissionService.hasPermission('configuration', 'access')}")
public class OxTrustConfigurationWebService {

    @Inject
    private OxTrustConfigurationService oxTrustConfigurationService;

    @Inject
    private OxTrustConfigDtoAssembly oxTrustConfigDtoAssembly;

    @GET
    @ApiOperation("Get the existing configuration")
    @ApiResponses(
            value = {@ApiResponse(code = 200, response = OxTrustConfig.class, message = "Success")}
    )
    public Response read() {
        AppConfiguration appConfiguration = oxTrustConfigurationService.find();
        OxTrustConfig oxTrustConfig = oxTrustConfigDtoAssembly.toDto(appConfiguration);
        return Response.ok(oxTrustConfig).build();
    }

    @PUT
    @ApiOperation("Update the oxTrust configuration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, response = OxTrustConfig.class, message = "Success"),
                    @ApiResponse(code = 400, response = ApiError.class, message = "Invalid configuration")
            }
    )
    public Response save(@Valid OxTrustConfig oxTrustConfig) {
        AppConfiguration appConfiguration = oxTrustConfigDtoAssembly.fromDto(oxTrustConfig);
        oxTrustConfigurationService.save(appConfiguration);
        return read();
    }

    @GET
    @Path("/form-definition")
    @ApiOperation(value = "Read the form definition")
    @ApiResponses(
            value = {@ApiResponse(code = 200, response = String.class, message = "Success")}
    )
    public Response formDefinition(@Context HttpServletRequest request) {
        String formDefinitionUrl = request.getContextPath() + "/form-definitions/oxtrust.json";
        return Response.status(TEMPORARY_REDIRECT)
                .header(LOCATION, formDefinitionUrl)
                .build();
    }

}
