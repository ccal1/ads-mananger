package com.mananger.ads.campaign.repository;

import com.mananger.ads.campaign.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {}
