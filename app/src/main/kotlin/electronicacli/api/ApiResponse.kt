package electronicacli.api
import electronicacli.utils.post
import electronicacli.utils.getServerUrl
import electronicacli.utils.get


class ApiResponse {
    private var token : String = "";

    fun login(username : String , password : String) : String {
        val json = "{\"username\":\"$username\",\"password\":\"$password\"}"

        try{
            val response = post("/login", json)
            token = response
            return "Login successful"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


}