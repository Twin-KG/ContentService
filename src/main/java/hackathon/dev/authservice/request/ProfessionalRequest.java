package hackathon.dev.authservice.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfessionalRequest {
    private List<ProfessionalFilter> professionals;
    private Long user_id;
}
