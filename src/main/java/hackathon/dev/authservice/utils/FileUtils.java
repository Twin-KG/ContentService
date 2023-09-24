package hackathon.dev.authservice.utils;

import hackathon.dev.authservice.constant.FileServerConstant;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUtils {

    private static RestTemplate restTemplate = new RestTemplate();

    public static String save(MultipartFile file){
        if(file != null && !file.isEmpty()){
            Resource resource;
            try{

                resource = new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                };


                // Create a MultiValueMap to hold the multipart request
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                body.add("file", resource);

                // Define request headers
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);

                // Create the request entity with the multipart data and headers
                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

                // Send the POST request with the multipart file using RestTemplate
                ResponseEntity<String> responseEntity = restTemplate.postForEntity(FileServerConstant.HOST + "/storage/upload", requestEntity, String.class);


                return responseEntity.getBody();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return null;
    }
}
