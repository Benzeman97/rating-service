package com.benz.security.test.controller;

import com.benz.security.test.dao.RoleDAO;
import com.benz.security.test.dao.UserDAO;
import com.benz.security.test.entity.Role;
import com.benz.security.test.entity.User;
import com.benz.security.test.payload.request.AuthenticationRequest;
import com.benz.security.test.payload.request.SignUpRequest;
import com.benz.security.test.payload.response.AuthenticationResponse;
import com.benz.security.test.payload.response.MessageResponse;
import com.benz.security.test.security.JwtUtil;
import com.benz.security.test.services.MyUserDetailsService;
import com.benz.security.test.util.ERole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Value("${log.rounds}")
    private int logRounds;

    @PostMapping("/signin")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{

            
             
             Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                     authenticationRequest.getUserName(),authenticationRequest.getPassword()));


             final UserDetails userDetails= (UserDetails) authentication.getPrincipal();


             final String token=jwtUtil.generateToken(userDetails);

           return ResponseEntity.ok(new AuthenticationResponse(userDetails.getUsername(),token));

        }catch (BadCredentialsException bx)
        {

            throw new Exception("Incorrect username or password",bx);
        }
    }

    @PostMapping("/signup")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
   public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest)
   {
          if(userDAO.existsByUserName(signUpRequest.getUserName()))
              return ResponseEntity.badRequest().body(new MessageResponse("Error username is already taken"));

          User user=new User();
          user.setUserName(signUpRequest.getUserName());
          user.setPassword(BCrypt.hashpw(signUpRequest.getPassword(),BCrypt.gensalt(logRounds)));
          user.setActive("y");

          Set<Role> roles=new HashSet<>();

          if(signUpRequest.getRoles().size()==0)
          {
              Role userRole=roleDAO.findByName(ERole.ROLE_USER)
                      .orElseThrow(()->new RuntimeException("Error : Role is not found"));
              roles.add(userRole);
          }else{
               signUpRequest.getRoles().forEach(role->{
                   switch(role.toLowerCase())
                   {
                       case "admin":
                            Role adminRole=roleDAO.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(()->new RuntimeException("Error : Role is not found"));
                            roles.add(adminRole);break;
                       case "mod":
                           Role modRole=roleDAO.findByName(ERole.ROLE_MODERATOR)
                                   .orElseThrow(()->new RuntimeException("Error : Role is not found"));
                           roles.add(modRole);break;
                       default:
                           Role userRole=roleDAO.findByName(ERole.ROLE_USER)
                                   .orElseThrow(()->new RuntimeException("Error : Role is not found"));
                           roles.add(userRole);
                   }
               });
          }
          user.setRoles(roles);
          userDAO.save(user);

          return new ResponseEntity<>(new MessageResponse("Registration has been done successfully"), HttpStatus.OK);

   }
}
