package ec.edu.espe.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@XmlRootElement(name = "GetTrackingStatusRequest", namespace = "http://logistica.com/ws/tracking")
@XmlType(propOrder = {"trackingNumber"})
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTrackingStatusRequest {

    @XmlElement(required = true)
    private String trackingNumber;
}