package pl.edu.pg.eti.AUI.Component.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.AUI.Component.Service.PlayerService;
import pl.edu.pg.eti.AUI.DTO.Event.CreatePlayerEventRequest;

@AllArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerEventController {

    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<Void> createPlayer(@RequestBody CreatePlayerEventRequest request, UriComponentsBuilder builder) {
        var player = playerService.create(request.into());
        return ResponseEntity.created(
                builder
                        .pathSegment("api", "players", "{id}")
                        .buildAndExpand(player.getId())
                        .toUri())
                .build();
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
