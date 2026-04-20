package djv.member.management.controller;

import djv.member.management.model.MemberEnrollmentRequest;
import djv.member.management.model.MemberEnrollmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/member-management/")
public class MemberProfileController {

    @Operation(
            summary = "Enroll a new user")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Member enrolled successfully"),
                    @ApiResponse(
                            responseCode = "504",
                            description = "Member is already enrolled"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request data"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Server Error"
                    ),
                    @ApiResponse(
                            responseCode = "503",
                            description = "Service unavailable"
                    )
            })
    @PostMapping("member-enrollment")
    public ResponseEntity<MemberEnrollmentResponse> enrollMember(@RequestBody MemberEnrollmentRequest memberEnrollmentRequest) {
        MemberEnrollmentResponse resp = new MemberEnrollmentResponse();
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }


}
