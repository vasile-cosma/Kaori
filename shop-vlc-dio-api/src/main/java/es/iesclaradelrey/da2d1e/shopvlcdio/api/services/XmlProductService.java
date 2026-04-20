package es.iesclaradelrey.da2d1e.shopvlcdio.api.services;

import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

public interface XmlProductService {

    Document exportAll() throws ParserConfigurationException;

    void importProducts(InputStream inputStream) throws Exception;

}
