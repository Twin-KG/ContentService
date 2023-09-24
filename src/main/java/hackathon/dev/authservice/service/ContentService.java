package hackathon.dev.authservice.service;

import hackathon.dev.authservice.dto.ContentRequestDto;
import hackathon.dev.authservice.model.Content;
import hackathon.dev.authservice.request.ProfessionalRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContentService {
    List<Content> getAllContentWithPaging();
    List<Content> getAllContentsByAccessTypeAndTierId(Long accessType, Long tierId);
    List<Content> getAllContentsByProfessionId(Long professionId);
    Content saveContent(ContentRequestDto contentRequestDto);
    Content updateContentById(Long id, ContentRequestDto contentRequestDto);
    Content deleteContentById(Long id);
    List<Content> filterContentsByRequest(ProfessionalRequest request);
}
