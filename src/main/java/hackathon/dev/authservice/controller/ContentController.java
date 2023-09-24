package hackathon.dev.authservice.controller;

import hackathon.dev.authservice.constant.FileServerConstant;
import hackathon.dev.authservice.domain.ZResponse;
import hackathon.dev.authservice.dto.ContentRequestDto;
import hackathon.dev.authservice.model.Content;
import hackathon.dev.authservice.request.ProfessionalRequest;
import hackathon.dev.authservice.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contents")
@AllArgsConstructor
@CrossOrigin
public class ContentController {

    private final ContentService contentService;
    private final RestTemplate restTemplate;

    @PostMapping("/filter")
    public List<Content> getContentsByProfessionId(@RequestBody ProfessionalRequest request){
        return contentService.filterContentsByRequest(request);
    }

    @PostMapping
    public ResponseEntity<ZResponse<Content>> addNewContent(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("audio") MultipartFile audio,
            @RequestParam("video") MultipartFile video,
            @RequestParam("image") MultipartFile image,
            @RequestParam("link") String link,
            @RequestParam("embeddedUrl") String embeddedUrl,
            @RequestParam("status") String status,
            @RequestParam("accessTypeId") Long accessTypeId,
            @RequestParam("tierId") Long tierId,
            @RequestParam("professionalId") Long professionalId) throws IOException {

        ContentRequestDto dto = ContentRequestDto
                .builder()
                .title(title).content(content).audio(audio).video(video).image(image)
                .link(link).embedUrl(embeddedUrl).status(status)
                .tierId(tierId).accessTypeId(accessTypeId).professionalId(professionalId)
                .build();

        Content result = contentService.saveContent(dto);
        return new ResponseEntity(
                ZResponse.<Content>builder()
                        .data(result).message("Successfully saved content").code(HttpStatus.CREATED.value()).success(true).build(),
                        HttpStatus.CREATED);
    }

    @GetMapping
    public Page<Content> getContentsByAccessTypeAndTierId(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "accessTypeId") Long accessTypeId,
            @RequestParam(value = "tierId", required = false) Long tierId){
        return contentService.getAllContentsByAccessTypeAndTierId(page, size, accessTypeId, tierId);
    }

    @GetMapping("/search")
    public Page<Content> getContentsByProfessionId(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("professionId") Long professionId){
        return contentService.getAllContentsByProfessionId(page, size, professionId);
    }

}
