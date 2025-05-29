package ec.edu.espe.soap.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tracking_events")
@XmlRootElement(name = "TrackingEvent", namespace = "http://logistica.com/ws/tracking")
@XmlType(propOrder = {"date", "description", "location"})
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    /**
     * Fecha y hora del evento
     */
    @XmlElement(required = true, namespace = "http://logistica.com/ws/tracking")
    @XmlSchemaType(name = "dateTime")
    private LocalDateTime date;

    /**
     * Descripción textual del evento
     */
    @XmlElement(required = true, namespace = "http://logistica.com/ws/tracking")
    private String description;

    /**
     * Ubicación donde ocurrió el evento
     */
    @XmlElement(required = true, namespace = "http://logistica.com/ws/tracking")
    private String location;

    /**
     * Relación inversa con Paquete
     */
    @ManyToOne
    @JoinColumn(name = "paquete_id")
    @XmlTransient
    private Package packageRef;
}