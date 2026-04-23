package djv.member.management.service;

import djv.member.management.entity.MemberProfileEntity;
import djv.member.management.exception.MemberAlreadyExistsException;
import djv.member.management.model.ErrorResponse;
import djv.member.management.model.MemberEnrollmentRequest;
import djv.member.management.model.MemberEnrollmentResponse;
import djv.member.management.model.MemberProfileResponse;
import djv.member.management.repo.MemberProfileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
public class MemberProfileService {

    private final MemberProfileRepository memberProfileRepository;

    public MemberEnrollmentResponse enrollMember(MemberEnrollmentRequest memberEnrollmentRequest) {

        MemberProfileEntity existingMemberEntity = memberProfileRepository.findByEmail(memberEnrollmentRequest.getEmail());

        if (existingMemberEntity != null) {
            throw new MemberAlreadyExistsException("Member already exists");
        }

        MemberProfileEntity createdMemberEntity = memberProfileRepository.save(createEntity(memberEnrollmentRequest));

        return createEnrollmentResponse(createdMemberEntity);
    }

    public MemberProfileResponse retrieveMember(Long id) {
        MemberProfileEntity member = memberProfileRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Member not found by id {}", id);
                    return new RuntimeException("Member not found with id: " + id);
                });

        return createProfileResponse(member);
    }

    private MemberEnrollmentResponse createEnrollmentResponse(MemberProfileEntity member) {
        return MemberEnrollmentResponse.builder()
                .id(member.getId())
                .accessNumber(member.getAccessNumber())
                .status(member.getStatus())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .dateOfBirth(member.getDateOfBirth())
                .email(member.getEmail())
                .responseCode(201)
                .message("Member created")
                .build();
    }

    private MemberProfileResponse createProfileResponse(MemberProfileEntity member) {
        return MemberProfileResponse.builder()
                .id(member.getId())
                .accessNumber(member.getAccessNumber())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .email(member.getEmail())
                .createdAt(member.getCreatedDate())
                .updatedAt(member.getUpdatedDate())
                .dateOfBirth(member.getDateOfBirth())
                .status(member.getStatus())
                .message("Member found")
                .responseCode(200)
                .build();
    }

//    private ErrorResponse createErrorResponse(int statusCode, String message) {
//
//    }

    private MemberProfileEntity createEntity(MemberEnrollmentRequest memberEnrollmentRequest) {
        return MemberProfileEntity.builder()
                .email(memberEnrollmentRequest.getEmail())
                .firstName(memberEnrollmentRequest.getFirstName())
                .lastName(memberEnrollmentRequest.getLastName())
                .phoneNumber(memberEnrollmentRequest.getPhoneNumber())
                .dateOfBirth(memberEnrollmentRequest.getDateOfBirth())
                .status("ACTIVE")
                .build();
    }

}
