import spark.Request
import spark.Response
import spark.Route

class MyHttpRoute : Route {
    companion object {
        var actualInfo = "{\"key\":\"user0\",\"user\":{\"chats\":[{\"messages\":[{\"content\":\"Привет!\",\"from\":1,\"time\":\"Jan 16, 2021 7:02:08 PM\",\"to\":0},{\"content\":\"Вечер добрый\",\"from\":0,\"time\":\"Jan 16, 2021 7:03:00 PM\",\"to\":1},{\"content\":\"Как дела?\",\"from\":1,\"time\":\"Jan 16, 2021 7:03:52 PM\",\"to\":0},{\"content\":\"Отлично, как твои?\",\"from\":0,\"time\":\"Jan 16, 2021 7:04:05 PM\",\"to\":1},{\"content\":\"Вполне себе! Чем занят?\",\"from\":1,\"time\":\"Jan 16, 2021 7:05:18 PM\",\"to\":0}],\"with\":1},{\"messages\":[{\"content\":\"Ты не видел мою кошку?\",\"from\":1,\"time\":\"Jan 16, 2021 7:02:08 PM\",\"to\":0},{\"content\":\"Барсик сбежал??!!\",\"from\":0,\"time\":\"Jan 16, 2021 7:03:00 PM\",\"to\":1},{\"content\":\"Да, со вчерашнего дня найти не можем :((\",\"from\":1,\"time\":\"Jan 16, 2021 7:03:52 PM\",\"to\":0},{\"content\":\"КОШМАР !!!\",\"from\":0,\"time\":\"Jan 16, 2021 7:04:05 PM\",\"to\":1}],\"with\":2},{\"messages\":[{\"content\":\"Как думаешь, реально пройти собеседование в яндекс?\",\"from\":1,\"time\":\"Jan 16, 2021 7:02:08 PM\",\"to\":0},{\"content\":\"Люди же как-то проходят – значит, реально. Надо просто постараться.\",\"from\":0,\"time\":\"Jan 16, 2021 7:03:00 PM\",\"to\":1},{\"content\":\"За месяц бы подготовиться и попробовать\",\"from\":1,\"time\":\"Jan 16, 2021 7:03:52 PM\",\"to\":0},{\"content\":\"А на кого хочешь?\",\"from\":0,\"time\":\"Jan 16, 2021 7:04:05 PM\",\"to\":1},{\"content\":\"На ондроед разработчика\",\"from\":1,\"time\":\"Jan 16, 2021 7:05:52 PM\",\"to\":0},{\"content\":\"Ого! Слышал, им много платят!\",\"from\":0,\"time\":\"Jan 16, 2021 7:06:05 PM\",\"to\":1},{\"content\":\"Да, даже больше, чем бэкендерам. А ещё и в яндексе...\",\"from\":1,\"time\":\"Jan 16, 2021 7:07:52 PM\",\"to\":0},{\"content\":\"Определённо речь идёт про трёхзначные суммы :)))\",\"from\":0,\"time\":\"Jan 16, 2021 7:06:05 PM\",\"to\":1},{\"content\":\"Хочется в это верить! Но сперва надо поднатаскаться..\",\"from\":1,\"time\":\"Jan 16, 2021 7:07:52 PM\",\"to\":0}],\"with\":3}],\"friends\":[{\"chats\":[],\"friends\":[],\"name\":\"Иван\",\"userID\":1},{\"chats\":[],\"friends\":[{\"chats\":[],\"friends\":[],\"name\":\"Иван\",\"userID\":1}],\"name\":\"Петр\",\"userID\":2},{\"chats\":[],\"friends\":[{\"chats\":[],\"friends\":[],\"name\":\"Иван\",\"userID\":1},{\"chats\":[],\"friends\":[{\"chats\":[],\"friends\":[],\"name\":\"Иван\",\"userID\":1}],\"name\":\"Петр\",\"userID\":2}],\"name\":\"Андрей\",\"userID\":3}],\"name\":\"Я\",\"userID\":0}}"
    }

    override fun handle(request: Request, response: Response): String {
        if (request.body().isNotEmpty())
            actualInfo = request.body()
        response.body(actualInfo)
        response.header("Access-Control-Allow-Origin", "*")
        return response.body()
    }
}
