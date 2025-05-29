package ec.edu.espe.soap.services.impl;

import ec.edu.espe.soap.model.GetTrackingStatusRequest;
import ec.edu.espe.soap.model.GetTrackingStatusResponse;
import ec.edu.espe.soap.model.TrackingError;
import ec.edu.espe.soap.repository.PackageRepository;
import ec.edu.espe.soap.services.TrackingService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@WebService(
        endpointInterface = "ec.edu.espe.soap.services.TrackingService",
        targetNamespace = "http://logistica.com/ws/tracking",
        serviceName = "TrackingService",
        portName = "TrackingServicePort",
        name = "TrackingService"
)
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    private PackageRepository repo;

    @Override
    @Transactional(readOnly = true)
    @WebMethod(operationName = "GetTrackingStatus")
    @WebResult(name = "GetTrackingStatusResponse", targetNamespace = "http://logistica.com/ws/tracking")
    public GetTrackingStatusResponse getTrackingStatus(
        @WebParam(name = "GetTrackingStatusRequest", targetNamespace = "http://logistica.com/ws/tracking") 
        GetTrackingStatusRequest request
    ) {
        // Validación de entrada
        if (request == null) {
            return createErrorResponse("REQUEST_NULL", "La solicitud no puede ser nula", "request");
        }

        if (request.getTrackingNumber() == null || request.getTrackingNumber().trim().isEmpty()) {
            return createErrorResponse("TRACKING_NUMBER_EMPTY", "El número de tracking es requerido", "trackingNumber");
        }

        String trackingNumber = request.getTrackingNumber().trim();

        // Validación del formato del tracking number (opcional pero recomendado)
        if (!isValidTrackingNumber(trackingNumber)) {
            return createErrorResponse("INVALID_TRACKING_FORMAT", "Formato de número de tracking inválido", "trackingNumber");
        }

        try {
            var result = repo.findByTrackingNumberWithHistory(trackingNumber);

            if (result.isEmpty()) {
                return createErrorResponse("PACKAGE_NOT_FOUND", "Paquete no encontrado con el número de tracking: " + trackingNumber, "trackingNumber");
            }

            ec.edu.espe.soap.model.Package pkg = result.get();

            return new GetTrackingStatusResponse(
                    pkg.getStatus(),
                    pkg.getCurrentLocation(),
                    pkg.getEstimatedDeliveryDate(),
                    pkg.getHistory()
            );

        } catch (Exception e) {
            return createErrorResponse("INTERNAL_ERROR", "Error interno del servidor: " + e.getMessage(), null);
        }
    }

    /**
     * Crea una respuesta de error estandarizada
     */
    private GetTrackingStatusResponse createErrorResponse(String errorCode, String errorMessage, String invalidField) {
        GetTrackingStatusResponse response = new GetTrackingStatusResponse();
        response.setStatus("ERROR");
        response.setCurrentLocation("N/A");
        response.setEstimatedDeliveryDate("N/A");
        response.setHistory(null);

        // Aquí podrías agregar el objeto TrackingError si modificas GetTrackingStatusResponse
        // response.setError(new TrackingError(getErrorCode(errorCode), errorMessage, invalidField));

        return response;
    }

    /**
     * Validación básica del formato del tracking number
     */
    private boolean isValidTrackingNumber(String trackingNumber) {
        // Ejemplo: debe tener al menos 10 caracteres y empezar con letras
        return trackingNumber.length() >= 10 && trackingNumber.matches("^[A-Z]{2}[0-9]+$");
    }

    /**
     * Mapea códigos de error a números
     */
    private int getErrorCode(String errorCode) {
        return switch (errorCode) {
            case "REQUEST_NULL" -> 1001;
            case "TRACKING_NUMBER_EMPTY" -> 1002;
            case "INVALID_TRACKING_FORMAT" -> 1003;
            case "PACKAGE_NOT_FOUND" -> 2001;
            case "INTERNAL_ERROR" -> 5001;
            default -> 9999;
        };
    }
}