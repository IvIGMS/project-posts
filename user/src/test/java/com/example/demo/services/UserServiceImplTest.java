package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.dto.UserNopassDTO;
import com.example.demo.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Optional;

public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById_ok() {
        // Preparacion
        Calendar calendario = Calendar.getInstance();
        calendario.set(1997, Calendar.JUNE, 30);

        UserNopassDTO userDTO = new UserNopassDTO(1L, "ivigms", "Ivan", "Frias", calendario.getTime(), "friasgilivan@gmail.com");
        User user = new User("ivigms", "Ivan", "Frias", calendario.getTime(), "friasgilivan@gmail.com");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Accion
        UserNopassDTO foundUser = userService.findById(1L);

        // Comprobamos el resultado de la funcion con lo que esperamos
        Assert.assertEquals(foundUser.getName(), userDTO.getName());
        Assert.assertEquals(foundUser.getUsername(), userDTO.getUsername());
    }

    @Test
    public void findById_null() {
        // Preparacion
        Calendar calendario = Calendar.getInstance();
        calendario.set(1997, Calendar.JUNE, 30);

        UserNopassDTO userDTO = new UserNopassDTO(1L, "ivigms", "Ivan", "Frias", calendario.getTime(), "friasgilivan@gmail.com");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Accion
        UserNopassDTO foundUser = userService.findById(1L);


        // Comprobamos
        Assert.assertNull(foundUser);
    }
}