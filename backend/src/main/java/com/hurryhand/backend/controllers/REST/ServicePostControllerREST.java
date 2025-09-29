package com.hurryhand.backend.controllers.REST;

import com.hurryhand.backend.dto.ApiResponse;
import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.dto.servicepost.ServicePostForVisualDTO;
import com.hurryhand.backend.enums.SortingDirection;
import com.hurryhand.backend.enums.SortingServicePosts;
import com.hurryhand.backend.mappers.ApiResponseMapper;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.services.ProviderService;
import com.hurryhand.backend.services.ServicePostPhotoStorageService;
import com.hurryhand.backend.services.ServicePostService;
import com.hurryhand.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/servicepost")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ServicePostControllerREST {

    private final ServicePostService servicePostService;
    private final UserService userService;
    private final ProviderService providerService;
    private final ApiResponseMapper apiResponseMapper;
    private final ServicePostPhotoStorageService servicePostPhotoStorageService;

    @PostMapping
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse> createServicePost(@Valid @RequestBody CreateServicePostDTO createServicePostDTO,
                                                         @AuthenticationPrincipal CustomUserDetails user) {

        Provider provider = providerService.getProviderByUser(userService.getUserById(user.getId()));

        servicePostService.createServicePost(createServicePostDTO, provider);

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK, "Service Post creado exitosamente");
    }

    @PostMapping(value = "/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<Map<String, String>> uploadServicePostPhoto(@RequestParam("file") MultipartFile file) {
        String photoUrl = servicePostPhotoStorageService.uploadPhoto(file);
        return ResponseEntity.ok(Map.of("url", photoUrl));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ServicePostForVisualDTO>> getAllServicePostsForVisual(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "CREATED_AT") SortingServicePosts sortBy,
            @RequestParam(defaultValue = "DESC") SortingDirection direction,
            @RequestParam(required = false) String query) {

        Page<ServicePostForVisualDTO> pageOfServicePosts = servicePostService.getPostsForVisual(
                page,
                size,
                sortBy,
                direction,
                query
        );

        return ResponseEntity.ok(pageOfServicePosts);
    }
}
