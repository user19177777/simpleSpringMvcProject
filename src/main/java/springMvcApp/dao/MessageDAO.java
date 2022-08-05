package springMvcApp.dao;

import org.springframework.stereotype.Component;
import springMvcApp.models.Message;

import java.util.*;

@Component
public class MessageDAO {
    private List<Message>messages;
    private static int MESSAGE_COUNT;
    {
        messages = new ArrayList<>();
        messages.add(new Message(++MESSAGE_COUNT,"hoho"));
        messages.add(new Message(++MESSAGE_COUNT,"fefe"));
        messages.add(new Message(++MESSAGE_COUNT,"bebe"));
    }

    public List<Message>index(){
        return messages;
    }

    public Message show(final int id){
        return messages.stream().filter(message->message.getId()==id).findAny().orElse(null);
    }
    public void save(Message message){
        message.setId(++MESSAGE_COUNT);
        messages.add(message);
    }
    public void update(int id,Message message){
        Message message1=show(id);
        message1.setText(message.getText());
    }
    public void delete(int id){
        messages.removeIf(m->m.getId()==id);
    }
}
