package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.ProfessionalView;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import br.com.listtta.backend.model.enums.UserGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProfessionalViewRepository extends JpaRepository<ProfessionalView, UUID> {

    @Query("SELECT u FROM ProfessionalView u WHERE " +
            "(:userGender is null OR u.userGender = :userGender) " +
            "AND (:city is null OR u.city = :city) " +
            "AND (:state is null OR u.state = :state)" +
            "AND(:type is null OR u.type = :type)")
    List<ProfessionalView> findAllByQueryParameters(
            @Param("userGender") UserGender userGender,
            @Param("city") String city,
            @Param("state") String state,
            @Param("type")ProfessionalsType type
    );

}
