package com.microsoft.windowsazure.services.media.implementation.content;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StreamingEndpointAccessControlType {
    
    @XmlElement(name = "Akamai", namespace = Constants.ODATA_DATA_NS)
    private AkamaiAccessControlType akamai;
    
    @XmlElement(name = "IP", namespace = Constants.ODATA_DATA_NS)
    private IPAccessControlType iP;
}
