package djv.member.management.controller;

import djv.member.management.model.MemberEnrollmentRequest;
import djv.member.management.model.MemberEnrollmentResponse;
import djv.member.management.model.MemberProfileResponse;
import djv.member.management.service.MemberProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

@RestController
@RequestMapping("/v1/member-management/")
public class MemberProfileController {

    private final MemberProfileService memberProfileService;

    public MemberProfileController(MemberProfileService memberProfileService) {
        this.memberProfileService = memberProfileService;
    }

    @Operation(
            summary = "Enroll a new user")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Member enrolled successfully"),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Member is already enrolled"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid member enrollment data"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "System error, please contact an admin"
                    ),
                    @ApiResponse(
                            responseCode = "503",
                            description = "Service unavailable"
                    )
            })
    @PostMapping("member-enrollment")
    public ResponseEntity<MemberEnrollmentResponse> enrollMember(@Valid @RequestBody MemberEnrollmentRequest memberEnrollmentRequest) {
        MemberEnrollmentResponse resp = memberProfileService.enrollMember(memberEnrollmentRequest);
        return new ResponseEntity<>(resp, HttpStatus.valueOf(resp.responseCode));
    }

    @GetMapping("member-enrollment")
    public ResponseEntity<MemberProfileResponse> getMember(@RequestParam @NotBlank Long id) {
        MemberProfileResponse resp = memberProfileService.retrieveMember(id);
        return new ResponseEntity<>(resp, HttpStatus.valueOf(resp.getResponseCode()));
    }


}
