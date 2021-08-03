package com.ant.linker.module.discussion.service;


import java.util.List;

import com.ant.linker.data.entity.*;
import com.ant.linker.module.shared.dto.discussion.*;

import com.ant.linker.module.shared.dto.security.UserSignUpDto;

public interface IDiscussionService {
	public DiscussionDto createAddMessage(DiscussionDto discussionDto);
}
