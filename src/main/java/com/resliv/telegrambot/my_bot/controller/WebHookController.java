package com.resliv.telegrambot.my_bot.controller;

import com.resliv.telegrambot.my_bot.model.TelegramBot;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;


@RestController
public class WebHookController {
    private final TelegramBot telegramBot;

    public WebHookController(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @RequestMapping(value = "/botResliv", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }

}
