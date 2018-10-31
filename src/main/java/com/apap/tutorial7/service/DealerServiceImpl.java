package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DealerServiceImpl implements DealerService{
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id) {
		return dealerDb.findById(id);
	}
	
	@Override
	public void addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
	}
	
	@Override
	public void deleteDealer (DealerModel dealer) {
		dealerDb.delete(dealer);
	}

	@Override
	public List<DealerModel> getAllDealer() {
		// TODO Auto-generated method stub
		return dealerDb.findAll();
	}
	
	@Override
	public void updateDealer(long id, Optional<DealerModel> newDealer) {
		DealerModel temp = dealerDb.getOne(id);
		temp.setAlamat(newDealer.get().getAlamat());
		temp.setNoTelp(newDealer.get().getNoTelp());
		dealerDb.save(temp);
	}

	@Override
	public List<DealerModel> viewAllDealer() {
		return dealerDb.findAll();
	}

	@Override
	public void updateDealer(Long idDealer, DealerModel dealer) {
		DealerModel updatedDealer = dealerDb.getOne(idDealer);
		updatedDealer.setAlamat(dealer.getAlamat());
		updatedDealer.setNoTelp(dealer.getNoTelp());
		dealerDb.save(updatedDealer);
	}
}