package com.example.playerdemo;

import com.example.playerdemo.DAO.PlayerDAO;
import com.example.playerdemo.DTO.PlayerDTO;
import com.example.playerdemo.Entity.Player;
import com.example.playerdemo.Service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PlayerServiceTest {

    @Mock
    private PlayerDAO playerDAO;

    @InjectMocks
    private PlayerService playerService;

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

        Player savedPlayer = new Player();
        savedPlayer.setId(1L);
        savedPlayer.setName(playerDTO.getName());
        savedPlayer.setUsername(playerDTO.getUsername());
        savedPlayer.setEmail(playerDTO.getEmail());

        when(playerDAO.save(any(Player.class))).thenReturn(savedPlayer);

        // Act
        Player result = playerService.createPlayer(playerDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedPlayer.getId(), result.getId());
        assertEquals(playerDTO.getName(), result.getName());
        assertEquals(playerDTO.getUsername(), result.getUsername());
        assertEquals(playerDTO.getEmail(), result.getEmail());
    }

    @Test
    void getPlayerById_ShouldReturnPlayer() {
        // Arrange
        Player player = new Player();
        player.setId(1L);
        player.setName("John Doe");

        when(playerDAO.findById(1L)).thenReturn(Optional.of(player));

        // Act
        Player result = playerService.getPlayerById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(player.getId(), result.getId());
        assertEquals(player.getName(), result.getName());
    }

    @Test
    void getPlayerById_ShouldThrowException_WhenPlayerNotFound() {
        // Arrange
        when(playerDAO.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> playerService.getPlayerById(1L));
    }

    @Test
    void getAllPlayers_ShouldReturnListOfPlayers() {
        // Arrange
        Player player1 = new Player();
        player1.setId(1L);
        Player player2 = new Player();
        player2.setId(2L);
        List<Player> players = Arrays.asList(player1, player2);

        when(playerDAO.findAll()).thenReturn(players);

        // Act
        List<Player> result = playerService.getAllPlayers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(players, result);
    }
}
