package com.example.playerdemo;

import com.example.playerdemo.Controller.PlayerController;
import com.example.playerdemo.DTO.PlayerDTO;
import com.example.playerdemo.Entity.Player;
import com.example.playerdemo.Service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PlayerControllerTest {
    
    @Mock
    private PlayerService playerService;
    
    @InjectMocks
    private PlayerController playerController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void createPlayer_ShouldReturnCreatedPlayer() {
        // Arrange
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("John Doe");
        playerDTO.setUsername("johndoe");
        playerDTO.setEmail("john@example.com");
        
        Player player = new Player();
        player.setId(1L);
        player.setName(playerDTO.getName());
        player.setUsername(playerDTO.getUsername());
        player.setEmail(playerDTO.getEmail());
        
        when(playerService.createPlayer(any(PlayerDTO.class))).thenReturn(player);
        
        // Act
        ResponseEntity<Player> response = playerController.createPlayer(playerDTO);
        
        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(player, response.getBody());
    }
    
    @Test
    void getPlayerById_ShouldReturnPlayer() {
        // Arrange
        Player player = new Player();
        player.setId(1L);
        player.setName("John Doe");
        
        when(playerService.getPlayerById(1L)).thenReturn(player);
        
        // Act
        ResponseEntity<Player> response = playerController.getPlayerById(1L);
        
        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(player, response.getBody());
    }
    
    @Test
    void getAllPlayers_ShouldReturnListOfPlayers() {
        // Arrange
        Player player1 = new Player();
        player1.setId(1L);
        Player player2 = new Player();
        player2.setId(2L);
        List<Player> players = Arrays.asList(player1, player2);
        
        when(playerService.getAllPlayers()).thenReturn(players);
        
        // Act
        ResponseEntity<List<Player>> response = playerController.getAllPlayers();
        
        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(players, response.getBody());
    }
}
