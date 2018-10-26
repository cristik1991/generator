package com.cristik.code.delegate.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;

/**
 * @author cristik
 */
public class GeneratorDtdResolver implements EntityResolver {

    private static final String DTD_EXTENSION = ".dtd";

    private static final String DTD_NAME = "generator";

    private static final Logger logger = LoggerFactory.getLogger(GeneratorDtdResolver.class);

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (logger.isTraceEnabled()) {
            logger.trace("Trying to resolve XML entity with public ID [" + publicId +
                    "] and system ID [" + systemId + "]");
        }
        if (systemId != null && systemId.endsWith(DTD_EXTENSION)) {
            int lastPathSeparator = systemId.lastIndexOf('/');
            int dtdNameStart = systemId.indexOf(DTD_NAME, lastPathSeparator);
            if (dtdNameStart != -1) {
                String dtdFile = DTD_NAME + DTD_EXTENSION;
                if (logger.isTraceEnabled()) {
                    logger.trace("Trying to locate [" + dtdFile + "] in Spring jar on classpath");
                }
                try {
                    URL resourceURL = GeneratorDtdResolver.class.getResource(dtdFile);
                    InputSource source = new InputSource(resourceURL.openStream());
                    source.setPublicId(publicId);
                    source.setSystemId(systemId);
                    logger.debug("Found beans DTD [" + systemId + "] in classpath: " + dtdFile);
                    return source;
                }
                catch (IOException ex) {
                    logger.debug("Could not resolve beans DTD [" + systemId + "]: not found in classpath", ex);
                }

            }
        }

        // Use the default behavior -> download from website or wherever.
        return null;
    }

}
