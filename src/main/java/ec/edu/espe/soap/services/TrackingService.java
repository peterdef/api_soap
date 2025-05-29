package ec.edu.espe.soap.services;

import ec.edu.espe.soap.model.GetTrackingStatusRequest;
import ec.edu.espe.soap.model.GetTrackingStatusResponse;
import ec.edu.espe.soap.model.TrackingError;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService(
        targetNamespace = "http://logistica.com/ws/tracking",
        name = "TrackingService"
)
public interface TrackingService {
    @WebMethod(operationName = "GetTrackingStatus")
    @WebResult(name = "GetTrackingStatusResponse", targetNamespace = "http://logistica.com/ws/tracking")
    GetTrackingStatusResponse getTrackingStatus(
        @WebParam(name = "GetTrackingStatusRequest", targetNamespace = "http://logistica.com/ws/tracking") 
        GetTrackingStatusRequest request
    );
}
