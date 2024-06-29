# InventoryStock-Management-Microservice

1. Inventory service has their own schema which would acting as master. And here I've implemented the CRUD Operations by using JPA which implements hibernate the default one. It will run on the port localhost:8080
2. Where in Stock service we will add the stocks and sell the stocks based on stock present on Stocks schema and Inventory schema. This service will run on the port 8081

**Steps to Test/Run the Inventory Service**

1. Run the Inventory Service and add the stocks by using the REST API : http://localhost:8080/api/product/newProduct
2. To see all the stocks present inside the inventory use the REST API : http://localhost:8080/api/product/getProducts
3. To get the stocks present inside the inventory by NAME use the REST API : http://localhost:8080/api/product/getProductByName/bike
4. To see the stocks present inside the inventory by CATEGORY use the REST API :http://localhost:8080/api/product/getProductByCategory/Automobile
5. To retrieve the stocks present inside the inventory by CATEGORY and NAME use the REST API http://localhost:8080/api/product/getProductsByNameAndCategory?category=Automobile&name=car
6. To update the stocks inside the schema use the API : http://localhost:8080/api/product/updateProductName
7. To delete the stocks use the REST API : http://localhost:8080/api/product/delete/twowheeler

**Steps to Test/Run the Stock Management Service**

1. Run the Stock Service and add the stocks by using the REST API : [http://localhost:8080/api/product/newProduct]
2. To add the stocks use the API : http://localhost:8081/api/stocks/addStocks
3. To sell the stocks run the API : http://localhost:8081/api/stocks/sellStocks
4. To see no.of stocks added and sold use the REST API : http://localhost:8081/api/stocks/getAllStocks
5. To see the records of particular product from Stock Service on Inventory service use the API : http://localhost:8081/api/stocks/getStocksAndInventory/bike