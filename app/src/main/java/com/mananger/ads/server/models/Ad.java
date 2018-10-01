package com.mananger.ads.server.models;

import com.mananger.ads.campaign.models.Campaign;
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
@Table(name = "ad")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne
  @JoinColumn(name = "campaign_id")
  Campaign campaign;

  @Column(name = "image_url")
  String imageUrl;
}
