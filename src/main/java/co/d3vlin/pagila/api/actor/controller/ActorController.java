package co.d3vlin.pagila.api.actor.controller;

import co.d3vlin.pagila.api.actor.service.ActorService;
import co.d3vlin.pagila.dto.ActorDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/actors")
@RequiredArgsConstructor
@Tag(name = "Actor Controller", description = "Controller for managing actors")
public class ActorController {
    private final ActorService actorService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<ActorDTO>> findAll() {
        List<ActorDTO> actors = actorService.findAll();
        if (actors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(actors);
    }
}
