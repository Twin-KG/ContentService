package hackathon.dev.authservice.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZResponse<T> {
    private boolean success;
    private int code;
    private T data;
    private String message;
}
