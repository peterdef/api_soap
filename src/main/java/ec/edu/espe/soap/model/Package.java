package ec.edu.espe.soap.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "paquetes")
@XmlRootElement(name = "Package", namespace = "http://logistica.com/ws/tracking")
@XmlType(propOrder = {"trackingNumber", "senderName", "receiverName", "origin", "destination", 
                     "weight", "dimensions", "status", "currentLocation", "estimatedDeliveryDate"})
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    @XmlElement(required = true)
    @Column(name = "tracking_number", unique = true, nullable = false)
    private String trackingNumber;

    @XmlElement(required = true)
    private String senderName;

    @XmlElement(required = true)
    private String receiverName;

    @XmlElement(required = true)
    private String origin;

    @XmlElement(required = true)
    private String destination;

    @XmlElement(required = true)
    private Double weight;

    @XmlElement(required = true)
    private String dimensions;

    @XmlElement(required = true)
    private String status;

    @XmlElement(required = true)
    private String currentLocation;

    @XmlElement(required = true)
    private String estimatedDeliveryDate;

    /**
     * Historial de eventos del paquete
     */
    @OneToMany(mappedBy = "packageRef", cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlTransient
    private List<TrackingEvent> history;

}


