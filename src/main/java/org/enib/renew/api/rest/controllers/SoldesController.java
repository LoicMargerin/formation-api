package org.enib.renew.api.rest.controllers;

import org.enib.renew.api.rest.model.APIError;
import org.enib.renew.api.rest.model.Solde;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.*;

import java.util.Map;

@RestController
@RequestMapping(SoldesController.PATH)

public class SoldesController {
    public static final String PATH = "business";

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
            @Parameter(name = "personId", required = true, schema = @Schema()) @PathVariable("personId") String personId)
 {
        return new ResponseEntity<>(new Solde(), HttpStatus.OK);

    }
}
