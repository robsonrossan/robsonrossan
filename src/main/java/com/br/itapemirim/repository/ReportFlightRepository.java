package com.br.itapemirim.repository;

import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.br.itapemirim.dto.FilterReportFlightDTO;

@Repository
public class ReportFlightRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Page<Object[]> getFueledPlanes(FilterReportFlightDTO filter, Pageable apiPageRequest) {
		StringBuilder sql = new StringBuilder("SELECT ID_FLIGHT, COD_FLIGHT, NAME_FLIGHT, DATE_FUEL ");
		sql.append(" FROM cad_flight CF ");
		sql.append(" where 1 = 1 ");
		conditionsReport(sql, filter);
		Query query = entityManager.createNativeQuery(sql.toString());
		parametersFueledPlanes(query, filter);
		
		int startPosition = apiPageRequest.getPageNumber() * apiPageRequest.getPageSize();
		List<Object[]> resultado = query.setFirstResult(startPosition).setMaxResults(apiPageRequest.getPageSize()).getResultList();
		return (Page<Object[]>) new PageImpl<Object[]>(resultado);
//		List<Object[]> resultado = query.getResultList();
//		return resultado;
	}
	
	private void conditionsReport(StringBuilder sql, FilterReportFlightDTO filter) {
		
		if (Objects.nonNull(filter.getCodFlight())) {
			sql.append(" AND CF.COD_FLIGHT = :codFlight ");
		}
		if (Objects.nonNull(filter.getNameFlight())) {
			sql.append(" AND CF.NAME_FLIGHT = :nameFlight ");
		}
		if (Objects.nonNull(filter.getDateFuelStart()) && Objects.nonNull(filter.getDateFuelEnd())) {
			sql.append(" AND CF.DATE_FUEL >= :dateFuelStart and CF.DATE_FUEL <= :dateFuelEnd ");
		}
	}
	private void parametersFueledPlanes(Query query, FilterReportFlightDTO filter) {

		if (Objects.nonNull(filter.getCodFlight())) {
			query.setParameter("codFlight", filter.getCodFlight());
		}
		if (Objects.nonNull(filter.getNameFlight())) {
			query.setParameter("nameFlight", filter.getNameFlight());
		}
		if (Objects.nonNull(filter.getDateFuelStart()) && Objects.nonNull(filter.getDateFuelEnd())) {
			query.setParameter("dateFuelStart", filter.getDateFuelStart().atStartOfDay());
			query.setParameter("dateFuelEnd", filter.getDateFuelEnd().atTime(23,59,59));
		}
	}
}
