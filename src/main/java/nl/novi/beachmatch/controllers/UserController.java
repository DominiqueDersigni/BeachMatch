package nl.novi.beachmatch.controllers;

import jakarta.validation.Valid;
import nl.novi.beachmatch.dtos.user.UserRequestDTO;
import nl.novi.beachmatch.dtos.user.UserResponseDTO;
import nl.novi.beachmatch.helpers.UrlHelper;
import nl.novi.beachmatch.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UrlHelper urlHelper;

    public UserController(UserService userService, UrlHelper urlHelper) {
        this.userService = userService;
        this.urlHelper = urlHelper;
    }

    @GetMapping("/{username}")
    @PreAuthorize("@securityHelper.isOwner(authentication.principal, #username)")
    public ResponseEntity<UserResponseDTO> getUserById (@AuthenticationPrincipal Jwt jwt, @PathVariable String username) {
        String keycloakId = jwt.getClaimAsString("sub");
        UserResponseDTO user = userService.findUserByKeycloakId(keycloakId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/{username}")
    @PreAuthorize("@securityHelper.isOwner(authentication.principal, #username)")
    public ResponseEntity<UserResponseDTO> createUser (@AuthenticationPrincipal Jwt jwt, @PathVariable String username) {
        String keycloakId = jwt.getClaimAsString("sub");
        String email = jwt.getClaimAsString("email");
        String name = jwt.getClaimAsString("name");
        UserResponseDTO userDTO = userService.createUser(keycloakId,name, email);
        return ResponseEntity.created(urlHelper.getCurrentUrl()).body(userDTO);
    }

    @PutMapping("/{username}")
    @PreAuthorize("@securityHelper.isOwner(authentication.principal, #username)")
    public ResponseEntity<UserResponseDTO> updateUser (@AuthenticationPrincipal Jwt jwt, @PathVariable String username, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        String keycloakId = jwt.getClaimAsString("sub");
        UserResponseDTO userDTO = userService.updateUser(keycloakId, userRequestDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("@securityHelper.isOwner(authentication.principal, #username)")
    public ResponseEntity<Void> deleteUser (@AuthenticationPrincipal Jwt jwt, @PathVariable String username) {
        String keycloakId = jwt.getClaimAsString("sub");
        userService.deleteUser(keycloakId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
