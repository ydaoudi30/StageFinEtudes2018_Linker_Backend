package com.ant.linker.data.dao;


import java.util.Date;
import java.util.List;

import com.ant.linker.data.entity.Guest;
import com.ant.linker.data.entity.User;

public interface IGuestDao {
	
	public Guest loadGuestByUser(User user);

	public Guest saveGuest(Guest guest);
	
	public void deleteGuest(Guest guest);
	
	public List<Guest> loadOutDatedGuests(Date date);

}
