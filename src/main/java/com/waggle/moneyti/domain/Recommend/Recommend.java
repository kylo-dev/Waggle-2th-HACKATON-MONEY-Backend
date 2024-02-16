package com.waggle.moneyti.domain.Recommend;

import com.waggle.moneyti.domain.MoneyTI.MoneyTI;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "moneyti_id")
    private MoneyTI moneyTI;

    private String content;
}
