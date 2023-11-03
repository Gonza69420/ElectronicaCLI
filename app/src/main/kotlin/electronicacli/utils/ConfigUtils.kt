package electronicacli.utils
import com.typesafe.config.ConfigFactory

fun getServerUrl() : String {
    val config = ConfigFactory.load("config/application.conf")

    return config.getString("server.url")
}