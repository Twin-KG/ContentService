package hackathon.dev.authservice.service;

import hackathon.dev.authservice.dto.ContentRequestDto;
import hackathon.dev.authservice.model.Content;
import hackathon.dev.authservice.request.ProfessionalRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContentService {
    Page<Content> getAllContentWithPaging(int page, int size);
    Page<Content> getAllContentsByAccessTypeAndTierId(int page, int size, Long accessType, Long tierId);
    Page<Content> getAllContentsByProfessionId(int page, int size, Long professionId);
    Content saveContent(ContentRequestDto contentRequestDto);
    Content updateContentById(Long id, ContentRequestDto contentRequestDto);
    Content deleteContentById(Long id);
    List<Content> filterContentsByRequest(ProfessionalRequest request);
}
