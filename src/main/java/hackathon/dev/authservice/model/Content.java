package hackathon.dev.authservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String audio;
    private String video;
    private String image;
    private String link;
    private String embedUrl;
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accessType_id", referencedColumnName = "id")
    private AccessType accessType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tier_id")
    private Tier tier;

    private Long professionalId;
}
