package com.spring.SchoolManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole()); // Ab role request se lega
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        User user = userRepository.findByUsername(req.getUsername()).get();
        return ResponseEntity.ok(new LoginResponse("Login Success", user.getRole()));
    }

   
    static class RegisterRequest { 
        private String username; 
        private String password;
        private String role;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
    
    static class LoginRequest { 
        private String username; 
        private String password;
        
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    
    static class LoginResponse { 
        private String message; 
        private String role; 
        
        public LoginResponse(String m, String r){
            this.message = m; 
            this.role = r;
        }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}

































/*
 * package com.spring.SchoolManagement;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/auth") public class AuthController {
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @Autowired private PasswordEncoder passwordEncoder;
 * 
 * @Autowired private AuthenticationManager authenticationManager;
 * 
 * @PostMapping("/register") public ResponseEntity<?> register(@RequestBody
 * RegisterRequest req) { if
 * (userRepository.findByUsername(req.getUsername()).isPresent()) { return
 * ResponseEntity.badRequest().body("Username already exists"); } User user =
 * new User(); user.setUsername(req.getUsername());
 * user.setPassword(passwordEncoder.encode(req.getPassword()));
 * user.setRole("ROLE_TEACHER"); // default role userRepository.save(user);
 * return ResponseEntity.ok("User registered successfully"); }
 * 
 * @PostMapping("/login") public ResponseEntity<?> login(@RequestBody
 * LoginRequest req) { authenticationManager.authenticate( new
 * UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()) );
 * User user = userRepository.findByUsername(req.getUsername()).get(); return
 * ResponseEntity.ok(new LoginResponse("Login Success", user.getRole())); }
 * 
 * // Lombok hata diya, manual getter-setter static class RegisterRequest {
 * private String username; private String password;
 * 
 * public String getUsername() { return username; } public void
 * setUsername(String username) { this.username = username; } public String
 * getPassword() { return password; } public void setPassword(String password) {
 * this.password = password; } }
 * 
 * static class LoginRequest { private String username; private String password;
 * 
 * public String getUsername() { return username; } public void
 * setUsername(String username) { this.username = username; } public String
 * getPassword() { return password; } public void setPassword(String password) {
 * this.password = password; } }
 * 
 * static class LoginResponse { private String message; private String role;
 * 
 * public LoginResponse(String m, String r){ this.message = m; this.role = r; }
 * 
 * public String getMessage() { return message; } public void setMessage(String
 * message) { this.message = message; } public String getRole() { return role; }
 * public void setRole(String role) { this.role = role; } } }
 */