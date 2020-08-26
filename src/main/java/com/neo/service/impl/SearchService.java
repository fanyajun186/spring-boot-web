package com.neo.service.impl;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import com.neo.dao.SearchDAO;
import com.neo.domain.SearchResult;

@Service
public class SearchService {
	
	@Resource
	private SearchDAO searchDAO;

	public SearchResult search(String queryString, int page, int rows) throws Exception {
		SolrQuery query = new SolrQuery("url_content:"+queryString);
		query.setStart((page-1)*rows);
		query.setRows(rows);
		return searchDAO.search(query);
	}
	
}

