package org.gluu.oxtrust.api.server.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.gluu.model.GluuAttribute;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GluuAttributeApi extends GluuAttribute{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8161676720448531706L;

}