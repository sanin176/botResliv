package com.resliv.telegrambot.my_bot.model;

import com.resliv.telegrambot.my_bot.dao.CitiesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Data
public class TelegramBot extends TelegramWebhookBot {
    @Autowired
    private CitiesRepository citiesRepository;

    private String webHookPath;
    private String botUserName;
    private String botToken;


    public TelegramBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {

            try {
                String sendMessageReturn = handleInputMessage(update.getMessage());
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setParseMode(ParseMode.HTML);
                sendMessage.setText(sendMessageReturn);
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private String handleInputMessage(Message message) {
        String inputMsg = message.getText();
        String answer;
        switch (inputMsg) {
            case "/start":
                answer = "<i>Добро пожаловать! Это самый лучший бот!!!</i>" +
                        "\n\nВведите название города, и я смогу дать информацию о нём :)";
                break;
            default:
                if(citiesRepository.getDescriptionByCity(inputMsg).size() != 0) {
                    if(citiesRepository.getDescriptionByCity(inputMsg).get(0).getDescription() .length() != 0){
                        answer = "«<b><u>" + message.getText() + "</u></b>», «" +
                                citiesRepository.getDescriptionByCity(inputMsg).get(0).getDescription() + "»";
                    }
                    else
                        answer = "<i>Описание у города «" + message.getText() + "» отсутствует</i>";
                }
                else
                    answer = "<b>Такого города не существует!</b>";
                break;
        }
        return answer;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }
}
