package org.enib.renew.api.rest.controllers;

import org.enib.renew.api.rest.controllers.model.APIError;
import org.enib.renew.business.IDevisesBusiness;
import org.enib.renew.business.ISoldesBusiness;
import org.enib.renew.business.model.Devise;
import org.enib.renew.business.model.Solde;
import org.enib.renew.exceptions.APIErrorException;
import org.enib.renew.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.*;

import java.util.List;

@RestController
@RequestMapping(MainController.PATH)

public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    public static final String PATH = "business";

    @Autowired
    protected ISoldesBusiness soldesBusiness;

    @Autowired
    protected IDevisesBusiness devisesBusiness;

    @Operation(operationId = "getSolde", summary = "Ramener le solde d'une personne", description = "Ramener le solde d'une personne", tags = {"Solde"}, responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Solde.class))
            }),
            @ApiResponse(responseCode = "404", description = "Pas de donnée trouvée", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server internal error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIError.class))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/persons/{personId}/solde", produces = {"application/json"})
    public ResponseEntity<Solde> getSolde(
            @Parameter(name = "personId", required = true, schema = @Schema()) @PathVariable("personId") String pPersonId) {

        try {

            final Solde fromBusiness = soldesBusiness.getSolde(pPersonId);
            if (null != fromBusiness) {
                return new ResponseEntity<>(fromBusiness, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }
        } catch (final BusinessException pEx) {
            LOGGER.error("Erreur sur getSolde pour la personne {} !", pPersonId, pEx);
            final APIError error = new APIError();

            error.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.setCause(pEx.getSpecificMessage());

            throw new APIErrorException(error);
        } catch (Exception pEx) {
            LOGGER.error("Erreur inattendue sur getSolde pour la personne {} !", pPersonId, pEx);
            final APIError error = new APIError();

            error.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());

            throw new APIErrorException(error);
        }
    }

    @Operation(operationId = "getPersonIds", summary = "Ramener tous les id des personnes", description = "Ramener tous les id des personnes", tags = {"Persons"}, responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Solde.class))
            }),
            @ApiResponse(responseCode = "404", description = "Pas de donnée trouvée", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server internal error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIError.class))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/persons", produces = {"application/json"})
    public ResponseEntity<List<String>> getPersonIds() {

        try {

            final List<String> fromBusiness = soldesBusiness.getPersonIds();
            if (null != fromBusiness) {
                return new ResponseEntity<>(fromBusiness, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }
        } catch (final BusinessException pEx) {
            LOGGER.error("Erreur sur la récupération des IDs personne !", pEx);
            final APIError error = new APIError();

            error.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.setCause(pEx.getSpecificMessage());

            throw new APIErrorException(error);
        } catch (Exception pEx) {
            LOGGER.error("Erreur inattendue sur getPersonIds !", pEx);
            final APIError error = new APIError();

            error.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());

            throw new APIErrorException(error);
        }
    }

    @Operation(operationId = "getDevises", summary = "Ramener toutes les devises", description = "Ramener toutes les devises", tags = {"Devises"}, responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Solde.class))
            }),
            @ApiResponse(responseCode = "404", description = "Pas de donnée trouvée", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIError.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server internal error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = APIError.class))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/devises", produces = {"application/json"})
    public ResponseEntity<List<Devise>> getDevises() {

        try {

            final List<Devise> fromBusiness = devisesBusiness.getDevises();
            if (null != fromBusiness) {
                return new ResponseEntity<>(fromBusiness, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }
        } catch (final BusinessException pEx) {
            LOGGER.error("Erreur sur la récupération des devises !", pEx);
            final APIError error = new APIError();

            error.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.setCause(pEx.getSpecificMessage());

            throw new APIErrorException(error);
        } catch (Exception pEx) {
            LOGGER.error("Erreur inattendue sur devises !", pEx);
            final APIError error = new APIError();

            error.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());

            throw new APIErrorException(error);
        }
    }

}
