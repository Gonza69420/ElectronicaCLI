package electronicacli.api
import electronicacli.utils.*
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class ApiResponse {
    private var token : String = "";

    fun login(username : String , password : String) : String {
        val json = "{\"username\":\"$username\",\"password\":\"$password\"}"

        try{
            val response = post("/user/login", json)
            token = response.split(":")[1].split("\"")[1]
            return "Login successful"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun addMachine () : String {
        try{
            val response = post("/admin/addMachine", "{}", token)
            println(response)
            return "Machine added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun deleteMachine(id : Int) : String {
        try{
            val response = delete("/admin/deleteMachine/$id", token)
            return "Machine deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getIncomeInMachine (id : Int) : String {
        try {
            return get("/admin/getIncome/${id}", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getAllMachines() : String {
        try{
            return get("/admin/getAllMachines", token)
        } catch (e : Exception) {
            throw Exception(e.message)
        }
    }

    fun addMaintenanceStaff( username : String , password : String , name : String) : String {
        val json = "{\"username\":\"$username\",\"password\":\"$password\",\"name\":\"$name\"}"

        try{
            val response = post("/admin/addMaintenanceStaff", json, token)
            return "Maintenance staff added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun deleteMaintenanceStaff (id : Int) : String {
        try{
            val response = delete("/admin/deleteMaintenanceStaff/$id", token)
            return "Maintenance staff deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getMachineById (id : Int) : String {
        try{
            return get("/admin/getMachine/$id", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getTotalIncome () : String {
        try{
            return get("/admin/getTotalIncome", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun adjustPrice(id : Int , newPrice : Int ) : String {
        val json = "{\"newPrice\":\"$newPrice\"}"

        try{
            val response = put("/admin/adjustPrice/${id}", json, token)
            return "Price adjusted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun addProduct(name : String , price : Int) : String {
        val json = "{\"name\":\"$name\",\"price\":\"$price\"}"

        try{
            val response = post("/admin/addProduct", json, token)
            return "Product added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun deleteProduct(id : Int) : String {
        try{
            val response = delete("/admin/deleteProduct/$id", token)
            return "Product deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getProduct(id : Int) : String {
        try{
            return get("/admin/getProducts/$id", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getAllProducts() : String {
        try{
            return get("/admin/getAllProducts", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getMaintenanceStaff(id : Int) : String {
        try{
            return get("/admin/getMaintenanceStaff/$id", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getAllMaintenanceStaff() : String {
        try{
            return get("/admin/allMaintenanceStaff", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}