package com.sts.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sts.dto.authentication.request.AuthenticationRequest;
import com.sts.dto.register.request.SignupRequest;
import com.sts.mapper.UserResourceMapper;
import com.sts.model.ResponseData;
import com.sts.service.user.UserDetailsImpl;
import com.sts.service.user.UserService;
import com.sts.util.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/no-auth")
@RequiredArgsConstructor
public class AuthResource {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtils;
    private final UserResourceMapper userRequestMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<Long>> register(@Valid @RequestBody SignupRequest signUpRequest) {
        log.info("User registration: {}", signUpRequest);
        var responseData = new ResponseData<Long>();

        var user = userRequestMapper.toUser(signUpRequest);
        var userId = userService.register(user);

        responseData.setData(userId);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<ResponseData<Boolean>> verifyEmail(@RequestParam("token") String token) {
        var responseData = new ResponseData<Boolean>();
        boolean isVerified = userService.verifyEmail(token);

        responseData.setData(isVerified);

        if (isVerified) {
            return ResponseEntity.ok(responseData);
        } else {
            return ResponseEntity.badRequest().body(responseData);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseData<String>> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        var responseData = new ResponseData<String>();

        responseData.setData(jwtUtils.generateToken(userDetails));
        return ResponseEntity.ok().body(responseData);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<ResponseData<String>> logout() {
        var responseData = new ResponseData<String>();
        responseData.setData("You've been signed out!");
        return ResponseEntity.ok(responseData);
    }
}
