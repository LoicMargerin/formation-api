package org.enib.renew.api.rest.controllers;

import org.enib.renew.api.rest.controllers.model.APIError;
import org.enib.renew.business.ISoldesBusiness;
import org.enib.renew.business.model.Solde;
import org.enib.renew.exceptions.APIErrorException;
import org.enib.renew.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.*;

@RestController
@RequestMapping(SoldesController.PATH)

public class SoldesController {
    public static final String PATH = "business";

    @Autowired
    protected ISoldesBusiness soldesBusiness;

    @Operation(operationId = "getSolde", summary = "Ramener le solde d'une personne", description = "Ramener le solde d'une personne", tags = {"Solde"}, responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Solde.class))
            }),
            @ApiResponse(responseCode = "401", description = "JWT not correctly provided or not fed.  Le JWT n'est pas correctement alimenté ou pas alimenté du tout.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIError.class))
            }),
            @ApiResponse(responseCode = "404", description = "No quality data found for the requested ID on the EFS from the JWT.  Pas de données qualité trouvées pour la personne requétée sur l'EFS issu du JWT.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server internal error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIError.class))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/soldes/{personId}", produces = {"application/json"})
    public ResponseEntity<Solde> getSolde(
            @Parameter(name = "personId", required = true, schema = @Schema()) @PathVariable("personId") String pPersonId) {

        try {

            final Solde fromBusiness = soldesBusiness.getSolde(pPersonId);
            if (null != fromBusiness) {
                return new ResponseEntity<>(fromBusiness, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }
        } catch (final BusinessException e) {
            final APIError error = new APIError();

            error.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.setCause(e.getSpecificMessage());

            throw new APIErrorException(error);
        }



    }
}
