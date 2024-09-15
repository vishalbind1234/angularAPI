package com.example.angularAPI.controller;

import com.example.angularAPI.jwt.JwtService;
import com.example.angularAPI.modal.AuthClass;
import com.example.angularAPI.modal.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.management.BadAttributeValueExpException;
import java.util.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManagerImpl;

    @RequestMapping(value = "/getAll" , method=RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> list = new ArrayList<UserResponse>();
        list.add(new UserResponse(11,"vishal","7789456","vishal@1234"));
        list.add(new UserResponse(12,"rama","456789","rama@1234"));
        list.add(new UserResponse(13,"sham","66352","sham@1234"));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @RequestMapping(value = "/getToken" , method=RequestMethod.POST)
    public ResponseEntity<Map<String,String>> getToken(@RequestBody AuthClass authClass){
        Map<String,String> resp = new HashMap<>();
        try{
            Authentication authentication = authenticationManagerImpl.authenticate(new UsernamePasswordAuthenticationToken(authClass.getUsername(),authClass.getPassword()));
            HttpStatusCode code;
            String token;
            if(authentication.isAuthenticated()){
                token = jwtService.generateToken(authClass.getUsername());
                code = HttpStatus.OK;
            }else{
                throw new BadCredentialsException("bad credential");
            }
            resp.put("data",token);
            return new ResponseEntity<>(resp, code);
        }catch (BadCredentialsException e){
            resp.put("data","bad credential");
            return new ResponseEntity<>(resp, HttpStatus.NO_CONTENT);
        }
    }
    @RequestMapping(value = "/logout" , method=RequestMethod.POST)
    public ResponseEntity<Map<String,String>> logout(){
        SecurityContextHolder.clearContext();
        HashMap<String,String> map = new HashMap<>();
        map.put("data","logout Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @RequestMapping(value = "/getContent" , method=RequestMethod.POST)
    public ResponseEntity<Map<String,String>> getContent(){
        Map<String,String> map = new HashMap<>();
        map.put("data","Hello this is good");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}

