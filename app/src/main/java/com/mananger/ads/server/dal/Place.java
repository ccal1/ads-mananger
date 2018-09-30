package com.mananger.ads.server.dal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "place")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Place {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

  @ManyToOne
  @JoinColumn(name = "campaign_id")
  Campaign campaign;

  @Column Double lat;

  @Column(name = "lng") Double lng;

  @Column(name = "visit_count")
  Long visitCount;
}
