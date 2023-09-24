package hackathon.dev.authservice.service.impl;

import hackathon.dev.authservice.model.Tier;
import hackathon.dev.authservice.repo.TierRepo;
import hackathon.dev.authservice.service.TierService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TierServiceImpl implements TierService {

    private final TierRepo tierRepo;

    @Override
    public Tier save(Tier tier) {
        return tierRepo.save(tier);
    }

    @Override
    public List<Tier> saveDefaultTiersByProfessionId(Long professionId) {

        Tier tier1 = Tier.builder()
                .imgUrl("https://i.ibb.co/ZXjmWP0/1.png").name("Basic").price(2).rank(1).professionId(professionId).build();

        Tier tier2 = Tier.builder()
                .imgUrl("https://i.ibb.co/Y0jW0Dc/2.png").name("Classic").price(5).rank(2).professionId(professionId).build();

        Tier tier3 = Tier.builder()
                .imgUrl("https://i.ibb.co/pjF557k/3.png").name("Premium").price(7).rank(3).professionId(professionId).build();

        return tierRepo.saveAll(List.of(tier1, tier2, tier3));
    }
}
