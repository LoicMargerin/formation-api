package org.enib.renew.exceptions;

import org.springframework.dao.DataAccessException;

import java.io.IOException;

public class DAOException extends Exception {
    public DAOException(Exception pEx) {
        super(pEx);
    }

    public DAOException(String pMessage) {
        super(pMessage);
    }

    public DAOException(String s, DataAccessException pEx) {
        super(pEx);
    }
}
