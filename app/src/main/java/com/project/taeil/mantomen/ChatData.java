package com.project.taeil.mantomen;

public class ChatData {  // 채팅데이터
    private String ChatuserID;
    private String ChatContents;

    public ChatData(){}

    public ChatData(String chatuserID, String chatContents) {
        ChatuserID = chatuserID;
        ChatContents = chatContents;
    }

    public String getChatuserID() {
        return ChatuserID;
    }

    public void setChatuserID(String chatuserID) {
        ChatuserID = chatuserID;
    }

    public String getChatContents() {
        return ChatContents;
    }

    public void setChatContents(String chatContents) {
        ChatContents = chatContents;
    }
}
