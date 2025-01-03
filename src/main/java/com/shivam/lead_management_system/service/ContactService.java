package com.shivam.lead_management_system.service;

import com.shivam.lead_management_system.entity.Contact;
import com.shivam.lead_management_system.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }
}
