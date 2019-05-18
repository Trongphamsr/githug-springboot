package vn.com.fpt.clt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import vn.com.fpt.clt.entities.SettingReferenceLink;

/**
 * 
 * @author ChienNV7
 *
 */

@Repository
public interface SettingReferenceLinkRepository extends JpaRepository<SettingReferenceLink, Long> {

	@Modifying
	//@Query("DELETE FROM SettingReferenceLink s WHERE s.project.id = :projectId")
	void deleteBySettingId(Long settingId);
}
