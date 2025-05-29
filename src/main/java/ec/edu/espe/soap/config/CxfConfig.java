package ec.edu.espe.soap.config;

import ec.edu.espe.soap.services.impl.TrackingServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.xml.ws.Endpoint;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CxfConfig {

    @Bean
    public Endpoint trackingEndpoint(Bus bus, TrackingServiceImpl impl) {
        EndpointImpl endpoint = new EndpointImpl(bus, impl);
        endpoint.publish("/TrackingService");
        return endpoint;
    }
}