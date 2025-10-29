package com.hurryhand.backend.controllers.REST;


import com.hurryhand.backend.dto.ApiResponse;
import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.dto.servicepost.GetServicePostParamsDTO;
import com.hurryhand.backend.dto.servicepost.ServicePostDTO;
import com.hurryhand.backend.dto.servicepost.ServicePostForVisualDTO;
import com.hurryhand.backend.enums.SortingDirection;
import com.hurryhand.backend.enums.SortingServicePosts;
import com.hurryhand.backend.exceptions.servicepost.ServicePostNotFoundException;
import com.hurryhand.backend.mappers.ApiResponseMapper;
import com.hurryhand.backend.models.BaseUser;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.services.MinioService;
import com.hurryhand.backend.services.ProviderService;
import com.hurryhand.backend.services.ServicePostService;
import com.hurryhand.backend.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/service-post")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ServicePostControllerREST {

    private final ServicePostService servicePostService;
    private final UserService userService;
    private final ProviderService providerService;
    private final ApiResponseMapper apiResponseMapper;
    private final MinioService minioService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<Map<String, Object>> createServicePostWithPhotos(
            @RequestPart("servicePost") @Valid CreateServicePostDTO createServicePostDTO,
            @RequestPart(value = "photos", required = false) List<MultipartFile> photos,
            @AuthenticationPrincipal CustomUserDetails user) {

        Provider provider = providerService.getProviderByUser(userService.getUserById(user.getId()));

        // Crear el service post
        ServicePost createdServicePost = servicePostService.createServicePost(createServicePostDTO, provider);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Service Post creado exitosamente");
        response.put("servicePostId", createdServicePost.getId());

        // Si se enviaron fotos, subirlas y asociarlas al service post
        if (photos != null && !photos.isEmpty()) {
            List<String> photoUrls = minioService.uploadServicePostPhotos(user.getEmail(), createdServicePost, photos);
            response.put("photoUrls", photoUrls);
            response.put("photosUploaded", photoUrls.size());
        } else {
            response.put("photoUrls", List.of());
            response.put("photosUploaded", 0);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Mantener endpoint original para compatibilidad (solo JSON)
    @PostMapping("/json-only")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse> createServicePostJsonOnly(@Valid @RequestBody CreateServicePostDTO createServicePostDTO,
                                                         @AuthenticationPrincipal CustomUserDetails user) {

        Provider provider = providerService.getProviderByUser(userService.getUserById(user.getId()));

        servicePostService.createServicePost(createServicePostDTO, provider);

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK,"Service Post creado exitosamente");
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ServicePostForVisualDTO>> getAllServicePostsForVisual(
            @RequestParam(name = "page", defaultValue = "1") @Positive(message = "La página debe ser positiva.") int page,
            @RequestParam(name = "size", defaultValue = "10") @Positive(message = "El tamaño de página debe ser positivo.") int size,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") @NotNull(message = "Se debe indicar el atributo por el cual se ordena.") SortingServicePosts sortBy,
            @RequestParam(name = "direction") @NotNull(message = "Se debe indicar la dirección en la que se ordena.") SortingDirection direction,
            @RequestParam(name = "query", required = false) String query) {

        // Convertir de página base-1 (frontend) a base-0 (Spring Data)
        Page<ServicePostForVisualDTO> pageOfServicePosts = servicePostService.getPostsForVisual(
                page - 1,
                size,
                sortBy,
                direction,
                query
        );

        return new ResponseEntity<>(pageOfServicePosts, HttpStatus.OK);
    }

    @PostMapping("/servicepost-photos")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<List<String>> uploadPhotosForServicePost(@RequestParam("files") List<MultipartFile> files,
                                                         @RequestParam("servicePostId") Long servicePostId,
                                                         @AuthenticationPrincipal CustomUserDetails userDetails) {

        ServicePost servicePost = servicePostService.getServicePostById(servicePostId);

        return new ResponseEntity<>(minioService.uploadServicePostPhotos(userDetails.getEmail(), servicePost, files), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ServicePostDTO> getServicePostDTOById(@PathVariable Long id) throws ServicePostNotFoundException {

        return new ResponseEntity<>(servicePostService.getServicePostDTOById(id), HttpStatus.OK);
    }

    @GetMapping("/provider")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<List<ServicePostDTO>> getServicePostsByProviderId(@AuthenticationPrincipal CustomUserDetails user) {

        return new ResponseEntity<>(servicePostService.getAllServicePostsByProviderId(user.getId()), HttpStatus.OK);


    }

    @DeleteMapping("/{servicePostId}")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse> deleteServicePost(@PathVariable Long servicePostId, @AuthenticationPrincipal CustomUserDetails user) {

        ServicePost servicePost = servicePostService.getServicePostById(servicePostId);
        servicePostService.deleteServicePost(servicePost, user.getId());

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK, "Service Post eliminado.");
    }

    @DeleteMapping("/{servicePostId}/available-dates")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse> deleteAvailableDate(
            @PathVariable Long servicePostId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateToDelete,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        ServicePost servicePost = servicePostService.getServicePostById(servicePostId);

        servicePostService.deleteAvailableDateFromServicePost(servicePost, dateToDelete, userDetails.getId());

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK, "Horario eliminado.");
    }

    @PatchMapping("/{servicePostId}/available-dates")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse> addAvailableDate(
            @PathVariable Long servicePostId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateToAdd,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        ServicePost servicePost = servicePostService.getServicePostById(servicePostId);

        servicePostService.addAvailableDate(servicePost, dateToAdd, userDetails.getId());

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK, "Horario agregado.");
    }




}
