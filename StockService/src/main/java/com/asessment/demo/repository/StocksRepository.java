package com.asessment.demo.repository;

import com.asessment.demo.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StocksRepository extends JpaRepository<Stocks,Long> {
    @Query(value = "select stocks_available  from stocks where stock_name=?1",nativeQuery = true)
    Long findStocksAvailable(String stocksName);

    @Query(value = "select stock_name  from stocks where stock_name=?1",nativeQuery = true)
   String findStockName(String stocksName);


    @Query(value = "select id from stocks where stock_name=?1",nativeQuery = true)
    Long findId(String name);

    @Query(value = "select stocks_sold  from stocks where stock_name=?1",nativeQuery = true)
    Long findSoldUnits(String stockName);

    @Query(value = "select category  from stocks where stock_name=?1",nativeQuery = true)
    String findCategory(String stockName);

    @Query(value = "select category  from stocks where stock_name=?1",nativeQuery = true)
    Long findStockCategory(String categoryName);

}
