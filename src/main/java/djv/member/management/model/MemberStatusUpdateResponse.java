package djv.member.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberStatusUpdateResponse {
    private Long id;
    private Long accessNumber;
    private int statusCode;
    private String message;
}
