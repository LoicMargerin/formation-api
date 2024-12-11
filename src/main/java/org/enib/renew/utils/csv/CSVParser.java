package org.enib.renew.utils.csv;

import org.enib.renew.exceptions.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVParser<T> {

    /**
     * Définition du mapper
     * @param <T>
     */
    public interface Mapper<T> {
        public T getFromValues(final String[] pValues) throws DAOException;
    }
    /**
     * Parser un CSV
     * @param pPath
     * @return
     * @throws DAOException
     */
    public List<T> parseFile(final ResourceLoader resourceLoader, final String pPath, final Mapper<T> pMapper) throws DAOException {
        final List<T> res = new ArrayList<>();
        try {
            // Lire toutes les lignes du fichier CSV
            final Resource resource = resourceLoader.getResource(String.format("classpath:%s", pPath));
            List<String> lignes = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8);

            // Itérer sur chaque ligne
            for (String ligne : lignes) {
                // Diviser la ligne par la virgule
                res.add(pMapper.getFromValues(ligne.split(";")));

            }
            return res;
        } catch (IOException pEx) {
            throw new DAOException(pEx);
        }
    }
}
