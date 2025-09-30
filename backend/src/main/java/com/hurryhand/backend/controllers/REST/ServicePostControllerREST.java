package com.hurryhand.backend.controllers.REST;


import com.hurryhand.backend.dto.ApiResponse;
import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.dto.servicepost.GetServicePostParamsDTO;
import com.hurryhand.backend.dto.servicepost.ServicePostForVisualDTO;
import com.hurryhand.backend.mappers.ApiResponseMapper;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.services.ProviderService;
import com.hurryhand.backend.services.ServicePostService;
import com.hurryhand.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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


    @PostMapping()
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ApiResponse> createServicePost(@Valid @RequestBody CreateServicePostDTO createServicePostDTO,
                                                         @AuthenticationPrincipal CustomUserDetails user) {

        Provider provider = providerService.getProviderByUser(userService.getUserById(user.getId()));

        servicePostService.createServicePost(createServicePostDTO, provider);

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK,"Service Post creado exitosamente");
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ServicePostForVisualDTO>>  getAllServicePostsForVisual(@Valid @RequestBody GetServicePostParamsDTO  getServicePostParamsDTO) {

        Page<ServicePostForVisualDTO> pageOfServicePosts = servicePostService.getPostsForVisual(getServicePostParamsDTO.getPage(),
                getServicePostParamsDTO.getSize(),
                getServicePostParamsDTO.getSortBy(),
                getServicePostParamsDTO.getDirection(),
                getServicePostParamsDTO.getQuery());

        System.out.println("ffdf");
        return new ResponseEntity<>(pageOfServicePosts, HttpStatus.OK);

    }



}
