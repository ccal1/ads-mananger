package com.mananger.ads.server.dal;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "campaign")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Campaign {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

  @Column(name = "visits_goal")
  Long visitsGoal;

  @ManyToOne
  @JoinColumn(name = "user_id")
  User user;

  @OneToMany(mappedBy = "campaign")
  Set<Place> places = new HashSet<>();

  @OneToMany(mappedBy = "campaign")
  Set<Ad> ads = new HashSet<>();

  @Column(name = "start_date")
  Date startDate;

  @Column(name = "end_date")
  Date endDate;
}
