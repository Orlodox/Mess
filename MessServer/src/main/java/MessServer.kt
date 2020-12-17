import spark.Filter
import spark.Service.ignite
import spark.Spark.before


fun main() {
    var actualInfo = "{\n" +
            "        \"key\": \"user0\", \"user\": {\n" +
            "            \"chats\": [{\n" +
            "                \"messages\": [{\n" +
            "                    \"content\": \"Привет!\",\n" +
            "                    \"from\": 1,\n" +
            "                    \"time\": \"Jan 16, 2021 7:02:08 PM\",\n" +
            "                    \"to\": 0\n" +
            "                }, {\n" +
            "                    \"content\": \"Вечер добрый\",\n" +
            "                    \"from\": 0,\n" +
            "                    \"time\": \"Jan 16, 2021 7:03:00 PM\",\n" +
            "                    \"to\": 1\n" +
            "                }, {\n" +
            "                    \"content\": \"Как дела?\",\n" +
            "                    \"from\": 1,\n" +
            "                    \"time\": \"Jan 16, 2021 7:03:52 PM\",\n" +
            "                    \"to\": 0\n" +
            "                }, {\n" +
            "                    \"content\": \"Отлично, как твои?\",\n" +
            "                    \"from\": 0,\n" +
            "                    \"time\": \"Jan 16, 2021 7:04:05 PM\",\n" +
            "                    \"to\": 1\n" +
            "                }, {\"content\": \"Вполне себе! Чем занят?\", \"from\": 1, \"time\": \"Jan 16, 2021 7:05:18 PM\", \"to\": 0}],\n" +
            "                \"with\": 1\n" +
            "            }, {\n" +
            "                \"messages\": [{\n" +
            "                    \"content\": \"Ты не видел мою кошку?\",\n" +
            "                    \"from\": 1,\n" +
            "                    \"time\": \"Jan 16, 2021 7:02:08 PM\",\n" +
            "                    \"to\": 0\n" +
            "                }, {\n" +
            "                    \"content\": \"Барсик сбежал??!!\",\n" +
            "                    \"from\": 0,\n" +
            "                    \"time\": \"Jan 16, 2021 7:03:00 PM\",\n" +
            "                    \"to\": 1\n" +
            "                }, {\n" +
            "                    \"content\": \"Да, со вчерашнего дня найти не можем :((\",\n" +
            "                    \"from\": 1,\n" +
            "                    \"time\": \"Jan 16, 2021 7:03:52 PM\",\n" +
            "                    \"to\": 0\n" +
            "                }, {\"content\": \"КОШМАР !!!\", \"from\": 0, \"time\": \"Jan 16, 2021 7:04:05 PM\", \"to\": 1}], \"with\": 2\n" +
            "            }, {\n" +
            "                \"messages\": [{\n" +
            "                    \"content\": \"Как думаешь, реально пройти собеседование в яндекс?\",\n" +
            "                    \"from\": 1,\n" +
            "                    \"time\": \"Jan 16, 2021 7:02:08 PM\",\n" +
            "                    \"to\": 0\n" +
            "                }, {\n" +
            "                    \"content\": \"Люди же как-то проходят – значит, реально. Надо просто постараться.\",\n" +
            "                    \"from\": 0,\n" +
            "                    \"time\": \"Jan 16, 2021 7:03:00 PM\",\n" +
            "                    \"to\": 1\n" +
            "                }, {\n" +
            "                    \"content\": \"За месяц бы подготовиться и попробовать\",\n" +
            "                    \"from\": 1,\n" +
            "                    \"time\": \"Jan 16, 2021 7:03:52 PM\",\n" +
            "                    \"to\": 0\n" +
            "                }, {\n" +
            "                    \"content\": \"А на кого хочешь?\",\n" +
            "                    \"from\": 0,\n" +
            "                    \"time\": \"Jan 16, 2021 7:04:05 PM\",\n" +
            "                    \"to\": 1\n" +
            "                }, {\n" +
            "                    \"content\": \"На ондроед разработчика\",\n" +
            "                    \"from\": 1,\n" +
            "                    \"time\": \"Jan 16, 2021 7:05:52 PM\",\n" +
            "                    \"to\": 0\n" +
            "                }, {\n" +
            "                    \"content\": \"Ого! Слышал, им много платят!\",\n" +
            "                    \"from\": 0,\n" +
            "                    \"time\": \"Jan 16, 2021 7:06:05 PM\",\n" +
            "                    \"to\": 1\n" +
            "                }, {\n" +
            "                    \"content\": \"Да, даже больше, чем бэкендерам. А ещё и в яндексе...\",\n" +
            "                    \"from\": 1,\n" +
            "                    \"time\": \"Jan 16, 2021 7:07:52 PM\",\n" +
            "                    \"to\": 0\n" +
            "                }, {\n" +
            "                    \"content\": \"Определённо речь идёт про трёхзначные суммы :)))\",\n" +
            "                    \"from\": 0,\n" +
            "                    \"time\": \"Jan 16, 2021 7:06:05 PM\",\n" +
            "                    \"to\": 1\n" +
            "                }, {\n" +
            "                    \"content\": \"Хочется в это верить! Но сперва надо поднатаскаться..\",\n" +
            "                    \"from\": 1,\n" +
            "                    \"time\": \"Jan 16, 2021 7:07:52 PM\",\n" +
            "                    \"to\": 0\n" +
            "                }], \"with\": 3\n" +
            "            }],\n" +
            "            \"friends\": [{\"chats\": [], \"friends\": [], \"name\": \"Иван\", \"userID\": 1}, {\n" +
            "                \"chats\": [],\n" +
            "                \"friends\": [{\"chats\": [], \"friends\": [], \"name\": \"Иван\", \"userID\": 1}],\n" +
            "                \"name\": \"Петр\",\n" +
            "                \"userID\": 2\n" +
            "            }, {\n" +
            "                \"chats\": [],\n" +
            "                \"friends\": [{\"chats\": [], \"friends\": [], \"name\": \"Иван\", \"userID\": 1}, {\n" +
            "                    \"chats\": [],\n" +
            "                    \"friends\": [{\"chats\": [], \"friends\": [], \"name\": \"Иван\", \"userID\": 1}],\n" +
            "                    \"name\": \"Петр\",\n" +
            "                    \"userID\": 2\n" +
            "                }],\n" +
            "                \"name\": \"Андрей\",\n" +
            "                \"userID\": 3\n" +
            "            }],\n" +
            "            \"name\": \"Я\",\n" +
            "            \"userID\": 0\n" +
            "        }\n" +
            "    }"
    var allowedKeys = arrayListOf<String>()
    val port = 1337
    val httpService = ignite()
    httpService.port(port)
    httpService.threadPool(30)
    httpService.options("/*") { request, response ->
        val accessControlRequestHeaders = request.headers("Access-Control-Request-Headers")
        if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders)
        }
        val accessControlRequestMethod = request.headers("Access-Control-Request-Method")
        if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods", accessControlRequestMethod)
        }
        "OK"
    }
    httpService.before(Filter { request, response ->
        response.header("Access-Control-Allow-Origin", "null")
        response.header("Access-Control-Request-Method", "*")
        response.header("Access-Control-Allow-Headers", "*")
        response.status(200)
    })

    httpService.post("/mess") { request, response ->
        response.type("application/json")
        println("POST HEADERS: ${request.headers("key")}")
        println("POST BODY: ${request.body()}")
        if (request.body().isNotEmpty())
            actualInfo = request.body()
        response.body(actualInfo)
        if (request.headers("key") != null) {
            if (allowedKeys.contains(request.headers("key")))
                return@post response.body()
            else
                return@post ""
        }
        println("POST RESPONSE: ${response.body()}")
        response.body()
    }

    httpService.put("/mess") { request, response ->
        println("PUT : ${request.body()}")
        if (request.body().isEmpty())
            allowedKeys.clear()
        else
            allowedKeys.add(request.body())
    }
}

fun parseKey(str: String): String {
    var key = str.substring(9)
    key = str.substring(0, str.indexOf('"'))
    return key
}

class MessServer {
    companion object {
        var actualInfo = ""
        var instance = MessServer()

        fun enableCORS(
            origin: String,
            methods: String,
            headers: String
        ) {
            before(Filter { request, response ->
                response.header("Access-Control-Allow-Origin", origin)
                response.header("Access-Control-Request-Method", methods)
                response.header("Access-Control-Allow-Headers", headers)
            })
        }
    }

    init {
        val port = 1337
        val httpService = ignite()
        httpService.port(port)
        httpService.threadPool(30)
        httpService.internalServerError("Error : 500 internal error")


//        httpService.post("/mess", MyHttpRoute())
    }
}