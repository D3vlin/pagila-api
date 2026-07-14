package co.d3vlin.pagila.api.actor.service;

import co.d3vlin.pagila.api.actor.repository.ActorRepository;
import co.d3vlin.pagila.dto.ActorDTO;
import co.d3vlin.pagila.entity.ActorEntity;
import co.d3vlin.pagila.mapper.ActorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Transactional(readOnly = true)
    public List<ActorDTO> findAll() {
        return actorRepository
                .findAll()
                .stream()
                .map(actorMapper::fromEntity)
                .toList();
    }
}
