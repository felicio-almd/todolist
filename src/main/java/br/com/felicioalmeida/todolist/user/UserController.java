package br.com.felicioalmeida.todolist.user;

import javax.swing.RepaintManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;

/**
 * Modificador -  
 * public
 * private
 * protected
 * 
 * Tipo -
 * interface, enum, class, etc
 */
@RestController
@RequestMapping("/users")
public class UserController  {

    /**
     * String
     * Integer
     * Double 
     * Float 
     * Char
     * Date
     * void
     */
    /**
     * Body
     *http://localhost:8080/
     */

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null){
            System.out.println("usuario ja existe");
            // Retornar Mensagem de erro e status code 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe.");
        }

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    } 
    
}