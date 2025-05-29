package ec.edu.espe.soap.config;

import ec.edu.espe.soap.model.Package;
import ec.edu.espe.soap.model.TrackingEvent;
import ec.edu.espe.soap.repository.PackageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner cargarDatos(PackageRepository repo) {
        return args -> {
            // Limpiar datos previos
            repo.deleteAll();

            // ===== PAQUETE 1: En tránsito =====
            TrackingEvent event1_1 = new TrackingEvent();
            event1_1.setDate(LocalDateTime.now().minusDays(5));
            event1_1.setDescription("Paquete recibido en bodega central");
            event1_1.setLocation("Lima");

            TrackingEvent event1_2 = new TrackingEvent();
            event1_2.setDate(LocalDateTime.now().minusDays(3));
            event1_2.setDescription("Salida hacia destino");
            event1_2.setLocation("Arequipa");

            TrackingEvent event1_3 = new TrackingEvent();
            event1_3.setDate(LocalDateTime.now().minusDays(1));
            event1_3.setDescription("En tránsito hacia ciudad de destino");
            event1_3.setLocation("Cuenca");

            Package p1 = new Package();
            p1.setTrackingNumber("PE1234567890");
            p1.setSenderName("Empresa A");
            p1.setReceiverName("Cliente B");
            p1.setOrigin("Quito");
            p1.setDestination("Guayaquil");
            p1.setWeight(2.5);
            p1.setDimensions("20x15x10");
            p1.setStatus("En tránsito");
            p1.setCurrentLocation("Cuenca");
            p1.setEstimatedDeliveryDate("2025-06-01");
            p1.setHistory(Arrays.asList(event1_1, event1_2, event1_3));

            // ===== PAQUETE 2: Entregado =====
            TrackingEvent event2_1 = new TrackingEvent();
            event2_1.setDate(LocalDateTime.now().minusDays(10));
            event2_1.setDescription("Paquete recibido en oficina de origen");
            event2_1.setLocation("Quito");

            TrackingEvent event2_2 = new TrackingEvent();
            event2_2.setDate(LocalDateTime.now().minusDays(8));
            event2_2.setDescription("En ruta hacia ciudad de destino");
            event2_2.setLocation("Quito");

            TrackingEvent event2_3 = new TrackingEvent();
            event2_3.setDate(LocalDateTime.now().minusDays(6));
            event2_3.setDescription("Llegada a ciudad de destino");
            event2_3.setLocation("Guayaquil");

            TrackingEvent event2_4 = new TrackingEvent();
            event2_4.setDate(LocalDateTime.now().minusDays(5));
            event2_4.setDescription("Entregado exitosamente");
            event2_4.setLocation("Guayaquil");

            Package p2 = new Package();
            p2.setTrackingNumber("EC9876543210");
            p2.setSenderName("Tienda XYZ");
            p2.setReceiverName("María González");
            p2.setOrigin("Quito");
            p2.setDestination("Guayaquil");
            p2.setWeight(1.2);
            p2.setDimensions("30x20x5");
            p2.setStatus("Entregado");
            p2.setCurrentLocation("Guayaquil");
            p2.setEstimatedDeliveryDate("2025-05-20");
            p2.setHistory(Arrays.asList(event2_1, event2_2, event2_3, event2_4));

            // ===== PAQUETE 3: Pendiente de recolección =====
            TrackingEvent event3_1 = new TrackingEvent();
            event3_1.setDate(LocalDateTime.now().minusDays(2));
            event3_1.setDescription("Paquete preparado para envío");
            event3_1.setLocation("Cuenca");

            Package p3 = new Package();
            p3.setTrackingNumber("CO5555666677");
            p3.setSenderName("Almacén Central");
            p3.setReceiverName("Pedro Ramírez");
            p3.setOrigin("Cuenca");
            p3.setDestination("Loja");
            p3.setWeight(0.8);
            p3.setDimensions("15x10x8");
            p3.setStatus("Pendiente de recolección");
            p3.setCurrentLocation("Cuenca");
            p3.setEstimatedDeliveryDate("2025-06-05");
            p3.setHistory(Arrays.asList(event3_1));

            // Guardar todos los paquetes
            repo.saveAll(Arrays.asList(p1, p2, p3));

            System.out.println("=== Datos de prueba cargados para seguimiento ===");
            System.out.println("Tracking Numbers disponibles:");
            System.out.println("- PE1234567890 (En tránsito)");
            System.out.println("- EC9876543210 (Entregado)");
            System.out.println("- CO5555666677 (Pendiente de recolección)");
            System.out.println("================================================");
        };
    }
}