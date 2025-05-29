# EnvíosExpress SOAP Tracking Service

Este proyecto implementa un servicio SOAP para el seguimiento de paquetes de EnvíosExpress S.A.C.

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.x
- Apache CXF para servicios SOAP
- JPA/Hibernate para persistencia
- H2 Database (en memoria)

## Requisitos

- JDK 17 o superior
- Maven 3.x

## Ejecución del Proyecto

1. Clonar el repositorio
2. Ejecutar `mvn spring-boot:run`
3. El servicio estará disponible en: `http://localhost:8080/services/TrackingService`

## WSDL

El WSDL del servicio está disponible en:
`http://localhost:8080/services/TrackingService?wsdl`

## Invocación del Servicio

### Usando SoapUI

1. Abrir SoapUI
2. Crear nuevo proyecto SOAP
3. Importar WSDL desde: `http://localhost:8080/services/TrackingService?wsdl`
4. Ejemplo de request:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:log="http://logistica.com/ws/tracking">
   <soapenv:Header/>
   <soapenv:Body>
      <log:GetTrackingStatus>
         <log:trackingNumber>PE1234567890</log:trackingNumber>
      </log:GetTrackingStatus>
   </soapenv:Body>
</soapenv:Envelope>
```

### Usando Postman

1. Crear nueva petición POST
2. URL: `http://localhost:8080/services/TrackingService`
3. Headers:
   - Content-Type: text/xml
   - SOAPAction: ""
4. Body (raw XML):
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:log="http://logistica.com/ws/tracking">
   <soapenv:Header/>
   <soapenv:Body>
      <log:GetTrackingStatus>
         <log:trackingNumber>PE1234567890</log:trackingNumber>
      </log:GetTrackingStatus>
   </soapenv:Body>
</soapenv:Envelope>
```

## Respuestas

### Respuesta Exitosa

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:log="http://logistica.com/ws/tracking">
   <soapenv:Header/>
   <soapenv:Body>
      <log:GetTrackingStatusResponse>
         <log:status>En tránsito</log:status>
         <log:currentLocation>Lima - Perú</log:currentLocation>
         <log:estimatedDeliveryDate>2025-04-15</log:estimatedDeliveryDate>
         <log:history>
            <log:event>
               <log:date>2025-04-05</log:date>
               <log:description>Paquete recibido en bodega central</log:description>
               <log:location>Lima</log:location>
            </log:event>
            <log:event>
               <log:date>2025-04-07</log:date>
               <log:description>Salida hacia Lima</log:description>
               <log:location>Arequipa</log:location>
            </log:event>
         </log:history>
      </log:GetTrackingStatusResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

### Respuesta de Error

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:log="http://logistica.com/ws/tracking">
   <soapenv:Header/>
   <soapenv:Body>
      <log:TrackingError>
         <log:errorCode>404</log:errorCode>
         <log:errorMessage>Paquete no encontrado</log:errorMessage>
         <log:invalidField>trackingNumber</log:invalidField>
      </log:TrackingError>
   </soapenv:Body>
</soapenv:Envelope>
```

## Códigos de Error

- 400: Número de tracking inválido
- 404: Paquete no encontrado

## Datos de Prueba

El sistema incluye datos de prueba que se cargan automáticamente al iniciar:

- Tracking Number: PE1234567890
- Estado: En tránsito
- Ubicación actual: Cuenca
- Fecha estimada de entrega: 2025-05-01
