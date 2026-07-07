package org.example.basicboard.domian.repository;

import org.example.basicboard.domian.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
