package electronicacli.api
import electronicacli.utils.post
import electronicacli.utils.put
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

    fun refillMachine(machineId : Int , newQuantity : Int , productId : Int) : String {
        val json = "{\"newQuantity\":\"$newQuantity\",\"productId\":\"$productId\"}"

        try {
            return put("/refillMachine/${machineId}", json, token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    fun workingInMachine(machineId: Int) : String {
        try {
            return put("/workingInMachine/${machineId}", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun machineIsReady(machineId: Int) : String {
        try {
            return put("/machineReady/${machineId}", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getMachineById(idInt: Int): Any {
        try {
            return get("/machine/${idInt}", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getAllMachines(): Any {
        try {
            return get("/machines", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getProductById(idInt: Int): Any {
        try {
            return get("/product/${idInt}", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getAllProducts(): Any {
        try {
            return get("/products", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}

