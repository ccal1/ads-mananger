package com.mananger.ads.server.dal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
public class User {
  @Id @GeneratedValue Long id;

  @Column String login;

  @Column String password;

  @OneToOne(mappedBy = "user")
  Balance balance;
}
