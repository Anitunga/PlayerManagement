package com.example.playerdemo;

import com.example.playerdemo.DAO.FriendDAO;
import com.example.playerdemo.DAO.PlayerDAO;
import com.example.playerdemo.Entity.Friend;
import com.example.playerdemo.Entity.Player;
import com.example.playerdemo.Service.FriendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FriendServiceTest {

    @Mock
    private PlayerDAO playerDAO;

    @Mock
    private FriendDAO friendDAO;

    @InjectMocks
    private FriendService friendService;

    private Player player1;
    private Player player2;
    private Friend friendship;

    @BeforeEach
    void setUp() {
        // Initialize test data
        player1 = new Player();
        player1.setId(1L);
        player1.setName("John Doe");

        player2 = new Player();
        player2.setId(2L);
        player2.setName("Bob Smith");

        friendship = new Friend(player1, player2);
    }

    @Test
    void addFriend_Success() {
        // Arrange
        when(playerDAO.findById(1L)).thenReturn(Optional.of(player1));
        when(playerDAO.findById(2L)).thenReturn(Optional.of(player2));
        when(friendDAO.findByPlayerAndFriendPlayer(player1, player2)).thenReturn(Optional.empty());
        when(friendDAO.save(any(Friend.class))).thenReturn(friendship);

        // Act
        Friend result = friendService.addFriend(1L, 2L);

        // Assert
        assertNotNull(result);
        assertEquals(player1, result.getPlayer());
        assertEquals(player2, result.getFriendPlayer());
    }

    @Test
    void addFriend_PlayerNotFound() {
        // Arrange
        when(playerDAO.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> friendService.addFriend(1L, 2L));
    }

    @Test
    void addFriend_FriendPlayerNotFound() {
        // Arrange
        when(playerDAO.findById(1L)).thenReturn(Optional.of(player1));
        when(playerDAO.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> friendService.addFriend(1L, 2L));
    }

    @Test
    void addFriend_FriendshipAlreadyExists() {
        // Arrange
        when(playerDAO.findById(1L)).thenReturn(Optional.of(player1));
        when(playerDAO.findById(2L)).thenReturn(Optional.of(player2));
        when(friendDAO.findByPlayerAndFriendPlayer(player1, player2)).thenReturn(Optional.of(friendship));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> friendService.addFriend(1L, 2L));
    }

    @Test
    void getPlayerFriends_Success() {
        // Arrange
        List<Friend> friendships = Arrays.asList(friendship);
        when(playerDAO.findById(1L)).thenReturn(Optional.of(player1));
        when(friendDAO.findByPlayer(player1)).thenReturn(friendships);

        // Act
        List<Friend> result = friendService.getPlayerFriends(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(player1, result.get(0).getPlayer());
        assertEquals(player2, result.get(0).getFriendPlayer());
    }

    @Test
    void getPlayerFriends_PlayerNotFound() {
        // Arrange
        when(playerDAO.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> friendService.getPlayerFriends(1L));
    }
}
