package com.sena.proyectodonneta.service.impl;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sena.proyectodonneta.dto.UserDto;
import com.sena.proyectodonneta.model.Role;
import com.sena.proyectodonneta.model.User;
import com.sena.proyectodonneta.repository.RoleRepository;
import com.sena.proyectodonneta.repository.UserRepository;
import com.sena.proyectodonneta.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        // el Dto es el de manipulación de datos por eso en la entity guardamos el nombre pero en el dto pedimos el
        // nombre y apellido
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // buscar si existe el ROLE_USER en caso de que sea null invocamos el metodo para que lo cree
        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }
        //asignamos los roles a un arreglo de lo que devuelve el metodo
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public void saveDomiciliario(UserDto userDto) {
        User user = new User();
        // el Dto es el de manipulación de datos por eso en la entity guardamos el nombre pero en el dto pedimos el
        // nombre y apellido
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // buscar si existe el ROLE_USER en caso de que sea null invocamos el metodo para que lo cree
        Role role = roleRepository.findByName("ROLE_DOMICILIARIO");
        if(role == null){
            role = checkDomExist();
        }
        //asignamos los roles a un arreglo de lo que devuelve el metodo
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    // se usa para que cuando mostremos los usuarios podamos mostrar el nombre y apellido por separado
    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    // crea el ROLE_USER si no existe esto se hace por defecto
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }

    private Role checkDomExist() {
        Role role = new Role();
        role.setName("ROLE_DOMICILIARIO");
        return roleRepository.save(role);
    }


    @Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}


}
