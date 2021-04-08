import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {

    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

        String text = update.getMessage().getText();
        sendMessage.setReplyMarkup(keyboardMarkup);
        getMessage(text);

        if(update.getMessage().getText().equals("Привет") || update.getMessage().getText().equals("привет")){
            sendMessage.setText("Здравстуй! Это бот-гид по Cолнечной Cистеме! Я постараюсь рассказать тебе о том, что существует в пределах нашей системы!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        } else if(update.getMessage().getText().equals("Планеты \uD83C\uDF0F") || update.getMessage().getText().equals("Спутники \uD83C\uDF0F") || update.getMessage().getText().equals("Полезная информация \uD83C\uDF0F") || update.getMessage().getText().equals("/start")){
            sendMessage.setText("Есть!");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
        else{
            sendMessage.setText("Извини, я тебя не понимаю :(");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        }


//        String message = update.getMessage().getText();
//        sendMsg(update.getMessage().getChatId().toString(), message);
    }

//    public synchronized void sendMsg(String chatId, String s) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.enableMarkdown(true);
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(s);
//        try {
//            sendMessage(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

    public String getMessage(String msg){
        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        if(msg.equals("Привет") || msg.equals("привет") || msg.equals("Меню") || msg.equals("меню")){
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardFirstRow.add("Планеты \uD83C\uDF0F");
            keyboardFirstRow.add("Спутники \uD83C\uDF15");
            keyboardFirstRow.add("Полезная информация \uD83D\uDD0E");
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            keyboardMarkup.setKeyboard(keyboard);

            return "Выбрать...";
        }

        if(msg.equals("Полезная информация \uD83D\uDD0E")){
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardFirstRow.add("Информация о планете \uD83C\uDF0F");
            keyboardFirstRow.add("Информация о спутнике \uD83C\uDF15");
            keyboardFirstRow.add("Меню");

            if(msg.equals("Назад ⬅️")) {
                keyBack();

                return "Выбрать...";
            }

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            keyboardMarkup.setKeyboard(keyboard);

            return "Выбрать....";
        }

        if(msg.equals("Планеты \uD83C\uDF0F")){
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardFirstRow.add("Список планет");
            keyboardFirstRow.add("Назад ⬅️");

            if(msg.equals("Назад ⬅️")) {
                keyBack();

                return "Выбрать...";
            }

            keyboard.add(keyboardFirstRow);
            keyboardMarkup.setKeyboard(keyboard);

            return "Выбрать...";
        }

        if(msg.equals("Спутники \uD83C\uDF15")){
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardFirstRow.add("Список спутников");
            keyboardFirstRow.add("Назад ⬅️");

            if(msg.equals("Назад ⬅️")) {
                keyBack();

                return "Выбрать...";
            }

            keyboard.add(keyboardFirstRow);
            keyboardMarkup.setKeyboard(keyboard);

            return "Выбрать...";
        }
        return msg;
    }

    public void keyBack(){
        String msg = "";
        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        if(msg.equals("Привет") || msg.equals("привет") || msg.equals("Меню") || msg.equals("меню")){
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardFirstRow.add("Планеты \uD83C\uDF0F");
            keyboardFirstRow.add("Спутники \uD83C\uDF15");
            keyboardFirstRow.add("Полезная информация \uD83D\uDD0E");
            keyboard.add(keyboardFirstRow);
            keyboardMarkup.setKeyboard(keyboard);
        }
    }

    @Override
    public String getBotUsername() {
        return "solar_system_BOT";
    }

    @Override
    public String getBotToken() {
        return "1702184934:AAE0NsCeI7vd3aYAPwgsRbI4xuaKkmwKZ8U";
    }
}
