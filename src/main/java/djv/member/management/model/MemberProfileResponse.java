package djv.member.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberProfileResponse {
    private int id;
    private Long accessNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String status;
    private int responseCode;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
