package com.example.Board.domain;


import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
public class Board {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt = LocalDateTime.now();
}
