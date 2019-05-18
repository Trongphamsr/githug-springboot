package vn.com.fpt.clt.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import vn.com.fpt.clt.dto.ProjectDto;
import vn.com.fpt.clt.entities.Project;
import vn.com.fpt.clt.entities.User;


@Component
public class ProjectRepositoryCustomImpl extends AbstractRepositoryCustomImpl<ProjectDto> implements ProjectRepositoryCustom{

	@Override
	public Page<Project> findByConditions(ProjectDto projectDto, Pageable pageable) {

		try {
			EntityGraph<?> entityGraph = entityManager.getEntityGraph("graph.Project.users_setting");
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Project> criteriaQuery =  criteriaBuilder.createQuery(Project.class);
			Root<Project> rootProject = criteriaQuery.from(Project.class);
			Join<Project, User> joinUser = rootProject.join("users");

			List<Predicate> conditions = new ArrayList<>();
			conditions.add(criteriaBuilder.equal(joinUser.get("id"), projectDto.getUserId()));

			if (StringUtils.isNotBlank(projectDto.getProjectName())) {
				conditions.add(criteriaBuilder.equal(rootProject.get("projectName"), projectDto.getProjectName()));
			}

			if(null != projectDto.getFromStartDate()) {
				conditions.add(criteriaBuilder.greaterThanOrEqualTo(rootProject.get("startDate"), projectDto.getFromStartDate()));
			}

			if (null != projectDto.getToStartDate()) {
				conditions.add(criteriaBuilder.lessThanOrEqualTo(rootProject.get("startDate"), projectDto.getToStartDate()));
			}
			
			if(StringUtils.isNotBlank(projectDto.getAreaName())) {
				conditions.add(criteriaBuilder.equal(rootProject.get("areaName"), projectDto.getAreaName()));
			}
			
			if(null != projectDto.getActive()) {
				conditions.add(criteriaBuilder.equal(rootProject.get("active"), projectDto.getActive()));
			}

			criteriaQuery.where(conditions.toArray(new Predicate[] {}));

			int firstResult = pageable.getPageNumber() * pageable.getPageSize();
			int maxResults = firstResult + pageable.getPageSize();

			TypedQuery<Project> typedQuery = entityManager.createQuery(criteriaQuery);
			typedQuery.setHint("javax.persistence.loadgraph", entityGraph);
			typedQuery.setFirstResult(firstResult);
			typedQuery.setMaxResults(maxResults);

			List<Project> lsProject = typedQuery.getResultList();
			
			return new PageImpl<>(lsProject, pageable, lsProject.size());
		} finally{
			entityManager.close();
		}
	}

}
