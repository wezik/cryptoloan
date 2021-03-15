package com.cryptoloan.domain.trackers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedQuery(
        name = "CurrencyTracker.findByCurrency",
        query = "FROM fav_currencies WHERE STRCMP(currency,:CURRENCY)=0"
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "fav_currencies")
public class CurrencyTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tracker_id")
    private Long tracker_id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "times_chosen")
    private int timesChosen;

}
