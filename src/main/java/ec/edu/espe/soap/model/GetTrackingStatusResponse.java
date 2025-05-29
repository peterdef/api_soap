package ec.edu.espe.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;

@XmlRootElement(name = "GetTrackingStatusResponse", namespace = "http://logistica.com/ws/tracking")
@XmlType(propOrder = {"status", "currentLocation", "estimatedDeliveryDate", "history", "error"})
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class GetTrackingStatusResponse {

    @XmlElement(required = true, namespace = "http://logistica.com/ws/tracking")
    private String status;
    
    @XmlElement(required = true, namespace = "http://logistica.com/ws/tracking")
    private String currentLocation;
    
    @XmlElement(required = true, namespace = "http://logistica.com/ws/tracking")
    private String estimatedDeliveryDate;
    
    @XmlElementWrapper(name = "history", namespace = "http://logistica.com/ws/tracking")
    @XmlElement(name = "event", namespace = "http://logistica.com/ws/tracking")
    private List<TrackingEvent> history;

    @XmlElement(namespace = "http://logistica.com/ws/tracking")
    private TrackingError error;

    // Constructor for successful responses
    public GetTrackingStatusResponse(String status, String currentLocation,
                                   String estimatedDeliveryDate, List<TrackingEvent> history) {
        this.status = status;
        this.currentLocation = currentLocation;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.history = history;
        this.error = null;
    }

    // Constructor for error responses
    public GetTrackingStatusResponse(TrackingError error) {
        this.status = "ERROR";
        this.currentLocation = "N/A";
        this.estimatedDeliveryDate = "N/A";
        this.history = null;
        this.error = error;
    }
}