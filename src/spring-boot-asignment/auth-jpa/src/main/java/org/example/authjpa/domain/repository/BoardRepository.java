package org.example.authjpa.domain.repository;

import org.example.authjpa.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
