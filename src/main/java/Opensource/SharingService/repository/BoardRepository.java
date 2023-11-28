package Opensource.SharingService.repository;

import Opensource.SharingService.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.boardIndex=:boardIndex")
    void updateHits(@Param("boardIndex") Long boardIndex); // 조회수 증가 쿼리 작성
}
