package com.masai.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.DTO.AdminDTO;
import com.masai.modelEntity.Admin;
import com.masai.modelEntity.ModelUser;
import com.masai.repository.AdminDao;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;

	@Override
	public Admin adminRegister(Admin admin) {
		return adminDao.save(admin);
	}

	@Override
	public Admin updatePassword(Admin admin, String username) {
		Admin updated= null;
		Optional<Admin> opt = adminDao.findByUserUsername(username);
		if (opt.get() == null);
		else {
			Admin toUpdate = opt.get();
			Integer id = toUpdate.getAdminId();
			ModelUser user = toUpdate.getUser();
			user.setPassword(admin.getUser().getPassword());
			Admin newOne = new Admin(id, user);
			updated= adminDao.save(newOne);
		}
		return updated;

	}

	@Override
	public Admin update(Admin admin, String Username) {
		Admin updated =null;
		Optional<Admin> opt=adminDao.findByUserUsername(Username);
		if(opt.get()==null);
		else{
			Admin toUpdate = opt.get();
			Integer id = toUpdate.getAdminId();
			Admin newOne = new Admin(id, admin.getUser());
			updated= adminDao.save(newOne);
		}
		return updated;
	
	}

	@Override
	public String deleteByUsername(AdminDTO dto) {
		Optional<Admin> opt= adminDao.findByUserUsername(dto.getUsername());
		if(opt.get()==null);
		else {
			Admin toBeDelete=opt.get();
			adminDao.delete( toBeDelete);
		}
		// TODO Auto-generated method stub
		return "Your Id with Username "+dto.getUsername()+" is Deleted.";
	}

}
