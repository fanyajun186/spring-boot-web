package com.neo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neo.domain.SearchResult;
import com.neo.service.SearchService;
import com.neo.util.RespDTO;



@Controller
public class SolrSearchController {
	
	@Autowired
	private SearchService searchService;

	@RequestMapping("/query")
	@ResponseBody
	public RespDTO getItemSearch(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "60") int rows, @RequestParam(defaultValue = "灯") String queryString) {
		try {
			// 解决乱码
			queryString = new String(queryString.getBytes("ISO8859-1"), "UTF-8");
			SearchResult result = searchService.search(queryString, page, rows);
			return RespDTO.success(result);
		} catch (Exception e) {
			e.printStackTrace();
			return RespDTO.fail(e.getMessage());
		}
	}
}
