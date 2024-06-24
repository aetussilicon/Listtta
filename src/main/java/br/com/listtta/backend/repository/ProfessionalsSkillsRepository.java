package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.Professionals.ProfessionalsSkills;
import br.com.listtta.backend.model.entities.filters.Filters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface ProfessionalsSkillsRepository extends JpaRepository<ProfessionalsSkills, UUID>{
    @Query("SELECT s.skillId FROM ProfessionalsSkills s WHERE s.puid = :puid")
    Set<Long> findSkillsByPuid(@Param("puid") String puid);

    @Modifying
    @Query("DELETE FROM ProfessionalsSkills s WHERE s.puid = :puid AND s.filterId = :filterId")
    void deleteByPuidAndFilterId(@Param("puid") String puid, @Param("filterId") Long filterId);
}
