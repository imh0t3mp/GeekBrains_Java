package name.imh0t3mp.course.geekbrains.controllers.api.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import name.imh0t3mp.course.geekbrains.config.JwtTokenUtil;
import name.imh0t3mp.course.geekbrains.services.JwtUserDetailsService;
import name.imh0t3mp.geekbrains.dto.AuthRequestDTO;
import name.imh0t3mp.geekbrains.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/api/1.0/auth")
@Api(tags = "Auth API")
@RestController
@Slf4j
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "/",
            produces = {"application/json"}
    )
    @ApiOperation("Авторизация пользователя")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 401, message = "unauthorized"),
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestDTO authenticationRequest) {
        log.debug("Auth user data:{}", authenticationRequest);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            log.warn("AUTH ERROR:{}", e.getMessage());
            log.warn("AUTH ERROR:{}", e.getStackTrace());
            throw new BadCredentialsException("Ошибка в имени пользователя или пароле");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
//        return ResponseEntity.ok(userDetailsService.save(user));
//    }
}