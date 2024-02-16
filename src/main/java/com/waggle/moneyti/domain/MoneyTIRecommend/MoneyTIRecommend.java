package com.waggle.moneyti.domain.MoneyTIRecommend;

import com.waggle.moneyti.domain.MoneyTI.MoneyTI;
import com.waggle.moneyti.domain.Recommend.Recommend;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "MoneyTI_Recommend")
public class MoneyTIRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recommend_id")
    private Recommend recommend;

    @ManyToOne
    @JoinColumn(name = "moneyti_id")
    private MoneyTI moneyTI;
}
