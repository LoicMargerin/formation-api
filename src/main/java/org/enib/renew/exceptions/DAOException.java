package org.enib.renew.exceptions;

import java.io.IOException;

public class DAOException extends Exception {
    public DAOException(IOException pEx) {
        super(pEx);
    }

    public DAOException(String pMessage) {
        super(pMessage);
    }
}
