package com.br.itapemirim.resquest;

import static java.util.Objects.isNull;
import java.util.Objects;

public class ApiPageRequest {

	private Integer pageNumber;
	private Integer pageSize;

	public Integer getPageNumber() {
		if (isNull(pageNumber))
			return 0;

		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		if (Objects.isNull(pageSize)) {
			pageSize = 1;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isPageSizeNotProvided(){
		return pageSize == null;
	}

	public ApiPageRequest generatePageRequest() {
		return new ApiPageRequest();
	}
}