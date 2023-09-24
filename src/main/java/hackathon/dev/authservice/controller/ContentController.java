package hackathon.dev.authservice.controller;

import hackathon.dev.authservice.domain.ZResponse;
import hackathon.dev.authservice.dto.ContentRequestDto;
import hackathon.dev.authservice.model.Content;
import hackathon.dev.authservice.request.ProfessionalRequest;
import hackathon.dev.authservice.service.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "audio", required = false) MultipartFile audio,
            @RequestParam(value = "video", required = false) MultipartFile video,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "embeddedUrl", required = false) String embeddedUrl,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "accessTypeId", required = false) Long accessTypeId,
            @RequestParam(value = "tierId", required = false) Long tierId,
            @RequestParam(value = "professionalId", required = false) Long professionalId) throws IOException {

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
    public List<Content> getContentsByAccessTypeAndTierId(
            @RequestParam(value = "accessTypeId") Long accessTypeId,
            @RequestParam(value = "tierId", required = false) Long tierId){
        return contentService.getAllContentsByAccessTypeAndTierId(accessTypeId, tierId);
    }

    @GetMapping("/search")
    public List<Content> getContentsByProfessionId(
            @RequestParam("professionId") Long professionId){
        return contentService.getAllContentsByProfessionId(professionId);
    }

}
