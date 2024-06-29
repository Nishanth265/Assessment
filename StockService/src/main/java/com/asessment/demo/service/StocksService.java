package com.asessment.demo.service;

import com.asessment.demo.dto.AddStocksDTO;
import com.asessment.demo.dto.SellDTO;
import com.asessment.demo.dto.StockDTO;
import com.asessment.demo.exception.StockNotFoundException;
import com.asessment.demo.model.Stocks;
import com.asessment.demo.repository.StocksRepository;
import com.asessment.demo.stockresponse.ProductDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StocksService {

    private final StocksRepository stocksRepository;

    private final WebClient webClient;
    @Transactional
    public Stocks insertNewStock(StockDTO stockDTO) {
        String isStockPresent = stocksRepository.findStockName(stockDTO.getStockName());
        Optional<String> checkNull = Optional.ofNullable(isStockPresent);
        if(!checkNull.isPresent() || !isStockPresent.equals(stockDTO.getStockName())) {
            try {
                ProductDetail[] productDetails = webClient.get()
                        .uri("http://localhost:8080/api/product/getProductByName/" + stockDTO.getStockName())
                        .retrieve()
                        .bodyToMono(ProductDetail[].class)
                        .block();
                Stocks stocks = new Stocks(stockDTO.getStockName(), stockDTO.getCategory());
                stocks.setStocksAvailable(1L);
                stocks.setStocksSold(0l);
                return stocksRepository.save(stocks);
            } catch (Exception e) {
                throw new StockNotFoundException("Please add the Product in Inventory first");
            }
        }
        else {
            throw new StockNotFoundException("Stock "+isStockPresent+" is already present please add the available count");
        }
    }
    public List<Stocks> getAllStocks() {
        List<Stocks> stockDetails=stocksRepository.findAll();
        if (stockDetails.size()==0)
        {
            throw new StockNotFoundException("Products are not available");
        }
        return stockDetails;
    }
    @Transactional
    public Stocks sellStocks(SellDTO sellDTO) {
        String isStockPresent = stocksRepository.findStockName(sellDTO.getStocksName());
        Long id=stocksRepository.findId(sellDTO.getStocksName());
        Long isAvailable = stocksRepository.findStocksAvailable(sellDTO.getStocksName());
        String findCategory = stocksRepository.findCategory(sellDTO.getStocksName());
        Optional<String> checkNull = Optional.ofNullable(isStockPresent);
        if(!checkNull.isPresent())
        {
            throw new StockNotFoundException("Currently the stock "+sellDTO.getStocksName()+" is unavailable to sell");
        }else if (isStockPresent.isEmpty() || isAvailable == 0) {
            throw new StockNotFoundException("Currently the stock "+sellDTO.getStocksName()+" is unavailable to sell");
         } else if (isAvailable < sellDTO.getUnitsToSold()) {
            throw new StockNotFoundException("Please sell the units below "+isAvailable+" range");
         } else if (!findCategory.equals(sellDTO.getCategoryName())){
             throw new StockNotFoundException("Stock "+isStockPresent+ " is not present in the category "+sellDTO.getCategoryName());
         } else {

            Stocks stocks = new Stocks(id,sellDTO.getStocksName(),findCategory,
                                        sellDTO.getUnitsToSold(),isAvailable - sellDTO.getUnitsToSold());
          return stocksRepository.save(stocks);
        }
    }
    @Transactional
    public Stocks addStocks(AddStocksDTO addStocksDTO) {
        String isStockPresent = stocksRepository.findStockName(addStocksDTO.getStockName());
        Long id=stocksRepository.findId(addStocksDTO.getStockName());
        Long isAvailable = stocksRepository.findStocksAvailable(addStocksDTO.getStockName());
        String category = stocksRepository.findCategory(addStocksDTO.getStockName());
        Long soldUnits=stocksRepository.findSoldUnits(addStocksDTO.getStockName());
        Optional<String> checkNull = Optional.ofNullable(isStockPresent);
        if(!checkNull.isPresent())
        {
            throw new StockNotFoundException("Currently the stock "+addStocksDTO.getStockName()+" is unavailable to add");
        }
        if (isStockPresent.isEmpty()) {
            throw new StockNotFoundException("Please add the item to the Stock before increasing the units");
        }
        else {
                Stocks stocks = new Stocks(id,addStocksDTO.getStockName(),
                        category,soldUnits,isAvailable + addStocksDTO.getAddUnits());
                return stocksRepository.save(stocks);
            }
    }

    /**
     * This function returns a values from the another microservices.
     * Inventory service is running on the port 8080 and the current service is running on port 8081
     *
     * @param stockName - We will get the stockName by the service running on the port 8081
     * @return It will the values from Inventory services schema.
     */
    public ProductDetail[] getStocksAndInventory(String stockName){
        String isStockPresent=stocksRepository.findStockName(stockName);
        Optional<String> checkNull = Optional.ofNullable(isStockPresent);
        if(!checkNull.isPresent())
        {
            throw new StockNotFoundException("Currently the stock "+stockName+" is not added in stock. Please check in Inventory, if it is present then add it in stock");
        }
        else if(!isStockPresent.isEmpty()) {
         ProductDetail[] productDetails =  webClient.get()
                    .uri("http://localhost:8080/api/product/getProductByName/" + stockName)
                    .retrieve()
                    .bodyToMono(ProductDetail[].class)
                    .block();
            return productDetails;
        }
       else {
           throw new StockNotFoundException("Stock is not Present");
        }
    }
}
