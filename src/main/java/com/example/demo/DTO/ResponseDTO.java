package com.example.demo.DTO;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
//@JacksonXmlRootElement(localName = "ResponseDTO")
@XmlRootElement(name = "ResponseDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("page")
	private int page;
	
	@JsonProperty("pageSize")
	private int pageSize;
	
	@JsonProperty("sortData")
	private Sort.Direction sortOrder;
	
	@JsonProperty("sortBy")
	private String sortBy;
	
	@JsonProperty("totalPages")
	private int totalPages;
	
	@JsonProperty("totalItemsFound")
	private long totalItemsFound;
	
	@JsonProperty("responseContents")
	List<?> responseContent;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Sort.Direction getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Sort.Direction sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalItemsFound() {
		return totalItemsFound;
	}

	public void setTotalItemsFound(long totalItemsFound) {
		this.totalItemsFound = totalItemsFound;
	}

	public List<?> getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(List<?> responseContent) {
		this.responseContent = responseContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
