package electronicacli.api
import electronicacli.utils.post
import electronicacli.utils.get
import electronicacli.utils.delete
import electronicacli.utils.put


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
            val response = post("/addMachine", "{}", token)
            return "Machine added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun deleteMachine(id : Int) : String {
        try{
            val response = delete("/deleteMachine/$id", token)
            return "Machine deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getIncomeInMachine (id : Int) : String {
        try {
            return get("/getIncome/${id}", token)
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
            val response = post("/addMaintenanceStaff", json, token)
            return "Maintenance staff added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun deleteMaintenanceStaff (id : Int) : String {
        try{
            val response = delete("/deleteMaintenanceStaff/$id", token)
            return "Maintenance staff deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getMachineById (id : Int) : String {
        try{
            return get("/getMachine/$id", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getTotalIncome () : String {
        try{
            return get("/getTotalIncome", token)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun adjustPrice(id : Int , newPrice : Int ) : String {
        val json = "{\"newPrice\":\"$newPrice\"}"

        try{
            val response = put("/adjustPrice/${id}", json, token)
            return "Price adjusted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun addProduct(name : String , price : Int) : String {
        val json = "{\"name\":\"$name\",\"price\":\"$price\"}"

        try{
            val response = post("/addProduct", json, token)
            return "Product added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun deleteProduct(id : Int) : String {
        try{
            val response = delete("/deleteProduct/$id", token)
            return "Product deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}