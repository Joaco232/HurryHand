package com.hurryhand.backend.controllers.REST;

import com.hurryhand.backend.dto.ApiResponse;
import com.hurryhand.backend.dto.credential.CreateCredentialDTO;
import com.hurryhand.backend.dto.credential.CredentialResponseDTO;
import com.hurryhand.backend.mappers.ApiResponseMapper;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.services.CredentialService;
import com.hurryhand.backend.services.ProviderService;
import com.hurryhand.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/credential")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class CredentialControllerREST {

    private final CredentialService credentialService;
    private final ProviderService providerService;
    private final UserService userService;
    private final ApiResponseMapper apiResponseMapper;

    @PostMapping()
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse> createCredential(@Valid @RequestBody CreateCredentialDTO createCredentialDTO,
                                                        @AuthenticationPrincipal CustomUserDetails user) {

        Provider provider = providerService.getProviderByUser(userService.getUserById(user.getId()));
        credentialService.addNewCredentailForUser(createCredentialDTO, provider);

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK,"Credential creada exitosamente");

    }

    @GetMapping("/all")
    public ResponseEntity<List<CredentialResponseDTO>> getCredentials(@AuthenticationPrincipal CustomUserDetails userDetails){

        User user = userService.getUserById(userDetails.getId());

        Provider provider = providerService.getProviderByUser(user);

        List<CredentialResponseDTO> credentials = credentialService.getCredentialRelatedToProvider(provider);

        return ResponseEntity.ok(credentials);

    }



}
