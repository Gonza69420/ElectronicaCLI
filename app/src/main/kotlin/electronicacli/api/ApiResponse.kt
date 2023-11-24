package electronicacli.api
import electronicacli.utils.*
import org.json.JSONObject



class ApiResponse {
    private var token : String = "";

    fun login(username : String , password : String) : String {
        val role = "Admin"
        val json = "{\"username\":\"$username\",\"password\":\"$password\",\"role\":\"$role\"}"

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
            val jsonResponse = JSONObject(response)

            // Extracting relevant information from the JSON response
            val machineId = jsonResponse.getJSONObject("machine").getInt("customId")
            val works = jsonResponse.getJSONObject("machine").getBoolean("works")
            val beingRepaired = jsonResponse.getJSONObject("machine").getBoolean("beingRepaired")
            val income = jsonResponse.getJSONObject("machine").getDouble("income")

            // Print the relevant information
            println("Machine added successfully:")
            println("Machine ID: $machineId")
            println("Works: $works")
            println("Being Repaired: $beingRepaired")
            println("Income: $income")

            return "Machine added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun deleteMachine(id : Int) : String {
        try{
            delete("/admin/deleteMachine/$id", token)
            return "Machine deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getIncomeInMachine(id: Int): String {
        try {
            val response = get("/admin/getIncome/$id", token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return "Error getting income"
            }

            // Extracting relevant information from the JSON response
            val income = jsonResponse.getDouble("data")

            // Print the relevant information
            println("Income: $income")

            return "Income retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    fun getAllMachines(): String {
        try {
            val response = get("/admin/getAllMachines", token)
            val jsonResponse = JSONObject(response)

            // Extracting relevant information from the JSON response
            val machinesArray = jsonResponse.getJSONObject("data").getJSONArray("machines")

            // Print the relevant information for each machine
            println("All Machines:")
            for (i in 0 until machinesArray.length()) {
                val machine = machinesArray.getJSONObject(i)
                val customId = machine.getInt("customId")
                val works = machine.getBoolean("works")
                val beingRepaired = machine.getBoolean("beingRepaired")
                val income = machine.getDouble("income")

                println("Machine ID: $customId")
                println("Works: $works")
                println("Being Repaired: $beingRepaired")
                println("Income: $income")

                // Print products for each machine
                val productsArray = machine.getJSONArray("products")
                println("Products:")
                for (j in 0 until productsArray.length()) {
                    val product = productsArray.getJSONObject(j)
                    val productName = product.getJSONObject("product").getString("name")
                    val productQuantity = product.getInt("quantity")
                    val productId = product.getInt("customId")

                    println("  Product ID: $productId")
                    println("  Product Name: $productName")
                    println("  Quantity: $productQuantity")
                    println("  ------------")
                }

                println("------------")
            }

            return "All machines retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }



    fun addMaintenanceStaff(username: String, password: String, name: String): String {
        val json = "{\"username\":\"$username\",\"password\":\"$password\",\"name\":\"$name\"}"

        try {
            val response = post("/admin/addMaintenanceStaff", json, token)
            val jsonResponse = JSONObject(response)

            // Extracting relevant information from the JSON response
            val user = jsonResponse.getJSONObject("user")
            val customId = user.getInt("customId")
            val createdUsername = user.getString("username")
            val createdName = user.getString("name")

            // Print the relevant information
            println("Maintenance staff added successfully:")
            println("User ID: $customId")
            println("Username: $createdUsername")
            println("Name: $createdName")

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

    fun getMachineById(id: Int): String {
        try {
            val response = get("/admin/getMachine/$id", token)
            val jsonResponse = JSONObject(response)

            // Check if the machine is found
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return "Machine not found"
            }

            // Extracting relevant information from the JSON response
            val machine = jsonResponse.getJSONObject("data").getJSONObject("machine")
            val customId = machine.getInt("customId")
            val works = machine.getBoolean("works")
            val beingRepaired = machine.getBoolean("beingRepaired")
            val income = machine.getDouble("income")

            // Print the relevant information
            println("Machine details:")
            println("Machine ID: $customId")
            println("Works: $works")
            println("Being Repaired: $beingRepaired")
            println("Income: $income")

            // Print products for the machine
            val productsArray = machine.getJSONArray("products")
            println("Products:")
            for (i in 0 until productsArray.length()) {
                val product = productsArray.getJSONObject(i)
                val productName = product.getJSONObject("product").getString("name")
                val productQuantity = product.getInt("quantity")
                val productId = product.getInt("customId")

                println("  Product ID: $productId")
                println("  Product Name: $productName")
                println("  Quantity: $productQuantity")
                println("  ------------")
            }

            return "Machine details retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    fun getTotalIncome(): String {
        try {
            val response = get("/admin/getTotalIncome", token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return "Error getting total income"
            }

            // Extracting relevant information from the JSON response
            val totalIncome = jsonResponse.getJSONObject("data").getDouble("totalIncome")

            // Print the relevant information
            println("Total Income: $totalIncome")

            return "Total income retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    fun adjustPrice(id: Int, newPrice: Int): String {
        val json = "{\"newPrice\":\"$newPrice\"}"

        try {
            val response = put("/admin/adjustPrice/$id", json, token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return "Error adjusting product price"
            }

            // Print the relevant information
            println("Product price adjusted successfully")

            return "Product price adjusted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    fun addProduct(name: String, price: Int): String {
        val json = "{\"name\":\"$name\",\"price\":\"$price\"}"

        try {
            val response = post("/admin/addProduct", json, token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return "Error adding product"
            }

            // Extracting relevant information from the JSON response
            val addedProduct = jsonResponse.getJSONObject("data")
            val productName = addedProduct.getString("name")
            val productPrice = addedProduct.getInt("price")
            val customId = addedProduct.getInt("customId")


            // Print the relevant information
            println("Product added successfully:")
            println("Product ID: $customId")
            println("Product Name: $productName")
            println("Product Price: $productPrice")

            return "Product added"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    fun deleteProduct(id: Int): String {
        try {
            val response = delete("/admin/deleteProduct/$id", token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return "Error deleting product"
            }

            // Print the relevant information
            println("Product deleted successfully")

            return "Product deleted"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


    fun getProduct(id: Int): String {
        try {
            val response = get("/admin/getProducts/$id", token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return "Error getting product"
            }

            // Extracting relevant information from the JSON response
            val product = jsonResponse.getJSONObject("data").getJSONObject("product")
            val productName = product.getString("name")
            val productPrice = product.getInt("price")
            val customId = product.getInt("customId")

            // Print the relevant information
            println("Product details:")
            println("Product ID: $customId")
            println("Product Name: $productName")
            println("Product Price: $productPrice")

            return "Product retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }



    fun getAllProducts(): String {
        try {
            val response = get("/admin/getAllProducts", token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return "Error getting all products"
            }

            // Extracting relevant information from the JSON response
            val productsArray = jsonResponse.getJSONObject("data").getJSONArray("products")

            // Print the relevant information
            println("All Products:")
            for (i in 0 until productsArray.length()) {
                val product = productsArray.getJSONObject(i)
                val productId = product.getInt("customId")
                val productName = product.getString("name")
                val productPrice = product.getInt("price")

                println("Product ID: $productId")
                println("Product Name: $productName")
                println("Product Price: $productPrice")
                println("------------")
            }

            return "All products retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getMaintenanceStaff(id: Int): String {
        try {
            val response = get("/admin/getMaintenanceStaff/$id", token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return "Error getting maintenance staff"
            }

            // Extracting relevant information from the JSON response
            val user = jsonResponse.getJSONObject("data").getJSONArray("user").getJSONObject(0)
            val userId = user.getInt("customId")
            val userName = user.getString("name")
            val userUsername = user.getString("username")

            // Print the relevant information
            println("Maintenance Staff details:")
            println("User ID: $userId")
            println("User Name: $userName")
            println("Username: $userUsername")

            return "Maintenance staff retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun getAllMaintenanceStaff(): String {
        try {
            val response = get("/admin/allMaintenanceStaff", token)
            val jsonResponse = JSONObject(response)

            // Check if the request was successful
            if (jsonResponse.has("error")) {
                // Print error message
                val errorMessage = jsonResponse.getString("error")
                println("Error: $errorMessage")
                return "Error getting all maintenance staff"
            }

            // Extracting relevant information from the JSON response
            val usersArray = jsonResponse.getJSONObject("data").getJSONArray("users")

            // Print the relevant information
            println("All Maintenance Staff:")
            for (i in 0 until usersArray.length()) {
                val user = usersArray.getJSONObject(i)
                val userId = user.getInt("customId")
                val userName = user.getString("name")
                val userUsername = user.getString("username")

                println("User ID: $userId")
                println("User Name: $userName")
                println("Username: $userUsername")
                println("------------")
            }

            return "All maintenance staff retrieved"
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

}