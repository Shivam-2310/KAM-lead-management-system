package com.shivam.lead_management_system.controller;

import com.shivam.lead_management_system.entity.Contact;
import com.shivam.lead_management_system.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.addContact(contact);
    }
}
