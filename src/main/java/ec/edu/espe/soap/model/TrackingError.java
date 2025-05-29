package ec.edu.espe.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@XmlRootElement(name = "TrackingError", namespace = "http://logistica.com/ws/tracking")
@XmlType(propOrder = {"errorCode", "errorMessage", "invalidField"})
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingError {

    @XmlElement(required = true)
    private int errorCode;

    @XmlElement(required = true)
    private String errorMessage;

    @XmlElement
    private String invalidField;
}