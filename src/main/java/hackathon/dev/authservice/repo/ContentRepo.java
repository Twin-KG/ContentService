package hackathon.dev.authservice.repo;

import hackathon.dev.authservice.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepo extends JpaRepository<Content, Long> {
    @Query("SELECT c FROM Content c " +
            "LEFT JOIN AccessType a ON a.id = :accessTypeId " +
            "LEFT JOIN Tier t ON t.id = :tierId " +
            "WHERE c.accessType.rank > a.rank OR c.tier.rank > t.rank")
    List<Content> findContentByAccessTypeOrTierId(Long accessTypeId, Long tierId);

    @Query("SELECT c FROM Content c WHERE c.professionalId = :professionId")
    List<Content> getAllByProfessionalId(Long professionId);

}
