package com.example.PW14.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.example.PW14.java.entities.Client;
import com.example.PW14.java.repositories.ClientRepository;
import com.example.PW14.java.services.UserDetailsServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {
    @Mock
    private ClientRepository cRepo;
    @Captor
    ArgumentCaptor<Client> captor;
    @Test
    void getClients() {
        Client cli1 = new Client("Vasya", "Vasiliev", "vasya@mail.ru", "vasya", "2208");
        Client cli2 = new Client("Lesha", "Vasiliev", "lesha@mail.ru", "lesha", "2208");
        Mockito.when(cRepo.findAll()).thenReturn(List.of(cli1, cli2));
        UserDetailsServiceImpl uService = new UserDetailsServiceImpl();
        Assertions.assertEquals(3, uService.getUsers().size());
        Assertions.assertEquals("Vasya", uService.getUsers().get(1).getName());
    }
    @Test
    public void saveOrUpdate() {
        Client cli = new Client("Alina", "Ivanova", "alina@mail.ru", "alina", "2208");
        UserDetailsServiceImpl cService = new UserDetailsServiceImpl();
        cService.saveOrUpdate(cli);
        Mockito.verify(cRepo).save(captor.capture());
        Client captured = captor.getValue();assertEquals("Alina", captured.getName());
    }
}