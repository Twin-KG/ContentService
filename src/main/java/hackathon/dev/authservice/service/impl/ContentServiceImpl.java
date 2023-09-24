package hackathon.dev.authservice.service.impl;

import hackathon.dev.authservice.dto.ContentRequestDto;
import hackathon.dev.authservice.model.AccessType;
import hackathon.dev.authservice.model.Content;
import hackathon.dev.authservice.model.Tier;
import hackathon.dev.authservice.repo.AccessTypeRepo;
import hackathon.dev.authservice.repo.ContentRepo;
import hackathon.dev.authservice.repo.TierRepo;
import hackathon.dev.authservice.request.ProfessionalFilter;
import hackathon.dev.authservice.request.ProfessionalRequest;
import hackathon.dev.authservice.service.ContentService;
import hackathon.dev.authservice.utils.FileUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepo contentRepo;
    private final AccessTypeRepo accessTypeRepo;
    private final TierRepo tierRepo;

    @Override
    public List<Content> getAllContentWithPaging() {
        return contentRepo.findAll();
    }

    @Override
    public List<Content> getAllContentsByAccessTypeAndTierId(Long accessTypeId, Long tierId) {
        return contentRepo.findContentByAccessTypeOrTierId(accessTypeId, tierId);
    }

    @Override
    public List<Content> getAllContentsByProfessionId(Long professionId) {
        return contentRepo.getAllByProfessionalId(professionId);
    }

    @Override
    public Content saveContent(ContentRequestDto dto) {

        Long currentTime = System.currentTimeMillis();

        String audioPath = null;
        String imagePath = null;
        String videoPath = null;

        audioPath = FileUtils.save(dto.getAudio());
        imagePath = FileUtils.save(dto.getImage());
        videoPath = FileUtils.save(dto.getVideo());

        Optional<AccessType> accessType = accessTypeRepo.findById(dto.getAccessTypeId());

        Content content = Content.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .embedUrl(dto.getEmbedUrl())
                .link(dto.getLink())
                .professionalId(dto.getProfessionalId())
                .audio(audioPath)
                .image(imagePath)
                .video(videoPath)
                .accessType(accessType.isPresent() ? accessType.get(): null)
                .professionalId(dto.getProfessionalId())
                .build();

        if(dto.getTierId() != null){
            Optional<Tier> tier = tierRepo.findById(dto.getTierId());
            content.setTier(tier.get());
        }


        return contentRepo.save(content);
    }

    @Override
    public Content updateContentById(Long id, ContentRequestDto contentRequestDto) {
        return null;
    }

    @Override
    public Content deleteContentById(Long id) {
        return null;
    }

    @Override
    public List<Content> filterContentsByRequest(ProfessionalRequest request) {
        List<Content> result = new ArrayList<>();
        List<Content> contentList = contentRepo.findAll();
        List<ProfessionalFilter> professionals = request.getProfessionals();

        for (ProfessionalFilter f : professionals) {
            for (Content c :contentList) {
                if(c.getTier().getId() == null){
                    result.add(c);
                }else if(f.getProfessional_id().equals(c.getProfessionalId()) && f.getTier_id().equals(c.getTier().getId())){
                    result.add(c);
                }
            }
        }

        return result;
    }
}
