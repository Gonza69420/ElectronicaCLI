package electronicacli.api
import electronicacli.utils.post
import electronicacli.utils.get
import electronicacli.utils.delete


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

    fun addMachine () : String {
        try{
            val response = post("/machine", "{}", token)
            return "Machine added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun deleteMachine(id : String) : String {
        try{
            val response = delete("/machine/$id", token)
            return "Machine deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getIncomeInMachine (id : String) : String {
        try {
            return get("/machine/$id/income", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getAllMachines() : String {
        try{
            return get("/getAllMachines", token)
        } catch (e : Exception) {
            throw Exception(e.message)
        }
    }

    fun addMaintenanceStaff( username : String , password : String , name : String) : String {
        val json = "{\"username\":\"$username\",\"password\":\"$password\",\"name\":\"$name\"}"

        try{
            val response = post("/mantainenceStaff", json, token)
            return "Mantainence staff added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun deleteMaintenanceStaff (id : String) : String {
        try{
            val response = delete("/mantainenceStaff/$id", token)
            return "Mantainence staff deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getMachineById (id : String) : String {
        try{
            return get("/machine/$id", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getTotalIncome () : String {
        try{
            return get("/totalIncome", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun adjustPrice(id : String , newPrice : String ) : String {
        val json = "{\"newPrice\":\"$newPrice\"}"

        try{
            val response = post("/machine/$id/adjustPrice", json, token)
            return "Price adjusted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun addProduct(name : String , price : String) : String {
        val json = "{\"name\":\"$name\",\"price\":\"$price\"}"

        try{
            val response = post("/product", json, token)
            return "Product added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun deleteProduct(id : String) : String {
        try{
            val response = delete("/product/$id", token)
            return "Product deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}