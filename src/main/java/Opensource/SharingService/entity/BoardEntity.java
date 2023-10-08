package Opensource.SharingService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // size default - 255, nullable
    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column
    private String boardPass;

    @Column String boardTitle;

    @Column(length = 500)
    private int boardContents;

    @Column
    private int boardHits;

    @Column
    private int fileAttached; // 1 or 0


}
