package com.epam.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Entry already exists") //404
	public class EntryAlreadyExistsInDatabase extends Exception {

		private static final long serialVersionUID = 1L;

		public EntryAlreadyExistsInDatabase(){
			super("Entry Already Exists in database");
		}
	}

