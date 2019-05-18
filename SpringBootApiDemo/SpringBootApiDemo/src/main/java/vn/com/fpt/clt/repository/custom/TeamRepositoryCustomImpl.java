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

import vn.com.fpt.clt.dto.TeamDto;
import vn.com.fpt.clt.entities.Project;
import vn.com.fpt.clt.entities.Team;
import vn.com.fpt.clt.entities.User;

@Component
public class TeamRepositoryCustomImpl extends AbstractRepositoryCustomImpl<TeamDto> implements TeamRepositoryCustom {

	@Override
	public Page<Team> findByConditions(TeamDto teamDto, Pageable pageable) {

		try {

			EntityGraph<?> entityGraph = entityManager.getEntityGraph("graph.Team.project.users_setting");
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Team> criteriaQuery = criteriaBuilder.createQuery(Team.class);
			Root<Team> rootTeam = criteriaQuery.from(Team.class);
			Join<Team, Project> joinProject = rootTeam.join("project");
			Join<Project, User> joinUser = joinProject.join("users");

			List<Predicate> conditions = new ArrayList<>();
			conditions.add(criteriaBuilder.equal(joinProject.get("id"), teamDto.getProjectId()));
			conditions.add(criteriaBuilder.equal(joinUser.get("id"), teamDto.getUserId()));

			if (StringUtils.isNotBlank(teamDto.getTeamName())) {
				conditions.add(criteriaBuilder.equal(rootTeam.get("teamName"), teamDto.getTeamName()));
			}

			if (null != teamDto.getFromStartDate()) {
				conditions.add(
						criteriaBuilder.greaterThanOrEqualTo(rootTeam.get("startDate"), teamDto.getFromStartDate()));
			}

			if (null != teamDto.getToStartDate()) {
				conditions
						.add(criteriaBuilder.lessThanOrEqualTo(rootTeam.get("startDate"), teamDto.getFromStartDate()));
			}

			if (null != teamDto.getActive()) {
				conditions.add(criteriaBuilder.equal(rootTeam.get("active"), teamDto.getActive()));
			}

			criteriaQuery.where(conditions.toArray(new Predicate[] {}));

			int firstResult = pageable.getPageNumber() * pageable.getPageSize();
			int maxResults = firstResult + pageable.getPageSize();

			TypedQuery<Team> typedQuery = entityManager.createQuery(criteriaQuery);
			typedQuery.setHint("javax.persistence.loadgraph", entityGraph);
			typedQuery.setFirstResult(firstResult);
			typedQuery.setMaxResults(maxResults);

			List<Team> lstTeam = typedQuery.getResultList();

			return new PageImpl<>(lstTeam, pageable, lstTeam.size());
		} finally {
			entityManager.close();
		}
	}

}
