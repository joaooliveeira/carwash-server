package com.carwash.ws;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carwash.model.Wash;
import com.carwash.service.WashService;

@RestController
@RequestMapping(value="wash")
public class WashWs {

	@Autowired
	private WashService washService;
	
	@RequestMapping(value="save", method=RequestMethod.PUT)
	public Wash saveWash(@RequestBody Wash wash) {
		return washService.save(wash);
	}
	
	@RequestMapping(value="filter/", method=RequestMethod.GET)
	public List<Wash> filterWashes(
			@RequestParam(name = "carId", required = false) String carId,
			@RequestParam(name = "clientId", required = false) String clientId,
			@RequestParam(name = "fromDate") String fromDate,
			@RequestParam(name = "toDate") String toDate) throws ParseException {
		return washService.filter(carId, clientId, fromDate, toDate);
	}
	
	@RequestMapping(value="running", method=RequestMethod.GET)
	public List<Wash> getRunningWashes() {
		return washService.getRunningWashes();
	}
	
	@RequestMapping(value="delete", method=RequestMethod.DELETE)
	public void deleteWash(
			@RequestParam(name = "id", required = true) String id) {
		washService.deleteWash(id);
	}
	
}
