//package com.driver;
//
//import java.util.*;
//
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class WhatsappRepository {
//
//    //Assume that each user belongs to at most one group
//    //You can use the below mentioned hashmaps or delete these and create your own.
//    private HashMap<Group, List<User>> groupUserMap;
//    private HashMap<Group, List<Message>> groupMessageMap;
//    private HashMap<Message, User> senderMap;
//    private HashMap<Group, User> adminMap;
//    private HashMap<String, User> userMobile;
//    private int customGroupCount;
//    private int messageId;
//
//    public WhatsappRepository(){
//        this.groupMessageMap = new HashMap<Group, List<Message>>();
//        this.groupUserMap = new HashMap<Group, List<User>>();
//        this.senderMap = new HashMap<Message, User>();
//        this.adminMap = new HashMap<Group, User>();
//        this.userMobile = new HashMap<>();
//        this.customGroupCount = 0;
//        this.messageId = 0;
//    }
//
//    public boolean isPresentMobile(String mobile)
//    {
//        if(userMobile.containsKey(mobile))
//        {
//            return true;
//        }
//        return false;
//    }
//
//    public String createUser(String name, String mobile)
//    {
//        userMobile.put(mobile, new User(name, mobile));
//        return "SUCCESS";
//    }
//
//    public Group createGroup(List<User> users)
//    {
//        //count the size of the userlist;
//        int groupSize = users.size();
//
//        if(groupSize>2)
//            {
//                //Group size is greater is 2 --> so need to increase the cnt
//                this.customGroupCount++;
//
//                //first one in a list is admin
//                User admin = users.get(0);
//
//                //Create a name for a Group
//                String name = "Group "+this.customGroupCount;
//
//                //create a group
//                Group group = new Group(name, groupSize);
//
//                adminMap.put(group, admin);
//
//                groupUserMap.put(group, users);
//
//                return group;
//            }
//
//            //first one in a list is admin
//            User admin = users.get(0);
//
//            //Create a name for a Group
//            String name = users.get(1).getName();
//
//            //create a group
//            Group group = new Group(name, groupSize);
//
//            adminMap.put(group, admin);
//
//            groupUserMap.put(group, users);
//
//            return group;
//
//    }
//
//    public int createMessage(String msg)
//    {
//        messageId++;
//        Message message = new Message(messageId, msg);
//        return this.messageId;
//    }
//
//    public String changeAdmin(User approver, User user, Group group) throws Exception{
//        //Group does not exist
//        if(!groupUserMap.containsKey(group))
//        {
//            throw new Exception("Group does not exist");
//        }
//
//        //Approver is not a admin
//        if(isAdmin(approver, group)==false)
//        {
//            throw new Exception("Approver does not have rights");
//        }
//
//        //check user in the group
//        if(isUser(user, group)==false)
//        {
//            throw new Exception("User is not a participant" );
//        }
//
//        //change the admin
//
//        adminMap.put(group, user);
//        return "SUCCESS";
//
//    }
//
//    public int sendMessage(Message message, User sender, Group group) throws Exception{
//        //check group is exist or not
//        if(!groupUserMap.containsKey(group))
//        {
//            throw new Exception("Group does not exist");
//        }
//
//        //check sender is a part of group or not
//        if(isUser(sender, group)==false)
//        {
//            throw new Exception("You are not allowed to send message");
//        }
//
//        //send the msg
//        List<Message> msgList = new ArrayList<>();
//        if(groupMessageMap.containsKey(group))
//        {
//            msgList = groupMessageMap.get(group);
//        }
//
//        msgList.add(message);
//        int noOfMsg = msgList.size();
//        groupMessageMap.put(group, msgList);
////        if(groupMessageMap.containsKey(group))
////        {
////            List<Message> msgList = groupMessageMap.get(group);
////            msgList.add(message);
////            int noOfMsg = msgList.size();
////            groupMessageMap.put(group, msgList);
////            return noOfMsg;
////        }
////
////        List<Message> msgList = new ArrayList<>();
////        msgList.add(message);
////        int noOfMsg = msgList.size();
////        groupMessageMap.put(group, msgList);
//        return noOfMsg;
//    }
//
//    public boolean isUser(User user, Group group)
//    {
//        List<User> userList = groupUserMap.get(group);
//        for(User u : userList)
//        {
//            if(u.equals(user))
//            {
//                return true;
//            }
//        }
//        return false;
//    }
//    public boolean isAdmin(User admin, Group group)
//    {
//        User currentAdmin = adminMap.get(group);
//        if(currentAdmin.equals(admin))
//        {
//            return true;
//        }
//        return false;
//    }
//
//}


package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<User, List<Message>> senderMap;
    private HashMap<Group, User> adminMap;
    // private HashSet<String> userMobile;
    private HashMap<String,User> userData;
    private int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<User,List<Message>>();
        this.adminMap = new HashMap<Group, User>();
        //this.userMobile = new HashSet<>();
        this.userData= new HashMap<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }
    public boolean isPresentMobile(String mobile){
        if(userData.containsKey(mobile)) return false;
        return true;
    }
    public String createUser(String name, String mobile) {
        userData.put(mobile,new User(name,mobile));
        return "SUCCESS";
    }

    public Group createGroup(List<User> users){
        if(users.size()==2)
            return this.createPersonalChat(users);

        this.customGroupCount++;
        String groupName="Group "+this.customGroupCount;
        Group group= new Group(groupName,users.size());
        groupUserMap.put(group,users);
        adminMap.put(group,users.get(0));
        return group;
    }

    public Group createPersonalChat(List<User> users){
        String groupName=users.get(1).getName();
        Group personalGroup= new Group(groupName,2);
        groupUserMap.put(personalGroup,users);
        return personalGroup;
    }
    public int createMessage(String content){
        this.messageId++;
        Message message= new Message(messageId,content);
        return this.messageId;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception{
        if(!groupUserMap.containsKey(group)){
            throw new Exception("Group does not exist");
        }
        if(!this.userExistsInGroup(group,sender)){
            throw new Exception("You are not allowed to send message");
        }
        List<Message> messages= new ArrayList<>();
        if(groupMessageMap.containsKey(group))
            messages=groupMessageMap.get(group);

        messages.add(message);
        groupMessageMap.put(group,messages);
        //mapping user and their send messages
        List<Message> messageList= new ArrayList<>();
        if(senderMap.containsKey(sender)){
            messageList=senderMap.get(sender);
            messageList.add(message);
        }
        senderMap.put(sender,messageList);

        return messages.size();
    }


    public String changeAdmin(User approver, User user, Group group) throws Exception{
        if(!groupUserMap.containsKey(group))
            throw new Exception("Group does not exist");
        if(!adminMap.get(group).equals(approver))
            throw new Exception("Approver does not have rights");
        if(!this.userExistsInGroup(group,user))
            throw new Exception("User is not a participant");

        adminMap.put(group,user);
        return "SUCCESS";
    }
    public boolean userExistsInGroup(Group group,User sender){
        List<User> users= groupUserMap.get(group);
        for(User user: users){
            if(user.equals(sender)) return true;
        }
        return false;
    }
}
