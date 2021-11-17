package com.br.itapemirim.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.itapemirim.dto.FilterReportFlightDTO;
import com.br.itapemirim.dto.ResultReportFlightFueledDTO;
import com.br.itapemirim.repository.ReportFlightRepository;

@Service
public class ReportFlightService {
	
	@Autowired
	private ReportFlightRepository relatorioRepository;
	
	public Page<ResultReportFlightFueledDTO> getFueledPlanes(FilterReportFlightDTO filter, Pageable pageable) {
		
		Page<Object[]> listObject = relatorioRepository.getFueledPlanes(filter, pageable);
		List<ResultReportFlightFueledDTO> listReport =
				listObject.stream()
				       .map(ResultReportFlightFueledDTO::new)
				       .collect(Collectors.toList());
		
		Page<ResultReportFlightFueledDTO> pagelist = new PageImpl<>(listReport);
		return pagelist;
	}
}
