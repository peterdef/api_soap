package ec.edu.espe.soap.repository;

import ec.edu.espe.soap.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PackageRepository extends JpaRepository<Package, Long> {
    @Query("SELECT p FROM Package p LEFT JOIN FETCH p.history WHERE p.trackingNumber = :trackingNumber")
    Optional<Package> findByTrackingNumberWithHistory(@Param("trackingNumber") String trackingNumber);
}