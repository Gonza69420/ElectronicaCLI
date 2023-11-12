package electronicacli.api
import electronicacli.utils.get
import electronicacli.utils.post
import electronicacli.utils.put
import org.json.JSONObject


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

    fun refillMachine(machineId : Int , newQuantity : Int , productId : Int) : String {
        val json = "{\"newQuantity\":\"$newQuantity\",\"productId\":\"$productId\"}"

        try {
            val response =  put("/maintenance/refillMachine/${machineId}", json, token)
            val jsonResponse = JSONObject(response)

            // Check if the machine is found
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return ""
            }
            return jsonResponse.getString("message")

        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    fun workingInMachine(machineId: Int) : String {
        try {
            val response = put("/maintenance/workingInMachine/${machineId}", token)
            val jsonResponse = JSONObject(response)

            // Check if the machine is found
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return ""
            }
            return jsonResponse.getString("message")
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun machineIsReady(machineId: Int) : String {
        try {
            val response = put("/maintenance/machineReady/${machineId}", token)
            val jsonResponse = JSONObject(response)

            // Check if the machine is found
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return ""
            }
            return jsonResponse.getString("message")
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getMachineById(id: Int): String {
        try {
            val response = get("/maintenance/getMachine/$id", token)
            val jsonResponse = JSONObject(response)

            // Check if the machine is found
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return ""
            }

            // Extracting relevant information from the JSON response
            val machine = jsonResponse.getJSONObject("data").getJSONObject("machine")
            printMachine(machine)

            return "Machine details retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getAllMachines(): String {
        try {
            val response = get("/maintenance/getAllMachines", token)
            val jsonResponse = JSONObject(response)

            // Extracting relevant information from the JSON response
            val machinesArray = jsonResponse.getJSONObject("data").getJSONArray("machines")

            // Print the relevant information for each machine
            println("All Machines:")
            for (i in 0 until machinesArray.length()) {
                printMachine(machinesArray.getJSONObject(i))
            }

            return "All machines retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getProduct(id: Int): String {
        try {
            val response = get("/maintenance/getProduct/$id", token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return ""
            }

            // Extracting relevant information from the JSON response
            val product = jsonResponse.getJSONObject("data").getJSONObject("product")
            printProduct(product)

            return "Product retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    fun getAllProducts(): String {
        try {
            val response = get("/maintenance/getAllProducts", token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return ""
            }

            // Extracting relevant information from the JSON response
            val productsArray = jsonResponse.getJSONObject("data").getJSONArray("products")

            // Print the relevant information
            println("All Products:")
            for (i in 0 until productsArray.length()) {
                printProduct(productsArray.getJSONObject(i))
            }

            return "All products retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun eliminateProductMachine(machineId: Int, productId: Int): String {
        try {
            val response = put("/maintenance/eliminateProductFromMachine/${machineId}/${productId}", token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return ""
            }

            return jsonResponse.getString("message")
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    private fun printProduct(product: JSONObject) {
        val productId = product.getInt("customId")
        val productName = product.getString("name")
        val productPrice = product.getInt("price")

        println("Product ID: $productId")
        println("Product Name: $productName")
        println("Product Price: $productPrice")
        println("------------")
    }

    private fun printMachine(machine: JSONObject) {
        val customId = machine.getInt("customId")
        val works = machine.getBoolean("works")
        val beingRepaired = machine.getBoolean("beingRepaired")

        println("Machine ID: $customId")
        println("Works: $works")
        println("Being Repaired: $beingRepaired")

        // Print products for each machine
        val productsArray = machine.getJSONArray("products")
        println("Products:")
        println("------------")
        for (j in 0 until productsArray.length()) {
            val productJSON = productsArray.getJSONObject(j).getJSONObject("product")
            printProduct(productJSON)
        }

    }

}

