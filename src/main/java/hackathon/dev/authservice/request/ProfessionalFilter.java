package hackathon.dev.authservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalFilter {
    private Long professional_id;
    private Long tier_id;
}
