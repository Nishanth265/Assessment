package com.asessment.demo.controller;

import com.asessment.demo.dto.AddStocksDTO;
import com.asessment.demo.dto.SellDTO;
import com.asessment.demo.dto.StockDTO;
import com.asessment.demo.service.StocksService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@AllArgsConstructor
@RequestMapping("/api/stocks")
public class StockController {

    private final StocksService stocksService;

    @PostMapping("/newStock")
    public ResponseEntity<?> newStock(@RequestBody StockDTO stockDTO){
            stocksService.insertNewStock(stockDTO);
            return new ResponseEntity<>("Stock posted successfully", HttpStatus.CREATED);
        }

    @GetMapping("/getAllStocks")
    public ResponseEntity<?> getAllStocks(){
        return ResponseEntity.status(HttpStatus.OK).body(stocksService.getAllStocks());
  }

  @PutMapping("/sellStocks")
  public ResponseEntity<?> sellStocks(@RequestBody SellDTO sellDTO){
      return ResponseEntity.status(HttpStatus.OK).body(stocksService.sellStocks(sellDTO));
  }

  @PutMapping("/addStocks")
  public ResponseEntity<?> addStocks(@RequestBody AddStocksDTO addStocksDTO ){
      return ResponseEntity.status(HttpStatus.OK).body(stocksService.addStocks(addStocksDTO));
  }


    @GetMapping("/getStocksAndInventory/{stockName}")
    public ResponseEntity<?> getStocksAndInventory(@PathVariable String stockName){
        return ResponseEntity.status(HttpStatus.OK).body(stocksService.getStocksAndInventory(stockName));
    }

    }




