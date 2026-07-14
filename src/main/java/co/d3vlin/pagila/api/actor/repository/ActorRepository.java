package co.d3vlin.pagila.api.actor.repository;

import co.d3vlin.pagila.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
}
