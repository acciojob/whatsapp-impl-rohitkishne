package com.driver;

import java.util.List;

public class WhatsappService {

    WhatsappRepository whatsappDB = new WhatsappRepository();

    public boolean isPresentMobile(String mobile)
    {
        return whatsappDB.isPresentMobile(mobile);
    }

    public String createUser(String name, String mobile)
    {
        return whatsappDB.createUser(name, mobile);
    }

    public Group createGroup(List<User> users)
    {
        return whatsappDB.createGroup(users);
    }

    public int createMessage(String msg)
    {
        return whatsappDB.createMessage(msg);
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception {
        return whatsappDB.changeAdmin(approver, user, group);
    }

    public int sendMessage(Message message, User sender, Group group)
    {
        return sendMessage(message, sender, group);
    }
}
