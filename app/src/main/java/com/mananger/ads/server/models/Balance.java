package com.mananger.ads.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "balance")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Balance {
  @Id
  @Column(name = "user_id")
  Long id;

  @OneToOne @MapsId User user;

  @Column Double ammount;
}
