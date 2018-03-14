package com.fangminx.repository;

import com.fangminx.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fangmin on 2017/9/13.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
}
