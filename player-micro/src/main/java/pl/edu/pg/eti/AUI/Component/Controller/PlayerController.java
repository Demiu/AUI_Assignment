package pl.edu.pg.eti.AUI.Component.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.AUI.Component.Service.PlayerService;
import pl.edu.pg.eti.AUI.DTO.CreatePlayerRequest;
import pl.edu.pg.eti.AUI.DTO.GetPlayerResponse;
import pl.edu.pg.eti.AUI.DTO.GetPlayersResponse;
import pl.edu.pg.eti.AUI.DTO.UpdatePlayerRequest;

@AllArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<GetPlayersResponse> getPlayers() {
        return ResponseEntity.ok(GetPlayersResponse.from(playerService.findAll()));
    }

    @GetMapping("{id:[0-9]+}")
    public ResponseEntity<GetPlayerResponse> getPlayer(@PathVariable("id") Long id) {
        return playerService.find(id)
                .map(p -> ResponseEntity.ok(GetPlayerResponse.from(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createPlayer(@RequestBody CreatePlayerRequest request, UriComponentsBuilder builder) {
        if (playerService.find(request.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // TODO: add info about the conflict?
        }
        var player = playerService.create(request.into());
        return ResponseEntity.created(
                builder
                        .pathSegment("api", "players", "{id}")
                        .buildAndExpand(player.getId())
                        .toUri())
                .build();
    }

    @PutMapping("{id:[0-9]+}")
    public ResponseEntity<Void> updatePlayer(@RequestBody UpdatePlayerRequest request, @PathVariable("id") Long id) {
        var player = playerService.find(id);
        if (player.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var sameNamePlayer = playerService.find(request.getName());
        if (sameNamePlayer.isPresent() && !(player.get().getId().equals(sameNamePlayer.get().getId()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // TODO: add info about the conflict?
        }
        request.apply(player.get());
        playerService.update(player.get());
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{id:[0-9]+}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("id") Long id) {
        var player = playerService.find(id);
        if (player.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        playerService.delete(player.get());
        return ResponseEntity.accepted().build();
    }
}
