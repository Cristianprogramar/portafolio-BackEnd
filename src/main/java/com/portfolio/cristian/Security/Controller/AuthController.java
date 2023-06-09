package com.portfolio.cristian.Security.Controller;

import com.portfolio.cristian.Security.Dto.JwtDto;
import com.portfolio.cristian.Security.Dto.LoginUsuario;
import com.portfolio.cristian.Security.Dto.NuevoUsuario;
import com.portfolio.cristian.Security.Entity.Rol;
import com.portfolio.cristian.Security.Entity.Usuario;
import com.portfolio.cristian.Security.Enums.RolNombre;
import com.portfolio.cristian.Security.Service.RolService;
import com.portfolio.cristian.Security.Service.UsuarioService;
import com.portfolio.cristian.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Los campos son incorrectos."), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Este usuario ya existe."), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("Este correo ya existe."), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));

        //Usuario por defecto
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        //Usuario administrador
        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("El usuario fue guardado."), HttpStatus.CREATED);
    }

    //Sistema de login
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

            return new ResponseEntity(jwtDto, HttpStatus.OK);
        }
        catch (AuthenticationException ex) {
            if (ex instanceof BadCredentialsException) {
                return new ResponseEntity(new Mensaje("La contraseña es incorrecta."), HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity(new Mensaje("No se pudo encontrar tu cuenta."), HttpStatus.UNAUTHORIZED);
            }
        }
    }
}
